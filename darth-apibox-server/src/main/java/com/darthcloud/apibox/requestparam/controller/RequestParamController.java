package com.darthcloud.apibox.requestparam.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.requestparam.model.RequestParam;
import com.darthcloud.apibox.requestparam.model.RequestParamQuery;
import com.darthcloud.apibox.requestparam.service.RequestParamService;
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
@RequestMapping("/requestParam")
@Api(name = "RequestParamController",desc = "请求参数管理")
public class RequestParamController {

    private static Logger logger = LoggerFactory.getLogger(RequestParamController.class);

    @Autowired
    private RequestParamService requestParamService;

    @RequestMapping(path="/createRequestParam",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestParam",desc = "创建请求参数")
    @ApiParam(name = "requestParam",desc = "请求参数DTO",required = true)
    public Result<String> createRequestParam(@RequestBody @NotNull @Valid RequestParam requestParam){
        String id = requestParamService.createRequestParam(requestParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestParam",desc = "更新请求参数")
    @ApiParam(name = "requestParam",desc = "请求参数DTO",required = true)
    public Result<Void> updateRequestParam(@RequestBody @NotNull @Valid RequestParam requestParam){
        requestParamService.updateRequestParam(requestParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestParam",desc = "根据ID删除请求参数")
    @ApiParam(name = "id",desc = "请求参数ID",required = true)
    public Result<Void> deleteRequestParam(@NotNull String id){
        requestParamService.deleteRequestParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestParam",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestParam",desc = "根据ID查找请求参数")
    @ApiParam(name = "id",desc = "请求参数ID",required = true)
    public Result<RequestParam> findRequestParam(@NotNull String id){
        RequestParam requestParam = requestParamService.findRequestParam(id);

        return Result.ok(requestParam);
    }

    @RequestMapping(path="/findAllRequestParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestParam",desc = "查找所有请求参数")
    public Result<List<RequestParam>> findAllRequestParam(){
        List<RequestParam> requestParamList = requestParamService.findAllRequestParam();

        return Result.ok(requestParamList);
    }

    @Validator
    @RequestMapping(path = "/findRequestParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestParamList",desc = "根据查询对象查找请求参数列表")
    @ApiParam(name = "requestParamQuery",desc = "查询对象",required = true)
    public Result<List<RequestParam>> findRequestParamList(@RequestBody @Valid @NotNull RequestParamQuery requestParamQuery){
        List<RequestParam> requestParamList = requestParamService.findRequestParamList(requestParamQuery);

        return Result.ok(requestParamList);
    }

    @Validator
    @RequestMapping(path = "/findRequestParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestParamPage",desc = "根据查询对象按分页查找请求参数列表")
    @ApiParam(name = "requestParamQuery",desc = "查询对象",required = true)
    public Result<Pagination<List<RequestParam>>> findRequestParamPage(@RequestBody @Valid @NotNull RequestParamQuery requestParamQuery){
        Pagination<List<RequestParam>> pagination = requestParamService.findRequestParamPage(requestParamQuery);

        return Result.ok(pagination);
    }

}
