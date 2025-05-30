package io.tiklab.postin.api.http.definition.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.api.http.definition.model.PathParam;
import io.tiklab.postin.api.http.definition.model.PathParamQuery;
import io.tiklab.postin.api.http.definition.service.PathParamService;
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
 * http协议 定义
 * 请求头 控制器
 */
@RestController
@RequestMapping("/pathParam")
//@Api(name = "PathParamController",desc = "请求头管理")
public class PathParamController {

    private static Logger logger = LoggerFactory.getLogger(PathParamController.class);

    @Autowired
    private PathParamService pathParamService;

    @RequestMapping(path="/createPathParam",method = RequestMethod.POST)
//    @ApiMethod(name = "createPathParam",desc = "创建请求头")
//    @ApiParam(name = "pathParam",desc = "请求头DTO",required = true)
    public Result<String> createPathParam(@RequestBody @NotNull @Valid PathParam pathParam){
        String id = pathParamService.createPathParam(pathParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updatePathParam",method = RequestMethod.POST)
//    @ApiMethod(name = "updatePathParam",desc = "更新请求头")
//    @ApiParam(name = "pathParam",desc = "请求头DTO",required = true)
    public Result<Void> updatePathParam(@RequestBody @NotNull @Valid PathParam pathParam){
        pathParamService.updatePathParam(pathParam);

        return Result.ok();
    }

    @RequestMapping(path="/deletePathParam",method = RequestMethod.POST)
//    @ApiMethod(name = "deletePathParam",desc = "根据ID删除请求头")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deletePathParam(@NotNull String id){
        pathParamService.deletePathParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findPathParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findPathParam",desc = "根据ID查找请求头")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<PathParam> findPathParam(@NotNull String id){
        PathParam pathParam = pathParamService.findPathParam(id);

        return Result.ok(pathParam);
    }

    @RequestMapping(path="/findAllPathParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllPathParam",desc = "查找所有请求头列表")
    public Result<List<PathParam>> findAllPathParam(){
        List<PathParam> pathParamList = pathParamService.findAllPathParam();

        return Result.ok(pathParamList);
    }


    @RequestMapping(path = "/findPathParamList",method = RequestMethod.POST)
//    @ApiMethod(name = "findPathParamList",desc = "根据查询对象查询请求头列表")
//    @ApiParam(name = "pathParamQuery",desc = "查询对象",required = true)
    public Result<List<PathParam>> findPathParamList(@RequestBody @Valid @NotNull PathParamQuery pathParamQuery){
        List<PathParam> pathParamList = pathParamService.findPathParamList(pathParamQuery);

        return Result.ok(pathParamList);
    }


    @RequestMapping(path = "/findPathParamPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findPathParamPage",desc = "根据查询对象按分页查询请求头列表")
//    @ApiParam(name = "pathParamQuery",desc = "查询对象",required = true)
    public Result<Pagination<PathParam>> findPathParamPage(@RequestBody @Valid @NotNull PathParamQuery pathParamQuery){
        Pagination<PathParam> pagination = pathParamService.findPathParamPage(pathParamQuery);

        return Result.ok(pagination);
    }

}
