package io.tiklab.postin.support.updateSql.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.support.updateSql.UpdateSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 更新sql 控制器
 */
@RestController
@RequestMapping("/sql")
//@Api(name = "UpdateSqlController",desc = "更新sql管理")
public class UpdateSqlController {

    @Autowired
    UpdateSqlService updateSqlService;

    @RequestMapping(path="/updateSql",method = RequestMethod.POST)
//    @ApiMethod(name = "updateSql",desc = "更新sql")
//    @ApiParam(name = "updateSql",desc = "updateSql")
    public Result<Void> updateSql(){
        updateSqlService.updateSql();

        return Result.ok();
    }
}
