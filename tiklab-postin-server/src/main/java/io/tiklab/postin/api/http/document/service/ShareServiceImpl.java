package io.tiklab.postin.api.http.document.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.document.model.ShareNode;
import io.tiklab.postin.api.http.document.model.ShareNodeQuery;
import io.tiklab.postin.api.ws.ws.model.WSApi;
import io.tiklab.postin.api.ws.ws.service.WSApiService;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.node.model.NodeQuery;
import io.tiklab.postin.node.service.NodeService;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.service.WorkspaceService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.postin.api.http.document.dao.ShareDao;
import io.tiklab.postin.api.http.document.entity.ShareEntity;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.api.http.document.model.Share;
import io.tiklab.postin.api.http.document.model.ShareQuery;


import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;

/**
* 分享 服务
*/
@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    ShareDao shareDao;

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ApixService apixService;

    @Autowired
    HttpApiService httpApiService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    NodeService nodeService;

    @Autowired
    WSApiService wsApiService;

    @Autowired
    ShareNodeService shareNodeService;

    @Override
    public String createShare(@NotNull @Valid Share share) {
        ShareEntity shareEntity = BeanMapper.map(share, ShareEntity.class);
        shareEntity.setId(share.getCode());
        shareEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        if(!share.getTargetType().equals("workspace")){
            List<String> nodeIds = share.getNodeIds();
            if(CollectionUtils.isNotEmpty(nodeIds)){
                for (String nodeId : nodeIds){
                    ShareNode shareNode = new ShareNode();
                    shareNode.setNodeId(nodeId);
                    shareNode.setShareId(shareEntity.getId());
                    shareNodeService.createShareNode(shareNode);
                }
            }
        }

        return shareDao.createShare(shareEntity);
    }

    @Override
    public void updateShare(@NotNull @Valid Share share) {
        ShareEntity shareEntity = BeanMapper.map(share, ShareEntity.class);
        shareEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        shareDao.updateShare(shareEntity);
    }

    @Override
    public void deleteShare(@NotNull String id) {
        shareDao.deleteShare(id);
        // 删除关联的nodeIds
        shareNodeService.deleteShareNodeByApiId(id);
    }

    @Override
    public void deleteShareByApiId(String apiId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(ShareEntity.class)
                .eq("targetId", apiId)
                .get();
        shareDao.deleteShare(deleteCondition);
    }

    @Override
    public Share findOne(String id) {
        ShareEntity shareEntity = shareDao.findShare(id);

        Share share = BeanMapper.map(shareEntity, Share.class);
        return share;
    }

    @Override
    public List<Share> findList(List<String> idList) {
        List<ShareEntity> shareEntityList =  shareDao.findShareList(idList);

        List<Share> shareList =  BeanMapper.mapList(shareEntityList,Share.class);
        return shareList;
    }

    @Override
    public Share findShare(@NotNull String id) {
        Share share = findOne(id);

        joinTemplate.joinQuery(share);
        List<String> nodeIds = getNodeIds(id);
        if(CollectionUtils.isNotEmpty(nodeIds)){
            share.setNodeIds(nodeIds);
        }

        return share;
    }

    @Override
    public List<Share> findAllShare() {
        List<ShareEntity> shareEntityList =  shareDao.findAllShare();

        List<Share> shareList =  BeanMapper.mapList(shareEntityList,Share.class);


        return shareList;
    }

    @Override
    public List<Share> findShareList(ShareQuery shareQuery) {
        List<ShareEntity> shareEntityList = shareDao.findShareList(shareQuery);

        List<Share> shareList = BeanMapper.mapList(shareEntityList,Share.class);

        joinTemplate.joinQuery(shareList);

        return shareList;
    }

    @Override
    public Pagination<Share> findSharePage(ShareQuery shareQuery) {
        Pagination<ShareEntity>  pagination = shareDao.findSharePage(shareQuery);

        List<Share> shareList = BeanMapper.mapList(pagination.getDataList(),Share.class);

        return PaginationBuilder.build(pagination,shareList);
    }

    private List<String> getNodeIds(String shareId){
        List<String> nodeIds = new ArrayList<>();
        ShareNodeQuery shareNodeQuery = new ShareNodeQuery();
        shareNodeQuery.setShareId(shareId);
        List<ShareNode> shareNodeList = shareNodeService.findShareNodeList(shareNodeQuery);
        if(CollectionUtils.isNotEmpty(shareNodeList)){
            for (ShareNode shareNode : shareNodeList){
                nodeIds.add(shareNode.getNodeId());
            }
        }
        return nodeIds;
    }

    @Override
    public Share findShareByUrlId(String id) {
        Share shareSql = findShare(id);

        Share share = new Share();
        share.setVisibility(shareSql.getVisibility());

        return share;
    }

    @Override
    public HashMap verify(Share share) {
        Share share1 = findShare(share.getId());

        HashMap<String, String> hashMap = new HashMap<>();

        //如果密码相同返回状态
        if(Objects.equals(share.getPassword(),share1.getPassword())){
            hashMap.put("status","success");
        }else {
            hashMap.put("status","error");
        }

        return hashMap;
    }



    @Override
    public List<Node> findShareTree(String id) {
        //通过分享id 查询 targetId
        Share share = findShare(id);

        switch (share.getTargetType()){
            case "workspace":
                return findNodeByWorkspace(share.getTargetId());
            case "category":
                return findNodeByCategory(share.getTargetId());
            case "api":
                Node nodeByApi = findNodeByApi(share.getTargetId());
                ArrayList<Node> arrayList = new ArrayList<>();
                arrayList.add(nodeByApi);
                return arrayList;
            case "custom":
                List<String> nodeIds = share.getNodeIds();
                return findNodeByCustom(nodeIds);
            default:
                break;
        }

        return null;
    }


    /**
     * 根据一组 nodeIds 构建节点树列表，
     * 内部复用 findNodeByApi 构造每棵树，并对相同根合并子节点。
     */
    private List<Node> findNodeByCustom(List<String> nodeIds) {
        // 用于按根节点 ID 去重，并合并子节点
        Map<String, Node> rootMap = new LinkedHashMap<>();

        for (String id : nodeIds) {
            // 拿到以该 id 为叶子的那棵树
            Node treeRoot = findNodeByApi(id);
            if (treeRoot == null) {
                continue;
            }

            String rootId = treeRoot.getId();
            // 如果已经有相同根，则把树的直接子节点合并进去
            if (rootMap.containsKey(rootId)) {
                Node existing = rootMap.get(rootId);
                if (treeRoot.getChildren() != null) {
                    if (existing.getChildren() == null) {
                        existing.setChildren(new ArrayList<>());
                    }
                    existing.getChildren().addAll(treeRoot.getChildren());
                }
            } else {
                // 第一次见到这个根，直接放进去
                rootMap.put(rootId, treeRoot);
            }
        }

        return new ArrayList<>(rootMap.values());
    }



    /**
     * 通过Workspace 查询的树形列表
     * @param workspaceId
     * @return
     */
    private List<Node> findNodeByWorkspace(String workspaceId){

        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setWorkspaceId(workspaceId);
        List<Node> nodeList = nodeService.findNodeTree(nodeQuery);

        return nodeList;
    }

    /**
     * 通过category 查询的树形列表
     * @return
     */
    private List<Node> findNodeByCategory(String categoryId){
        Node node = nodeService.findNode(categoryId);

        ArrayList<Node> arrayList = new ArrayList<>();
        arrayList.add(node);

        return arrayList;
    }

    /**
     * 通过api 查询的树形列表
     * @param targetId
     * @return
     */
    private Node findNodeByApi(String targetId) {
        Node node = nodeService.findNode(targetId);
        if (node == null) {
            return null;
        }
        if (node.getParentId() == null) {
            return node;
        }

        // 递归查找父节点
        Node parentNode = findNodeByApi(node.getParentId());
        if (parentNode == null) {
            return node;
        }
        if (parentNode.getChildren() == null) {
            parentNode.setChildren(new ArrayList<>());
        }
        parentNode.setChildren(new ArrayList<>());
        parentNode.getChildren().add(node);
        return parentNode;
    }

    /**
     * 接口 根据类型匹配
     * @param id
     * @return
     */
    @Override
    public HttpApi findHttpApi(String id) {
        HttpApi httpApi = httpApiService.findHttpApi(id);

        return  httpApi;
    }


    @Override
    public WSApi findWSApi(String id) {
        WSApi wsApi = wsApiService.findWSApi(id);

        return  wsApi;
    }


}