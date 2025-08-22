package io.tiklab.postin.api.http.definition.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseVariable;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseVariableQuery;
import io.tiklab.postin.api.http.definition.service.OperateDatabaseVariableService;
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
 * 数据库操作中的变量 控制器
 */
@RestController
@RequestMapping("/operateDatabaseVariable")
//@Api(name = "OperateDatabaseVariableController",desc = "数据库操作中的变量管理")
public class OperateDatabaseVariableController {

    private static Logger logger = LoggerFactory.getLogger(OperateDatabaseVariableController.class);

    @Autowired
    private OperateDatabaseVariableService operateDatabaseVariableService;

    @RequestMapping(path="/createOperateDatabaseVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "createOperateDatabaseVariable",desc = "创建数据库操作中的变量")
//    @ApiParam(name = "operateDatabaseVariable",desc = "数据库操作中的变量DTO",required = true)
    public Result<String> createOperateDatabaseVariable(@RequestBody @NotNull @Valid OperateDatabaseVariable operateDatabaseVariable){
        String id = operateDatabaseVariableService.createOperateDatabaseVariable(operateDatabaseVariable);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateOperateDatabaseVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "updateOperateDatabaseVariable",desc = "更新数据库操作中的变量")
//    @ApiParam(name = "operateDatabaseVariable",desc = "数据库操作中的变量DTO",required = true)
    public Result<Void> updateOperateDatabaseVariable(@RequestBody @NotNull @Valid OperateDatabaseVariable operateDatabaseVariable){
        operateDatabaseVariableService.updateOperateDatabaseVariable(operateDatabaseVariable);

        return Result.ok();
    }

    @RequestMapping(path="/deleteOperateDatabaseVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteOperateDatabaseVariable",desc = "根据ID删除数据库操作中的变量")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deleteOperateDatabaseVariable(@NotNull String id){
        operateDatabaseVariableService.deleteOperateDatabaseVariable(id);

        return Result.ok();
    }

    @RequestMapping(path="/findOperateDatabaseVariable",method = RequestMethod.POST)
//    @ApiMethod(name = "findOperateDatabaseVariable",desc = "根据ID查找数据库操作中的变量")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<OperateDatabaseVariable> findOperateDatabaseVariable(@NotNull String id){
        OperateDatabaseVariable operateDatabaseVariable = operateDatabaseVariableService.findOperateDatabaseVariable(id);

        return Result.ok(operateDatabaseVariable);
    }

    @RequestMapping(path = "/findOperateDatabaseVariableList",method = RequestMethod.POST)
//    @ApiMethod(name = "findOperateDatabaseVariableList",desc = "根据查询参数查找数据库操作中的变量列表")
//    @ApiParam(name = "operateDatabaseVariableQuery",desc = "operateDatabaseVariableQuery",required = true)
    public Result<List<OperateDatabaseVariable>> findFormParamList(@RequestBody @Valid @NotNull OperateDatabaseVariableQuery operateDatabaseVariableQuery){
        List<OperateDatabaseVariable> operateDatabaseVariableList = operateDatabaseVariableService.findOperateDatabaseVariableList(operateDatabaseVariableQuery);

        return Result.ok(operateDatabaseVariableList);
    }



}
