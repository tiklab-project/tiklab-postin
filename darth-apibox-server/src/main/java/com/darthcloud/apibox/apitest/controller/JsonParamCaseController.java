package com.darthcloud.apibox.apitest.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.apitest.model.JsonParamCase;
import com.darthcloud.apibox.apitest.model.JsonParamCaseQuery;
import com.darthcloud.apibox.apitest.service.JsonParamCaseService;
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
@RequestMapping("/jsonParamCase")
@Api(name = "JsonParamCaseController",desc = "接口用例-json参数管理")
public class JsonParamCaseController {

    private static Logger logger = LoggerFactory.getLogger(JsonParamCaseController.class);

    @Autowired
    private JsonParamCaseService jsonParamCaseService;

    @RequestMapping(path="/createJsonParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "createJsonParamCase",desc = "createJsonParamCase")
    @ApiParam(name = "jsonParamCase",desc = "jsonParamCase",required = true)
    public Result<String> createJsonParamCase(@RequestBody @NotNull @Valid JsonParamCase jsonParamCase){
        String id = jsonParamCaseService.createJsonParamCase(jsonParamCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateJsonParamCase",desc = "updateJsonParamCase")
    @ApiParam(name = "jsonParamCase",desc = "jsonParamCase",required = true)
    public Result<Void> updateJsonParamCase(@RequestBody @NotNull @Valid JsonParamCase jsonParamCase){
        jsonParamCaseService.updateJsonParamCase(jsonParamCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteJsonParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteJsonParamCase",desc = "deleteJsonParamCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteJsonParamCase(@NotNull String id){
        jsonParamCaseService.deleteJsonParamCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findJsonParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamCase",desc = "findJsonParamCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<JsonParamCase> findJsonParamCase(@NotNull String id){
        JsonParamCase jsonParamCase = jsonParamCaseService.findJsonParamCase(id);

        return Result.ok(jsonParamCase);
    }

    @RequestMapping(path="/findAllJsonParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllJsonParamCase",desc = "findAllJsonParamCase")
    public Result<List<JsonParamCase>> findAllJsonParamCase(){
        List<JsonParamCase> jsonParamCaseList = jsonParamCaseService.findAllJsonParamCase();

        return Result.ok(jsonParamCaseList);
    }

    @Validator
    @RequestMapping(path = "/findJsonParamCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamCaseList",desc = "findJsonParamCaseList")
    @ApiParam(name = "jsonParamCaseQuery",desc = "jsonParamCaseQuery",required = true)
    public Result<List<JsonParamCase>> findJsonParamCaseList(@RequestBody @Valid @NotNull JsonParamCaseQuery jsonParamCaseQuery){
        List<JsonParamCase> jsonParamCaseList = jsonParamCaseService.findJsonParamCaseList(jsonParamCaseQuery);

        return Result.ok(jsonParamCaseList);
    }

    @Validator
    @RequestMapping(path = "/findJsonParamCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamCasePage",desc = "findJsonParamCasePage")
    @ApiParam(name = "jsonParamCaseQuery",desc = "jsonParamCaseQuery",required = true)
    public Result<Pagination<JsonParamCase>> findJsonParamCasePage(@RequestBody @Valid @NotNull JsonParamCaseQuery jsonParamCaseQuery){
        Pagination<JsonParamCase> pagination = jsonParamCaseService.findJsonParamCasePage(jsonParamCaseQuery);

        return Result.ok(pagination);
    }

    @Validator
    @RequestMapping(path = "/findJsonParamCaseListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamCaseListTree",desc = "findJsonParamCaseListTree")
    @ApiParam(name = "jsonParamCaseQuery",desc = "jsonParamCaseQuery",required = true)
    public Result<List<JsonParamCase>> findJsonParamCaseListTree(@RequestBody @Valid @NotNull JsonParamCaseQuery jsonParamCaseQuery){
        List<JsonParamCase> jsonParamCaseList = jsonParamCaseService.findJsonParamCaseListTree(jsonParamCaseQuery);

        return Result.ok(jsonParamCaseList);
    }

}
