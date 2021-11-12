package com.doublekit.apibox.apitest.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apitest.model.TestInstance;
import com.doublekit.apibox.apitest.model.TestInstanceQuery;
import com.doublekit.apibox.apitest.service.TestInstanceService;
import com.doublekit.common.page.Pagination;
import com.doublekit.common.Result;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
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
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/testInstance")
@Api(name = "TestInstanceController",desc = "测试实例管理")
public class TestInstanceController {

    private static Logger logger = LoggerFactory.getLogger(TestInstanceController.class);

    @Autowired
    private TestInstanceService testInstanceService;

    @RequestMapping(path="/createTestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createTestInstance",desc = "createTestInstance")
    @ApiParam(name = "testInstance",desc = "testInstance",required = true)
    public Result<String> createTestInstance(@RequestBody @NotNull @Valid TestInstance testInstance){
        String id = testInstanceService.createTestInstanceWithNest(testInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateTestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateTestInstance",desc = "updateTestInstance")
    @ApiParam(name = "testInstance",desc = "testInstance",required = true)
    public Result<Void> updateTestInstance(@RequestBody @NotNull @Valid TestInstance testInstance){
        testInstanceService.updateTestInstance(testInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteTestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteTestInstance",desc = "deleteTestInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestInstance(@NotNull String id){
        testInstanceService.deleteTestInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findTestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findTestInstance",desc = "findTestInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<TestInstance> findTestInstance(@NotNull String id){
        TestInstance testInstance = testInstanceService.findTestInstanceWithNest(id);

        return Result.ok(testInstance);
    }

    @RequestMapping(path="/findAllTestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllTestInstance",desc = "findAllTestInstance")
    public Result<List<TestInstance>> findAllTestInstance(){
        List<TestInstance> testInstanceList = testInstanceService.findAllTestInstance();

        return Result.ok(testInstanceList);
    }


    @RequestMapping(path = "/findTestInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestInstanceList",desc = "findTestInstanceList")
    @ApiParam(name = "testInstanceQuery",desc = "testInstanceQuery",required = true)
    public Result<List<TestInstance>> findTestInstanceList(@RequestBody @Valid @NotNull TestInstanceQuery testInstanceQuery){
        List<TestInstance> testInstanceList = testInstanceService.findTestInstanceList(testInstanceQuery);

        return Result.ok(testInstanceList);
    }


    @RequestMapping(path = "/findTestInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findTestInstancePage",desc = "findTestInstancePage")
    @ApiParam(name = "testInstanceQuery",desc = "testInstanceQuery",required = true)
    public Result<Pagination<TestInstance>> findTestInstancePage(@RequestBody @Valid @NotNull TestInstanceQuery testInstanceQuery){
        Pagination<TestInstance> pagination = testInstanceService.findTestInstancePage(testInstanceQuery);

        return Result.ok(pagination);
    }

}
