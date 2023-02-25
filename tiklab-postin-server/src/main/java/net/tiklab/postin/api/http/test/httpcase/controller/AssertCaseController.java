package net.tiklab.postin.api.http.test.httpcase.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.api.http.test.httpcase.model.AssertCase;
import net.tiklab.postin.api.http.test.httpcase.model.AssertCaseQuery;
import net.tiklab.postin.api.http.test.httpcase.service.AssertCaseService;
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
@RequestMapping("/assertCase")
@Api(name = "AssertCaseController",desc = "接口用例-断言管理")
public class AssertCaseController {

    private static Logger logger = LoggerFactory.getLogger(AssertCaseController.class);

    @Autowired
    private AssertCaseService assertCaseService;

    @RequestMapping(path="/createAssertCase",method = RequestMethod.POST)
    @ApiMethod(name = "createAssertCase",desc = "createAssertCase")
    @ApiParam(name = "assertCase",desc = "assertCase",required = true)
    public Result<String> createAssertCase(@RequestBody @NotNull @Valid AssertCase assertCase){
        String id = assertCaseService.createAssertCase(assertCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAssertCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateAssertCase",desc = "updateAssertCase")
    @ApiParam(name = "assertCase",desc = "assertCase",required = true)
    public Result<Void> updateAssertCase(@RequestBody @NotNull @Valid AssertCase assertCase){
        assertCaseService.updateAssertCase(assertCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAssertCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAssertCase",desc = "deleteAssertCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAssertCase(@NotNull String id){
        assertCaseService.deleteAssertCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAssertCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertCase",desc = "findAssertCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AssertCase> findAssertCase(@NotNull String id){
        AssertCase assertCase = assertCaseService.findAssertCase(id);

        return Result.ok(assertCase);
    }

    @RequestMapping(path="/findAllAssertCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAssertCase",desc = "findAllAssertCase")
    public Result<List<AssertCase>> findAllAssertCase(){
        List<AssertCase> assertCaseList = assertCaseService.findAllAssertCase();

        return Result.ok(assertCaseList);
    }


    @RequestMapping(path = "/findAssertCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertCaseList",desc = "findAssertCaseList")
    @ApiParam(name = "assertCaseQuery",desc = "assertCaseQuery",required = true)
    public Result<List<AssertCase>> findAssertCaseList(@RequestBody @Valid @NotNull AssertCaseQuery assertCaseQuery){
        List<AssertCase> assertCaseList = assertCaseService.findAssertCaseList(assertCaseQuery);

        return Result.ok(assertCaseList);
    }


    @RequestMapping(path = "/findAssertCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertCasePage",desc = "findAssertCasePage")
    @ApiParam(name = "assertCaseQuery",desc = "assertCaseQuery",required = true)
    public Result<Pagination<AssertCase>> findAssertCasePage(@RequestBody @Valid @NotNull AssertCaseQuery assertCaseQuery){
        Pagination<AssertCase> pagination = assertCaseService.findAssertCasePage(assertCaseQuery);

        return Result.ok(pagination);
    }

}
