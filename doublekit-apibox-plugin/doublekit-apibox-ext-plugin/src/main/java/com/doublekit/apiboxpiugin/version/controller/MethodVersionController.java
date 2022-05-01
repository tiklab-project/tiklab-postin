package com.doublekit.apiboxpiugin.version.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import com.doublekit.apibox.apidef.apix.model.Apix;
import com.doublekit.apibox.apidef.apix.model.ApixQuery;
import com.doublekit.apiboxpiugin.version.model.VersionMethod;
import com.doublekit.apiboxpiugin.version.model.VersionMethodQuery;
import com.doublekit.apiboxpiugin.version.service.MethodVersionService;
import com.doublekit.core.Result;
import com.doublekit.core.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/methodVersion")
@Api(name = "MethodVersionController",desc = "接口版本管理")
public class MethodVersionController {

    private static Logger logger = LoggerFactory.getLogger(MethodVersionController.class);

    @Autowired
    private MethodVersionService MethodVersionService;

    @RequestMapping(path = "/findMethodVersionPage",method = RequestMethod.POST)
    @ApiMethod(name = "findMethodVersionPage",desc = "查询当前接口所有版本列表")
    @ApiParam(name = "methodExQuery",desc = "查询对象",required = true)
    public Result<Pagination<Apix>> findMethodVersionPage(@RequestBody @Valid @NotNull ApixQuery methodExQuery){
        Pagination<Apix> methodVersion = MethodVersionService.findMethodVersionPage(methodExQuery);
        return Result.ok(methodVersion);
    }

    @RequestMapping(path = "/createVersion",method = RequestMethod.POST)
    @ApiMethod(name = "createVersion",desc = "添加历史版本")
    @ApiParam(name = "methodEx",desc = "添加对象",required = true)
    public Result<String> addHistoryVersion( @RequestBody @NotNull Apix methodEx){

        String s = MethodVersionService.addHistoryVersion(methodEx);
        return Result.ok(s);
    }


    @RequestMapping(path = "/contrastVersion",method = RequestMethod.POST)
    @ApiMethod(name = "contrastVersion",desc = "版本对比")
    @ApiParam(name = "versionMethodQuery",desc = "查询参数",required = true)
    public Result<Map> contrastVersion(@RequestBody @NotNull VersionMethodQuery versionMethodQuery){
        Map contrastVersion= MethodVersionService.contrastVersion(versionMethodQuery);

        return Result.ok(contrastVersion);
    }

    @RequestMapping(path = "/queryVersiondetail",method = RequestMethod.POST)
    @ApiMethod(name = "queryVersiondetail",desc = "查询版本详情")
    @ApiParam(name = "versionId",desc = "查询参数",required = true)
    public Result<VersionMethod> queryVersiondetail(@NotNull String versionId){
        VersionMethod versionMethod=MethodVersionService.queryVersiondetail(versionId);

        return Result.ok(versionMethod);
    }
}
