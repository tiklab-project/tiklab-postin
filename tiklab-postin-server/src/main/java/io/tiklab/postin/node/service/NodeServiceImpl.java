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
        //  查询所有节点
        List<Node> nodeList = findNodeList(nodeQuery);
        if (nodeList == null || nodeList.isEmpty()) {
            return Collections.emptyList();
        }

        // 构建快速查找 & 组织关系容器
        Map<String, Node>   nodeMap  = new HashMap<>();
        Map<String, List<Node>>  childrenMap = new HashMap<>();
        Set<String>  processed = new HashSet<>();
        List<Node>   roots   = new ArrayList<>();

        // 全部放入 map
        for (Node node : nodeList) {
            nodeMap.put(node.getId(), node);
        }
        // 递归处理每个节点，补齐父节点并收集根列表
        for (Node node : nodeList) {
            processNode(node, nodeMap, childrenMap, processed, roots);
        }

        // 构建树形结构，确保每个 node.children 至少是空列表
        for (Node root : roots) {
            buildTree(root, childrenMap);
        }
        return roots;
    }

    private void processNode(Node node,
                             Map<String, Node> nodeMap,
                             Map<String, List<Node>> childrenMap,
                             Set<String> processed,
                             List<Node> roots) {
        if (!processed.add(node.getId())) {
            return;  // 已处理过，直接返回
        }

        String parentId = node.getParentId();
        if (parentId == null) {
            // 无父 ID，直接作为根节点
            roots.add(node);
        } else {
            Node parent = nodeMap.get(parentId);
            if (parent == null) {
                // 父节点不在缓存中，尝试查询
                parent = findOne(parentId);
                if (parent != null) {
                    nodeMap.put(parent.getId(), parent);
                    processNode(parent, nodeMap, childrenMap, processed, roots);
                } else {
                    // 父节点真不存在，也当作根节点
                    roots.add(node);
                }
            }
            if (parent != null) {
                // 建立 parent → child 关联
                childrenMap
                        .computeIfAbsent(parentId, k -> new ArrayList<>())
                        .add(node);
            }
        }
    }

    private void buildTree(Node node, Map<String, List<Node>> childrenMap) {
        // 即使没有子节点，也要设置成空列表，保证前端展示
        List<Node> children = childrenMap.getOrDefault(node.getId(), Collections.emptyList());
        node.setChildren(children);

        // 递归构造下级
        for (Node child : children) {
            buildTree(child, childrenMap);
        }
    }


}