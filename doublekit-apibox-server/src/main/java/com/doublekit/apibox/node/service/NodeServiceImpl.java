package com.doublekit.apibox.node.service;

import com.doublekit.apibox.node.dao.NodeDao;
import com.doublekit.apibox.node.entity.NodeEntity;
import com.doublekit.apibox.node.model.Node;
import com.doublekit.apibox.node.model.NodeQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* 用户服务业务处理
*/
@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    NodeDao nodeDao;

    @Override
    public String createNode(@NotNull @Valid Node node) {
        NodeEntity nodeEntity = BeanMapper.map(node, NodeEntity.class);

        return nodeDao.createNode(nodeEntity);
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
    public Node findNode(@NotNull String id) {
        NodeEntity nodeEntity = nodeDao.findNode(id);

        return BeanMapper.map(nodeEntity, Node.class);
    }

    @Override
    public List<Node> findAllNode() {
        List<NodeEntity> nodeEntityList =  nodeDao.findAllNode();

        return BeanMapper.mapList(nodeEntityList,Node.class);
    }

    @Override
    public List<Node> findNodeList(NodeQuery nodeQuery) {
        List<NodeEntity> nodeEntityList = nodeDao.findNodeList(nodeQuery);

        return BeanMapper.mapList(nodeEntityList,Node.class);
    }

    @Override
    public Pagination<Node> findNodePage(NodeQuery nodeQuery) {
        Pagination<Node> pg = new Pagination<>();

        Pagination<NodeEntity>  pagination = nodeDao.findNodePage(nodeQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Node> nodeList = BeanMapper.mapList(pagination.getDataList(),Node.class);
        pg.setDataList(nodeList);
        return pg;
    }

    @Override
    public List<Node> findNodeTree(String workspaceId) {
        List<Node> nodeList = findTopNodeList(workspaceId);

        for(Node node:nodeList){
            buildNodeTree(node);
        }
        return null;
    }

    /**
     * 查找一级节点列表
     * @param workspaceId
     * @return
     */
    List<Node> findTopNodeList(String workspaceId) {
        return null;
    }

    /**
     * 递归构造节点树
     * @param node
     */
    void buildNodeTree(Node node){
        //
    }


}