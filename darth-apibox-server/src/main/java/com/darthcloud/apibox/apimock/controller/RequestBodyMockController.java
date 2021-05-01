package com.darthcloud.apibox.apimock.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.apimock.model.RequestBodyMock;
import com.darthcloud.apibox.apimock.model.RequestBodyMockQuery;
import com.darthcloud.apibox.apimock.service.RequestBodyMockService;
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
@RequestMapping("/requestBodyMock")
@Api(name = "RequestBodyMockController",desc = "接口mock-请求体管理")
public class RequestBodyMockController {

    private static Logger logger = LoggerFactory.getLogger(RequestBodyMockController.class);

    @Autowired
    private RequestBodyMockService requestBodyMockService;

    @RequestMapping(path="/createRequestBodyMock",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestBodyMock",desc = "createRequestBodyMock")
    @ApiParam(name = "requestBodyMock",desc = "requestBodyMock",required = true)
    public Result<String> createRequestBodyMock(@RequestBody @NotNull @Valid RequestBodyMock requestBodyMock){
        String id = requestBodyMockService.createRequestBodyMock(requestBodyMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestBodyMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestBodyMock",desc = "updateRequestBodyMock")
    @ApiParam(name = "requestBodyMock",desc = "requestBodyMock",required = true)
    public Result<Void> updateRequestBodyMock(@RequestBody @NotNull @Valid RequestBodyMock requestBodyMock){
        requestBodyMockService.updateRequestBodyMock(requestBodyMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestBodyMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestBodyMock",desc = "deleteRequestBodyMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestBodyMock(@NotNull String id){
        requestBodyMockService.deleteRequestBodyMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestBodyMock",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyMock",desc = "findRequestBodyMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestBodyMock> findRequestBodyMock(@NotNull String id){
        RequestBodyMock requestBodyMock = requestBodyMockService.findRequestBodyMock(id);

        return Result.ok(requestBodyMock);
    }

    @RequestMapping(path="/findAllRequestBodyMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestBodyMock",desc = "findAllRequestBodyMock")
    public Result<List<RequestBodyMock>> findAllRequestBodyMock(){
        List<RequestBodyMock> requestBodyMockList = requestBodyMockService.findAllRequestBodyMock();

        return Result.ok(requestBodyMockList);
    }

    @Validator
    @RequestMapping(path = "/findRequestBodyMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyMockList",desc = "findRequestBodyMockList")
    @ApiParam(name = "requestBodyMockQuery",desc = "requestBodyMockQuery",required = true)
    public Result<List<RequestBodyMock>> findRequestBodyMockList(@RequestBody @Valid @NotNull RequestBodyMockQuery requestBodyMockQuery){
        List<RequestBodyMock> requestBodyMockList = requestBodyMockService.findRequestBodyMockList(requestBodyMockQuery);

        return Result.ok(requestBodyMockList);
    }

    @Validator
    @RequestMapping(path = "/findRequestBodyMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyMockPage",desc = "findRequestBodyMockPage")
    @ApiParam(name = "requestBodyMockQuery",desc = "requestBodyMockQuery",required = true)
    public Result<Pagination<RequestBodyMock>> findRequestBodyMockPage(@RequestBody @Valid @NotNull RequestBodyMockQuery requestBodyMockQuery){
        Pagination<RequestBodyMock> pagination = requestBodyMockService.findRequestBodyMockPage(requestBodyMockQuery);

        return Result.ok(pagination);
    }

}
