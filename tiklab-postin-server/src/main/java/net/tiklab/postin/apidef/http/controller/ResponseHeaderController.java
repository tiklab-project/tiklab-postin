package net.tiklab.postin.apidef.http.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.apidef.http.model.ResponseHeader;
import net.tiklab.postin.apidef.http.model.ResponseHeaderQuery;
import net.tiklab.postin.apidef.http.service.ResponseHeaderService;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
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
@RequestMapping("/responseHeader")
@Api(name = "ResponseHeaderController",desc = "响应头管理")
public class ResponseHeaderController {

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderController.class);

    @Autowired
    private ResponseHeaderService responseHeaderService;

    @RequestMapping(path="/createResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "createResponseHeader",desc = "创建响应头")
    @ApiParam(name = "responseHeader",desc = "响应头DTO",required = true)
    public Result<String> createResponseHeader(@RequestBody @NotNull @Valid ResponseHeader responseHeader){
        String id = responseHeaderService.createResponseHeader(responseHeader);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseHeader",desc = "更新响应头")
    @ApiParam(name = "responseHeader",desc = "响应头DTO",required = true)
    public Result<Void> updateResponseHeader(@RequestBody @NotNull @Valid ResponseHeader responseHeader){
        responseHeaderService.updateResponseHeader(responseHeader);

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
    public Result<ResponseHeader> findResponseHeader(@NotNull String id){
        ResponseHeader responseHeader = responseHeaderService.findResponseHeader(id);

        return Result.ok(responseHeader);
    }

    @RequestMapping(path="/findAllResponseHeader",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseHeader",desc = "查找所有响应头")
    public Result<List<ResponseHeader>> findAllResponseHeader(){
        List<ResponseHeader> responseHeaderList = responseHeaderService.findAllResponseHeader();

        return Result.ok(responseHeaderList);
    }


    @RequestMapping(path = "/findResponseHeaderList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeaderList",desc = "根据查询条件查询响应头列表")
    @ApiParam(name = "responseHeaderQuery",desc = "查询条件",required = true)
    public Result<List<ResponseHeader>> findResponseHeaderList(@RequestBody @Valid @NotNull ResponseHeaderQuery responseHeaderQuery){
        List<ResponseHeader> responseHeaderList = responseHeaderService.findResponseHeaderList(responseHeaderQuery);

        return Result.ok(responseHeaderList);
    }


    @RequestMapping(path = "/findResponseHeaderPage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseHeaderPage",desc = "根据查询条件按分页查询响应头列表")
    @ApiParam(name = "responseHeaderQuery",desc = "查询条件",required = true)
    public Result<Pagination<ResponseHeader>> findResponseHeaderPage(@RequestBody @Valid @NotNull ResponseHeaderQuery responseHeaderQuery){
        Pagination<ResponseHeader> pagination = responseHeaderService.findResponseHeaderPage(responseHeaderQuery);

        return Result.ok(pagination);
    }

}
