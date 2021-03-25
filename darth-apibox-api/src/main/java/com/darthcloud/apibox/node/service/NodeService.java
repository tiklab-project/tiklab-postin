package com.darthcloud.apibox.node.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.node.model.Node;
import com.darthcloud.apibox.node.model.NodeQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface NodeService {

    /**
    * 创建用户
    * @param node
    * @return
    */
    String createNode(@NotNull @Valid Node node);

    /**
    * 更新用户
    * @param node
    */
    void updateNode(@NotNull @Valid Node node);

    /**
    * 删除用户
    * @param id
    */
    void deleteNode(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    Node findNode(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<Node> findAllNode();

    /**
    * 查询列表
    * @param nodeQuery
    * @return
    */
    List<Node> findNodeList(NodeQuery nodeQuery);

    /**
    * 按分页查询
    * @param nodeQuery
    * @return
    */
    Pagination<Node> findNodePage(NodeQuery nodeQuery);

    /**
     * 根据空间ID查找节点树
     * @param workspaceId
     * @return
     */
    List<Node> findNodeTree(String workspaceId);

}