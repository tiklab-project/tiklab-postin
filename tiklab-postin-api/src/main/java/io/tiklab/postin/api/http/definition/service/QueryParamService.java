package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.definition.model.QueryParam;
import io.tiklab.postin.api.http.definition.model.QueryParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* query服务接口
*/
public interface QueryParamService {

    /**
    * 创建query
    * @param queryParam
    * @return
    */
    String createQueryParam(@NotNull @Valid QueryParam queryParam);

    /**
    * 更新query
    * @param queryParam
    */
    void updateQueryParam(@NotNull @Valid QueryParam queryParam);

    /**
    * 删除query
    * @param id
    */
    void deleteQueryParam(@NotNull String id);

    /**
    * 查找query
    * @param id
    * @return
    */
    QueryParam findQueryParam(@NotNull String id);

    /**
    * 查找所有query
    * @return
    */
    List<QueryParam> findAllQueryParam();

    /**
    * 查询列表query
    * @param queryParamQuery
    * @return
    */
    List<QueryParam> findQueryParamList(QueryParamQuery queryParamQuery);

    /**
    * 按分页查询query
    * @param queryParamQuery
    * @return
    */
    Pagination<QueryParam> findQueryParamPage(QueryParamQuery queryParamQuery);

}