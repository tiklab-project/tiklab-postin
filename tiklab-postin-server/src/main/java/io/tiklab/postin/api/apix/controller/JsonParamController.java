package io.tiklab.postin.api.apix.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.api.apix.model.JsonParam;
import io.tiklab.postin.api.apix.model.JsonParamQuery;
import io.tiklab.postin.api.apix.service.JsonParamService;
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
 * 请求中json 控制器
 */
@RestController
@RequestMapping("/jsonParam")
//@Api(name = "JsonParamController",desc = "json请求参数管理")
public class JsonParamController {

    private static Logger logger = LoggerFactory.getLogger(JsonParamController.class);

    @Autowired
    private JsonParamService jsonParamService;

    @RequestMapping(path="/createJsonParam",method = RequestMethod.POST)
//    @ApiMethod(name = "createJsonParam",desc = "创建json请求参数")
//    @ApiParam(name = "jsonParam",desc = "json请求参数DTO",required = true)
    public Result<String> createJsonParam(@RequestBody @NotNull @Valid JsonParam jsonParam){
        String id = jsonParamService.createJsonParam(jsonParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonParam",method = RequestMethod.POST)
//    @ApiMethod(name = "updateJsonParam",desc = "更新json请求参数")
//    @ApiParam(name = "jsonParam",desc = "json请求参数DTO",required = true)
    public Result<Void> updateJsonParam(@RequestBody @NotNull @Valid JsonParam jsonParam){
        jsonParamService.updateJsonParam(jsonParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteJsonParam",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteJsonParam",desc = "根据ID删除json请求参数")
//    @ApiParam(name = "id",desc = "json请求参数ID",required = true)
    public Result<Void> deleteJsonParam(@NotNull String id){
        jsonParamService.deleteJsonParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findJsonParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonParam",desc = "根据ID查找json请求参数")
//    @ApiParam(name = "id",desc = "json请求参数ID",required = true)
    public Result<JsonParam> findJsonParam(@NotNull String id){
        JsonParam jsonParam = jsonParamService.findJsonParam(id);

        return Result.ok(jsonParam);
    }

    @RequestMapping(path="/findAllJsonParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllJsonParam",desc = "查找所有json请求参数")
    public Result<List<JsonParam>> findAllJsonParam(){
        List<JsonParam> jsonParamList = jsonParamService.findAllJsonParam();

        return Result.ok(jsonParamList);
    }


    @RequestMapping(path = "/findJsonParamList",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonParamList",desc = "根据查询对象查找json请求参数列表")
//    @ApiParam(name = "jsonParamQuery",desc = "查询对象",required = true)
    public Result<List<JsonParam>> findJsonParamList(@RequestBody @Valid @NotNull JsonParamQuery jsonParamQuery){
        List<JsonParam> jsonParamList = jsonParamService.findJsonParamList(jsonParamQuery);

        return Result.ok(jsonParamList);
    }


    @RequestMapping(path = "/findJsonParamPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findJsonParamPage",desc = "根据查询对象按分页查找json请求参数列表")
//    @ApiParam(name = "jsonParamQuery",desc = "查询对象",required = true)
    public Result<Pagination<JsonParam>> findJsonParamPage(@RequestBody @Valid @NotNull JsonParamQuery jsonParamQuery){
        Pagination<JsonParam> pagination = jsonParamService.findJsonParamPage(jsonParamQuery);

        return Result.ok(pagination);
    }

}
