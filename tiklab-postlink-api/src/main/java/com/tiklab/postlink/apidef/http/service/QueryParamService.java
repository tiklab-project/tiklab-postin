package com.tiklab.postlink.apidef.http.service;

import com.tiklab.core.page.Pagination;

import com.tiklab.postlink.apidef.http.model.QueryParam;
import com.tiklab.postlink.apidef.http.model.QueryParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface QueryParamService {

    /**
    * 创建用户
    * @param queryParam
    * @return
    */
    String createQueryParam(@NotNull @Valid QueryParam queryParam);

    /**
    * 更新用户
    * @param queryParam
    */
    void updateQueryParam(@NotNull @Valid QueryParam queryParam);

    /**
    * 删除用户
    * @param id
    */
    void deleteQueryParam(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    QueryParam findQueryParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<QueryParam> findAllQueryParam();

    /**
    * 查询列表
    * @param queryParamQuery
    * @return
    */
    List<QueryParam> findQueryParamList(QueryParamQuery queryParamQuery);

    /**
    * 按分页查询
    * @param queryParamQuery
    * @return
    */
    Pagination<QueryParam> findQueryParamPage(QueryParamQuery queryParamQuery);

}