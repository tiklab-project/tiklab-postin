package com.doublekit.apibox.apitest.apicase.support;

import com.doublekit.apibox.apitest.apicase.model.*;
import com.doublekit.apibox.apitest.apicase.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class CaseApiAllDataFn {

    @Autowired
    RequestHeaderCaseService requestHeaderCaseService;

    @Autowired
    QueryParamCaseService queryParamCaseService;

    @Autowired
    RequestBodyCaseService requestBodyCaseService;

    @Autowired
    FormParamCaseService formParamCaseService;

    @Autowired
    FormUrlencodedCaseService formUrlencodedCaseService;

    @Autowired
    JsonParamCaseService jsonParamCaseService;

    @Autowired
    RawParamCaseService rawParamCaseService;

    @Autowired
    BinaryParamCaseService binaryParamCaseService;

    @Autowired
    PreScriptCaseService preScriptCaseService;

    @Autowired
    AfterScriptCaseService afterScriptCaseService;


    public void getData(String testcaseId, Testcase testcase) {
        //获取请求头中的数据
        getCaseHeaderData(testcaseId,testcase);

        //获取查询参数的数据
        getCaseQueryData(testcaseId,testcase);

        //获取请求体的类型
        getCaseBodyTypeData(testcaseId,testcase);

        //获取前置脚本数据
        getCasePreScriptData(testcaseId,testcase);

        //获取后置脚本的数据
        getCaseAfterScriptData(testcaseId,testcase);
    }

    /**
     * 获取请求头中的数据
     * @param testcaseId
     * @param testcase
     */
    public void getCaseHeaderData(String testcaseId, Testcase testcase) {
        List<RequestHeaderCase> requestHeaderCaseList = requestHeaderCaseService.findRequestHeaderCaseList(new RequestHeaderCaseQuery().setTestcaseId(testcaseId));

        if(CollectionUtils.isNotEmpty(requestHeaderCaseList)){
            testcase.setRequestHeaderCaseList(requestHeaderCaseList);
        }
    }


    public void getCaseQueryData(String testcaseId, Testcase testcase) {
        List<QueryParamCase> queryParamCaseList = queryParamCaseService.findQueryParamCaseList(new QueryParamCaseQuery().setTestcaseId(testcaseId));

        if(CollectionUtils.isNotEmpty(queryParamCaseList)){
            testcase.setQueryParamCaseList(queryParamCaseList);
        }
    }

    public void getCaseBodyTypeData(String testcaseId, Testcase testcase) {
        RequestBodyCase requestBodyCase = requestBodyCaseService.findRequestBodyCase(testcaseId);
        testcase.setRequestBodyCase(requestBodyCase);
        
        String bodyType = requestBodyCase.getBodyType();
        getCaseBodyData(bodyType,testcaseId,testcase);
    }

    private void getCaseBodyData(String bodyType, String testcaseId, Testcase testcase) {
        switch (bodyType){
            case "formdata":
                getCaseFormParamData(testcaseId,testcase);
                break;
            case "formUrlencoded":
                getCaseFormUrlencodedData(testcaseId,testcase);
                break;
            case "json":
                getCaseJsonParamData(testcaseId,testcase);
                break;
            case "raw":
                getCaseRawParamData(testcaseId,testcase);
                break;
            case "binary":
                //问题
                break;
            default:
                break;
        }
    }

    private void getCaseFormParamData(String testcaseId, Testcase testcase) {
        List<FormParamCase> formParamCaseList = formParamCaseService.findFormParamCaseList(new FormParamCaseQuery().setTestcaseId(testcaseId));

        if(CollectionUtils.isNotEmpty(formParamCaseList)){
            testcase.setFormParamCaseList(formParamCaseList);
        }
    }

    private void getCaseFormUrlencodedData(String testcaseId, Testcase testcase) {
        List<FormUrlencodedCase> formUrlencodedCaseList = formUrlencodedCaseService.findFormUrlencodedCaseList(new FormUrlencodedCaseQuery().setTestcaseId(testcaseId));

        if(CollectionUtils.isNotEmpty(formUrlencodedCaseList)){
            testcase.setFormUrlencodedCaseList(formUrlencodedCaseList);
        }
    }

    private void getCaseJsonParamData(String testcaseId, Testcase testcase) {
        List<JsonParamCase> jsonParamCaseList = jsonParamCaseService.findJsonParamCaseList(new JsonParamCaseQuery().setTestcaseId(testcaseId));

        if(CollectionUtils.isNotEmpty(jsonParamCaseList)){
            testcase.setJsonParamCaseList(jsonParamCaseList);
        }
    }

    private void getCaseRawParamData(String testcaseId, Testcase testcase) {
        RawParamCase rawParamCase = rawParamCaseService.findRawParamCase(testcaseId);

        if(!ObjectUtils.isEmpty(rawParamCase)){
            testcase.setRawParamCase(rawParamCase);
        }
    }

    public void getCasePreScriptData(String testcaseId, Testcase testcase) {
        PreScriptCase preScriptCase = preScriptCaseService.findPreScriptCase(testcaseId);

        if(!ObjectUtils.isEmpty(preScriptCase)){
            testcase.setPreScriptCase(preScriptCase);
        }
    }

    public void getCaseAfterScriptData(String testcaseId, Testcase testcase) {
        AfterScriptCase afterScriptCase = afterScriptCaseService.findAfterScriptCase(testcaseId);

        if(!ObjectUtils.isEmpty(afterScriptCase)){
            testcase.setAfterScriptCase(afterScriptCase);
        }
    }

}
