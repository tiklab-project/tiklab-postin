package net.tiklab.postin.integration.share.service;


import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.postin.apidef.http.model.HttpApi;
import net.tiklab.postin.category.model.Category;
import net.tiklab.postin.integration.share.model.Share;
import net.tiklab.postin.integration.share.model.ShareQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
* ShareService
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