package com.doublekit.apibox.apitest.apicase.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apitest.apicase.model.QueryParamCase;
import com.doublekit.apibox.apitest.apicase.model.QueryParamCaseQuery;
import com.doublekit.apibox.apitest.apicase.service.QueryParamCaseService;
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
@RequestMapping("/queryParamCase")
@Api(name = "QueryParamCaseController",desc = "接口用例-查询参数管理")
public class QueryParamCaseController {

    private static Logger logger = LoggerFactory.getLogger(QueryParamCaseController.class);

    @Autowired
    private QueryParamCaseService queryParamCaseService;

    @RequestMapping(path="/createQueryParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "createQueryParamCase",desc = "createQueryParamCase")
    @ApiParam(name = "queryParamCase",desc = "queryParamCase",required = true)
    public Result<String> createQueryParamCase(@RequestBody @NotNull @Valid QueryParamCase queryParamCase){
        String id = queryParamCaseService.createQueryParamCase(queryParamCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateQueryParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateQueryParamCase",desc = "updateQueryParamCase")
    @ApiParam(name = "queryParamCase",desc = "queryParamCase",required = true)
    public Result<Void> updateQueryParamCase(@RequestBody @NotNull @Valid QueryParamCase queryParamCase){
        queryParamCaseService.updateQueryParamCase(queryParamCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteQueryParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteQueryParamCase",desc = "deleteQueryParamCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteQueryParamCase(@NotNull String id){
        queryParamCaseService.deleteQueryParamCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findQueryParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamCase",desc = "findQueryParamCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<QueryParamCase> findQueryParamCase(@NotNull String id){
        QueryParamCase queryParamCase = queryParamCaseService.findQueryParamCase(id);

        return Result.ok(queryParamCase);
    }

    @RequestMapping(path="/findAllQueryParamCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllQueryParamCase",desc = "findAllQueryParamCase")
    public Result<List<QueryParamCase>> findAllQueryParamCase(){
        List<QueryParamCase> queryParamCaseList = queryParamCaseService.findAllQueryParamCase();

        return Result.ok(queryParamCaseList);
    }


    @RequestMapping(path = "/findQueryParamCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamCaseList",desc = "findQueryParamCaseList")
    @ApiParam(name = "queryParamCaseQuery",desc = "queryParamCaseQuery",required = true)
    public Result<List<QueryParamCase>> findQueryParamCaseList(@RequestBody @Valid @NotNull QueryParamCaseQuery queryParamCaseQuery){
        List<QueryParamCase> queryParamCaseList = queryParamCaseService.findQueryParamCaseList(queryParamCaseQuery);

        return Result.ok(queryParamCaseList);
    }


    @RequestMapping(path = "/findQueryParamCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findQueryParamCasePage",desc = "findQueryParamCasePage")
    @ApiParam(name = "queryParamCaseQuery",desc = "queryParamCaseQuery",required = true)
    public Result<Pagination<QueryParamCase>> findQueryParamCasePage(@RequestBody @Valid @NotNull QueryParamCaseQuery queryParamCaseQuery){
        Pagination<QueryParamCase> pagination = queryParamCaseService.findQueryParamCasePage(queryParamCaseQuery);

        return Result.ok(pagination);
    }

}
