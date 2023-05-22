package io.tiklab.postin.api.http.test.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.test.cases.model.JsonParamCases;
import io.tiklab.postin.api.http.test.cases.model.JsonParamCaseQuery;
import io.tiklab.postin.api.http.test.cases.service.JsonParamCaseService;
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
@RequestMapping("/jsonParamCase")
@Api(name = "JsonParamCaseController",desc = "接口用例-json参数管理")
public class JsonParamCaseController {

    private static Logger logger = LoggerFactory.getLogger(JsonParamCaseController.class);

    @Autowired
    private JsonParamCaseService jsonParamCaseService;

    @RequestMapping(path="/createJsonParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "createJsonParamCase",desc = "createJsonParamCase")
    @ApiParam(name = "jsonParamCase",desc = "jsonParamCase",required = true)
    public Result<String> createJsonParamCase(@RequestBody @NotNull @Valid JsonParamCases jsonParamCases){
        String id = jsonParamCaseService.createJsonParamCase(jsonParamCases);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateJsonParamCase",desc = "updateJsonParamCase")
    @ApiParam(name = "jsonParamCase",desc = "jsonParamCase",required = true)
    public Result<Void> updateJsonParamCase(@RequestBody @NotNull @Valid JsonParamCases jsonParamCases){
        jsonParamCaseService.updateJsonParamCase(jsonParamCases);

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
    public Result<JsonParamCases> findJsonParamCase(@NotNull String id){
        JsonParamCases jsonParamCases = jsonParamCaseService.findJsonParamCase(id);

        return Result.ok(jsonParamCases);
    }

    @RequestMapping(path="/findAllJsonParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllJsonParamCase",desc = "findAllJsonParamCase")
    public Result<List<JsonParamCases>> findAllJsonParamCase(){
        List<JsonParamCases> jsonParamCasesList = jsonParamCaseService.findAllJsonParamCase();

        return Result.ok(jsonParamCasesList);
    }


    @RequestMapping(path = "/findJsonParamCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamCaseList",desc = "findJsonParamCaseList")
    @ApiParam(name = "jsonParamCaseQuery",desc = "jsonParamCaseQuery",required = true)
    public Result<List<JsonParamCases>> findJsonParamCaseList(@RequestBody @Valid @NotNull JsonParamCaseQuery jsonParamCaseQuery){
        List<JsonParamCases> jsonParamCasesList = jsonParamCaseService.findJsonParamCaseList(jsonParamCaseQuery);

        return Result.ok(jsonParamCasesList);
    }


    @RequestMapping(path = "/findJsonParamCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamCasePage",desc = "findJsonParamCasePage")
    @ApiParam(name = "jsonParamCaseQuery",desc = "jsonParamCaseQuery",required = true)
    public Result<Pagination<JsonParamCases>> findJsonParamCasePage(@RequestBody @Valid @NotNull JsonParamCaseQuery jsonParamCaseQuery){
        Pagination<JsonParamCases> pagination = jsonParamCaseService.findJsonParamCasePage(jsonParamCaseQuery);

        return Result.ok(pagination);
    }


    @RequestMapping(path = "/findJsonParamCaseListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamCaseListTree",desc = "findJsonParamCaseListTree")
    @ApiParam(name = "jsonParamCaseQuery",desc = "jsonParamCaseQuery",required = true)
    public Result<List<JsonParamCases>> findJsonParamCaseListTree(@RequestBody @Valid @NotNull JsonParamCaseQuery jsonParamCaseQuery){
        List<JsonParamCases> jsonParamCasesList = jsonParamCaseService.findJsonParamCaseListTree(jsonParamCaseQuery);

        return Result.ok(jsonParamCasesList);
    }

}
