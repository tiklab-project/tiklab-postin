package io.tiklab.postin.api.http.definition.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.definition.model.ResponseHeaders;
import io.tiklab.postin.api.http.definition.model.ResponseHeaderQuery;
import io.tiklab.postin.api.http.definition.service.ResponseHeaderService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
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
 * http协议 定义
 * 响应头 控制器
 */
@RestController
@RequestMapping("/responseHeader")
@Api(name = "ResponseHeaderController",desc = "响应头管理")
public class ResponseHeaderController {

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderController.class);

    @Autowired
    private ResponseHeaderService responseHeaderService;

    @RequestMapping(path="/createResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "createResponseHeader",desc = "创建响应头")
    @ApiParam(name = "responseHeader",desc = "响应头DTO",required = true)
    public Result<String> createResponseHeader(@RequestBody @NotNull @Valid ResponseHeaders responseHeaders){
        String id = responseHeaderService.createResponseHeader(responseHeaders);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseHeader",desc = "更新响应头")
    @ApiParam(name = "responseHeader",desc = "响应头DTO",required = true)
    public Result<Void> updateResponseHeader(@RequestBody @NotNull @Valid ResponseHeaders responseHeaders){
        responseHeaderService.updateResponseHeader(responseHeaders);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "deleteResponseHeader",desc = "根据ID删除响应头")
    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deleteResponseHeader(@NotNull String id){
        responseHeaderService.deleteResponseHeader(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeader",desc = "根据ID查找响应头")
    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<ResponseHeaders> findResponseHeader(@NotNull String id){
        ResponseHeaders responseHeaders = responseHeaderService.findResponseHeader(id);

        return Result.ok(responseHeaders);
    }

    @RequestMapping(path="/findAllResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseHeader",desc = "查找所有响应头")
    public Result<List<ResponseHeaders>> findAllResponseHeader(){
        List<ResponseHeaders> responseHeadersList = responseHeaderService.findAllResponseHeader();

        return Result.ok(responseHeadersList);
    }


    @RequestMapping(path = "/findResponseHeaderList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeaderList",desc = "根据查询条件查询响应头列表")
    @ApiParam(name = "responseHeaderQuery",desc = "查询条件",required = true)
    public Result<List<ResponseHeaders>> findResponseHeaderList(@RequestBody @Valid @NotNull ResponseHeaderQuery responseHeaderQuery){
        List<ResponseHeaders> responseHeadersList = responseHeaderService.findResponseHeaderList(responseHeaderQuery);

        return Result.ok(responseHeadersList);
    }


    @RequestMapping(path = "/findResponseHeaderPage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeaderPage",desc = "根据查询条件按分页查询响应头列表")
    @ApiParam(name = "responseHeaderQuery",desc = "查询条件",required = true)
    public Result<Pagination<ResponseHeaders>> findResponseHeaderPage(@RequestBody @Valid @NotNull ResponseHeaderQuery responseHeaderQuery){
        Pagination<ResponseHeaders> pagination = responseHeaderService.findResponseHeaderPage(responseHeaderQuery);

        return Result.ok(pagination);
    }

}
