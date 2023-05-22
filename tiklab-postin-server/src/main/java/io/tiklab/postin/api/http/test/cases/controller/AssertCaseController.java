package io.tiklab.postin.api.http.test.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.test.cases.model.AssertCases;
import io.tiklab.postin.api.http.test.cases.model.AssertCaseQuery;
import io.tiklab.postin.api.http.test.cases.service.AssertCaseService;
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
@RequestMapping("/assertCase")
@Api(name = "AssertCaseController",desc = "接口用例-断言管理")
public class AssertCaseController {

    private static Logger logger = LoggerFactory.getLogger(AssertCaseController.class);

    @Autowired
    private AssertCaseService assertCaseService;

    @RequestMapping(path="/createAssertCase",method = RequestMethod.POST)
    @ApiMethod(name = "createAssertCase",desc = "createAssertCase")
    @ApiParam(name = "assertCase",desc = "assertCase",required = true)
    public Result<String> createAssertCase(@RequestBody @NotNull @Valid AssertCases assertCases){
        String id = assertCaseService.createAssertCase(assertCases);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAssertCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateAssertCase",desc = "updateAssertCase")
    @ApiParam(name = "assertCase",desc = "assertCase",required = true)
    public Result<Void> updateAssertCase(@RequestBody @NotNull @Valid AssertCases assertCases){
        assertCaseService.updateAssertCase(assertCases);

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
    public Result<AssertCases> findAssertCase(@NotNull String id){
        AssertCases assertCases = assertCaseService.findAssertCase(id);

        return Result.ok(assertCases);
    }

    @RequestMapping(path="/findAllAssertCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAssertCase",desc = "findAllAssertCase")
    public Result<List<AssertCases>> findAllAssertCase(){
        List<AssertCases> assertCasesList = assertCaseService.findAllAssertCase();

        return Result.ok(assertCasesList);
    }


    @RequestMapping(path = "/findAssertCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertCaseList",desc = "findAssertCaseList")
    @ApiParam(name = "assertCaseQuery",desc = "assertCaseQuery",required = true)
    public Result<List<AssertCases>> findAssertCaseList(@RequestBody @Valid @NotNull AssertCaseQuery assertCaseQuery){
        List<AssertCases> assertCasesList = assertCaseService.findAssertCaseList(assertCaseQuery);

        return Result.ok(assertCasesList);
    }


    @RequestMapping(path = "/findAssertCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findAssertCasePage",desc = "findAssertCasePage")
    @ApiParam(name = "assertCaseQuery",desc = "assertCaseQuery",required = true)
    public Result<Pagination<AssertCases>> findAssertCasePage(@RequestBody @Valid @NotNull AssertCaseQuery assertCaseQuery){
        Pagination<AssertCases> pagination = assertCaseService.findAssertCasePage(assertCaseQuery);

        return Result.ok(pagination);
    }

}
