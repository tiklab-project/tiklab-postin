package net.tiklab.postin.apitest.http.httpcase.service;

import net.tiklab.postin.apidef.http.model.ApiRequest;
import net.tiklab.postin.apidef.http.service.ApiRequestService;
import net.tiklab.postin.apitest.http.httpcase.dao.HttpTestcaseDao;
import net.tiklab.postin.apitest.http.httpcase.entity.HttpTestcaseEntity;
import net.tiklab.postin.apitest.http.httpcase.model.*;
import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.postin.apitest.http.httpcase.model.*;
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


    @Override
    public String createTestcase(@NotNull @Valid HttpTestcase httpTestcase) {
        HttpTestcaseEntity httpTestcaseEntity = BeanMapper.map(httpTestcase, HttpTestcaseEntity.class);

        String id = httpTestcaseDao.createTestcase(httpTestcaseEntity);

        //初始化body
        RequestCase requestCase = new RequestCase();
        requestCase.setId(id);
        requestCase.setHttpCaseId(id);
        //用例请求体跟随接口定义
        ApiRequest apiRequest = apiRequestService.findApiRequest(httpTestcase.getHttp().getId());
        requestCase.setBodyType(apiRequest.getBodyType());
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
            List<JsonParamCase> jsonParamCaseList = httpTestcase.getJsonList();
            if(jsonParamCaseList != null){
                for(JsonParamCase jsonParamCase:jsonParamCaseList){
                    jsonParamCase.setHttpCase(httpTestcase);
                    jsonParamCaseService.createJsonParamCase(jsonParamCase);
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
        List<AssertCase> assertCaseList = httpTestcase.getAssertList();
        if(assertCaseList != null){
            for(AssertCase assertCase:assertCaseList){
                assertCase.setHttpCase(httpTestcase);
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
            List<JsonParamCase> jsonParamCaseList = jsonParamCaseService.findJsonParamCaseList(new JsonParamCaseQuery().setHttpCaseId(testcaseId));
            if(CollectionUtils.isNotEmpty(jsonParamCaseList)){
                httpTestcase.setJsonList(jsonParamCaseList);
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

        return httpTestcaseList;
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
                        List<JsonParamCase> jsonParamCaseListTree = jsonParamCaseService.findJsonParamCaseListTree(new JsonParamCaseQuery().setHttpCaseId(httpTestcase.getId()));
                        if(jsonParamCaseListTree.size()>0){
                            httpTestcase.setJsonList(jsonParamCaseListTree);
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