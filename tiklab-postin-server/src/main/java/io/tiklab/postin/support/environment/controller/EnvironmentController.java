package io.tiklab.postin.support.environment.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.support.environment.model.Environment;
import io.tiklab.postin.support.environment.model.EnvironmentQuery;
import io.tiklab.postin.support.environment.service.EnvironmentService;
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
 * 接口环境 控制器
 */
@RestController
@RequestMapping("/environment")
//@Api(name = "EnvironmentController",desc = "环境管理")
public class EnvironmentController {

    private static Logger logger = LoggerFactory.getLogger(EnvironmentController.class);

    @Autowired
    private EnvironmentService environmentService;

    @RequestMapping(path="/createEnvironment",method = RequestMethod.POST)
//    @ApiMethod(name = "createEnvironment",desc = "创建环境")
//    @ApiParam(name = "environment",desc = "environment",required = true)
    public Result<String> createEnvironment(@RequestBody @NotNull @Valid Environment environment){
        String id = environmentService.createEnvironment(environment);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateEnvironment",method = RequestMethod.POST)
//    @ApiMethod(name = "updateEnvironment",desc = "更新环境")
//    @ApiParam(name = "environment",desc = "environment",required = true)
    public Result<Void> updateEnvironment(@RequestBody @NotNull @Valid Environment environment){
        environmentService.updateEnvironment(environment);

        return Result.ok();
    }

    @RequestMapping(path="/deleteEnvironment",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteEnvironment",desc = "删除环境")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteEnvironment(@NotNull String id){
        environmentService.deleteEnvironment(id);

        return Result.ok();
    }


    @RequestMapping(path="/findEnvironment",method = RequestMethod.POST)
//    @ApiMethod(name = "findEnvironment",desc = "通过id查找环境")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Environment> findEnvironment(@NotNull String id){
        Environment environment = environmentService.findEnvironment(id);

        return Result.ok(environment);
    }

    @RequestMapping(path="/findAllEnvironment",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllEnvironment",desc = "查找所有环境")
    public Result<List<Environment>> findAllEnvironment(){
        List<Environment> environmentList = environmentService.findAllEnvironment();

        return Result.ok(environmentList);
    }


    @RequestMapping(path = "/findEnvironmentList",method = RequestMethod.POST)
//    @ApiMethod(name = "findEnvironmentList",desc = "根据查询参数查找环境")
//    @ApiParam(name = "environmentQuery",desc = "environmentQuery",required = true)
    public Result<List<Environment>> findEnvironmentList(@RequestBody @Valid @NotNull EnvironmentQuery environmentQuery){
        List<Environment> environmentList = environmentService.findEnvironmentList(environmentQuery);

        return Result.ok(environmentList);
    }


    @RequestMapping(path = "/findEnvironmentPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findEnvironmentPage",desc = "根据查询参数按分页查找环境")
//    @ApiParam(name = "environmentQuery",desc = "environmentQuery",required = true)
    public Result<Pagination<Environment>> findEnvironmentPage(@RequestBody @Valid @NotNull EnvironmentQuery environmentQuery){
        Pagination<Environment> pagination = environmentService.findEnvironmentPage(environmentQuery);

        return Result.ok(pagination);
    }

}
