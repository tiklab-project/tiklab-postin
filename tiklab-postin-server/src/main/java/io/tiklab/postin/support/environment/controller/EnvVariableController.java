package io.tiklab.postin.support.environment.controller;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.support.environment.model.EnvVariable;
import io.tiklab.postin.support.environment.model.EnvVariableQuery;
import io.tiklab.postin.support.environment.service.EnvVariableService;
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
 * 环境中变量 控制器
 */
@RestController
@RequestMapping("/envVariable")
//@Api(name = "EnvVariableController",desc = "环境中变量管理")
public class EnvVariableController {

    private static Logger logger = LoggerFactory.getLogger(EnvVariableController.class);

    @Autowired
    private EnvVariableService envVariableService;

    @RequestMapping(path="/createEnvVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "createEnvVariable",desc = "创建环境中变量")
//    @ApiParam(name = "envVariable",desc = "envVariable",required = true)
    public Result<String> createEnvVariable(@RequestBody @NotNull @Valid EnvVariable envVariable){
        String id = envVariableService.createEnvVariable(envVariable);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateEnvVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "updateEnvVariable",desc = "更新环境中变量")
//    @ApiParam(name = "envVariable",desc = "envVariable",required = true)
    public Result<Void> updateEnvVariable(@RequestBody @NotNull @Valid EnvVariable envVariable){
        envVariableService.updateEnvVariable(envVariable);

        return Result.ok();
    }

    @RequestMapping(path="/deleteEnvVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteEnvVariable",desc = "删除环境中变量")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteEnvVariable(@NotNull String id){
        envVariableService.deleteEnvVariable(id);

        return Result.ok();
    }


    @RequestMapping(path="/findEnvVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "findEnvVariable",desc = "通过id查找环境中变量")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<EnvVariable> findEnvVariable(@NotNull String id){
        EnvVariable envVariable = envVariableService.findEnvVariable(id);

        return Result.ok(envVariable);
    }

    @RequestMapping(path="/findAllEnvVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllEnvVariable",desc = "查找所有环境中变量")
    public Result<List<EnvVariable>> findAllEnvVariable(){
        List<EnvVariable> envVariableList = envVariableService.findAllEnvVariable();

        return Result.ok(envVariableList);
    }


    @RequestMapping(path = "/findEnvVariableList",method = RequestMethod.POST)
//    @ApiMethod(name = "findEnvVariableList",desc = "根据查询参数查找环境中变量")
//    @ApiParam(name = "envVariableQuery",desc = "envVariableQuery",required = true)
    public Result<List<EnvVariable>> findEnvVariableList(@RequestBody @Valid @NotNull EnvVariableQuery envVariableQuery){
        List<EnvVariable> envVariableList = envVariableService.findEnvVariableList(envVariableQuery);

        return Result.ok(envVariableList);
    }


    @RequestMapping(path = "/findEnvVariablePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findEnvVariablePage",desc = "根据查询参数按分页查找环境中变量")
//    @ApiParam(name = "envVariableQuery",desc = "envVariableQuery",required = true)
    public Result<Pagination<EnvVariable>> findEnvVariablePage(@RequestBody @Valid @NotNull EnvVariableQuery envVariableQuery){
        Pagination<EnvVariable> pagination = envVariableService.findEnvVariablePage(envVariableQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/batchCreateVariable",method = RequestMethod.POST)
    public Result<Void> batchCreateVariable(@RequestBody @Valid @NotNull JSONObject jsonObject){
        envVariableService.batchCreateVariable(jsonObject);

        return Result.ok();
    }

}
