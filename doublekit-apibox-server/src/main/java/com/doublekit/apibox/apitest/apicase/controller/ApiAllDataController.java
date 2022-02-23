package com.doublekit.apibox.apitest.apicase.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import com.doublekit.apibox.apitest.apicase.model.ApiAllData;
import com.doublekit.apibox.apitest.apicase.model.CaseApiAllData;
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
    @ApiMethod(name = "findApiAllData",desc = "查询接口所有数据")
    @ApiParam(name = "methodId",desc = "methodId",required = true)
    public Result<ApiAllData> findApiAllData(@NotNull String methodId){
        ApiAllData apiAllData = apiAllDataService.findApiAllData(methodId);

        return Result.ok(apiAllData);

    }

    @RequestMapping(path="/findCaseApiAllData",method = RequestMethod.POST)
    @ApiMethod(name = "findCaseApiAllData",desc = "查询接口用例所有数据")
    @ApiParam(name = "testcaseId",desc = "testcaseId",required = true)
    public Result<CaseApiAllData> findCaseApiAllData(@NotNull String testcaseId){
        CaseApiAllData caseApiAllData = apiAllDataService.findCaseApiAllData(testcaseId);

        return Result.ok(caseApiAllData);

    }


}
