package com.doublekit.apibox.apitest.apicase.type;

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



    public void getData(String testcaseId, CaseApiAllData caseApiAllData) {
        //获取请求头中的数据
        getCaseHeaderData(testcaseId,caseApiAllData);

        //获取查询参数的数据
        getCaseQueryData(testcaseId,caseApiAllData);

        //获取请求体的类型
        getCaseBodyTypeData(testcaseId,caseApiAllData);

        //获取前置脚本数据
        getCasePreScriptData(testcaseId,caseApiAllData);

        //获取后置脚本的数据
        getCaseAfterScriptData(testcaseId,caseApiAllData);
    }

    /**
     * 获取请求头中的数据
     * @param testcaseId
     * @param caseApiAllData
     */
    public void getCaseHeaderData(String testcaseId, CaseApiAllData caseApiAllData) {
        List<RequestHeaderCase> requestHeaderCaseList = requestHeaderCaseService.findRequestHeaderCaseList(new RequestHeaderCaseQuery().setTestcaseId(testcaseId));

        if(CollectionUtils.isNotEmpty(requestHeaderCaseList)){
            caseApiAllData.setRequestHeaderList(requestHeaderCaseList);
        }
    }


    public void getCaseQueryData(String testcaseId, CaseApiAllData caseApiAllData) {
        List<QueryParamCase> queryParamCaseList = queryParamCaseService.findQueryParamCaseList(new QueryParamCaseQuery().setTestcaseId(testcaseId));

        if(CollectionUtils.isNotEmpty(queryParamCaseList)){
            caseApiAllData.setQueryParamList(queryParamCaseList);
        }
    }

    public void getCaseBodyTypeData(String testcaseId, CaseApiAllData caseApiAllData) {
        RequestBodyCase requestBodyCase = requestBodyCaseService.findRequestBodyCase(testcaseId);
        String bodyType = requestBodyCase.getBodyType();
        caseApiAllData.setBodyType(bodyType);

        getCaseBodyData(bodyType,testcaseId,caseApiAllData);
    }

    private void getCaseBodyData(String bodyType, String testcaseId, CaseApiAllData caseApiAllData) {
        switch (bodyType){
            case "formdata":
                getCaseFormParamData(testcaseId,caseApiAllData);
                break;
            case "formUrlencoded":
                getCaseFormUrlencodedData(testcaseId,caseApiAllData);
                break;
            case "json":
                getCaseJsonParamData(testcaseId,caseApiAllData);
                break;
            case "raw":
                getCaseRawParamData(testcaseId,caseApiAllData);
                break;
            case "binary":
                //问题
                break;
            default:
                break;
        }
    }

    private void getCaseFormParamData(String testcaseId, CaseApiAllData caseApiAllData) {
        List<FormParamCase> formParamCaseList = formParamCaseService.findFormParamCaseList(new FormParamCaseQuery().setTestcaseId(testcaseId));

        if(CollectionUtils.isNotEmpty(formParamCaseList)){
            caseApiAllData.setFormParamList(formParamCaseList);
        }
    }

    private void getCaseFormUrlencodedData(String testcaseId, CaseApiAllData caseApiAllData) {
        List<FormUrlencodedCase> formUrlencodedCaseList = formUrlencodedCaseService.findFormUrlencodedCaseList(new FormUrlencodedCaseQuery().setTestcaseId(testcaseId));

        if(CollectionUtils.isNotEmpty(formUrlencodedCaseList)){
            caseApiAllData.setFormUrlencodedList(formUrlencodedCaseList);
        }
    }

    private void getCaseJsonParamData(String testcaseId, CaseApiAllData caseApiAllData) {
        List<JsonParamCase> jsonParamCaseList = jsonParamCaseService.findJsonParamCaseList(new JsonParamCaseQuery().setTestcaseId(testcaseId));

        if(CollectionUtils.isNotEmpty(jsonParamCaseList)){
            caseApiAllData.setJsonParamList(jsonParamCaseList);
        }
    }

    private void getCaseRawParamData(String testcaseId, CaseApiAllData caseApiAllData) {
        RawParamCase rawParamCase = rawParamCaseService.findRawParamCase(testcaseId);

        if(!ObjectUtils.isEmpty(rawParamCase)){
            caseApiAllData.setRawParam(rawParamCase);
        }
    }

    public void getCasePreScriptData(String testcaseId, CaseApiAllData caseApiAllData) {
        PreScriptCase preScriptCase = preScriptCaseService.findPreScriptCase(testcaseId);

        if(!ObjectUtils.isEmpty(preScriptCase)){
            caseApiAllData.setPreScript(preScriptCase);
        }
    }

    public void getCaseAfterScriptData(String testcaseId, CaseApiAllData caseApiAllData) {
        AfterScriptCase afterScriptCase = afterScriptCaseService.findAfterScriptCase(testcaseId);

        if(!ObjectUtils.isEmpty(afterScriptCase)){
            caseApiAllData.setAfterScript(afterScriptCase);
        }
    }

}
