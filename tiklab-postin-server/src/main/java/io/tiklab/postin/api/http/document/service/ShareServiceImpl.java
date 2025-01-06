package io.tiklab.postin.api.http.document.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.apix.entity.QueryParamEntity;
import io.tiklab.postin.api.ws.ws.model.WSApi;
import io.tiklab.postin.api.ws.ws.service.WSApiService;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.node.model.NodeQuery;
import io.tiklab.postin.node.service.NodeService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.postin.api.http.document.dao.ShareDao;
import io.tiklab.postin.api.http.document.entity.ShareEntity;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.model.CategoryQuery;
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
import java.util.stream.Collectors;

/**
* 分享 服务
*/
@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    ShareDao shareDao;

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

    @Override
    public String createShare(@NotNull @Valid Share share) {
        ShareEntity shareEntity = BeanMapper.map(share, ShareEntity.class);
        shareEntity.setId(share.getCode());
        shareEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));


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

        return share;
    }

    @Override
    public List<Share> findAllShare() {
        List<ShareEntity> shareEntityList =  shareDao.findAllShare();

        List<Share> shareList =  BeanMapper.mapList(shareEntityList,Share.class);

        joinTemplate.joinQuery(shareList);

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

        joinTemplate.joinQuery(shareList);

        return PaginationBuilder.build(pagination,shareList);
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
        String targetId = share.getTargetId();

        switch (share.getTargetType()){
            case "workspace":
                return findNodeByWorkspace(targetId);
            case "category":
                return findNodeByCategory(targetId);
            case "api":
                Node nodeByApi = findNodeByApi(targetId);
                ArrayList<Node> arrayList = new ArrayList<>();
                arrayList.add(nodeByApi);
                return arrayList;
            default:
                break;
        }

        return null;
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