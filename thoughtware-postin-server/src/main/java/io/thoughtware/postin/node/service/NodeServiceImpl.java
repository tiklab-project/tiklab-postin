package io.thoughtware.postin.node.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.postin.api.apix.service.ApixService;
import io.thoughtware.postin.node.dao.NodeDao;
import io.thoughtware.postin.node.entity.NodeEntity;
import io.thoughtware.postin.node.model.Node;
import io.thoughtware.postin.node.model.NodeQuery;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.security.logging.service.LoggingTypeService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    LoggingTypeService loggingTypeService;

    @Autowired
    JoinTemplate joinTemplate;


    @Override
    public String createNode(@NotNull @Valid Node node) {
        NodeEntity nodeEntity = BeanMapper.map(node, NodeEntity.class);
        if (StringUtils.isEmpty(node.getId())) {
            String uid = UUID.randomUUID().toString();
            String id = uid.trim().replaceAll("-", "").substring(0, 12);;
            nodeEntity.setId(id);
        }
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

        return node;
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


   



}