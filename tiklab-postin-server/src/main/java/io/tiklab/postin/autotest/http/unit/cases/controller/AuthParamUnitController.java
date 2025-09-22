package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.service.AuthParamUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 认证 控制器
 */
@RestController
@RequestMapping("/authParamUnit")
//@Api(name = "AuthParamUnitController",desc = "认证管理")
public class AuthParamUnitController {

    private static Logger logger = LoggerFactory.getLogger(AuthParamUnitController.class);

    @Autowired
    private AuthParamUnitService authParamUnitService;

    @RequestMapping(path="/createAuthParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "createAuthParamUnit",desc = "创建认证")
//    @ApiParam(name = "authParamUnit",desc = "认证DTO",required = true)
    public Result<String> createAuthParamUnit(@RequestBody @NotNull @Valid AuthParamUnit authParamUnit){
        String id = authParamUnitService.createAuthParamUnit(authParamUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAuthParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "updateAuthParamUnit",desc = "更新认证")
//    @ApiParam(name = "authParamUnit",desc = "认证DTO",required = true)
    public Result<Void> updateAuthParamUnit(@RequestBody @NotNull @Valid AuthParamUnit authParamUnit){
        authParamUnitService.updateAuthParamUnit(authParamUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAuthParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteAuthParamUnit",desc = "根据ID删除认证")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deleteAuthParamUnit(@NotNull String id){
        authParamUnitService.deleteAuthParamUnit(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAuthParamUnit",method = RequestMethod.POST)
//    @ApiMethod(name = "findAuthParamUnit",desc = "根据ID查找认证")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<AuthParamUnit> findAuthParamUnit(@NotNull String id){
        AuthParamUnit authParamUnit = authParamUnitService.findAuthParamUnit(id);

        return Result.ok(authParamUnit);
    }

}
