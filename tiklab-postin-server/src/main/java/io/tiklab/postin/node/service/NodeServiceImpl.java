package io.tiklab.postin.node.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.service.ApiRecentService;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.postin.api.ws.ws.service.WSApiService;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.common.PostInUnit;
import io.tiklab.postin.node.dao.NodeDao;
import io.tiklab.postin.node.entity.NodeEntity;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.node.model.NodeQuery;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
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

    @Autowired
    ApiRecentService apiRecentService;


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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        nodeEntity.setUpdateTime(timestamp);
        nodeEntity.setUpdateUser(LoginContext.getLoginId());

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
                apiRecentService.deleteApiRecentByApiId(id);
                break;
            case MagicValue.PROTOCOL_TYPE_WS:
                apixService.deleteApix(id);
                wsApiService.deleteWSApi(id);
                apiRecentService.deleteApiRecentByApiId(id);
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
    public List<Node> findNodeTree(NodeQuery nodeQuery) {

        // 获取构建树所需的所有节点
        List<Node> allNodes = findNodeList(nodeQuery);
        if (allNodes == null || allNodes.isEmpty()) {
            return Collections.emptyList();
        }

        // 构建ID到节点的快速查找Map，并初始化每个节点的子节点列表
        Map<String, Node> nodeMap = new HashMap<>(allNodes.size());
        for (Node node : allNodes) {
            node.setChildren(new ArrayList<>());
            nodeMap.put(node.getId(), node);
        }

        // 构建树形结构
        List<Node> roots = new ArrayList<>();
        for (Node node : allNodes) {
            String parentId = node.getParentId();
            // 尝试获取父节点
            Node parentNode = (parentId != null) ? nodeMap.get(parentId) : null;

            if (parentNode != null) {
                // 如果找到父节点，则将当前节点加入父节点的子列表中
                parentNode.getChildren().add(node);
            } else {
                // 如果没有父ID或父节点不在当前列表中，则视为根节点
                roots.add(node);
            }
        }

        // 对根节点列表排序
        sortNodeList(roots);
        for (Node root : roots) {
            // 递归排序所有子节点
            sortChildrenRecursively(root);
        }

        return roots;
    }

    /**
     * 递归地对指定节点的所有子孙节点进行排序。
     *
     * @param node 当前节点
     */
    private void sortChildrenRecursively(Node node) {
        if (node == null || node.getChildren() == null || node.getChildren().isEmpty()) {
            return;
        }

        // 对当前节点的直接子节点进行排序
        sortNodeList(node.getChildren());

        // 递归处理所有子节点
        for (Node child : node.getChildren()) {
            sortChildrenRecursively(child);
        }
    }

    /**
     * 对节点列表进行排序，目录（category）优先。
     *
     * @param nodes 待排序的节点列表
     */
    private void sortNodeList(List<Node> nodes) {
        nodes.sort(Comparator.comparingInt(node -> "category".equals(node.getType()) ? 0 : 1));
    }

}