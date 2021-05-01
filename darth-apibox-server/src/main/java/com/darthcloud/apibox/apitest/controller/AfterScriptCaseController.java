package com.darthcloud.apibox.apitest.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.apitest.model.AfterScriptCase;
import com.darthcloud.apibox.apitest.model.AfterScriptCaseQuery;
import com.darthcloud.apibox.apitest.service.AfterScriptCaseService;
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
import com.darthcloud.web.validation.annotation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/afterScriptCase")
@Api(name = "AfterScriptCaseController",desc = "接口用例-后置脚本管理")
public class AfterScriptCaseController {

    private static Logger logger = LoggerFactory.getLogger(AfterScriptCaseController.class);

    @Autowired
    private AfterScriptCaseService afterScriptCaseService;

    @RequestMapping(path="/createAfterScriptCase",method = RequestMethod.POST)
    @ApiMethod(name = "createAfterScriptCase",desc = "createAfterScriptCase")
    @ApiParam(name = "afterScriptCase",desc = "afterScriptCase",required = true)
    public Result<String> createAfterScriptCase(@RequestBody @NotNull @Valid AfterScriptCase afterScriptCase){
        String id = afterScriptCaseService.createAfterScriptCase(afterScriptCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAfterScriptCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateAfterScriptCase",desc = "updateAfterScriptCase")
    @ApiParam(name = "afterScriptCase",desc = "afterScriptCase",required = true)
    public Result<Void> updateAfterScriptCase(@RequestBody @NotNull @Valid AfterScriptCase afterScriptCase){
        afterScriptCaseService.updateAfterScriptCase(afterScriptCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAfterScriptCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAfterScriptCase",desc = "deleteAfterScriptCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAfterScriptCase(@NotNull String id){
        afterScriptCaseService.deleteAfterScriptCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAfterScriptCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAfterScriptCase",desc = "findAfterScriptCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AfterScriptCase> findAfterScriptCase(@NotNull String id){
        AfterScriptCase afterScriptCase = afterScriptCaseService.findAfterScriptCase(id);

        return Result.ok(afterScriptCase);
    }

    @RequestMapping(path="/findAllAfterScriptCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAfterScriptCase",desc = "findAllAfterScriptCase")
    public Result<List<AfterScriptCase>> findAllAfterScriptCase(){
        List<AfterScriptCase> afterScriptCaseList = afterScriptCaseService.findAllAfterScriptCase();

        return Result.ok(afterScriptCaseList);
    }

    @Validator
    @RequestMapping(path = "/findAfterScriptCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findAfterScriptCaseList",desc = "findAfterScriptCaseList")
    @ApiParam(name = "afterScriptCaseQuery",desc = "afterScriptCaseQuery",required = true)
    public Result<List<AfterScriptCase>> findAfterScriptCaseList(@RequestBody @Valid @NotNull AfterScriptCaseQuery afterScriptCaseQuery){
        List<AfterScriptCase> afterScriptCaseList = afterScriptCaseService.findAfterScriptCaseList(afterScriptCaseQuery);

        return Result.ok(afterScriptCaseList);
    }

    @Validator
    @RequestMapping(path = "/findAfterScriptCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findAfterScriptCasePage",desc = "findAfterScriptCasePage")
    @ApiParam(name = "afterScriptCaseQuery",desc = "afterScriptCaseQuery",required = true)
    public Result<Pagination<AfterScriptCase>> findAfterScriptCasePage(@RequestBody @Valid @NotNull AfterScriptCaseQuery afterScriptCaseQuery){
        Pagination<AfterScriptCase> pagination = afterScriptCaseService.findAfterScriptCasePage(afterScriptCaseQuery);

        return Result.ok(pagination);
    }

}
