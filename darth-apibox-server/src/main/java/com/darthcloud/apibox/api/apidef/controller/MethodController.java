package com.darthcloud.apibox.api.apidef.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.api.apidef.model.MethodEx;
import com.darthcloud.apibox.api.apidef.model.MethodExQuery;
import com.darthcloud.apibox.api.apidef.service.MethodService;
import com.darthcloud.common.Pagination;
import com.darthcloud.common.Result;
import com.darthcloud.apibox.annotation.ApiMethod;
import com.darthcloud.apibox.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.darthcloud.validation.annotation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/method")
@Api(name = "MethodController",desc = "接口管理")
public class MethodController {

    private static Logger logger = LoggerFactory.getLogger(MethodController.class);

    @Autowired
    private MethodService methodService;

    @RequestMapping(path="/createMethod",method = RequestMethod.POST)
    @ApiMethod(name = "createMethod",desc = "创建接口")
    @ApiParam(name = "methodEx",desc = "接口DTO",required = true)
    public Result<String> createMethod(@RequestBody @NotNull @Valid MethodEx methodEx){
        String id = methodService.createMethod(methodEx);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateMethod",method = RequestMethod.POST)
    @ApiMethod(name = "updateMethod",desc = "更新接口")
    @ApiParam(name = "methodEx",desc = "接口DTO",required = true)
    public Result<Void> updateMethod(@RequestBody @NotNull MethodEx methodEx){
        methodService.updateMethod(methodEx);

        return Result.ok();
    }

    @RequestMapping(path="/deleteMethod",method = RequestMethod.POST)
    @ApiMethod(name = "deleteMethod",desc = "根据接口ID删除接口")
    @ApiParam(name = "id",desc = "接口ID",required = true)
    public Result<Void> deleteMethod(@NotNull String id){
        methodService.deleteMethod(id);

        return Result.ok();
    }

    @RequestMapping(path="/findMethod",method = RequestMethod.POST)
    @ApiMethod(name = "findMethod",desc = "根据接口ID查找接口")
    @ApiParam(name = "id",desc = "接口ID",required = true)
    public Result<MethodEx> findMethod(@NotNull String id){
        MethodEx methodEx = methodService.findMethod(id);

        return Result.ok(methodEx);
    }

    @RequestMapping(path="/findAllMethod",method = RequestMethod.POST)
    @ApiMethod(name = "findAllMethod",desc = "查找所有接口")
    public Result<List<MethodEx>> findAllMethod(){
        List<MethodEx> methodExList = methodService.findAllMethod();

        return Result.ok(methodExList);
    }

    @Validator
    @RequestMapping(path = "/findMethodList",method = RequestMethod.POST)
    @ApiMethod(name = "findMethodList",desc = "根据查询对象查找接口列表")
    @ApiParam(name = "methodExQuery",desc = "查询对象",required = true)
    public Result<List<MethodEx>> findMethodList(@RequestBody @Valid @NotNull MethodExQuery methodExQuery){
        List<MethodEx> methodExList = methodService.findMethodList(methodExQuery);

        return Result.ok(methodExList);
    }

    @Validator
    @RequestMapping(path = "/findMethodPage",method = RequestMethod.POST)
    @ApiMethod(name = "findMethodPage",desc = "根据查询对象按分页查询接口列表")
    @ApiParam(name = "methodExQuery",desc = "查询对象",required = true)
    public Result<Pagination<List<MethodEx>>> findMethodPage(@RequestBody @Valid @NotNull MethodExQuery methodExQuery){
        Pagination<List<MethodEx>> pagination = methodService.findMethodPage(methodExQuery);

        return Result.ok(pagination);
    }

}
