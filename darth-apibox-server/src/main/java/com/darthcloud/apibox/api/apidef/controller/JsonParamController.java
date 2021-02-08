package com.darthcloud.apibox.api.apidef.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.api.apidef.model.JsonParam;
import com.darthcloud.apibox.api.apidef.model.JsonParamQuery;
import com.darthcloud.apibox.api.apidef.service.JsonParamService;
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
@RequestMapping("/jsonParam")
@Api(name = "JsonParamController",desc = "json请求参数管理")
public class JsonParamController {

    private static Logger logger = LoggerFactory.getLogger(JsonParamController.class);

    @Autowired
    private JsonParamService jsonParamService;

    @RequestMapping(path="/createJsonParam",method = RequestMethod.POST)
    @ApiMethod(name = "createJsonParam",desc = "创建json请求参数")
    @ApiParam(name = "jsonParam",desc = "json请求参数DTO",required = true)
    public Result<String> createJsonParam(@RequestBody @NotNull @Valid JsonParam jsonParam){
        String id = jsonParamService.createJsonParam(jsonParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateJsonParam",desc = "更新json请求参数")
    @ApiParam(name = "jsonParam",desc = "json请求参数DTO",required = true)
    public Result<Void> updateJsonParam(@RequestBody @NotNull @Valid JsonParam jsonParam){
        jsonParamService.updateJsonParam(jsonParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteJsonParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteJsonParam",desc = "根据ID删除json请求参数")
    @ApiParam(name = "id",desc = "json请求参数ID",required = true)
    public Result<Void> deleteJsonParam(@NotNull String id){
        jsonParamService.deleteJsonParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findJsonParam",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParam",desc = "根据ID查找json请求参数")
    @ApiParam(name = "id",desc = "json请求参数ID",required = true)
    public Result<JsonParam> findJsonParam(@NotNull String id){
        JsonParam jsonParam = jsonParamService.findJsonParam(id);

        return Result.ok(jsonParam);
    }

    @RequestMapping(path="/findAllJsonParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllJsonParam",desc = "查找所有json请求参数")
    public Result<List<JsonParam>> findAllJsonParam(){
        List<JsonParam> jsonParamList = jsonParamService.findAllJsonParam();

        return Result.ok(jsonParamList);
    }

    @Validator
    @RequestMapping(path = "/findJsonParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamList",desc = "根据查询对象查找json请求参数列表")
    @ApiParam(name = "jsonParamQuery",desc = "查询对象",required = true)
    public Result<List<JsonParam>> findJsonParamList(@RequestBody @Valid @NotNull JsonParamQuery jsonParamQuery){
        List<JsonParam> jsonParamList = jsonParamService.findJsonParamList(jsonParamQuery);

        return Result.ok(jsonParamList);
    }

    @Validator
    @RequestMapping(path = "/findJsonParamListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamListTree",desc = "根据查询对象查找json请求参数列表树")
    @ApiParam(name = "jsonParamQuery",desc = "查询对象",required = true)
    public Result<List<JsonParam>> findJsonParamListTree(@RequestBody @Valid @NotNull JsonParamQuery jsonParamQuery){
        List<JsonParam> jsonParamList = jsonParamService.findJsonParamListTree(jsonParamQuery);

        return Result.ok(jsonParamList);
    }

    @Validator
    @RequestMapping(path = "/findJsonParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamPage",desc = "根据查询对象按分页查找json请求参数列表")
    @ApiParam(name = "jsonParamQuery",desc = "查询对象",required = true)
    public Result<Pagination<List<JsonParam>>> findJsonParamPage(@RequestBody @Valid @NotNull JsonParamQuery jsonParamQuery){
        Pagination<List<JsonParam>> pagination = jsonParamService.findJsonParamPage(jsonParamQuery);

        return Result.ok(pagination);
    }

}
