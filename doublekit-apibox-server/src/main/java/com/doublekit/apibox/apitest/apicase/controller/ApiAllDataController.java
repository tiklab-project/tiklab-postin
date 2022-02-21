package com.doublekit.apibox.apitest.apicase.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import com.doublekit.apibox.apitest.apicase.model.ApiAllData;
import com.doublekit.apibox.apitest.apicase.service.ApiAllDataService;
import com.doublekit.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/apiAllData")
@Api(name = "ApiAllDataController",desc = "接口所有数据管理")
public class ApiAllDataController {

    @Autowired
    ApiAllDataService apiAllDataService;


    @RequestMapping(path="/findApiAllData",method = RequestMethod.POST)
    @ApiMethod(name = "findApiAllData",desc = "findApiAllData")
    @ApiParam(name = "methodId",desc = "methodId",required = true)
    public Result<ApiAllData> findApiAllData(@NotNull String methodId){
        ApiAllData apiAllData = apiAllDataService.findApiAllData(methodId);

        return Result.ok(apiAllData);

    }

    @RequestMapping(path="/findCaseApiAllData",method = RequestMethod.POST)
    @ApiMethod(name = "findCaseApiAllData",desc = "findCaseApiAllData")
    @ApiParam(name = "methodId",desc = "methodId",required = true)
    public Result<ApiAllData> findCaseApiAllData(@NotNull String methodId){
        ApiAllData apiAllData = apiAllDataService.findApiAllData(methodId);

        return Result.ok(apiAllData);

    }


}
