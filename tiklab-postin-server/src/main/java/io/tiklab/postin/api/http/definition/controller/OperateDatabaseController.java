package io.tiklab.postin.api.http.definition.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.api.http.definition.model.OperateDatabase;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseQuery;
import io.tiklab.postin.api.http.definition.service.OperateDatabaseService;
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
import java.util.Map;

/**
 * 数据库操作 控制器
 */
@RestController
@RequestMapping("/operateDatabase")
//@Api(name = "OperateDatabaseController",desc = "数据库操作管理")
public class OperateDatabaseController {

    private static Logger logger = LoggerFactory.getLogger(OperateDatabaseController.class);

    @Autowired
    private OperateDatabaseService operateDatabaseService;

    @RequestMapping(path="/createOperateDatabase",method = RequestMethod.POST)
//    @ApiMethod(name = "createOperateDatabase",desc = "创建数据库操作")
//    @ApiParam(name = "operateDatabase",desc = "数据库操作DTO",required = true)
    public Result<String> createOperateDatabase(@RequestBody @NotNull @Valid OperateDatabase operateDatabase){
        String id = operateDatabaseService.createOperateDatabase(operateDatabase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateOperateDatabase",method = RequestMethod.POST)
//    @ApiMethod(name = "updateOperateDatabase",desc = "更新数据库操作")
//    @ApiParam(name = "operateDatabase",desc = "数据库操作DTO",required = true)
    public Result<Void> updateOperateDatabase(@RequestBody @NotNull @Valid OperateDatabase operateDatabase){
        operateDatabaseService.updateOperateDatabase(operateDatabase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteOperateDatabase",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteOperateDatabase",desc = "根据ID删除数据库操作")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deleteOperateDatabase(@NotNull String id){
        operateDatabaseService.deleteOperateDatabase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findOperateDatabase",method = RequestMethod.POST)
//    @ApiMethod(name = "findOperateDatabase",desc = "根据ID查找数据库操作")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<OperateDatabase> findOperateDatabase(@NotNull String id){
        OperateDatabase operateDatabase = operateDatabaseService.findOperateDatabase(id);

        return Result.ok(operateDatabase);
    }

    @RequestMapping(path = "/findOperateDatabaseList",method = RequestMethod.POST)
//    @ApiMethod(name = "findOperateDatabaseList",desc = "根据查询参数查找form-data参数")
//    @ApiParam(name = "operateDatabaseQuery",desc = "operateDatabaseQuery",required = true)
    public Result<List<OperateDatabase>> findFormParamList(@RequestBody @Valid @NotNull OperateDatabaseQuery operateDatabaseQuery){
        List<OperateDatabase> operateDatabaseList = operateDatabaseService.findOperateDatabaseList(operateDatabaseQuery);

        return Result.ok(operateDatabaseList);
    }


    @RequestMapping(path = "/executeSql",method = RequestMethod.POST)
    //@ApiMethod(name = "execute",desc = "执行数据库操作")
    //@ApiParam(name = "operateDatabase",desc = "operateDatabase",required = true)
    public Result<Map<String,Object>> executeSql(@RequestBody @Valid @NotNull OperateDatabase operateDatabase){
        Map<String,Object> map = operateDatabaseService.executeSql(operateDatabase);
        return Result.ok(map);
    }



}
