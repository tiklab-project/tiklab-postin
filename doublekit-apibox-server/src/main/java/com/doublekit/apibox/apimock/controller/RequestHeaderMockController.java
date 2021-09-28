package com.doublekit.apibox.apimock.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apimock.model.RequestHeaderMock;
import com.doublekit.apibox.apimock.model.RequestHeaderMockQuery;
import com.doublekit.apibox.apimock.service.RequestHeaderMockService;
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
@RequestMapping("/requestHeaderMock")
@Api(name = "RequestHeaderMockController",desc = "接口mock-请求头部管理")
public class RequestHeaderMockController {

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderMockController.class);

    @Autowired
    private RequestHeaderMockService requestHeaderMockService;

    @RequestMapping(path="/createRequestHeaderMock",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestHeaderMock",desc = "createRequestHeaderMock")
    @ApiParam(name = "requestHeaderMock",desc = "requestHeaderMock",required = true)
    public Result<String> createRequestHeaderMock(@RequestBody @NotNull @Valid RequestHeaderMock requestHeaderMock){
        String id = requestHeaderMockService.createRequestHeaderMock(requestHeaderMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestHeaderMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestHeaderMock",desc = "updateRequestHeaderMock")
    @ApiParam(name = "requestHeaderMock",desc = "requestHeaderMock",required = true)
    public Result<Void> updateRequestHeaderMock(@RequestBody @NotNull @Valid RequestHeaderMock requestHeaderMock){
        requestHeaderMockService.updateRequestHeaderMock(requestHeaderMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestHeaderMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestHeaderMock",desc = "deleteRequestHeaderMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestHeaderMock(@NotNull String id){
        requestHeaderMockService.deleteRequestHeaderMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestHeaderMock",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderMock",desc = "findRequestHeaderMock")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestHeaderMock> findRequestHeaderMock(@NotNull String id){
        RequestHeaderMock requestHeaderMock = requestHeaderMockService.findRequestHeaderMock(id);

        return Result.ok(requestHeaderMock);
    }

    @RequestMapping(path="/findAllRequestHeaderMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestHeaderMock",desc = "findAllRequestHeaderMock")
    public Result<List<RequestHeaderMock>> findAllRequestHeaderMock(){
        List<RequestHeaderMock> requestHeaderMockList = requestHeaderMockService.findAllRequestHeaderMock();

        return Result.ok(requestHeaderMockList);
    }


    @RequestMapping(path = "/findRequestHeaderMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderMockList",desc = "findRequestHeaderMockList")
    @ApiParam(name = "requestHeaderMockQuery",desc = "requestHeaderMockQuery",required = true)
    public Result<List<RequestHeaderMock>> findRequestHeaderMockList(@RequestBody @Valid @NotNull RequestHeaderMockQuery requestHeaderMockQuery){
        List<RequestHeaderMock> requestHeaderMockList = requestHeaderMockService.findRequestHeaderMockList(requestHeaderMockQuery);

        return Result.ok(requestHeaderMockList);
    }


    @RequestMapping(path = "/findRequestHeaderMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderMockPage",desc = "findRequestHeaderMockPage")
    @ApiParam(name = "requestHeaderMockQuery",desc = "requestHeaderMockQuery",required = true)
    public Result<Pagination<RequestHeaderMock>> findRequestHeaderMockPage(@RequestBody @Valid @NotNull RequestHeaderMockQuery requestHeaderMockQuery){
        Pagination<RequestHeaderMock> pagination = requestHeaderMockService.findRequestHeaderMockPage(requestHeaderMockQuery);

        return Result.ok(pagination);
    }

}