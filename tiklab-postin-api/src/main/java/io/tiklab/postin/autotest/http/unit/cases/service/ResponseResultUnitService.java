package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.ResponseResultUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.ResponseResultUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应结果 服务接口
*/
public interface ResponseResultUnitService {

    /**
    * 创建响应结果
    * @param responseResultUnit
    * @return
    */
    String createResponseResult(@NotNull @Valid ResponseResultUnit responseResultUnit);

    /**
    * 更新响应结果
    * @param responseResultUnit
    */
    void updateResponseResult(@NotNull @Valid ResponseResultUnit responseResultUnit);

    /**
    * 删除响应结果
    * @param id
    */
    void deleteResponseResult(@NotNull String id);

    ResponseResultUnit findOne(@NotNull String id);

    List<ResponseResultUnit> findList(List<String> idList);

    /**
    * 查找响应结果
    * @param id
    * @return
    */
    ResponseResultUnit findResponseResult(@NotNull String id);

    /**
    * 查找所有响应结果
    * @return
    */
    List<ResponseResultUnit> findAllResponseResult();

    /**
    * 根据查询参数查询响应结果列表
    * @param responseResultUnitQuery
    * @return
    */
    List<ResponseResultUnit> findResponseResultList(ResponseResultUnitQuery responseResultUnitQuery);

    /**
    * 根据查询参数按分页查询响应结果
    * @param responseResultUnitQuery
    * @return
    */
    Pagination<ResponseResultUnit> findResponseResultPage(ResponseResultUnitQuery responseResultUnitQuery);

}