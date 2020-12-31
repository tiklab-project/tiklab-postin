package com.darthcloud.apibox.responseresult.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.responseresult.model.ResponseResult;
import com.darthcloud.apibox.responseresult.model.ResponseResultQuery;
import com.darthcloud.apibox.responseresult.service.ResponseResultService;
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
@RequestMapping("/responseResult")
@Api(name = "ResponseResultController",desc = "响应结果管理")
public class ResponseResultController {

    private static Logger logger = LoggerFactory.getLogger(ResponseResultController.class);

    @Autowired
    private ResponseResultService responseResultService;

    @RequestMapping(path="/createResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "createResponseResult",desc = "创建响应结果")
    @ApiParam(name = "responseResult",desc = "响应结果DTO",required = true)
    public Result<String> createResponseResult(@RequestBody @NotNull @Valid ResponseResult responseResult){
        String id = responseResultService.createResponseResult(responseResult);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseResult",desc = "更新响应结果")
    @ApiParam(name = "responseResult",desc = "响应结果DTO",required = true)
    public Result<Void> updateResponseResult(@RequestBody @NotNull @Valid ResponseResult responseResult){
        responseResultService.updateResponseResult(responseResult);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "deleteResponseResult",desc = "根据ID删除响应结果")
    @ApiParam(name = "id",desc = "唯一ID",required = true)
    public Result<Void> deleteResponseResult(@NotNull String id){
        responseResultService.deleteResponseResult(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResult",desc = "根据ID查找响应结果")
    @ApiParam(name = "id",desc = "唯一ID",required = true)
    public Result<ResponseResult> findResponseResult(@NotNull String id){
        ResponseResult responseResult = responseResultService.findResponseResult(id);

        return Result.ok(responseResult);
    }

    @RequestMapping(path="/findAllResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseResult",desc = "查找所有响应结果")
    public Result<List<ResponseResult>> findAllResponseResult(){
        List<ResponseResult> responseResultList = responseResultService.findAllResponseResult();

        return Result.ok(responseResultList);
    }

    @Validator
    @RequestMapping(path = "/findResponseResultList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResultList",desc = "根据查询对象查询响应结果列表")
    @ApiParam(name = "responseResultQuery",desc = "查询对象",required = true)
    public Result<List<ResponseResult>> findResponseResultList(@RequestBody @Valid @NotNull ResponseResultQuery responseResultQuery){
        List<ResponseResult> responseResultList = responseResultService.findResponseResultList(responseResultQuery);

        return Result.ok(responseResultList);
    }

    @Validator
    @RequestMapping(path = "/findResponseResultPage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResultPage",desc = "根据查询对象按分页查询响应结果列表")
    @ApiParam(name = "responseResultQuery",desc = "查询对象",required = true)
    public Result<Pagination<List<ResponseResult>>> findResponseResultPage(@RequestBody @Valid @NotNull ResponseResultQuery responseResultQuery){
        Pagination<List<ResponseResult>> pagination = responseResultService.findResponseResultPage(responseResultQuery);

        return Result.ok(pagination);
    }

}
