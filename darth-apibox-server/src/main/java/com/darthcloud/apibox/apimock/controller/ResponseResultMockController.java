package com.darthcloud.apibox.apimock.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.apimock.model.ResponseResultMock;
import com.darthcloud.apibox.apimock.model.ResponseResultMockQuery;
import com.darthcloud.apibox.apimock.service.ResponseResultMockService;
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
@RequestMapping("/responseResultMock")
@Api(name = "ResponseResultMockController",desc = "接口mock-返回结果管理")
public class ResponseResultMockController {

    private static Logger logger = LoggerFactory.getLogger(ResponseResultMockController.class);

    @Autowired
    private ResponseResultMockService responseResultMockService;

    @RequestMapping(path="/createResponseResultMock",method = RequestMethod.POST)
    @ApiMethod(name = "createResponseResultMock",desc = "createResponseResultMock")
    @ApiParam(name = "responseResultMock",desc = "responseResultMock",required = true)
    public Result<String> createResponseResultMock(@RequestBody @NotNull @Valid ResponseResultMock responseResultMock){
        String id = responseResultMockService.createResponseResultMock(responseResultMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseResultMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseResultMock",desc = "updateResponseResultMock")
    @ApiParam(name = "responseResultMock",desc = "responseResultMock",required = true)
    public Result<Void> updateResponseResultMock(@RequestBody @NotNull @Valid ResponseResultMock responseResultMock){
        responseResultMockService.updateResponseResultMock(responseResultMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseResultMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteResponseResultMock",desc = "deleteResponseResultMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteResponseResultMock(@NotNull String id){
        responseResultMockService.deleteResponseResultMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseResultMock",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResultMock",desc = "findResponseResultMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ResponseResultMock> findResponseResultMock(@NotNull String id){
        ResponseResultMock responseResultMock = responseResultMockService.findResponseResultMock(id);

        return Result.ok(responseResultMock);
    }

    @RequestMapping(path="/findAllResponseResultMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseResultMock",desc = "findAllResponseResultMock")
    public Result<List<ResponseResultMock>> findAllResponseResultMock(){
        List<ResponseResultMock> responseResultMockList = responseResultMockService.findAllResponseResultMock();

        return Result.ok(responseResultMockList);
    }

    @Validator
    @RequestMapping(path = "/findResponseResultMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResultMockList",desc = "findResponseResultMockList")
    @ApiParam(name = "responseResultMockQuery",desc = "responseResultMockQuery",required = true)
    public Result<List<ResponseResultMock>> findResponseResultMockList(@RequestBody @Valid @NotNull ResponseResultMockQuery responseResultMockQuery){
        List<ResponseResultMock> responseResultMockList = responseResultMockService.findResponseResultMockList(responseResultMockQuery);

        return Result.ok(responseResultMockList);
    }

    @Validator
    @RequestMapping(path = "/findResponseResultMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResultMockPage",desc = "findResponseResultMockPage")
    @ApiParam(name = "responseResultMockQuery",desc = "responseResultMockQuery",required = true)
    public Result<Pagination<List<ResponseResultMock>>> findResponseResultMockPage(@RequestBody @Valid @NotNull ResponseResultMockQuery responseResultMockQuery){
        Pagination<List<ResponseResultMock>> pagination = responseResultMockService.findResponseResultMockPage(responseResultMockQuery);

        return Result.ok(pagination);
    }

}