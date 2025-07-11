package io.tiklab.postin.api.http.definition.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.http.definition.model.JsonResponse;
import io.tiklab.postin.api.http.definition.model.JsonResponseQuery;
import io.tiklab.postin.api.http.definition.service.JsonResponseService;
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
 * 响应中json 控制器
 */
@RestController
@RequestMapping("/jsonResponse")
//@Api(name = "JsonResponseController",desc = "json响应结果管理")
public class JsonResponseController {

    private static Logger logger = LoggerFactory.getLogger(JsonResponseController.class);

    @Autowired
    private JsonResponseService jsonResponseService;

    @RequestMapping(path="/createJsonResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "createJsonResponse",desc = "创建json响应结果")
//    @ApiParam(name = "jsonResponse",desc = "json响应结果DTO",required = true)
    public Result<String> createJsonResponse(@RequestBody @NotNull @Valid JsonResponse jsonResponse){
        String id = jsonResponseService.createJsonResponse(jsonResponse);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "updateJsonResponse",desc = "更新json响应结果")
//    @ApiParam(name = "jsonResponse",desc = "json响应结果DTO",required = true)
    public Result<Void> updateJsonResponse(@RequestBody @NotNull @Valid JsonResponse jsonResponse){
        jsonResponseService.updateJsonResponse(jsonResponse);

        return Result.ok();
    }

    @RequestMapping(path="/deleteJsonResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteJsonResponse",desc = "根据ID删除json响应结果")
//    @ApiParam(name = "id",desc = "唯一ID",required = true)
    public Result<Void> deleteJsonResponse(@NotNull String id){
        jsonResponseService.deleteJsonResponse(id);

        return Result.ok();
    }

    @RequestMapping(path="/findJsonResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonResponse",desc = "根据ID查找json响应结果")
//    @ApiParam(name = "id",desc = "唯一ID",required = true)
    public Result<JsonResponse> findJsonResponse(@NotNull String id){
        JsonResponse jsonResponse = jsonResponseService.findJsonResponse(id);

        return Result.ok(jsonResponse);
    }

    @RequestMapping(path="/findAllJsonResponse",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllJsonResponse",desc = "查找所有json响应结果")
    public Result<List<JsonResponse>> findAllJsonResponse(){
        List<JsonResponse> jsonResponseList = jsonResponseService.findAllJsonResponse();

        return Result.ok(jsonResponseList);
    }


    @RequestMapping(path = "/findJsonResponseList",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonResponseList",desc = "根据查询对象查询json响应结果列表")
//    @ApiParam(name = "jsonResponseQuery",desc = "查询对象",required = true)
    public Result<List<JsonResponse>> findJsonResponseList(@RequestBody @Valid @NotNull JsonResponseQuery jsonResponseQuery){
        List<JsonResponse> jsonResponseList = jsonResponseService.findJsonResponseList(jsonResponseQuery);

        return Result.ok(jsonResponseList);
    }


    @RequestMapping(path = "/findJsonResponsePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonResponsePage",desc = "根据查询对象按分页查询json响应结果列表")
//    @ApiParam(name = "jsonResponseQuery",desc = "查询对象",required = true)
    public Result<Pagination<JsonResponse>> findJsonResponsePage(@RequestBody @Valid @NotNull JsonResponseQuery jsonResponseQuery){
        Pagination<JsonResponse> pagination = jsonResponseService.findJsonResponsePage(jsonResponseQuery);

        return Result.ok(pagination);
    }


    @RequestMapping(path = "/findJsonResponseListTree",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonResponseListTree",desc = "根据查询对象查询json响应结果列表树")
//    @ApiParam(name = "jsonResponseQuery",desc = "查询对象",required = true)
    public Result<List<JsonResponse>> findJsonResponseListTree(@RequestBody @Valid @NotNull JsonResponseQuery jsonResponseQuery){
        List<JsonResponse> jsonResponseList = jsonResponseService.findJsonResponseListTree(jsonResponseQuery);

        return Result.ok(jsonResponseList);
    }

}
