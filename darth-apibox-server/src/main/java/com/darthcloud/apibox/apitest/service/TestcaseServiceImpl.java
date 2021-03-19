package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.dao.TestcaseDao;
import com.darthcloud.apibox.apitest.entity.TestcasePo;
import com.darthcloud.apibox.apitest.model.*;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.join.join.JoinQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* 用户服务业务处理
*/
@Service
public class TestcaseServiceImpl implements TestcaseService {

    @Autowired
    TestcaseDao testcaseDao;

    @Autowired
    JoinQuery joinQuery;

    @Autowired
    RequestHeaderCaseService requestHeaderCaseService;

    @Autowired
    QueryParamCaseService queryParamCaseService;

    @Autowired
    RequestBodyCaseService requestBodyCaseService;

    @Autowired
    FormParamCaseService formParamCaseService;

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

    @Override
    public String createTestcase(@NotNull @Valid Testcase testcase) {
        TestcasePo testcasePo = BeanMapper.map(testcase, TestcasePo.class);

        return testcaseDao.createTestcase(testcasePo);
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
        TestcasePo testcasePo = BeanMapper.map(testcase, TestcasePo.class);

        testcaseDao.updateTestcase(testcasePo);
    }

    @Override
    public void deleteTestcase(@NotNull String id) {
        testcaseDao.deleteTestcase(id);
    }

    @Override
    public Testcase findOne(String id) {
        TestcasePo testcasePo = testcaseDao.findTestcase(id);

        Testcase testcase = BeanMapper.map(testcasePo, Testcase.class);
        return testcase;
    }

    @Override
    public List<Testcase> findList(List<String> idList) {
        List<TestcasePo> testcasePoList =  testcaseDao.findTestcaseList(idList);

        List<Testcase> testcaseList =  BeanMapper.mapList(testcasePoList,Testcase.class);
        return testcaseList;
    }

    @Override
    public Testcase findTestcase(@NotNull String id) {
        Testcase testcase = findOne(id);

        joinQuery.queryOne(testcase);
        return testcase;
    }

    @Override
    public List<Testcase> findAllTestcase() {
        List<TestcasePo> testcasePoList =  testcaseDao.findAllTestcase();

        List<Testcase> testcaseList =  BeanMapper.mapList(testcasePoList,Testcase.class);

        joinQuery.queryList(testcaseList);
        return testcaseList;
    }

    @Override
    public List<Testcase> findTestcaseList(TestcaseQuery testcaseQuery) {
        List<TestcasePo> testcasePoList = testcaseDao.findTestcaseList(testcaseQuery);

        List<Testcase> testcaseList = BeanMapper.mapList(testcasePoList,Testcase.class);

        joinQuery.queryList(testcaseList);

        return testcaseList;
    }

    @Override
    public Pagination<List<Testcase>> findTestcasePage(TestcaseQuery testcaseQuery) {
        Pagination<List<Testcase>> pg = new Pagination<>();

        Pagination<List<TestcasePo>>  pagination = testcaseDao.findTestcasePage(testcaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Testcase> testcaseList = BeanMapper.mapList(pagination.getDataList(),Testcase.class);

        joinQuery.queryList(testcaseList);

        pg.setDataList(testcaseList);
        return pg;
    }
}