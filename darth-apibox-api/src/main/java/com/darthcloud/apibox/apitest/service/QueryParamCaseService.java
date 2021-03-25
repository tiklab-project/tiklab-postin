package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.model.QueryParamCase;
import com.darthcloud.apibox.apitest.model.QueryParamCaseQuery;
import com.darthcloud.common.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface QueryParamCaseService {

    /**
    * 创建用户
    * @param queryParamCase
    * @return
    */
    String createQueryParamCase(@NotNull @Valid QueryParamCase queryParamCase);

    /**
    * 更新用户
    * @param queryParamCase
    */
    void updateQueryParamCase(@NotNull @Valid QueryParamCase queryParamCase);

    /**
    * 删除用户
    * @param id
    */
    void deleteQueryParamCase(@NotNull String id);

    QueryParamCase findOne(@NotNull String id);

    List<QueryParamCase> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    QueryParamCase findQueryParamCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<QueryParamCase> findAllQueryParamCase();

    /**
    * 查询列表
    * @param queryParamCaseQuery
    * @return
    */
    List<QueryParamCase> findQueryParamCaseList(QueryParamCaseQuery queryParamCaseQuery);

    /**
    * 按分页查询
    * @param queryParamCaseQuery
    * @return
    */
    Pagination<QueryParamCase> findQueryParamCasePage(QueryParamCaseQuery queryParamCaseQuery);

}