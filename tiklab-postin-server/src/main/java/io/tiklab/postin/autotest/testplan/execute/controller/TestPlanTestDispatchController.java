package io.tiklab.postin.autotest.testplan.execute.controller;

import io.tiklab.postin.autotest.testplan.execute.model.TestPlanTestData;
import io.tiklab.postin.autotest.testplan.execute.model.TestPlanTestResponse;
import io.tiklab.postin.autotest.testplan.execute.service.TestPlanExecuteDispatchService;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @pi.protocol: http
 * @pi.groupName: 测试计划测试 控制器
 * @pi.path:/testPlanTestDispatch
 */
@RestController
@RequestMapping("/testPlanTestDispatch")
@Api(name = "测试计划测试调度",desc = "测试计划测试调度")
public class TestPlanTestDispatchController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanTestDispatchController.class);

    @Autowired
    TestPlanExecuteDispatchService testPlanExecuteDispatchService;

    /**
     * @pi.name:execute
     * @pi.url:/execute
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.postin.autotest.testplan.execute.model.TestPlanTestData
     */
    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    @ApiMethod(name = "执行测试", desc = "执行测试")
    @ApiParam(name = "testPlanTestData", desc = "执行需要传的参数", required = true)
    public Result<String> execute(@RequestBody @Valid @NotNull TestPlanTestData testPlanTestData) {
        String instanceId = testPlanExecuteDispatchService.execute(testPlanTestData);

        return Result.ok(instanceId);
    }

    /**
     * @pi.name:exeResult
     * @pi.url:/exeResult
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path = "/exeResult", method = RequestMethod.POST)
    @ApiMethod(name = "获取测试结果", desc = "获取测试结果")
    public Result<TestPlanTestResponse> exeResult(@NotNull String testPlanId) {
        TestPlanTestResponse testPlanTestResponse = testPlanExecuteDispatchService.exeResult(testPlanId);
        return Result.ok(testPlanTestResponse);
    }


    @RequestMapping(path = "/cleanUpExecutionData", method = RequestMethod.POST)
//    @ApiMethod(name = "cleanUpExecutionData", desc = "清楚测试数据")
    public Result<Void> cleanUpExecutionData(@NotNull String testPlanId) {
        testPlanExecuteDispatchService.cleanUpExecutionData(testPlanId);
        return Result.ok();
    }

}
