package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.AssertUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AssertUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 断言 服务接口
*/
public interface AssertUnitService {

    /**
    * 创建断言
    * @param assertUnit
    * @return
    */
    String createAssertCase(@NotNull @Valid AssertUnit assertUnit);

    /**
    * 更新断言
    * @param assertUnit
    */
    void updateAssertCase(@NotNull @Valid AssertUnit assertUnit);

    /**
    * 删除断言
    * @param id
    */
    void deleteAssertCase(@NotNull String id);

    void deleteAllAssertCase( String caseId);

    AssertUnit findOne(@NotNull String id);

    List<AssertUnit> findList(List<String> idList);

    /**
    * 通过id查找断言
    * @param id
    * @return
    */
    AssertUnit findAssertCase(@NotNull String id);

    /**
    * 查找所有断言
    * @return
    */
    List<AssertUnit> findAllAssertCase();

    /**
    * 查询断言列表
    * @param assertUnitQuery
    * @return
    */
    List<AssertUnit> findAssertCaseList(AssertUnitQuery assertUnitQuery);

    /**
    * 按分页查询断言
    * @param assertUnitQuery
    * @return
    */
    Pagination<AssertUnit> findAssertCasePage(AssertUnitQuery assertUnitQuery);

}