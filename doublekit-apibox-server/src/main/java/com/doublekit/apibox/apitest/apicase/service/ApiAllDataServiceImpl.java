package com.doublekit.apibox.apitest.apicase.service;

import com.doublekit.apibox.apidef.model.*;
import com.doublekit.apibox.apidef.service.*;
import com.doublekit.apibox.apitest.apicase.model.ApiAllData;
import com.doublekit.apibox.apitest.apicase.model.CaseApiAllData;
import com.doublekit.apibox.apitest.apicase.model.Testcase;
import com.doublekit.apibox.apitest.apicase.type.ApiAllDataFn;
import com.doublekit.apibox.apitest.apicase.type.CaseApiAllDataFn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiAllDataServiceImpl implements ApiAllDataService {


    @Autowired
    MethodService methodService;

    @Autowired
    TestcaseService testcaseService;

    @Autowired
    ApiAllDataFn apiAllDataFn;

    @Autowired
    CaseApiAllDataFn caseApiAllDataFn;


    @Override
    public ApiAllData findApiAllData(String methodId) {

        ApiAllData apiAllData = new ApiAllData();

        //获取method中的数据
        MethodEx method = methodService.findMethod(methodId);
        apiAllData.setRequestType(method.getRequestType());
        apiAllData.setPath(method.getPath());

        //获取请求头中的数据
        apiAllDataFn.getHeaderData(methodId,apiAllData);

        //获取查询参数的数据
        apiAllDataFn.getQueryData(methodId,apiAllData);

        //获取请求体的类型
        apiAllDataFn.getBodyTypeData(methodId,apiAllData);

        //获取前置脚本数据
        apiAllDataFn.getPreScriptData(methodId,apiAllData);

        //获取后置脚本的数据
        apiAllDataFn.getAfterScriptData(methodId,apiAllData);

        return apiAllData;
    }


    @Override
    public CaseApiAllData findCaseApiAllData(String testcaseId){
        CaseApiAllData caseApiAllData = new CaseApiAllData();

        Testcase testcase = testcaseService.findTestcase(testcaseId);
        caseApiAllData.setRequestType(testcase.getMethod().getRequestType());
        caseApiAllData.setPath(testcase.getMethod().getPath());
        caseApiAllData.setName(testcase.getName());

        caseApiAllDataFn.getData(testcaseId,caseApiAllData);


        return  caseApiAllData;
    }



}
