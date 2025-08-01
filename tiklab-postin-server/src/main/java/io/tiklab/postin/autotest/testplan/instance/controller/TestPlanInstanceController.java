package io.tiklab.postin.autotest.testplan.instance.controller;

import io.tiklab.postin.autotest.testplan.instance.model.TestPlanInstance;
import io.tiklab.postin.autotest.testplan.instance.model.TestPlanInstanceQuery;
import io.tiklab.postin.autotest.testplan.instance.service.TestPlanInstanceService;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @pi.protocol: http
 * @pi.groupName: 测试计划实例 控制器
 * @pi.path:/testPlanInstance
 */
@RestController
@RequestMapping("/testPlanInstance")
@Api(name = "测试计划实例管理",desc = "测试计划实例管理")
public class TestPlanInstanceController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanInstanceController.class);

    @Autowired
    private TestPlanInstanceService testPlanInstanceService;

    /**
     * @pi.name:createTestPlanInstance
     * @pi.url:/createTestPlanInstance
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.postin.autotest.testplan.instance.model.TestPlanInstance
     */
    @RequestMapping(path="/createTestPlanInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "createTestPlanInstance",desc = "创建测试计划实例")
//    @ApiParam(name = "testPlanInstance",desc = "testPlanInstance",required = true)
    public Result<String> createTestPlanInstance(@RequestBody @NotNull @Valid TestPlanInstance testPlanInstance){
        String id = testPlanInstanceService.createTestPlanInstance(testPlanInstance);

        return Result.ok(id);
    }

    /**
     * @pi.name:updateTestPlanInstance
     * @pi.url:/updateTestPlanInstance
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.postin.autotest.testplan.instance.model.TestPlanInstance
     */
    @RequestMapping(path="/updateTestPlanInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "updateTestPlanInstance",desc = "更新测试计划实例")
//    @ApiParam(name = "testPlanInstance",desc = "testPlanInstance",required = true)
    public Result<Void> updateTestPlanInstance(@RequestBody @NotNull @Valid TestPlanInstance testPlanInstance){
        testPlanInstanceService.updateTestPlanInstance(testPlanInstance);

        return Result.ok();
    }

    /**
     * @pi.name:deleteTestPlanInstance
     * @pi.url:/deleteTestPlanInstance
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: name=id;dataType=string;value=id;desc=当前删除的id
     */
    @RequestMapping(path="/deleteTestPlanInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteTestPlanInstance",desc = "删除测试计划实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestPlanInstance(@NotNull String id){
        testPlanInstanceService.deleteTestPlanInstance(id);

        return Result.ok();
    }

    /**
     * @pi.name:findTestPlanInstance
     * @pi.url:/findTestPlanInstance
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;desc=当前查找的id
     */
    @RequestMapping(path="/findTestPlanInstance",method = RequestMethod.POST)
    @ApiMethod(name = "根据id查找测试计划实例",desc = "根据id查找测试计划实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<TestPlanInstance> findTestPlanInstance(@NotNull String id){
        TestPlanInstance testPlanInstance = testPlanInstanceService.findTestPlanInstance(id);

        return Result.ok(testPlanInstance);
    }

    /**
     * @pi.name:findAllTestPlan
     * @pi.url:/findAllTestPlan
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllTestPlanInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllTestPlanInstance",desc = "查找所有测试计划实例")
    public Result<List<TestPlanInstance>> findAllTestPlanInstance(){
        List<TestPlanInstance> testPlanInstanceList = testPlanInstanceService.findAllTestPlanInstance();

        return Result.ok(testPlanInstanceList);
    }

    /**
     * @pi.name:findTestPlanInstanceList
     * @pi.url:/findTestPlanInstanceList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.postin.autotest.testplan.instance.model.TestPlanInstanceQuery
     */
    @RequestMapping(path = "/findTestPlanInstanceList",method = RequestMethod.POST)
//    @ApiMethod(name = "findTestPlanInstanceList",desc = "根据查询参数查询测试计划实例列表")
//    @ApiParam(name = "testPlanInstanceQuery",desc = "testPlanInstanceQuery",required = true)
    public Result<List<TestPlanInstance>> findTestPlanInstanceList(@RequestBody @Valid @NotNull TestPlanInstanceQuery testPlanInstanceQuery){
        List<TestPlanInstance> testPlanInstanceList = testPlanInstanceService.findTestPlanInstanceList(testPlanInstanceQuery);

        return Result.ok(testPlanInstanceList);
    }

    /**
     * @pi.name:findTestPlanInstancePage
     * @pi.url:/findTestPlanInstancePage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.postin.autotest.testplan.instance.model.TestPlanInstanceQuery
     */
    @RequestMapping(path = "/findTestPlanInstancePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findTestPlanInstancePage",desc = "根据查询参数按分页查询测试计划实例")
//    @ApiParam(name = "testPlanInstanceQuery",desc = "testPlanInstanceQuery",required = true)
    public Result<Pagination<TestPlanInstance>> findTestPlanInstancePage(@RequestBody @Valid @NotNull TestPlanInstanceQuery testPlanInstanceQuery){
        Pagination<TestPlanInstance> pagination = testPlanInstanceService.findTestPlanInstancePage(testPlanInstanceQuery);

        return Result.ok(pagination);
    }

}
