package io.tiklab.postin.autotest.testplan.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanCaseInstanceBindQuery;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanCaseInstanceBind;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 测试计划下用例的实例中间层 服务接口
*/
public interface TestPlanCaseInstanceBindService {

    /**
    * 创建用例的实例中间层
    * @param testPlanCaseInstanceBind
    * @return
    */
    String createTestPlanCaseInstanceBind(@NotNull @Valid TestPlanCaseInstanceBind testPlanCaseInstanceBind);

    /**
    * 更新用例的实例中间层
    * @param testPlanCaseInstanceBind
    */
    void updateTestPlanCaseInstanceBind(@NotNull @Valid TestPlanCaseInstanceBind testPlanCaseInstanceBind);

    /**
    * 删除用例的实例中间层
    * @param id
    */
    void deleteTestPlanCaseInstanceBind(@NotNull String id);

    TestPlanCaseInstanceBind findOne(@NotNull String id);

    List<TestPlanCaseInstanceBind> findList(List<String> idList);

    /**
    * 查找用例的实例中间层
    * @param id
    * @return
    */
    TestPlanCaseInstanceBind findTestPlanCaseInstanceBind(@NotNull String id);

    /**
    * 查找所有用例的实例中间层
    * @return
    */
    List<TestPlanCaseInstanceBind> findAllTestPlanCaseInstanceBind();

    /**
    * 根据查询参数查询用例的实例中间层列表
    * @param testPlanCaseInstanceBindQuery
    * @return
    */
    List<TestPlanCaseInstanceBind> findTestPlanCaseInstanceBindList(TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery);

    /**
    * 根据查询参数按分页查询用例的实例中间层
    * @param testPlanCaseInstanceBindQuery
    * @return
    */
    Pagination<TestPlanCaseInstanceBind> findTestPlanCaseInstanceBindPage(TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery);

}