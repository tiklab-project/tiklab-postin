package com.doublekit.apibox.apitest.apicase.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apitest.apicase.model.RequestHeaderCase;
import com.doublekit.apibox.apitest.apicase.model.RequestHeaderCaseQuery;
import com.doublekit.apibox.apitest.apicase.service.RequestHeaderCaseService;
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
@RequestMapping("/requestHeaderCase")
@Api(name = "RequestHeaderCaseController",desc = "接口用例-请求头管理")
public class RequestHeaderCaseController {

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderCaseController.class);

    @Autowired
    private RequestHeaderCaseService requestHeaderCaseService;

    @RequestMapping(path="/createRequestHeaderCase",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestHeaderCase",desc = "createRequestHeaderCase")
    @ApiParam(name = "requestHeaderCase",desc = "requestHeaderCase",required = true)
    public Result<String> createRequestHeaderCase(@RequestBody @NotNull @Valid RequestHeaderCase requestHeaderCase){
        String id = requestHeaderCaseService.createRequestHeaderCase(requestHeaderCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestHeaderCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestHeaderCase",desc = "updateRequestHeaderCase")
    @ApiParam(name = "requestHeaderCase",desc = "requestHeaderCase",required = true)
    public Result<Void> updateRequestHeaderCase(@RequestBody @NotNull @Valid RequestHeaderCase requestHeaderCase){
        requestHeaderCaseService.updateRequestHeaderCase(requestHeaderCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestHeaderCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestHeaderCase",desc = "deleteRequestHeaderCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestHeaderCase(@NotNull String id){
        requestHeaderCaseService.deleteRequestHeaderCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestHeaderCase",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderCase",desc = "findRequestHeaderCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestHeaderCase> findRequestHeaderCase(@NotNull String id){
        RequestHeaderCase requestHeaderCase = requestHeaderCaseService.findRequestHeaderCase(id);

        return Result.ok(requestHeaderCase);
    }

    @RequestMapping(path="/findAllRequestHeaderCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestHeaderCase",desc = "findAllRequestHeaderCase")
    public Result<List<RequestHeaderCase>> findAllRequestHeaderCase(){
        List<RequestHeaderCase> requestHeaderCaseList = requestHeaderCaseService.findAllRequestHeaderCase();

        return Result.ok(requestHeaderCaseList);
    }


    @RequestMapping(path = "/findRequestHeaderCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderCaseList",desc = "findRequestHeaderCaseList")
    @ApiParam(name = "requestHeaderCaseQuery",desc = "requestHeaderCaseQuery",required = true)
    public Result<List<RequestHeaderCase>> findRequestHeaderCaseList(@RequestBody @Valid @NotNull RequestHeaderCaseQuery requestHeaderCaseQuery){
        List<RequestHeaderCase> requestHeaderCaseList = requestHeaderCaseService.findRequestHeaderCaseList(requestHeaderCaseQuery);

        return Result.ok(requestHeaderCaseList);
    }


    @RequestMapping(path = "/findRequestHeaderCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderCasePage",desc = "findRequestHeaderCasePage")
    @ApiParam(name = "requestHeaderCaseQuery",desc = "requestHeaderCaseQuery",required = true)
    public Result<Pagination<RequestHeaderCase>> findRequestHeaderCasePage(@RequestBody @Valid @NotNull RequestHeaderCaseQuery requestHeaderCaseQuery){
        Pagination<RequestHeaderCase> pagination = requestHeaderCaseService.findRequestHeaderCasePage(requestHeaderCaseQuery);

        return Result.ok(pagination);
    }

}
