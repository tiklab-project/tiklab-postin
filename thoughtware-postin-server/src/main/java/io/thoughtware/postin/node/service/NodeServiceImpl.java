package io.thoughtware.postin.node.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.postin.api.apix.service.ApixService;
import io.thoughtware.postin.api.http.definition.service.HttpApiService;
import io.thoughtware.postin.api.ws.ws.service.WSApiService;
import io.thoughtware.postin.category.service.CategoryService;
import io.thoughtware.postin.common.MagicValue;
import io.thoughtware.postin.common.PostInUnit;
import io.thoughtware.postin.node.dao.NodeDao;
import io.thoughtware.postin.node.entity.NodeEntity;
import io.thoughtware.postin.node.model.Node;
import io.thoughtware.postin.node.model.NodeQuery;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;

/**
* 分类 服务
*/
@Exporter
@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    NodeDao nodeDao;

    @Autowired
    ApixService apixService;

    @Autowired
    HttpApiService httpApiService;

    @Autowired
    WSApiService wsApiService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    PostInUnit postInUnit;


    @Override
    public String createNode(@NotNull @Valid Node node) {
        NodeEntity nodeEntity = BeanMapper.map(node, NodeEntity.class);
        if (StringUtils.isEmpty(node.getId())) {
            String id = postInUnit.generateId();
            nodeEntity.setId(id);
        }

        String userId = LoginContext.getLoginId();
        nodeEntity.setCreateUser(userId);
        nodeEntity.setUpdateUser(userId);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        nodeEntity.setCreateTime(timestamp);
        nodeEntity.setUpdateTime(timestamp);
        String nodeId = nodeDao.createNode(nodeEntity);

        return nodeId;
    }

    @Override
    public void updateNode(@NotNull @Valid Node node) {
        NodeEntity nodeEntity = BeanMapper.map(node, NodeEntity.class);

        nodeDao.updateNode(nodeEntity);
    }

    @Override
    public void deleteNode(@NotNull String id) {
        Node node = findNode(id);

        if (node == null) {
            return;
        }

        // 递归删除子节点
        if (node.getChildren() != null && !node.getChildren().isEmpty()) {
            for (Node child : node.getChildren()) {
                deleteNode(child.getId());
            }
        }


        switch (node.getType()){
            case MagicValue.CATEGORY:
                categoryService.deleteCategory(id);
                break;
            case MagicValue.PROTOCOL_TYPE_HTTP:
                apixService.deleteApix(id);
                httpApiService.deleteHttpApi(id);
                break;
            case MagicValue.PROTOCOL_TYPE_WS:
                apixService.deleteApix(id);
                wsApiService.deleteWSApi(id);
                break;
        }

        nodeDao.deleteNode(id);
    }

    @Override
    public Node findOne(String id) {
        NodeEntity nodeEntity = nodeDao.findNode(id);

        return BeanMapper.map(nodeEntity, Node.class);
    }

    @Override
    public List<Node> findList(List<String> idList) {
        List<NodeEntity> nodeEntityList =  nodeDao.findNodeList(idList);

        return BeanMapper.mapList(nodeEntityList,Node.class);
    }


    @Override
    public Node findNode(@NotNull String id) {
        Node node = findOne(id);
        joinTemplate.joinQuery(node);

        //递归查找子节点
        List<Node> nodeList = findChildren(id);
        if(!nodeList.isEmpty()){
            node.setChildren(nodeList);

            for(Node child:nodeList){
                if(Objects.equals(child.getType(), MagicValue.CATEGORY)){
                    findNode(child.getId());
                }
            }
        }

        return node;
    }

    private List<Node> findChildren(String parentId) {
        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setParentId(parentId);
        return findNodeList(nodeQuery);
    }


    @Override
    public List<Node> findAllNode() {
        List<NodeEntity> nodeEntityList =  nodeDao.findAllNode();

        List<Node> nodeList = BeanMapper.mapList(nodeEntityList,Node.class);

        joinTemplate.joinQuery(nodeList);

        return nodeList;
    }

    @Override
    public int findNodeNum(String workspaceId) {
        int nodeNum = nodeDao.findNodeNum(workspaceId);
        return nodeNum;
    }

    @Override
    public List<Node> findNodeList(NodeQuery nodeQuery) {

        List<NodeEntity> nodeEntityList = nodeDao.findNodeList(nodeQuery);

        List<Node> nodeList = BeanMapper.mapList(nodeEntityList,Node.class);

        joinTemplate.joinQuery(nodeList);

        return nodeList;
    }

    @Override
    public Pagination<Node> findNodePage(NodeQuery nodeQuery) {

        Pagination<NodeEntity>  pagination = nodeDao.findNodePage(nodeQuery);

        List<Node> nodeList = BeanMapper.mapList(pagination.getDataList(),Node.class);

        joinTemplate.joinQuery(nodeList);

        return PaginationBuilder.build(pagination,nodeList);
    }

    @Override
   public List<Node> findNodeTree(NodeQuery nodeQuery){
       List<Node> nodeList = findNodeList(nodeQuery);

       Map<String, Node> nodeMap = new HashMap<>();
       List<Node> roots = new ArrayList<>();

       // 构建节点Map，并找到根节点
       for (Node node : nodeList) {
           nodeMap.put(node.getId(), node);
           if (node.getParentId() == null) {
               roots.add(node);
           }
       }

       // 构建树
       buildTree(roots, nodeMap);

       return roots;
   }

    private void buildTree(List<Node> nodes, Map<String, Node> map) {
        for (Node node : nodes) {
            List<Node> children = new ArrayList<>();
            for (Node child : map.values()) {
                if (Objects.equals(child.getParentId(), node.getId())) {
                    children.add(child);
                }
            }
            node.setChildren(children);
            buildTree(children, map);
        }
    }



}