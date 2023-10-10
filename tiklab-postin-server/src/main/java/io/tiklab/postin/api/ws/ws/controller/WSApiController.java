package io.tiklab.postin.api.ws.ws.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.api.ws.ws.model.WSApi;
import io.tiklab.postin.api.ws.ws.model.WSApiQuery;
import io.tiklab.postin.api.ws.ws.service.WSApiService;
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
 * ws接口  控制器
 */
@RestController
@RequestMapping("/ws")
@Api(name = "WSApiController",desc = "接口管理")
public class WSApiController {

    private static Logger logger = LoggerFactory.getLogger(WSApiController.class);

    @Autowired
    private WSApiService wsApiService;



    @RequestMapping(path="/createWSApi",method = RequestMethod.POST)
    @ApiMethod(name = "createWSApi",desc = "创建接口")
    @ApiParam(name = "wsApi",desc = "接口DTO",required = true)
    public Result<String> createWSApi(@RequestBody @NotNull @Valid WSApi wsApi){

        String id = wsApiService.createWSApi(wsApi);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWSApi",method = RequestMethod.POST)
    @ApiMethod(name = "updateWSApi",desc = "更新接口")
    @ApiParam(name = "wsApi",desc = "接口DTO",required = true)
    public Result<Void> updateWSApi(@RequestBody @NotNull WSApi wsApi){
        wsApiService.updateWSApi(wsApi);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWSApi",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWSApi",desc = "根据接口ID删除接口")
    @ApiParam(name = "id",desc = "接口ID",required = true)
    public Result<Void> deleteWSApi(@NotNull String id){
        wsApiService.deleteWSApi(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWSApi",method = RequestMethod.POST)
    @ApiMethod(name = "findWSApi",desc = "根据接口ID查找接口")
    @ApiParam(name = "id",desc = "接口ID",required = true)
    public Result<WSApi> findWSApi(@NotNull String id){
        WSApi wsApi = wsApiService.findWSApi(id);

        return Result.ok(wsApi);
    }

    @RequestMapping(path="/findAllWSApi",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWSApi",desc = "查找所有接口")
    public Result<List<WSApi>> findAllWSApi(){
        List<WSApi> wsApiList = wsApiService.findAllWSApi();

        return Result.ok(wsApiList);
    }


    @RequestMapping(path = "/findWSApiList",method = RequestMethod.POST)
    @ApiMethod(name = "findWSApiList",desc = "根据查询对象查找接口列表")
    @ApiParam(name = "wsApiQuery",desc = "查询对象",required = true)
    public Result<List<WSApi>> findWSApiList(@RequestBody @Valid @NotNull WSApiQuery wsApiQuery){
        List<WSApi> wsApiList = wsApiService.findWSApiList(wsApiQuery);

        return Result.ok(wsApiList);
    }


    @RequestMapping(path = "/findWSApiPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWSApiPage",desc = "根据查询对象按分页查询接口列表")
    @ApiParam(name = "wsApiQuery",desc = "查询对象",required = true)
    public Result<Pagination<WSApi>> findWSApiPage(@RequestBody @Valid @NotNull WSApiQuery wsApiQuery){
        Pagination<WSApi> pagination = wsApiService.findWSApiPage(wsApiQuery);

        return Result.ok(pagination);
    }


}
