package io.thoughtware.postin.api.http.document.service;


import io.thoughtware.postin.api.http.definition.model.HttpApi;
import io.thoughtware.postin.category.model.Category;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.postin.api.http.document.model.Share;
import io.thoughtware.postin.api.http.document.model.ShareQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
* 分享 服务模型
*/
@JoinProvider(model = Share.class)
public interface ShareService {

    /**
    * 创建
    * @param share
    * @return
    */
    String createShare(@NotNull @Valid Share share);

    /**
    * 更新
    * @param share
    */
    void updateShare(@NotNull @Valid Share share);

    /**
    * 删除
    * @param id
    */
    void deleteShare(@NotNull String id);
    @FindOne
    Share findOne(@NotNull String id);
    @FindList
    List<Share> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    Share findShare(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<Share> findAllShare();

    /**
    * 查询列表
    * @param shareQuery
    * @return
    */
    List<Share> findShareList(ShareQuery shareQuery);

    /**
    * 按分页查询
    * @param shareQuery
    * @return
    */
    Pagination<Share> findSharePage(ShareQuery shareQuery);

    /**
     * 密码校验
     * @param id
     * @return
     */
    Share findShareByUrlId(String id);

    /**
     * 通过shareId，查询里面targetId 构造左侧树
     * @return
     */
    List<Category> findShareTree(String id);

    /**
     * 通过shareId，查询里面targetId 构造左侧树
     * @return
     */
    HashMap verify(Share share);

    /**
     * id
     * @param id
     * @return
     */
    HttpApi findHttpApi(String id);

}