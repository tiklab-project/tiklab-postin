package com.darthcloud.apibox.apxmethod.controller;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.apxmethod.model.ApxMethod;
import com.darthcloud.apibox.apxmethod.model.ApxMethodQuery;
import com.darthcloud.apibox.apxmethod.service.ApxMethodService;
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
@RequestMapping("/apxMethod")
@Api(name = "ApxMethodController",desc = "接口管理")
public class ApxMethodController {

    private static Logger logger = LoggerFactory.getLogger(ApxMethodController.class);

    @Autowired
    private ApxMethodService apxMethodService;

    @RequestMapping(path="/createApxMethod",method = RequestMethod.POST)
    @ApiMethod(name = "createApxMethod",desc = "创建接口")
    @ApiParam(name = "apxMethod",desc = "接口DTO",required = true)
    public Result<String> createApxMethod(@RequestBody @NotNull @Valid ApxMethod apxMethod){
        String id = apxMethodService.createApxMethod(apxMethod);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateApxMethod",method = RequestMethod.POST)
    @ApiMethod(name = "updateApxMethod",desc = "更新接口")
    @ApiParam(name = "apxMethod",desc = "接口DTO",required = true)
    public Result<Void> updateApxMethod(@RequestBody @NotNull ApxMethod apxMethod){
        apxMethodService.updateApxMethod(apxMethod);

        return Result.ok();
    }

    @RequestMapping(path="/deleteApxMethod",method = RequestMethod.POST)
    @ApiMethod(name = "deleteApxMethod",desc = "根据接口ID删除接口")
    @ApiParam(name = "id",desc = "接口ID",required = true)
    public Result<Void> deleteApxMethod(@NotNull String id){
        apxMethodService.deleteApxMethod(id);

        return Result.ok();
    }

    @RequestMapping(path="/findApxMethod",method = RequestMethod.POST)
    @ApiMethod(name = "findApxMethod",desc = "根据接口ID查找接口")
    @ApiParam(name = "id",desc = "接口ID",required = true)
    public Result<ApxMethod> findApxMethod(@NotNull String id){
        ApxMethod apxMethod = apxMethodService.findApxMethod(id);

        return Result.ok(apxMethod);
    }

    @RequestMapping(path="/findAllApxMethod",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApxMethod",desc = "查找所有接口")
    public Result<List<ApxMethod>> findAllApxMethod(){
        List<ApxMethod> apxMethodList = apxMethodService.findAllApxMethod();

        return Result.ok(apxMethodList);
    }

    @Validator
    @RequestMapping(path = "/findApxMethodList",method = RequestMethod.POST)
    @ApiMethod(name = "findApxMethodList",desc = "根据查询对象查找接口列表")
    @ApiParam(name = "apxMethodQuery",desc = "查询对象",required = true)
    public Result<List<ApxMethod>> findApxMethodList(@RequestBody @Valid @NotNull ApxMethodQuery apxMethodQuery){
        List<ApxMethod> apxMethodList = apxMethodService.findApxMethodList(apxMethodQuery);

        return Result.ok(apxMethodList);
    }

    @Validator
    @RequestMapping(path = "/findApxMethodPage",method = RequestMethod.POST)
    @ApiMethod(name = "findApxMethodPage",desc = "根据查询对象按分页查询接口列表")
    @ApiParam(name = "apxMethodQuery",desc = "查询对象",required = true)
    public Result<Pagination<List<ApxMethod>>> findApxMethodPage(@RequestBody @Valid @NotNull ApxMethodQuery apxMethodQuery){
        Pagination<List<ApxMethod>> pagination = apxMethodService.findApxMethodPage(apxMethodQuery);

        return Result.ok(pagination);
    }

}
