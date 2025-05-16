package io.tiklab.postin.api.http.document.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.http.document.model.ShareNode;
import io.tiklab.postin.api.http.document.model.ShareNodeQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 分享绑定的节点 服务模型
*/
@JoinProvider(model = ShareNode.class)
public interface ShareNodeService {

    /**
    * 创建
    * @param shareNode
    * @return
    */
    String createShareNode(@NotNull @Valid ShareNode shareNode);

    /**
    * 更新
    * @param shareNode
    */
    void updateShareNode(@NotNull @Valid ShareNode shareNode);

    /**
    * 删除
    * @param id
    */
    void deleteShareNode(@NotNull String id);

    /**
     * 通过shareId删除分享
     * @param shareId
     */
    void deleteShareNodeByApiId(String shareId);

    @FindOne
    ShareNode findOne(@NotNull String id);
    @FindList
    List<ShareNode> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ShareNode findShareNode(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<ShareNode> findAllShareNode();

    /**
    * 查询列表
    * @param shareNodeQuery
    * @return
    */
    List<ShareNode> findShareNodeList(ShareNodeQuery shareNodeQuery);

    /**
    * 按分页查询
    * @param shareNodeQuery
    * @return
    */
    Pagination<ShareNode> findShareNodePage(ShareNodeQuery shareNodeQuery);


}