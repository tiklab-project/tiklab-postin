package io.thoughtware.postin.node.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.node.model.Node;
import io.thoughtware.postin.node.model.NodeQuery;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 节点服务接口
*/
@JoinProvider(model = Node.class)
public interface NodeService {

    /**
    * 创建节点
    * @param node
    * @return
    */
    String createNode(@NotNull @Valid Node node);

    /**
    * 更新节点
    * @param node
    */
    void updateNode(@NotNull @Valid Node node);

    /**
    * 删除节点
    * @param id
    */
    void deleteNode(@NotNull String id);

    @FindOne
    Node findOne(@NotNull String id);

    @FindList
    List<Node> findList(List<String> idList);

    /**
    * 查找节点
    * @param id
    * @return
    */
    Node findNode(@NotNull String id);

    /**
    * 查找所有节点
    * @return
    */
    @FindAll
    List<Node> findAllNode();

    /**
     * 查询分组总数
     * @param workspaceId
     * @return
     */
    int findNodeNum(String workspaceId);


    /**
    * 查询列表节点
    * @param nodeQuery
    * @return
    */
    List<Node> findNodeList(NodeQuery nodeQuery);

    /**
    * 按分页查询节点
    * @param nodeQuery
    * @return
    */
    Pagination<Node> findNodePage(NodeQuery nodeQuery);

    /**
     * 构建树
     * @param nodeQuery
     * @return
     */
    List<Node> findNodeTree(NodeQuery nodeQuery);
}