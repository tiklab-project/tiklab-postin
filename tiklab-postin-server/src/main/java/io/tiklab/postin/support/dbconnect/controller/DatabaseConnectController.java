package io.tiklab.postin.support.dbconnect.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnect;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnectQuery;
import io.tiklab.postin.support.dbconnect.service.DatabaseConnectService;
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
 * 数据库连接 控制器
 */
@RestController
@RequestMapping("/databaseConnect")
//@Api(name = "DatabaseConnectController",desc = "数据库连接管理")
public class DatabaseConnectController {

    private static Logger logger = LoggerFactory.getLogger(DatabaseConnectController.class);

    @Autowired
    private DatabaseConnectService databaseConnectService;

    @RequestMapping(path="/createDatabaseConnect",method = RequestMethod.POST)
//    @ApiMethod(name = "createDatabaseConnect",desc = "创建数据库连接")
//    @ApiParam(name = "databaseConnect",desc = "databaseConnect",required = true)
    public Result<String> createDatabaseConnect(@RequestBody @NotNull @Valid DatabaseConnect databaseConnect){
        String id = databaseConnectService.createDatabaseConnect(databaseConnect);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateDatabaseConnect",method = RequestMethod.POST)
//    @ApiMethod(name = "updateDatabaseConnect",desc = "更新数据库连接")
//    @ApiParam(name = "databaseConnect",desc = "databaseConnect",required = true)
    public Result<Void> updateDatabaseConnect(@RequestBody @NotNull @Valid DatabaseConnect databaseConnect){
        databaseConnectService.updateDatabaseConnect(databaseConnect);

        return Result.ok();
    }

    @RequestMapping(path="/deleteDatabaseConnect",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteDatabaseConnect",desc = "删除数据库连接")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteDatabaseConnect(@NotNull String id){
        databaseConnectService.deleteDatabaseConnect(id);

        return Result.ok();
    }

    @RequestMapping(path="/findDatabaseConnect",method = RequestMethod.POST)
//    @ApiMethod(name = "findDatabaseConnect",desc = "根据id查找数据库连接")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<DatabaseConnect> findDatabaseConnect(@NotNull String id){
        DatabaseConnect databaseConnect = databaseConnectService.findDatabaseConnect(id);

        return Result.ok(databaseConnect);
    }

    @RequestMapping(path="/findAllDatabaseConnect",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllDatabaseConnect",desc = "查找所有数据库连接")
    public Result<List<DatabaseConnect>> findAllDatabaseConnect(){
        List<DatabaseConnect> databaseConnectList = databaseConnectService.findAllDatabaseConnect();

        return Result.ok(databaseConnectList);
    }


    @RequestMapping(path = "/findDatabaseConnectList",method = RequestMethod.POST)
//    @ApiMethod(name = "findDatabaseConnectList",desc = "根据查询参数查找数据库连接")
//    @ApiParam(name = "databaseConnectQuery",desc = "databaseConnectQuery",required = true)
    public Result<List<DatabaseConnect>> findDatabaseConnectList(@RequestBody @Valid @NotNull DatabaseConnectQuery databaseConnectQuery){
        List<DatabaseConnect> databaseConnectList = databaseConnectService.findDatabaseConnectList(databaseConnectQuery);

        return Result.ok(databaseConnectList);
    }


    @RequestMapping(path = "/findDatabaseConnectPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findDatabaseConnectPage",desc = "根据查询参数按分页查找数据库连接")
//    @ApiParam(name = "databaseConnectQuery",desc = "databaseConnectQuery",required = true)
    public Result<Pagination<DatabaseConnect>> findDatabaseConnectPage(@RequestBody @Valid @NotNull DatabaseConnectQuery databaseConnectQuery){
        Pagination<DatabaseConnect> pagination = databaseConnectService.findDatabaseConnectPage(databaseConnectQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/testDatabaseConnect",method = RequestMethod.POST)
    public Result<Boolean> testDatabaseConnect(@RequestBody @NotNull @Valid DatabaseConnect databaseConnect){
        Boolean result = databaseConnectService.testDatabaseConnect(databaseConnect);

        return Result.ok(result);
    }

}
