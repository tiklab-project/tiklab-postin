package com.darthcloud.apibox.apitest.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.apitest.model.Testcase;
import com.darthcloud.apibox.apitest.model.TestcaseQuery;
import com.darthcloud.apibox.apitest.service.TestcaseService;
import com.darthcloud.common.Pagination;
import com.darthcloud.common.Result;
import com.darthcloud.apibox.annotation.ApiMethod;
import com.darthcloud.apibox.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.darthcloud.validation.annotation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/testcase")
@Api(name = "TestcaseController",desc = "接口用例管理")
public class TestcaseController {

    private static Logger logger = LoggerFactory.getLogger(TestcaseController.class);

    @Autowired
    private TestcaseService testcaseService;

    @RequestMapping(path="/createTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "createTestcase",desc = "创建测试用例")
    @ApiParam(name = "testcase",desc = "testcase",required = true)
    public Result<String> createTestcase(@RequestBody @NotNull @Valid Testcase testcase){
        String id = testcaseService.createTestcase(testcase);

        return Result.ok(id);
    }

    @RequestMapping(path="/createTestcaseWithNest",method = RequestMethod.POST)
    @ApiMethod(name = "createTestcaseWithNest",desc = "创建测试用例，级联保存从、子表数据")
    @ApiParam(name = "testcase",desc = "testcase",required = true)
    public Result<String> createTestcaseWithNest(@RequestBody @NotNull @Valid Testcase testcase){
        String id = testcaseService.createTestcaseWithNest(testcase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "updateTestcase",desc = "updateTestcase")
    @ApiParam(name = "testcase",desc = "testcase",required = true)
    public Result<Void> updateTestcase(@RequestBody @NotNull @Valid Testcase testcase){
        testcaseService.updateTestcase(testcase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteTestcase",desc = "deleteTestcase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestcase(@NotNull String id){
        testcaseService.deleteTestcase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "findTestcase",desc = "findTestcase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Testcase> findTestcase(@NotNull String id){
        Testcase testcase = testcaseService.findTestcase(id);

        return Result.ok(testcase);
    }

    @RequestMapping(path="/findAllTestcase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllTestcase",desc = "findAllTestcase")
    public Result<List<Testcase>> findAllTestcase(){
        List<Testcase> testcaseList = testcaseService.findAllTestcase();

        return Result.ok(testcaseList);
    }

    @Validator
    @RequestMapping(path = "/findTestcaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestcaseList",desc = "findTestcaseList")
    @ApiParam(name = "testcaseQuery",desc = "testcaseQuery",required = true)
    public Result<List<Testcase>> findTestcaseList(@RequestBody @Valid @NotNull TestcaseQuery testcaseQuery){
        List<Testcase> testcaseList = testcaseService.findTestcaseList(testcaseQuery);

        return Result.ok(testcaseList);
    }

    @Validator
    @RequestMapping(path = "/findTestcasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findTestcasePage",desc = "findTestcasePage")
    @ApiParam(name = "testcaseQuery",desc = "testcaseQuery",required = true)
    public Result<Pagination<List<Testcase>>> findTestcasePage(@RequestBody @Valid @NotNull TestcaseQuery testcaseQuery){
        Pagination<List<Testcase>> pagination = testcaseService.findTestcasePage(testcaseQuery);

        return Result.ok(pagination);
    }

}
