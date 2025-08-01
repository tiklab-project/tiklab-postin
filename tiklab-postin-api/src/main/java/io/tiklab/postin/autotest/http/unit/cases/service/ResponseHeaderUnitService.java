package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.ResponseHeaderUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.ResponseHeaderUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应头 服务接口
*/
public interface ResponseHeaderUnitService {

    /**
    * 创建响应头
    * @param responseHeaderUnit
    * @return
    */
    String createResponseHeader(@NotNull @Valid ResponseHeaderUnit responseHeaderUnit);

    /**
    * 更新响应头
    * @param responseHeaderUnit
    */
    void updateResponseHeader(@NotNull @Valid ResponseHeaderUnit responseHeaderUnit);

    /**
    * 删除响应头
    * @param id
    */
    void deleteResponseHeader(@NotNull String id);

    void deleteAllResponseHeader( String caseId);

    ResponseHeaderUnit findOne(@NotNull String id);

    List<ResponseHeaderUnit> findList(List<String> idList);

    /**
    * 查找响应头
    * @param id
    * @return
    */
    ResponseHeaderUnit findResponseHeader(@NotNull String id);

    /**
    * 查找所有响应头
    * @return
    */
    List<ResponseHeaderUnit> findAllResponseHeader();

    /**
    * 根据查询参数查询响应头列表
    * @param responseHeaderUnitQuery
    * @return
    */
    List<ResponseHeaderUnit> findResponseHeaderList(ResponseHeaderUnitQuery responseHeaderUnitQuery);

    /**
    * 根据查询参数按分页查询响应头
    * @param responseHeaderUnitQuery
    * @return
    */
    Pagination<ResponseHeaderUnit> findResponseHeaderPage(ResponseHeaderUnitQuery responseHeaderUnitQuery);

}