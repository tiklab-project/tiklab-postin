package com.doublekit.apibox.apidef.http.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.apidef.apix.model.ApixQuery;
import com.doublekit.apibox.apidef.http.model.HttpApi;
import com.doublekit.apibox.apidef.http.model.HttpApiQuery;
import com.doublekit.apibox.apidef.http.service.HttpApiService;
import com.doublekit.common.page.Pagination;
import com.doublekit.common.Result;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/http")
@Api(name = "HttpApiController",desc = "接口管理")
public class HttpApiController {

    private static Logger logger = LoggerFactory.getLogger(HttpApiController.class);

    @Autowired
    private HttpApiService httpApiService;



    @RequestMapping(path="/createHttpApi",method = RequestMethod.POST)
    @ApiMethod(name = "createHttpApi",desc = "创建接口")
    @ApiParam(name = "httpApi",desc = "接口DTO",required = true)
    public Result<String> createHttpApi(@RequestBody @NotNull @Valid  HttpApi httpApi){

        String id = httpApiService.createHttpApi(httpApi);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateHttpApi",method = RequestMethod.POST)
    @ApiMethod(name = "updateHttpApi",desc = "更新接口")
    @ApiParam(name = "httpApi",desc = "接口DTO",required = true)
    public Result<Void> updateHttpApi(@RequestBody @NotNull HttpApi httpApi){
        httpApiService.updateHttpApi(httpApi);

        return Result.ok();
    }

    @RequestMapping(path="/deleteHttpApi",method = RequestMethod.POST)
    @ApiMethod(name = "deleteHttpApi",desc = "根据接口ID删除接口")
    @ApiParam(name = "id",desc = "接口ID",required = true)
    public Result<Void> deleteHttpApi(@NotNull String id){
        httpApiService.deleteHttpApi(id);

        return Result.ok();
    }

    @RequestMapping(path="/findHttpApi",method = RequestMethod.POST)
    @ApiMethod(name = "findHttpApi",desc = "根据接口ID查找接口")
    @ApiParam(name = "id",desc = "接口ID",required = true)
    public Result<HttpApi> findHttpApi(@NotNull String id){
        HttpApi httpApi = httpApiService.findHttpApi(id);

        return Result.ok(httpApi);
    }

    @RequestMapping(path="/findAllHttpApi",method = RequestMethod.POST)
    @ApiMethod(name = "findAllHttpApi",desc = "查找所有接口")
    public Result<List<HttpApi>> findAllHttpApi(){
        List<HttpApi> httpApiList = httpApiService.findAllHttpApi();

        return Result.ok(httpApiList);
    }


    @RequestMapping(path = "/findHttpApiList",method = RequestMethod.POST)
    @ApiMethod(name = "findHttpApiList",desc = "根据查询对象查找接口列表")
    @ApiParam(name = "httpApiQuery",desc = "查询对象",required = true)
    public Result<List<HttpApi>> findHttpApiList(@RequestBody @Valid @NotNull HttpApiQuery httpApiQuery){
        List<HttpApi> httpApiList = httpApiService.findHttpApiList(httpApiQuery);

        return Result.ok(httpApiList);
    }


    @RequestMapping(path = "/findHttpApiPage",method = RequestMethod.POST)
    @ApiMethod(name = "findHttpApiPage",desc = "根据查询对象按分页查询接口列表")
    @ApiParam(name = "httpApiQuery",desc = "查询对象",required = true)
    public Result<Pagination<HttpApi>> findHttpApiPage(@RequestBody @Valid @NotNull HttpApiQuery httpApiQuery){
        Pagination<HttpApi> pagination = httpApiService.findHttpApiPage(httpApiQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findHttpApiListByApix",method = RequestMethod.POST)
    @ApiMethod(name = "findHttpApiListByApix",desc = "根据查询对象查找接口列表")
    @ApiParam(name = "apixQuery",desc = "查询对象",required = true)
    public Result<List<HttpApi>> findHttpApiListByApix(@RequestBody @Valid @NotNull ApixQuery apixQuery){
        List<HttpApi> httpApiList = httpApiService.findHttpApiListByApix(apixQuery);

        return Result.ok(httpApiList);
    }


}
