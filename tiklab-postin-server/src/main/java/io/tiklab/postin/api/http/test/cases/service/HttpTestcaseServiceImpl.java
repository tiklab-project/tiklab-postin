package io.tiklab.postin.api.http.test.cases.service;

import io.tiklab.postin.api.http.definition.model.ApiRequest;
import io.tiklab.postin.api.http.definition.model.RawParams;
import io.tiklab.postin.api.http.definition.service.ApiRequestService;
import io.tiklab.postin.api.http.definition.service.RawParamService;
import io.tiklab.postin.api.http.test.cases.dao.HttpTestcaseDao;
import io.tiklab.postin.api.http.test.cases.entity.HttpTestcaseEntity;
import io.tiklab.postin.api.http.test.cases.model.*;
import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.postin.api.http.test.instance.model.HttpInstance;
import io.tiklab.postin.api.http.test.instance.model.HttpInstanceQuery;
import io.tiklab.postin.api.http.test.instance.service.TestInstanceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* 用户服务业务处理
*/
@Service
public class HttpTestcaseServiceImpl implements HttpTestcaseService {

    @Autowired
    HttpTestcaseDao httpTestcaseDao;

    @Autowired
    ApiRequestService apiRequestService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    RequestHeaderCaseService requestHeaderCaseService;

    @Autowired
    QueryParamCaseService queryParamCaseService;

    @Autowired
    RequestCaseService requestCaseService;

    @Autowired
    FormParamCaseService formParamCaseService;

    @Autowired
    FormUrlencodedCaseService formUrlencodedCaseService;

    @Autowired
    JsonParamCaseService jsonParamCaseService;

    @Autowired
    RawParamCaseService rawParamCaseService;

    @Autowired
    AssertCaseService assertCaseService;


    @Autowired
    RawParamService rawParamService;

    @Autowired
    TestInstanceService testInstanceService;


    @Override
    public String createTestcase(@NotNull @Valid HttpTestcase httpTestcase) {
        HttpTestcaseEntity httpTestcaseEntity = BeanMapper.map(httpTestcase, HttpTestcaseEntity.class);

        String id = httpTestcaseDao.createTestcase(httpTestcaseEntity);

        //初始化
        RequestCase requestCase = new RequestCase();
        requestCase.setId(id);
        requestCase.setHttpCaseId(id);
        //用例请求体跟随接口定义
        ApiRequest apiRequest = apiRequestService.findApiRequest(httpTestcase.getHttp().getId());
        requestCase.setBodyType(apiRequest.getBodyType());

        //如果是bodyType 是raw 类型，需要创建rawParam
        if(Objects.equals(apiRequest.getBodyType(),"raw")){
            RawParamCase rawParamCase = new RawParamCase();
            rawParamCase.setId(id);
            httpTestcase.setId(id);
            rawParamCase.setHttpCase(httpTestcase);

            //用例请求体中的raw里的类型根据接口定义里的设置
            RawParams rawParams = rawParamService.findRawParam(httpTestcase.getHttp().getId());
            rawParamCase.setType(rawParams.getType());

            rawParamCaseService.createRawParamCase(rawParamCase);
        }

        requestCaseService.createRequestCase(requestCase);

        return id;
    }

    @Override
    public String createTestcaseWithNest(HttpTestcase httpTestcase) {
        //创建主表
        HttpTestcaseEntity httpTestcaseEntity = BeanMapper.map(httpTestcase, HttpTestcaseEntity.class);
        String id = httpTestcaseDao.createTestcase(httpTestcaseEntity);
        httpTestcase.setId(id);

        //级联创建从表、子表
        //创建请求头
        List<RequestHeaderCase> requestHeaderCaseList = httpTestcase.getHeaderList();
        if(requestHeaderCaseList != null){
            for(RequestHeaderCase requestHeaderCase:requestHeaderCaseList){
                requestHeaderCase.setHttpCase(httpTestcase);
                requestHeaderCaseService.createRequestHeaderCase(requestHeaderCase);
            }
        }

        //创建查询参数
        List<QueryParamCase> queryParamCaseList = httpTestcase.getQueryList();
        if(queryParamCaseList != null){
            for(QueryParamCase queryParamCase:queryParamCaseList){
                queryParamCase.setHttpCase(httpTestcase);
                queryParamCaseService.createQueryParamCase(queryParamCase);
            }
        }

        //创建请求体
        RequestCase requestCase = httpTestcase.getRequest();
        if(requestCase != null){
            requestCase.setId(id);
            requestCase.setHttpCaseId(id);
            requestCaseService.createRequestCase(requestCase);

            //创建请求体-form参数
            List<FormParamCase> formParamCaseList = httpTestcase.getFormList();
            if(formParamCaseList != null){
                for(FormParamCase formParamCase:formParamCaseList){
                    formParamCase.setHttpCase(httpTestcase);
                    formParamCaseService.createFormParamCase(formParamCase);
                }
            }

            //创建请求体-formUrlencoded参数
            List<FormUrlencodedCase> formUrlencodedCaseList = httpTestcase.getUrlencodedList();
            if(formUrlencodedCaseList != null){
                for(FormUrlencodedCase formUrlencodedCase:formUrlencodedCaseList){
                    formUrlencodedCase.setHttpCase(httpTestcase);
                    formUrlencodedCaseService.createFormUrlencodedCase(formUrlencodedCase);
                }
            }

            //创建请求体-json参数
            List<JsonParamCases> jsonParamCasesList = httpTestcase.getJsonList();
            if(jsonParamCasesList != null){
                for(JsonParamCases jsonParamCases : jsonParamCasesList){
                    jsonParamCases.setHttpCase(httpTestcase);
                    jsonParamCaseService.createJsonParamCase(jsonParamCases);
                }
            }

            //创建请求体-raw参数
            RawParamCase rawParamCase = httpTestcase.getRawParamCase();
            if(rawParamCase != null){
                rawParamCase.setId(id);
                rawParamCase.setHttpCase(httpTestcase);
                rawParamCaseService.createRawParamCase(rawParamCase);
            }
        }

        //创建断言列表
        List<AssertCases> assertCasesList = httpTestcase.getAssertList();
        if(assertCasesList != null){
            for(AssertCases assertCases : assertCasesList){
                assertCases.setHttpCase(httpTestcase);
                assertCaseService.createAssertCase(assertCases);
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
        List<RequestHeaderCase> requestHeaderCaseList = requestHeaderCaseService.findRequestHeaderCaseList(new RequestHeaderCaseQuery().setHttpCaseId(testcaseId));
        if(CollectionUtils.isNotEmpty(requestHeaderCaseList)){
            httpTestcase.setHeaderList(requestHeaderCaseList);
        }

        //获取查询参数的数据
        List<QueryParamCase> queryParamCaseList = queryParamCaseService.findQueryParamCaseList(new QueryParamCaseQuery().setHttpCaseId(testcaseId));
        if(CollectionUtils.isNotEmpty(queryParamCaseList)){
            httpTestcase.setQueryList(queryParamCaseList);
        }

        //获取请求体的类型
        RequestCase requestCase = requestCaseService.findRequestCase(testcaseId);
        httpTestcase.setRequest(requestCase);
        String bodyType = requestCase.getBodyType();


        if(bodyType.equals("formdata")){
            //获取formdata数据
            List<FormParamCase> formParamCaseList = formParamCaseService.findFormParamCaseList(new FormParamCaseQuery().setHttpCaseId(testcaseId));
            if(CollectionUtils.isNotEmpty(formParamCaseList)){
                httpTestcase.setFormList(formParamCaseList);
            }

        }else if(bodyType.equals("formUrlencoded")){
            //获取formurlencoded数据
            List<FormUrlencodedCase> formUrlencodedCaseList = formUrlencodedCaseService.findFormUrlencodedCaseList(new FormUrlencodedCaseQuery().setHttpCaseId(testcaseId));
            if(CollectionUtils.isNotEmpty(formUrlencodedCaseList)){
                httpTestcase.setUrlencodedList(formUrlencodedCaseList);
            }

        }else if(bodyType.equals("json")){
            //获取json数据
            List<JsonParamCases> jsonParamCasesList = jsonParamCaseService.findJsonParamCaseList(new JsonParamCaseQuery().setHttpCaseId(testcaseId));
            if(CollectionUtils.isNotEmpty(jsonParamCasesList)){
                httpTestcase.setJsonList(jsonParamCasesList);
            }

        }else if(bodyType.equals("raw")){
            //获取raw数据
            RawParamCase rawParamCase = rawParamCaseService.findRawParamCase(testcaseId);
            if(!ObjectUtils.isEmpty(rawParamCase)){
                httpTestcase.setRawParamCase(rawParamCase);
            }

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

        //用于存处理后的list
        ArrayList<HttpTestcase> newArray = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(httpTestcaseList)){
            for(HttpTestcase httpTestcase: httpTestcaseList){

                HttpInstanceQuery httpInstanceQuery = new HttpInstanceQuery();
                httpInstanceQuery.setHttpCaseId(httpTestcase.getId());
                List<HttpInstance> testInstanceList = testInstanceService.findTestInstanceList(httpInstanceQuery);
                if(CollectionUtils.isNotEmpty(testInstanceList)){
                    //如果有历史记录，获取最近一条历史记录的结果
                    HttpInstance httpInstance = testInstanceList.get(0);

                    httpTestcase.setResult(httpInstance.getResult());
                }else {
                    httpTestcase.setResult(-1);
                }

                newArray.add(httpTestcase);
            }
        }


        return newArray;
    }

    @Override
    public Pagination<HttpTestcase> findTestcasePage(HttpTestcaseQuery httpTestcaseQuery) {

        Pagination<HttpTestcaseEntity>  pagination = httpTestcaseDao.findTestcasePage(httpTestcaseQuery);

        List<HttpTestcase> httpTestcaseList = BeanMapper.mapList(pagination.getDataList(), HttpTestcase.class);

        for(HttpTestcase httpTestcase : httpTestcaseList){


            List<RequestHeaderCase> requestHeaderCaseList = requestHeaderCaseService.findRequestHeaderCaseList(new RequestHeaderCaseQuery().setHttpCaseId(httpTestcase.getId()));
            if(requestHeaderCaseList.size()>0){
                httpTestcase.setHeaderList(requestHeaderCaseList);
            }


            List<QueryParamCase> queryParamCaseList = queryParamCaseService.findQueryParamCaseList(new QueryParamCaseQuery().setHttpCaseId(httpTestcase.getId()));
            if(requestHeaderCaseList.size()>0) {
                httpTestcase.setQueryList(queryParamCaseList);
            }

            RequestCase requestCase = requestCaseService.findRequestCase(httpTestcase.getId());
            if(requestCase !=null&&!requestCase.getBodyType().equals("none") ) {
                httpTestcase.setRequest(requestCase);

                switch (requestCase.getBodyType()){
                    case "formdata":
                        List<FormParamCase> formParamCaseList = formParamCaseService.findFormParamCaseList(new FormParamCaseQuery().setHttpCaseId(httpTestcase.getId()));
                        if(formParamCaseList.size()>0) {
                            httpTestcase.setFormList(formParamCaseList);
                        }
                        break;
                    case "formUrlencoded":
                        List<FormUrlencodedCase> formUrlencodedCaseList = formUrlencodedCaseService.findFormUrlencodedCaseList(new FormUrlencodedCaseQuery().setHttpCaseId(httpTestcase.getId()));
                        if(formUrlencodedCaseList.size()>0) {
                            httpTestcase.setUrlencodedList(formUrlencodedCaseList);
                        }
                        break;
                    case "json":
                        List<JsonParamCases> jsonParamCasesListTree = jsonParamCaseService.findJsonParamCaseListTree(new JsonParamCaseQuery().setHttpCaseId(httpTestcase.getId()));
                        if(jsonParamCasesListTree.size()>0){
                            httpTestcase.setJsonList(jsonParamCasesListTree);
                        }
                        break;
                    case "raw":
                        RawParamCase rawParamCase = rawParamCaseService.findRawParamCase(httpTestcase.getId());
                        if(rawParamCase != null) {
                            httpTestcase.setRawParamCase(rawParamCase);
                        }
                        break;
                    default:
                        break;
                }
            }


        }


        joinTemplate.joinQuery(httpTestcaseList);

        return PaginationBuilder.build(pagination, httpTestcaseList);
    }
}