package io.tiklab.postin.api.http.test.instance.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.test.instance.model.AssertInstances;
import io.tiklab.postin.api.http.test.instance.model.AssertInstanceQuery;
import io.tiklab.postin.api.http.test.instance.service.AssertInstanceService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
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
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/assertInstance")
@Api(name = "AssertInstanceController",desc = "测试实例-断言管理")
public class AssertInstanceController {

    private static Logger logger = LoggerFactory.getLogger(AssertInstanceController.class);

    @Autowired
    private AssertInstanceService assertInstanceService;

    @RequestMapping(path="/createAssertInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createAssertInstance",desc = "createAssertInstance")
    @ApiParam(name = "assertInstance",desc = "assertInstance",required = true)
    public Result<String> createAssertInstance(@RequestBody @NotNull @Valid AssertInstances assertInstances){
        String id = assertInstanceService.createAssertInstance(assertInstances);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAssertInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateAssertInstance",desc = "updateAssertInstance")
    @ApiParam(name = "assertInstance",desc = "assertInstance",required = true)
    public Result<Void> updateAssertInstance(@RequestBody @NotNull @Valid AssertInstances assertInstances){
        assertInstanceService.updateAssertInstance(assertInstances);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAssertInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAssertInstance",desc = "deleteAssertInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAssertInstance(@NotNull String id){
        assertInstanceService.deleteAssertInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAssertInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertInstance",desc = "findAssertInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AssertInstances> findAssertInstance(@NotNull String id){
        AssertInstances assertInstances = assertInstanceService.findAssertInstance(id);

        return Result.ok(assertInstances);
    }

    @RequestMapping(path="/findAllAssertInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAssertInstance",desc = "findAllAssertInstance")
    public Result<List<AssertInstances>> findAllAssertInstance(){
        List<AssertInstances> assertInstancesList = assertInstanceService.findAllAssertInstance();

        return Result.ok(assertInstancesList);
    }


    @RequestMapping(path = "/findAssertInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertInstanceList",desc = "findAssertInstanceList")
    @ApiParam(name = "assertInstanceQuery",desc = "assertInstanceQuery",required = true)
    public Result<List<AssertInstances>> findAssertInstanceList(@RequestBody @Valid @NotNull AssertInstanceQuery assertInstanceQuery){
        List<AssertInstances> assertInstancesList = assertInstanceService.findAssertInstanceList(assertInstanceQuery);

        return Result.ok(assertInstancesList);
    }


    @RequestMapping(path = "/findAssertInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertInstancePage",desc = "findAssertInstancePage")
    @ApiParam(name = "assertInstanceQuery",desc = "assertInstanceQuery",required = true)
    public Result<Pagination<AssertInstances>> findAssertInstancePage(@RequestBody @Valid @NotNull AssertInstanceQuery assertInstanceQuery){
        Pagination<AssertInstances> pagination = assertInstanceService.findAssertInstancePage(assertInstanceQuery);

        return Result.ok(pagination);
    }

}
