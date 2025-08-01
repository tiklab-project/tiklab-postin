package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.cases.model.QueryParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.QueryParamUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* query 服务接口
*/
public interface QueryParamUnitService {

    /**
    * 创建query
    * @param queryParamUnit
    * @return
    */
    String createQueryParam(@NotNull @Valid QueryParamUnit queryParamUnit);

    /**
    * 更新query
    * @param queryParamUnit
    */
    void updateQueryParam(@NotNull @Valid QueryParamUnit queryParamUnit);

    /**
    * 删除query
    * @param id
    */
    void deleteQueryParam(@NotNull String id);

    void deleteAllQueryParam( String caseId);

    QueryParamUnit findOne(@NotNull String id);

    List<QueryParamUnit> findList(List<String> idList);

    /**
    * 根据id查找query
    * @param id
    * @return
    */
    QueryParamUnit findQueryParam(@NotNull String id);

    /**
    * 查找所有query
    * @return
    */
    List<QueryParamUnit> findAllQueryParam();

    /**
    * 根据查询参数查询query列表
    * @param queryParamUnitQuery
    * @return
    */
    List<QueryParamUnit> findQueryParamList(QueryParamUnitQuery queryParamUnitQuery);

    /**
    * 根据查询参数按分页查询query
    * @param queryParamUnitQuery
    * @return
    */
    Pagination<QueryParamUnit> findQueryParamPage(QueryParamUnitQuery queryParamUnitQuery);

    /**
     * 构造query参数
     * @param step
     * @return
     */
    String jointParam(ApiUnitCase step);

}