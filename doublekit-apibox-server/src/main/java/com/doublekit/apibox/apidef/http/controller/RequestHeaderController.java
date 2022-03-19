package com.doublekit.apibox.apidef.http.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apidef.http.model.RequestHeader;
import com.doublekit.apibox.apidef.http.model.RequestHeaderQuery;
import com.doublekit.apibox.apidef.http.service.RequestHeaderService;
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
@RequestMapping("/requestHeader")
@Api(name = "RequestHeaderController",desc = "请求头管理")
public class RequestHeaderController {

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderController.class);

    @Autowired
    private RequestHeaderService requestHeaderService;

    @RequestMapping(path="/createRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestHeader",desc = "创建请求头")
    @ApiParam(name = "requestHeader",desc = "请求头DTO",required = true)
    public Result<String> createRequestHeader(@RequestBody @NotNull @Valid RequestHeader requestHeader){
        String id = requestHeaderService.createRequestHeader(requestHeader);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestHeader",desc = "更新请求头")
    @ApiParam(name = "requestHeader",desc = "请求头DTO",required = true)
    public Result<Void> updateRequestHeader(@RequestBody @NotNull @Valid RequestHeader requestHeader){
        requestHeaderService.updateRequestHeader(requestHeader);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestHeader",desc = "根据ID删除请求头")
    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deleteRequestHeader(@NotNull String id){
        requestHeaderService.deleteRequestHeader(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeader",desc = "根据ID查找请求头")
    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<RequestHeader> findRequestHeader(@NotNull String id){
        RequestHeader requestHeader = requestHeaderService.findRequestHeader(id);

        return Result.ok(requestHeader);
    }

    @RequestMapping(path="/findAllRequestHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestHeader",desc = "查找所有请求头列表")
    public Result<List<RequestHeader>> findAllRequestHeader(){
        List<RequestHeader> requestHeaderList = requestHeaderService.findAllRequestHeader();

        return Result.ok(requestHeaderList);
    }


    @RequestMapping(path = "/findRequestHeaderList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderList",desc = "根据查询对象查询请求头列表")
    @ApiParam(name = "requestHeaderQuery",desc = "查询对象",required = true)
    public Result<List<RequestHeader>> findRequestHeaderList(@RequestBody @Valid @NotNull RequestHeaderQuery requestHeaderQuery){
        List<RequestHeader> requestHeaderList = requestHeaderService.findRequestHeaderList(requestHeaderQuery);

        return Result.ok(requestHeaderList);
    }


    @RequestMapping(path = "/findRequestHeaderPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestHeaderPage",desc = "根据查询对象按分页查询请求头列表")
    @ApiParam(name = "requestHeaderQuery",desc = "查询对象",required = true)
    public Result<Pagination<RequestHeader>> findRequestHeaderPage(@RequestBody @Valid @NotNull RequestHeaderQuery requestHeaderQuery){
        Pagination<RequestHeader> pagination = requestHeaderService.findRequestHeaderPage(requestHeaderQuery);

        return Result.ok(pagination);
    }

}
