package io.tiklab.postin.autotest.http.unit.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.instance.model.RequestInstanceUnit;
import io.tiklab.postin.autotest.http.unit.instance.model.RequestInstanceUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求数据实例 服务接口
*/
public interface RequestInstanceUnitService {

    /**
    * 创建请求数据实例
    * @param requestInstanceUnit
    * @return
    */
    String createRequestInstance(@NotNull @Valid RequestInstanceUnit requestInstanceUnit);

    /**
    * 更新
    * @param requestInstanceUnit
    */
    void updateRequestInstance(@NotNull @Valid RequestInstanceUnit requestInstanceUnit);

    /**
    * 删除请求数据实例
    * @param id
    */
    void deleteRequestInstance(@NotNull String id);

    RequestInstanceUnit findOne(@NotNull String id);

    List<RequestInstanceUnit> findList(List<String> idList);

    /**
    * 查找请求数据实例
    * @param id
    * @return
    */
    RequestInstanceUnit findRequestInstance(@NotNull String id);

    /**
    * 查找所有请求数据实例
    * @return
    */
    List<RequestInstanceUnit> findAllRequestInstance();

    /**
    * 根据查询参数查询请求数据实例列表
    * @param requestInstanceUnitQuery
    * @return
    */
    List<RequestInstanceUnit> findRequestInstanceList(RequestInstanceUnitQuery requestInstanceUnitQuery);

    /**
    * 根据查询参数按分页查询请求数据实例
    * @param requestInstanceUnitQuery
    * @return
    */
    Pagination<RequestInstanceUnit> findRequestInstancePage(RequestInstanceUnitQuery requestInstanceUnitQuery);

}