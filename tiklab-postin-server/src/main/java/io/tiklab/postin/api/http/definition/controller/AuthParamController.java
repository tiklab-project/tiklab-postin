package io.tiklab.postin.api.http.definition.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.api.http.definition.model.AuthParam;
import io.tiklab.postin.api.http.definition.service.AuthParamService;
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
@RequestMapping("/authParam")
//@Api(name = "AuthParamController",desc = "认证管理")
public class AuthParamController {

    private static Logger logger = LoggerFactory.getLogger(AuthParamController.class);

    @Autowired
    private AuthParamService authParamService;

    @RequestMapping(path="/createAuthParam",method = RequestMethod.POST)
//    @ApiMethod(name = "createAuthParam",desc = "创建认证")
//    @ApiParam(name = "authParam",desc = "认证DTO",required = true)
    public Result<String> createAuthParam(@RequestBody @NotNull @Valid AuthParam authParam){
        String id = authParamService.createAuthParam(authParam);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAuthParam",method = RequestMethod.POST)
//    @ApiMethod(name = "updateAuthParam",desc = "更新认证")
//    @ApiParam(name = "authParam",desc = "认证DTO",required = true)
    public Result<Void> updateAuthParam(@RequestBody @NotNull @Valid AuthParam authParam){
        authParamService.updateAuthParam(authParam);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAuthParam",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteAuthParam",desc = "根据ID删除认证")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<Void> deleteAuthParam(@NotNull String id){
        authParamService.deleteAuthParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAuthParam",method = RequestMethod.POST)
//    @ApiMethod(name = "findAuthParam",desc = "根据ID查找认证")
//    @ApiParam(name = "id",desc = "唯一标识",required = true)
    public Result<AuthParam> findAuthParam(@NotNull String id){
        AuthParam authParam = authParamService.findAuthParam(id);

        return Result.ok(authParam);
    }

}
