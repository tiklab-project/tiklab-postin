package io.tiklab.postin.autotest.http.unit.instance.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.instance.model.AssertInstanceUnit;
import io.tiklab.postin.autotest.http.unit.instance.model.AssertInstanceUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 断言实例 服务接口
*/
public interface AssertInstanceUnitService {

    /**
    * 创建断言实例
    * @param assertInstanceUnit
    * @return
    */
    String createAssertInstance(@NotNull @Valid AssertInstanceUnit assertInstanceUnit);

    /**
    * 更新
    * @param assertInstanceUnit
    */
    void updateAssertInstance(@NotNull @Valid AssertInstanceUnit assertInstanceUnit);

    /**
    * 删除断言实例
    * @param id
    */
    void deleteAssertInstance(@NotNull String id);

    AssertInstanceUnit findOne(@NotNull String id);

    List<AssertInstanceUnit> findList(List<String> idList);

    /**
    * 根据id查找断言实例
    * @param id
    * @return
    */
    AssertInstanceUnit findAssertInstance(@NotNull String id);

    /**
    * 查找所有断言实例
    * @return
    */
    List<AssertInstanceUnit> findAllAssertInstance();

    /**
    * 根据查询参数查询断言实例列表
    * @param assertInstanceUnitQuery
    * @return
    */
    List<AssertInstanceUnit> findAssertInstanceList(AssertInstanceUnitQuery assertInstanceUnitQuery);

    /**
    * 根据查询参数按分页查询断言实例
    * @param assertInstanceUnitQuery
    * @return
    */
    Pagination<AssertInstanceUnit> findAssertInstancePage(AssertInstanceUnitQuery assertInstanceUnitQuery);

}