package io.tiklab.postin.autotest.http.unit.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.instance.model.ResponseInstanceUnit;
import io.tiklab.postin.autotest.http.unit.instance.model.ResponseInstanceUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应数据实例 服务接口
*/
public interface ResponseInstanceUnitService {

    /**
    * 创建响应数据实例
    * @param responseInstanceUnit
    * @return
    */
    String createResponseInstance(@NotNull @Valid ResponseInstanceUnit responseInstanceUnit);

    /**
    * 更新响应数据实例
    * @param responseInstanceUnit
    */
    void updateResponseInstance(@NotNull @Valid ResponseInstanceUnit responseInstanceUnit);

    /**
    * 删除响应数据实例
    * @param id
    */
    void deleteResponseInstance(@NotNull String id);

    ResponseInstanceUnit findOne(@NotNull String id);

    List<ResponseInstanceUnit> findList(List<String> idList);

    /**
    * 根据id查找响应数据实例
    * @param id
    * @return
    */
    ResponseInstanceUnit findResponseInstance(@NotNull String id);

    /**
    * 查找所有响应数据实例
    * @return
    */
    List<ResponseInstanceUnit> findAllResponseInstance();

    /**
    * 根据查询参数查询响应数据实例列表
    * @param responseInstanceUnitQuery
    * @return
    */
    List<ResponseInstanceUnit> findResponseInstanceList(ResponseInstanceUnitQuery responseInstanceUnitQuery);

    /**
    * 根据查询参数按分页查询响应数据实例
    * @param responseInstanceUnitQuery
    * @return
    */
    Pagination<ResponseInstanceUnit> findResponseInstancePage(ResponseInstanceUnitQuery responseInstanceUnitQuery);

}