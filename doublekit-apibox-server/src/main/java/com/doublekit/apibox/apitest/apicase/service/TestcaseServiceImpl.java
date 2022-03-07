package com.doublekit.apibox.apitest.apicase.service;

import com.doublekit.apibox.apitest.apicase.dao.TestcaseDao;
import com.doublekit.apibox.apitest.apicase.entity.TestcaseEntity;
import com.doublekit.apibox.apitest.apicase.model.*;

import com.doublekit.apibox.apitest.apicase.support.CaseApiAllDataFn;
import com.doublekit.common.page.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class TestcaseServiceImpl implements TestcaseService {

    @Autowired
    TestcaseDao testcaseDao;

    @Autowired
    JoinTemplate joinTemplate;

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
    PreScriptCaseService preScriptCaseService;

    @Autowired
    AfterScriptCaseService afterScriptCaseService;

    @Autowired
    AssertCaseService assertCaseService;

    @Autowired
    CaseApiAllDataFn caseApiAllDataFn;

    @Override
    public String createTestcase(@NotNull @Valid Testcase testcase) {
        TestcaseEntity testcaseEntity = BeanMapper.map(testcase, TestcaseEntity.class);

        return testcaseDao.createTestcase(testcaseEntity);
    }

    @Override
    public String createTestcaseWithNest(Testcase testcase) {
        //创建主表
        String id = createTestcase(testcase);
        testcase.setId(id);

        //级联创建从表、子表

        //创建请求头
        List<RequestHeaderCase> requestHeaderCaseList = testcase.getRequestHeaderCaseList();
        if(requestHeaderCaseList != null){
            for(RequestHeaderCase requestHeaderCase:requestHeaderCaseList){
                requestHeaderCase.setTestcase(testcase);
                requestHeaderCaseService.createRequestHeaderCase(requestHeaderCase);
            }
        }

        //创建查询参数
        List<QueryParamCase> queryParamCaseList = testcase.getQueryParamCaseList();
        if(queryParamCaseList != null){
            for(QueryParamCase queryParamCase:queryParamCaseList){
                queryParamCase.setTestcase(testcase);
                queryParamCaseService.createQueryParamCase(queryParamCase);
            }
        }

        //创建请求体
        RequestBodyCase requestBodyCase = testcase.getRequestBodyCase();
        if(requestBodyCase != null){
            requestBodyCase.setId(id);
            requestBodyCase.setTestcase(testcase);
            requestBodyCaseService.createRequestBodyCase(requestBodyCase);

            //创建请求体-form参数
            List<FormParamCase> formParamCaseList = testcase.getFormParamCaseList();
            if(formParamCaseList != null){
                for(FormParamCase formParamCase:formParamCaseList){
                    formParamCase.setTestcase(testcase);
                    formParamCaseService.createFormParamCase(formParamCase);
                }
            }

            //创建请求体-formUrlencoded参数
            List<FormUrlencodedCase> formUrlencodedCaseList = testcase.getFormUrlencodedCaseList();
            if(formUrlencodedCaseList != null){
                for(FormUrlencodedCase formUrlencodedCase:formUrlencodedCaseList){
                    formUrlencodedCase.setTestcase(testcase);
                    formUrlencodedCaseService.createFormUrlencodedCase(formUrlencodedCase);
                }
            }

            //创建请求体-json参数
            List<JsonParamCase> jsonParamCaseList = testcase.getJsonParamCaseList();
            if(jsonParamCaseList != null){
                for(JsonParamCase jsonParamCase:jsonParamCaseList){
                    jsonParamCase.setTestcase(testcase);
                    jsonParamCaseService.createJsonParamCase(jsonParamCase);
                }
            }

            //创建请求体-raw参数
            RawParamCase rawParamCase = testcase.getRawParamCase();
            if(rawParamCase != null){
                rawParamCase.setId(id);
                rawParamCase.setTestcase(testcase);
                rawParamCaseService.createRawParamCase(rawParamCase);
            }
        }

        //创建前置脚本
        PreScriptCase preScriptCase = testcase.getPreScriptCase();
        if(preScriptCase != null){
            preScriptCase.setId(id);
            preScriptCase.setTestcase(testcase);
            preScriptCaseService.createPreScriptCase(preScriptCase);
        }

        //创建后置脚本
        AfterScriptCase afterScriptCase = testcase.getAfterScriptCase();
        if(afterScriptCase != null){
            afterScriptCase.setId(id);
            afterScriptCase.setTestcase(testcase);
            afterScriptCaseService.createAfterScriptCase(afterScriptCase);
        }

        //创建断言列表
        List<AssertCase> assertCaseList = testcase.getAssertCaseList();
        if(assertCaseList != null){
            for(AssertCase assertCase:assertCaseList){
                assertCase.setTestcase(testcase);
                assertCaseService.createAssertCase(assertCase);
            }
        }

        return id;
    }

    @Override
    public void updateTestcase(@NotNull @Valid Testcase testcase) {
        TestcaseEntity testcaseEntity = BeanMapper.map(testcase, TestcaseEntity.class);

        testcaseDao.updateTestcase(testcaseEntity);
    }

    @Override
    public void deleteTestcase(@NotNull String id) {
        testcaseDao.deleteTestcase(id);
    }

    @Override
    public Testcase findOne(String id) {
        TestcaseEntity testcaseEntity = testcaseDao.findTestcase(id);

        Testcase testcase = BeanMapper.map(testcaseEntity, Testcase.class);
        return testcase;
    }

    @Override
    public List<Testcase> findList(List<String> idList) {
        List<TestcaseEntity> testcaseEntityList =  testcaseDao.findTestcaseList(idList);

        List<Testcase> testcaseList =  BeanMapper.mapList(testcaseEntityList,Testcase.class);
        return testcaseList;
    }

    @Override
    public Testcase findTestcase(@NotNull String id) {
        Testcase testcase = findOne(id);

        caseApiAllDataFn.getData(testcase.getId(),testcase);

        joinTemplate.joinQuery(testcase);
        return testcase;
    }

    @Override
    public List<Testcase> findAllTestcase() {
        List<TestcaseEntity> testcaseEntityList =  testcaseDao.findAllTestcase();

        List<Testcase> testcaseList =  BeanMapper.mapList(testcaseEntityList,Testcase.class);

        joinTemplate.joinQuery(testcaseList);
        return testcaseList;
    }

    @Override
    public List<Testcase> findTestcaseList(TestcaseQuery testcaseQuery) {
        List<TestcaseEntity> testcaseEntityList = testcaseDao.findTestcaseList(testcaseQuery);

        List<Testcase> testcaseList = BeanMapper.mapList(testcaseEntityList,Testcase.class);

        joinTemplate.joinQuery(testcaseList);

        return testcaseList;
    }

    @Override
    public Pagination<Testcase> findTestcasePage(TestcaseQuery testcaseQuery) {

        Pagination<TestcaseEntity>  pagination = testcaseDao.findTestcasePage(testcaseQuery);

        List<Testcase> testcaseList = BeanMapper.mapList(pagination.getDataList(),Testcase.class);

        for(Testcase testcase:testcaseList){


            List<RequestHeaderCase> requestHeaderCaseList = requestHeaderCaseService.findRequestHeaderCaseList(new RequestHeaderCaseQuery().setTestcaseId(testcase.getId()));
            if(requestHeaderCaseList.size()>0){
                testcase.setRequestHeaderCaseList(requestHeaderCaseList);
            }


            List<QueryParamCase> queryParamCaseList = queryParamCaseService.findQueryParamCaseList(new QueryParamCaseQuery().setTestcaseId(testcase.getId()));
            if(requestHeaderCaseList.size()>0) {
                testcase.setQueryParamCaseList(queryParamCaseList);
            }

            RequestBodyCase requestBodyCase = requestBodyCaseService.findRequestBodyCase(testcase.getId());
            if(requestBodyCase !=null ) {
                testcase.setRequestBodyCase(requestBodyCase);
            }

            switch (requestBodyCase.getBodyType()){
                case "formdata":
                    List<FormParamCase> formParamCaseList = formParamCaseService.findFormParamCaseList(new FormParamCaseQuery().setTestcaseId(testcase.getId()));
                    if(formParamCaseList.size()>0) {
                        testcase.setFormParamCaseList(formParamCaseList);
                    }
                    break;
                case "formUrlencoded":
                    List<FormUrlencodedCase> formUrlencodedCaseList = formUrlencodedCaseService.findFormUrlencodedCaseList(new FormUrlencodedCaseQuery().setTestcaseId(testcase.getId()));
                    if(formUrlencodedCaseList.size()>0) {
                        testcase.setFormUrlencodedCaseList(formUrlencodedCaseList);
                    }
                    break;
                case "json":
                    List<JsonParamCase> jsonParamCaseListTree = jsonParamCaseService.findJsonParamCaseListTree(new JsonParamCaseQuery().setTestcaseId(testcase.getId()));
                    if(jsonParamCaseListTree.size()>0){
                        testcase.setJsonParamCaseList(jsonParamCaseListTree);
                    }
                    break;
                case "raw":
                    RawParamCase rawParamCase = rawParamCaseService.findRawParamCase(testcase.getId());
                    if(rawParamCase != null) {
                        testcase.setRawParamCase(rawParamCase);
                    }
                    break;
            }

            PreScriptCase preScriptCase = preScriptCaseService.findPreScriptCase(testcase.getId());
            if(preScriptCase !=null ) {
                testcase.setPreScriptCase(preScriptCase);
            }

            AfterScriptCase afterScriptCase = afterScriptCaseService.findAfterScriptCase(testcase.getId());
            if(afterScriptCase !=null ) {
                testcase.setAfterScriptCase(afterScriptCase);
            }

        }


        joinTemplate.joinQuery(testcaseList);

        return PaginationBuilder.build(pagination,testcaseList);
    }
}