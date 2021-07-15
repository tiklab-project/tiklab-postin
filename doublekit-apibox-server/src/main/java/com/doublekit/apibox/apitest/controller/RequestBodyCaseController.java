package com.doublekit.apibox.apitest.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apitest.model.RequestBodyCase;
import com.doublekit.apibox.apitest.model.RequestBodyCaseQuery;
import com.doublekit.apibox.apitest.service.RequestBodyCaseService;
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


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/requestBodyCase")
@Api(name = "RequestBodyCaseController",desc = "接口用例-请求体管理")
public class RequestBodyCaseController {

    private static Logger logger = LoggerFactory.getLogger(RequestBodyCaseController.class);

    @Autowired
    private RequestBodyCaseService requestBodyCaseService;

    @RequestMapping(path="/createRequestBodyCase",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestBodyCase",desc = "createRequestBodyCase")
    @ApiParam(name = "requestBodyCase",desc = "requestBodyCase",required = true)
    public Result<String> createRequestBodyCase(@RequestBody @NotNull @Valid RequestBodyCase requestBodyCase){
        String id = requestBodyCaseService.createRequestBodyCase(requestBodyCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestBodyCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestBodyCase",desc = "updateRequestBodyCase")
    @ApiParam(name = "requestBodyCase",desc = "requestBodyCase",required = true)
    public Result<Void> updateRequestBodyCase(@RequestBody @NotNull @Valid RequestBodyCase requestBodyCase){
        requestBodyCaseService.updateRequestBodyCase(requestBodyCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestBodyCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestBodyCase",desc = "deleteRequestBodyCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestBodyCase(@NotNull String id){
        requestBodyCaseService.deleteRequestBodyCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestBodyCase",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyCase",desc = "findRequestBodyCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestBodyCase> findRequestBodyCase(@NotNull String id){
        RequestBodyCase requestBodyCase = requestBodyCaseService.findRequestBodyCase(id);

        return Result.ok(requestBodyCase);
    }

    @RequestMapping(path="/findAllRequestBodyCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestBodyCase",desc = "findAllRequestBodyCase")
    public Result<List<RequestBodyCase>> findAllRequestBodyCase(){
        List<RequestBodyCase> requestBodyCaseList = requestBodyCaseService.findAllRequestBodyCase();

        return Result.ok(requestBodyCaseList);
    }


    @RequestMapping(path = "/findRequestBodyCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyCaseList",desc = "findRequestBodyCaseList")
    @ApiParam(name = "requestBodyCaseQuery",desc = "requestBodyCaseQuery",required = true)
    public Result<List<RequestBodyCase>> findRequestBodyCaseList(@RequestBody @Valid @NotNull RequestBodyCaseQuery requestBodyCaseQuery){
        List<RequestBodyCase> requestBodyCaseList = requestBodyCaseService.findRequestBodyCaseList(requestBodyCaseQuery);

        return Result.ok(requestBodyCaseList);
    }


    @RequestMapping(path = "/findRequestBodyCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyCasePage",desc = "findRequestBodyCasePage")
    @ApiParam(name = "requestBodyCaseQuery",desc = "requestBodyCaseQuery",required = true)
    public Result<Pagination<RequestBodyCase>> findRequestBodyCasePage(@RequestBody @Valid @NotNull RequestBodyCaseQuery requestBodyCaseQuery){
        Pagination<RequestBodyCase> pagination = requestBodyCaseService.findRequestBodyCasePage(requestBodyCaseQuery);

        return Result.ok(pagination);
    }

}
