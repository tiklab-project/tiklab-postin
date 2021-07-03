package com.doublekit.apibox.apitest.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.apibox.apitest.model.PreScriptCaseQuery;
import com.doublekit.apibox.apitest.service.PreScriptCaseService;
import com.doublekit.common.Pagination;
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
import com.doublekit.web.validation.annotation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/preScriptCase")
@Api(name = "PreScriptCaseController",desc = "接口用例-前置脚本管理")
public class PreScriptCaseController {

    private static Logger logger = LoggerFactory.getLogger(PreScriptCaseController.class);

    @Autowired
    private PreScriptCaseService preScriptCaseService;

    @RequestMapping(path="/createPreScriptCase",method = RequestMethod.POST)
    @ApiMethod(name = "createPreScriptCase",desc = "createPreScriptCase")
    @ApiParam(name = "preScriptCase",desc = "preScriptCase",required = true)
    public Result<String> createPreScriptCase(@RequestBody @NotNull @Valid PreScriptCase preScriptCase){
        String id = preScriptCaseService.createPreScriptCase(preScriptCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updatePreScriptCase",method = RequestMethod.POST)
    @ApiMethod(name = "updatePreScriptCase",desc = "updatePreScriptCase")
    @ApiParam(name = "preScriptCase",desc = "preScriptCase",required = true)
    public Result<Void> updatePreScriptCase(@RequestBody @NotNull @Valid PreScriptCase preScriptCase){
        preScriptCaseService.updatePreScriptCase(preScriptCase);

        return Result.ok();
    }

    @RequestMapping(path="/deletePreScriptCase",method = RequestMethod.POST)
    @ApiMethod(name = "deletePreScriptCase",desc = "deletePreScriptCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deletePreScriptCase(@NotNull String id){
        preScriptCaseService.deletePreScriptCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findPreScriptCase",method = RequestMethod.POST)
    @ApiMethod(name = "findPreScriptCase",desc = "findPreScriptCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<PreScriptCase> findPreScriptCase(@NotNull String id){
        PreScriptCase preScriptCase = preScriptCaseService.findPreScriptCase(id);

        return Result.ok(preScriptCase);
    }

    @RequestMapping(path="/findAllPreScriptCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllPreScriptCase",desc = "findAllPreScriptCase")
    public Result<List<PreScriptCase>> findAllPreScriptCase(){
        List<PreScriptCase> preScriptCaseList = preScriptCaseService.findAllPreScriptCase();

        return Result.ok(preScriptCaseList);
    }

    @Validator
    @RequestMapping(path = "/findPreScriptCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findPreScriptCaseList",desc = "findPreScriptCaseList")
    @ApiParam(name = "preScriptCaseQuery",desc = "preScriptCaseQuery",required = true)
    public Result<List<PreScriptCase>> findPreScriptCaseList(@RequestBody @Valid @NotNull PreScriptCaseQuery preScriptCaseQuery){
        List<PreScriptCase> preScriptCaseList = preScriptCaseService.findPreScriptCaseList(preScriptCaseQuery);

        return Result.ok(preScriptCaseList);
    }

    @Validator
    @RequestMapping(path = "/findPreScriptCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findPreScriptCasePage",desc = "findPreScriptCasePage")
    @ApiParam(name = "preScriptCaseQuery",desc = "preScriptCaseQuery",required = true)
    public Result<Pagination<PreScriptCase>> findPreScriptCasePage(@RequestBody @Valid @NotNull PreScriptCaseQuery preScriptCaseQuery){
        Pagination<PreScriptCase> pagination = preScriptCaseService.findPreScriptCasePage(preScriptCaseQuery);

        return Result.ok(pagination);
    }

}
