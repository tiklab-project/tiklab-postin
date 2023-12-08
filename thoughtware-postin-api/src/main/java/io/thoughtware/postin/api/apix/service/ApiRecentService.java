package io.thoughtware.postin.api.apix.service;


import io.thoughtware.core.page.Pagination;
import io.thoughtware.join.annotation.FindAll;
import io.thoughtware.join.annotation.FindList;
import io.thoughtware.join.annotation.FindOne;
import io.thoughtware.join.annotation.JoinProvider;
import io.thoughtware.postin.api.apix.model.ApiRecent;
import io.thoughtware.postin.api.apix.model.ApiRecentQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 最近访问接口 服务接口
*/
@JoinProvider(model = ApiRecent.class)
public interface ApiRecentService {

    /**
    * 创建最近访问接口
    * @param ApiRecent
    * @return
    */
    String createApiRecent(@NotNull @Valid ApiRecent ApiRecent);

    /**
    * 更新最近访问接口
    * @param ApiRecent
    */
    void updateApiRecent(@NotNull @Valid ApiRecent ApiRecent);

    /**
    * 删除最近访问接口
    * @param id
    */
    void deleteApiRecent(@NotNull String id);

    @FindOne
    ApiRecent findOne(@NotNull String id);

    @FindList
    List<ApiRecent> findList(List<String> idList);

    /**
    * 查找最近访问接口
    * @param id
    * @return
    */
    ApiRecent findApiRecent(@NotNull String id);

    /**
    * 查找所有最近访问接口
    * @return
    */
    @FindAll
    List<ApiRecent> findAllApiRecent();

    /**
    * 查询最近访问的列表，返回的是接口list
    * @param ApiRecentQuery
    * @return
    */
    List<ApiRecent> findApiRecentList(ApiRecentQuery ApiRecentQuery);


    /**
    * 按分页查询最近访问接口
    * @param ApiRecentQuery
    * @return
    */
    Pagination<ApiRecent> findApiRecentPage(ApiRecentQuery ApiRecentQuery);

    /**
     * 设置最近浏览的接口
     * @param ApiRecent
     */
    void apiRecent(@NotNull @Valid ApiRecent ApiRecent);

}