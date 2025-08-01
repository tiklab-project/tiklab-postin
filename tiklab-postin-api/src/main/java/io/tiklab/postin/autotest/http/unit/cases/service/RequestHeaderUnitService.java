package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.cases.model.RequestHeaderUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RequestHeaderUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 请求头 服务接口
*/
public interface RequestHeaderUnitService {

    /**
    * 创建请求头
    * @param requestHeaderUnit
    * @return
    */
    String createRequestHeader(@NotNull @Valid RequestHeaderUnit requestHeaderUnit);

    /**
    * 更新请求头
    * @param requestHeaderUnit
    */
    void updateRequestHeader(@NotNull @Valid RequestHeaderUnit requestHeaderUnit);

    /**
    * 删除请求头
    * @param id
    */
    void deleteRequestHeader(@NotNull String id);

    void deleteAllRequestHeader(String caseId);

    RequestHeaderUnit findOne(@NotNull String id);

    List<RequestHeaderUnit> findList(List<String> idList);

    /**
    * 查找请求头
    * @param id
    * @return
    */
    RequestHeaderUnit findRequestHeader(@NotNull String id);

    /**
    * 查找所有请求头
    * @return
    */
    List<RequestHeaderUnit> findAllRequestHeader();

    /**
    * 根据参数查询请求头列表
    * @param requestHeaderUnitQuery
    * @return
    */
    List<RequestHeaderUnit> findRequestHeaderList(RequestHeaderUnitQuery requestHeaderUnitQuery);

    /**
    * 根据参数按分页查询请求头
    * @param requestHeaderUnitQuery
    * @return
    */
    Pagination<RequestHeaderUnit> findRequestHeaderPage(RequestHeaderUnitQuery requestHeaderUnitQuery);

    /**
     *请求头拼接
     * @param step 用例步骤
     *
     */
    Map<String, String> jointHeader(ApiUnitCase step);

}