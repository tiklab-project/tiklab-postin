package com.doublekit.apibox.apidef.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apidef.model.ResponseResult;
import com.doublekit.apibox.apidef.model.ResponseResultQuery;
import com.doublekit.apibox.apidef.service.ResponseResultService;
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
@RequestMapping("/responseResult")
@Api(name = "ResponseResultController",desc = "返回结果管理")
public class ResponseResultController {

    private static Logger logger = LoggerFactory.getLogger(ResponseResultController.class);

    @Autowired
    private ResponseResultService responseResultService;

    @RequestMapping(path="/createResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "createResponseResult",desc = "createResponseResult")
    @ApiParam(name = "responseResult",desc = "responseResult",required = true)
    public Result<String> createResponseResult(@RequestBody @NotNull @Valid ResponseResult responseResult){
        String id = responseResultService.createResponseResult(responseResult);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseResult",desc = "updateResponseResult")
    @ApiParam(name = "responseResult",desc = "responseResult",required = true)
    public Result<Void> updateResponseResult(@RequestBody @NotNull @Valid ResponseResult responseResult){
        responseResultService.updateResponseResult(responseResult);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "deleteResponseResult",desc = "deleteResponseResult")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteResponseResult(@NotNull String id){
        responseResultService.deleteResponseResult(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResult",desc = "findResponseResult")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ResponseResult> findResponseResult(@NotNull String id){
        ResponseResult responseResult = responseResultService.findResponseResult(id);

        return Result.ok(responseResult);
    }

    @RequestMapping(path="/findAllResponseResult",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseResult",desc = "findAllResponseResult")
    public Result<List<ResponseResult>> findAllResponseResult(){
        List<ResponseResult> responseResultList = responseResultService.findAllResponseResult();

        return Result.ok(responseResultList);
    }


    @RequestMapping(path = "/findResponseResultList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResultList",desc = "findResponseResultList")
    @ApiParam(name = "responseResultQuery",desc = "responseResultQuery",required = true)
    public Result<List<ResponseResult>> findResponseResultList(@RequestBody @Valid @NotNull ResponseResultQuery responseResultQuery){
        List<ResponseResult> responseResultList = responseResultService.findResponseResultList(responseResultQuery);

        return Result.ok(responseResultList);
    }


    @RequestMapping(path = "/findResponseResultPage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseResultPage",desc = "findResponseResultPage")
    @ApiParam(name = "responseResultQuery",desc = "responseResultQuery",required = true)
    public Result<Pagination<ResponseResult>> findResponseResultPage(@RequestBody @Valid @NotNull ResponseResultQuery responseResultQuery){
        Pagination<ResponseResult> pagination = responseResultService.findResponseResultPage(responseResultQuery);

        return Result.ok(pagination);
    }

}
