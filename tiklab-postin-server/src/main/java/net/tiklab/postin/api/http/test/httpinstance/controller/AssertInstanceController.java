package net.tiklab.postin.api.http.test.httpinstance.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.api.http.test.httpinstance.model.AssertInstance;
import net.tiklab.postin.api.http.test.httpinstance.model.AssertInstanceQuery;
import net.tiklab.postin.api.http.test.httpinstance.service.AssertInstanceService;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
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
    public Result<String> createAssertInstance(@RequestBody @NotNull @Valid AssertInstance assertInstance){
        String id = assertInstanceService.createAssertInstance(assertInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAssertInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateAssertInstance",desc = "updateAssertInstance")
    @ApiParam(name = "assertInstance",desc = "assertInstance",required = true)
    public Result<Void> updateAssertInstance(@RequestBody @NotNull @Valid AssertInstance assertInstance){
        assertInstanceService.updateAssertInstance(assertInstance);

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
    public Result<AssertInstance> findAssertInstance(@NotNull String id){
        AssertInstance assertInstance = assertInstanceService.findAssertInstance(id);

        return Result.ok(assertInstance);
    }

    @RequestMapping(path="/findAllAssertInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAssertInstance",desc = "findAllAssertInstance")
    public Result<List<AssertInstance>> findAllAssertInstance(){
        List<AssertInstance> assertInstanceList = assertInstanceService.findAllAssertInstance();

        return Result.ok(assertInstanceList);
    }


    @RequestMapping(path = "/findAssertInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertInstanceList",desc = "findAssertInstanceList")
    @ApiParam(name = "assertInstanceQuery",desc = "assertInstanceQuery",required = true)
    public Result<List<AssertInstance>> findAssertInstanceList(@RequestBody @Valid @NotNull AssertInstanceQuery assertInstanceQuery){
        List<AssertInstance> assertInstanceList = assertInstanceService.findAssertInstanceList(assertInstanceQuery);

        return Result.ok(assertInstanceList);
    }


    @RequestMapping(path = "/findAssertInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertInstancePage",desc = "findAssertInstancePage")
    @ApiParam(name = "assertInstanceQuery",desc = "assertInstanceQuery",required = true)
    public Result<Pagination<AssertInstance>> findAssertInstancePage(@RequestBody @Valid @NotNull AssertInstanceQuery assertInstanceQuery){
        Pagination<AssertInstance> pagination = assertInstanceService.findAssertInstancePage(assertInstanceQuery);

        return Result.ok(pagination);
    }

}
