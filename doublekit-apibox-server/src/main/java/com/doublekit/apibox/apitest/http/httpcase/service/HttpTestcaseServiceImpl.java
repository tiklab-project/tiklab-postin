package com.doublekit.apibox.apitest.http.httpcase.service;

import com.doublekit.apibox.apitest.http.httpcase.dao.HttpTestcaseDao;
import com.doublekit.apibox.apitest.http.httpcase.entity.HttpTestcaseEntity;
import com.doublekit.apibox.apitest.http.httpcase.model.*;
import com.doublekit.common.page.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class HttpTestcaseServiceImpl implements TestcaseService {

    @Autowired
    HttpTestcaseDao httpTestcaseDao;

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


    @Override
    public String createTestcase(@NotNull @Valid HttpTestcase httpTestcase) {
        HttpTestcaseEntity httpTestcaseEntity = BeanMapper.map(httpTestcase, HttpTestcaseEntity.class);

        return httpTestcaseDao.createTestcase(httpTestcaseEntity);
    }

    @Override
    public String createTestcaseWithNest(HttpTestcase httpTestcase) {
        //创建主表
        String id = createTestcase(httpTestcase);
        httpTestcase.setId(id);

        //级联创建从表、子表

        //创建请求头
        List<RequestHeaderCase> requestHeaderCaseList = httpTestcase.getRequestHeaderCaseList();
        if(requestHeaderCaseList != null){
            for(RequestHeaderCase requestHeaderCase:requestHeaderCaseList){
                requestHeaderCase.setTestcase(httpTestcase);
                requestHeaderCaseService.createRequestHeaderCase(requestHeaderCase);
            }
        }

        //创建查询参数
        List<QueryParamCase> queryParamCaseList = httpTestcase.getQueryParamCaseList();
        if(queryParamCaseList != null){
            for(QueryParamCase queryParamCase:queryParamCaseList){
                queryParamCase.setTestcase(httpTestcase);
                queryParamCaseService.createQueryParamCase(queryParamCase);
            }
        }

        //创建请求体
        RequestBodyCase requestBodyCase = httpTestcase.getRequestBodyCase();
        if(requestBodyCase != null){
            requestBodyCase.setId(id);
            requestBodyCase.setTestcase(httpTestcase);
            requestBodyCaseService.createRequestBodyCase(requestBodyCase);

            //创建请求体-form参数
            List<FormParamCase> formParamCaseList = httpTestcase.getFormParamCaseList();
            if(formParamCaseList != null){
                for(FormParamCase formParamCase:formParamCaseList){
                    formParamCase.setTestcase(httpTestcase);
                    formParamCaseService.createFormParamCase(formParamCase);
                }
            }

            //创建请求体-formUrlencoded参数
            List<FormUrlencodedCase> formUrlencodedCaseList = httpTestcase.getFormUrlencodedCaseList();
            if(formUrlencodedCaseList != null){
                for(FormUrlencodedCase formUrlencodedCase:formUrlencodedCaseList){
                    formUrlencodedCase.setTestcase(httpTestcase);
                    formUrlencodedCaseService.createFormUrlencodedCase(formUrlencodedCase);
                }
            }

            //创建请求体-json参数
            List<JsonParamCase> jsonParamCaseList = httpTestcase.getJsonParamCaseList();
            if(jsonParamCaseList != null){
                for(JsonParamCase jsonParamCase:jsonParamCaseList){
                    jsonParamCase.setTestcase(httpTestcase);
                    jsonParamCaseService.createJsonParamCase(jsonParamCase);
                }
            }

            //创建请求体-raw参数
            RawParamCase rawParamCase = httpTestcase.getRawParamCase();
            if(rawParamCase != null){
                rawParamCase.setId(id);
                rawParamCase.setTestcase(httpTestcase);
                rawParamCaseService.createRawParamCase(rawParamCase);
            }
        }

        //创建前置脚本
        PreScriptCase preScriptCase = httpTestcase.getPreScriptCase();
        if(preScriptCase != null){
            preScriptCase.setId(id);
            preScriptCase.setTestcase(httpTestcase);
            preScriptCaseService.createPreScriptCase(preScriptCase);
        }

        //创建后置脚本
        AfterScriptCase afterScriptCase = httpTestcase.getAfterScriptCase();
        if(afterScriptCase != null){
            afterScriptCase.setId(id);
            afterScriptCase.setTestcase(httpTestcase);
            afterScriptCaseService.createAfterScriptCase(afterScriptCase);
        }

        //创建断言列表
        List<AssertCase> assertCaseList = httpTestcase.getAssertCaseList();
        if(assertCaseList != null){
            for(AssertCase assertCase:assertCaseList){
                assertCase.setTestcase(httpTestcase);
                assertCaseService.createAssertCase(assertCase);
            }
        }

        return id;
    }

    @Override
    public void updateTestcase(@NotNull @Valid HttpTestcase httpTestcase) {
        HttpTestcaseEntity httpTestcaseEntity = BeanMapper.map(httpTestcase, HttpTestcaseEntity.class);

        httpTestcaseDao.updateTestcase(httpTestcaseEntity);
    }

    @Override
    public void deleteTestcase(@NotNull String id) {
        httpTestcaseDao.deleteTestcase(id);
    }

    @Override
    public HttpTestcase findOne(String id) {
        HttpTestcaseEntity httpTestcaseEntity = httpTestcaseDao.findTestcase(id);

        HttpTestcase httpTestcase = BeanMapper.map(httpTestcaseEntity, HttpTestcase.class);
        return httpTestcase;
    }

    @Override
    public List<HttpTestcase> findList(List<String> idList) {
        List<HttpTestcaseEntity> httpTestcaseEntityList =  httpTestcaseDao.findTestcaseList(idList);

        List<HttpTestcase> httpTestcaseList =  BeanMapper.mapList(httpTestcaseEntityList, HttpTestcase.class);
        return httpTestcaseList;
    }

    @Override
    public HttpTestcase findTestcase(@NotNull String id) {
        HttpTestcase httpTestcase = findOne(id);
        String testcaseId = httpTestcase.getId();

        //获取请求头中的数据
        List<RequestHeaderCase> requestHeaderCaseList = requestHeaderCaseService.findRequestHeaderCaseList(new RequestHeaderCaseQuery().setTestcaseId(testcaseId));
        if(CollectionUtils.isNotEmpty(requestHeaderCaseList)){
            httpTestcase.setRequestHeaderCaseList(requestHeaderCaseList);
        }

        //获取查询参数的数据
        List<QueryParamCase> queryParamCaseList = queryParamCaseService.findQueryParamCaseList(new QueryParamCaseQuery().setTestcaseId(testcaseId));
        if(CollectionUtils.isNotEmpty(queryParamCaseList)){
            httpTestcase.setQueryParamCaseList(queryParamCaseList);
        }

        //获取请求体的类型
        RequestBodyCase requestBodyCase = requestBodyCaseService.findRequestBodyCase(testcaseId);
        httpTestcase.setRequestBodyCase(requestBodyCase);
        String bodyType = requestBodyCase.getBodyType();


        if(bodyType.equals("formdata")){
            //获取formdata数据
            List<FormParamCase> formParamCaseList = formParamCaseService.findFormParamCaseList(new FormParamCaseQuery().setTestcaseId(testcaseId));
            if(CollectionUtils.isNotEmpty(formParamCaseList)){
                httpTestcase.setFormParamCaseList(formParamCaseList);
            }

        }else if(bodyType.equals("formUrlencoded")){
            //获取formurlencoded数据
            List<FormUrlencodedCase> formUrlencodedCaseList = formUrlencodedCaseService.findFormUrlencodedCaseList(new FormUrlencodedCaseQuery().setTestcaseId(testcaseId));
            if(CollectionUtils.isNotEmpty(formUrlencodedCaseList)){
                httpTestcase.setFormUrlencodedCaseList(formUrlencodedCaseList);
            }

        }else if(bodyType.equals("json")){
            //获取json数据
            List<JsonParamCase> jsonParamCaseList = jsonParamCaseService.findJsonParamCaseList(new JsonParamCaseQuery().setTestcaseId(testcaseId));
            if(CollectionUtils.isNotEmpty(jsonParamCaseList)){
                httpTestcase.setJsonParamCaseList(jsonParamCaseList);
            }

        }else if(bodyType.equals("raw")){
            //获取raw数据
            RawParamCase rawParamCase = rawParamCaseService.findRawParamCase(testcaseId);
            if(!ObjectUtils.isEmpty(rawParamCase)){
                httpTestcase.setRawParamCase(rawParamCase);
            }

        }

        //获取前置脚本数据
        PreScriptCase preScriptCase = preScriptCaseService.findPreScriptCase(testcaseId);
        if(!ObjectUtils.isEmpty(preScriptCase)){
            httpTestcase.setPreScriptCase(preScriptCase);
        }

        //获取后置脚本数据
        AfterScriptCase afterScriptCase = afterScriptCaseService.findAfterScriptCase(testcaseId);
        if(!ObjectUtils.isEmpty(afterScriptCase)){
            httpTestcase.setAfterScriptCase(afterScriptCase);
        }

        joinTemplate.joinQuery(httpTestcase);
        return httpTestcase;
    }

    @Override
    public List<HttpTestcase> findAllTestcase() {
        List<HttpTestcaseEntity> httpTestcaseEntityList =  httpTestcaseDao.findAllTestcase();

        List<HttpTestcase> httpTestcaseList =  BeanMapper.mapList(httpTestcaseEntityList, HttpTestcase.class);

        joinTemplate.joinQuery(httpTestcaseList);
        return httpTestcaseList;
    }

    @Override
    public List<HttpTestcase> findTestcaseList(HttpTestcaseQuery httpTestcaseQuery) {
        List<HttpTestcaseEntity> httpTestcaseEntityList = httpTestcaseDao.findTestcaseList(httpTestcaseQuery);

        List<HttpTestcase> httpTestcaseList = BeanMapper.mapList(httpTestcaseEntityList, HttpTestcase.class);

        joinTemplate.joinQuery(httpTestcaseList);

        return httpTestcaseList;
    }

    @Override
    public Pagination<HttpTestcase> findTestcasePage(HttpTestcaseQuery httpTestcaseQuery) {

        Pagination<HttpTestcaseEntity>  pagination = httpTestcaseDao.findTestcasePage(httpTestcaseQuery);

        List<HttpTestcase> httpTestcaseList = BeanMapper.mapList(pagination.getDataList(), HttpTestcase.class);

        for(HttpTestcase httpTestcase : httpTestcaseList){


            List<RequestHeaderCase> requestHeaderCaseList = requestHeaderCaseService.findRequestHeaderCaseList(new RequestHeaderCaseQuery().setTestcaseId(httpTestcase.getId()));
            if(requestHeaderCaseList.size()>0){
                httpTestcase.setRequestHeaderCaseList(requestHeaderCaseList);
            }


            List<QueryParamCase> queryParamCaseList = queryParamCaseService.findQueryParamCaseList(new QueryParamCaseQuery().setTestcaseId(httpTestcase.getId()));
            if(requestHeaderCaseList.size()>0) {
                httpTestcase.setQueryParamCaseList(queryParamCaseList);
            }

            RequestBodyCase requestBodyCase = requestBodyCaseService.findRequestBodyCase(httpTestcase.getId());
            if(requestBodyCase !=null ) {
                httpTestcase.setRequestBodyCase(requestBodyCase);

                switch (requestBodyCase.getBodyType()){
                    case "formdata":
                        List<FormParamCase> formParamCaseList = formParamCaseService.findFormParamCaseList(new FormParamCaseQuery().setTestcaseId(httpTestcase.getId()));
                        if(formParamCaseList.size()>0) {
                            httpTestcase.setFormParamCaseList(formParamCaseList);
                        }
                        break;
                    case "formUrlencoded":
                        List<FormUrlencodedCase> formUrlencodedCaseList = formUrlencodedCaseService.findFormUrlencodedCaseList(new FormUrlencodedCaseQuery().setTestcaseId(httpTestcase.getId()));
                        if(formUrlencodedCaseList.size()>0) {
                            httpTestcase.setFormUrlencodedCaseList(formUrlencodedCaseList);
                        }
                        break;
                    case "json":
                        List<JsonParamCase> jsonParamCaseListTree = jsonParamCaseService.findJsonParamCaseListTree(new JsonParamCaseQuery().setTestcaseId(httpTestcase.getId()));
                        if(jsonParamCaseListTree.size()>0){
                            httpTestcase.setJsonParamCaseList(jsonParamCaseListTree);
                        }
                        break;
                    case "raw":
                        RawParamCase rawParamCase = rawParamCaseService.findRawParamCase(httpTestcase.getId());
                        if(rawParamCase != null) {
                            httpTestcase.setRawParamCase(rawParamCase);
                        }
                        break;
                }
            }


            PreScriptCase preScriptCase = preScriptCaseService.findPreScriptCase(httpTestcase.getId());
            if(preScriptCase !=null ) {
                httpTestcase.setPreScriptCase(preScriptCase);
            }

            AfterScriptCase afterScriptCase = afterScriptCaseService.findAfterScriptCase(httpTestcase.getId());
            if(afterScriptCase !=null ) {
                httpTestcase.setAfterScriptCase(afterScriptCase);
            }

        }


        joinTemplate.joinQuery(httpTestcaseList);

        return PaginationBuilder.build(pagination, httpTestcaseList);
    }
}