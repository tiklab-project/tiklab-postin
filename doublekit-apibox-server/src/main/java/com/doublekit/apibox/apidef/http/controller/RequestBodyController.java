package com.doublekit.apibox.apidef.http.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import com.doublekit.apibox.apidef.http.model.RequestBodyEx;
import com.doublekit.apibox.apidef.http.model.RequestBodyExQuery;
import com.doublekit.apibox.apidef.http.service.RequestBodyService;
import com.doublekit.core.page.Pagination;
import com.doublekit.core.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/requestBody")
@Api(name = "RequestBodyController",desc = "请求体管理")
public class RequestBodyController {

    private static Logger logger = LoggerFactory.getLogger(RequestBodyController.class);

    @Autowired
    private RequestBodyService requestBodyService;

    @RequestMapping(path="/createRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestBody",desc = "createRequestBody")
    @ApiParam(name = "requestBody",desc = "requestBody",required = true)
    public Result<String> createRequestBody(@RequestBody @NotNull @Valid RequestBodyEx requestBody){
        String id = requestBodyService.createRequestBody(requestBody);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestBody",desc = "updateRequestBody")
    @ApiParam(name = "requestBody",desc = "requestBody",required = true)
    public Result<Void> updateRequestBody(@RequestBody @NotNull @Valid RequestBodyEx requestBody){
        requestBodyService.updateRequestBody(requestBody);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestBody",desc = "deleteRequestBody")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestBody(@NotNull String id){
        requestBodyService.deleteRequestBody(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBody",desc = "findRequestBody")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestBodyEx> findRequestBody(@NotNull String id){
        RequestBodyEx requestBody = requestBodyService.findRequestBody(id);

        return Result.ok(requestBody);
    }

    @RequestMapping(path="/findAllRequestBody",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestBody",desc = "findAllRequestBody")
    public Result<List<RequestBodyEx>> findAllRequestBody(){
        List<RequestBodyEx> requestBodyList = requestBodyService.findAllRequestBody();

        return Result.ok(requestBodyList);
    }


    @RequestMapping(path = "/findRequestBodyList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyList",desc = "findRequestBodyList")
    @ApiParam(name = "requestBodyQuery",desc = "requestBodyQuery",required = true)
    public Result<List<RequestBodyEx>> findRequestBodyList(@RequestBody @Valid @NotNull RequestBodyExQuery requestBodyQuery){
        List<RequestBodyEx> requestBodyList = requestBodyService.findRequestBodyList(requestBodyQuery);

        return Result.ok(requestBodyList);
    }


    @RequestMapping(path = "/findRequestBodyPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestBodyPage",desc = "findRequestBodyPage")
    @ApiParam(name = "requestBodyQuery",desc = "requestBodyQuery",required = true)
    public Result<Pagination<RequestBodyEx>> findRequestBodyPage(@RequestBody @Valid @NotNull RequestBodyExQuery requestBodyQuery){
        Pagination<RequestBodyEx> pagination = requestBodyService.findRequestBodyPage(requestBodyQuery);

        return Result.ok(pagination);
    }

}
