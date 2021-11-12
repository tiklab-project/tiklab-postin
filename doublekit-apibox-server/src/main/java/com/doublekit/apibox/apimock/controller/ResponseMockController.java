package com.doublekit.apibox.apimock.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apimock.model.ResponseMock;
import com.doublekit.apibox.apimock.model.ResponseMockQuery;
import com.doublekit.apibox.apimock.service.ResponseMockService;
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
@RequestMapping("/responseMock")
@Api(name = "ResponseMockController",desc = "接口mock-响应管理")
public class ResponseMockController {

    private static Logger logger = LoggerFactory.getLogger(ResponseMockController.class);

    @Autowired
    private ResponseMockService responseMockService;

    @RequestMapping(path="/createResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "createResponseMock",desc = "createResponseMock")
    @ApiParam(name = "responseMock",desc = "responseMock",required = true)
    public Result<String> createResponseMock(@RequestBody @NotNull @Valid ResponseMock responseMock){
        String id = responseMockService.createResponseMock(responseMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseMock",desc = "updateResponseMock")
    @ApiParam(name = "responseMock",desc = "responseMock",required = true)
    public Result<Void> updateResponseMock(@RequestBody @NotNull @Valid ResponseMock responseMock){
        responseMockService.updateResponseMock(responseMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteResponseMock",desc = "deleteResponseMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteResponseMock(@NotNull String id){
        responseMockService.deleteResponseMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseMock",desc = "findResponseMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ResponseMock> findResponseMock(@NotNull String id){
        ResponseMock responseMock = responseMockService.findResponseMock(id);

        return Result.ok(responseMock);
    }

    @RequestMapping(path="/findAllResponseMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseMock",desc = "findAllResponseMock")
    public Result<List<ResponseMock>> findAllResponseMock(){
        List<ResponseMock> responseMockList = responseMockService.findAllResponseMock();

        return Result.ok(responseMockList);
    }


    @RequestMapping(path = "/findResponseMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseMockList",desc = "findResponseMockList")
    @ApiParam(name = "responseMockQuery",desc = "responseMockQuery",required = true)
    public Result<List<ResponseMock>> findResponseMockList(@RequestBody @Valid @NotNull ResponseMockQuery responseMockQuery){
        List<ResponseMock> responseMockList = responseMockService.findResponseMockList(responseMockQuery);

        return Result.ok(responseMockList);
    }


    @RequestMapping(path = "/findResponseMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseMockPage",desc = "findResponseMockPage")
    @ApiParam(name = "responseMockQuery",desc = "responseMockQuery",required = true)
    public Result<Pagination<ResponseMock>> findResponseMockPage(@RequestBody @Valid @NotNull ResponseMockQuery responseMockQuery){
        Pagination<ResponseMock> pagination = responseMockService.findResponseMockPage(responseMockQuery);

        return Result.ok(pagination);
    }

}
