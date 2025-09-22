package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.postin.autotest.http.scene.cases.service.ApiSceneStepService;
import io.tiklab.postin.autotest.http.unit.cases.dao.ApiUnitCaseDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.ApiUnitCaseEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.*;
import io.tiklab.postin.autotest.http.unit.mock.JsonGenerator;
import io.tiklab.postin.autotest.instance.service.InstanceService;
import io.tiklab.postin.autotest.test.model.TestCase;
import io.tiklab.postin.autotest.test.model.TestCaseQuery;
import io.tiklab.postin.autotest.test.service.TestCaseService;
import io.tiklab.postin.autotest.testplan.cases.service.TestPlanCaseService;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
* 接口单元 服务
*/
@Service
public class ApiUnitCaseServiceImpl implements ApiUnitCaseService {

    @Autowired
    ApiUnitCaseDao apiApiUnitCaseDao;

    @Autowired
    JoinTemplate joinTemplate;
    
    @Autowired
    TestCaseService testCaseService;

    @Autowired
    RequestHeaderUnitService requestHeaderUnitService;

    @Autowired
    RequestBodyUnitService requestBodyUnitService;

    @Autowired
    QueryParamUnitService queryParamUnitService;

    @Autowired
    PathParamUnitService pathParamUnitService;

    @Autowired
    RawParamUnitService rawParamUnitService;

    @Autowired
    FormParamUnitService formParamUnitService;

    @Autowired
    FormUrlencodedUnitService formUrlencodedUnitService;

    @Autowired
    JsonParamUnitService jsonParamUnitService;

    @Autowired
    AuthParamUnitService authParamUnitService;

    @Autowired
    PreParamUnitService preParamUnitService;

    @Autowired
    AfterParamUnitService afterParamUnitService;

    @Autowired
    ResponseResultUnitServiceImpl responseResultService;

    @Autowired
    ResponseHeaderUnitService responseHeaderUnitService;

    @Autowired
    AssertUnitService assertUnitService;

    @Autowired
    InstanceService instanceService;

    @Autowired
    ApiSceneStepService apiSceneStepService;

    @Autowired
    TestPlanCaseService testPlanCaseService;

    @Override
    public String createApiUnitCase(@NotNull @Valid ApiUnitCase apiUnitCase) {
        //添加apiUnit 返回id
        ApiUnitCaseEntity apiUnitCaseEntity = BeanMapper.map(apiUnitCase, ApiUnitCaseEntity.class);
        String id = apiApiUnitCaseDao.createApiUnitCase(apiUnitCaseEntity);

        //把testCaseId 设置成与 apiUnitId 一样
        apiUnitCaseEntity.setTestCaseId(id);
        apiUnitCaseEntity.setId(id);
        apiApiUnitCaseDao.updateApiUnitCase(apiUnitCaseEntity);

        //初始化请求响应中的类型
        RequestBodyUnit requestBodyUnit = new RequestBodyUnit();
        requestBodyUnit.setId(id);
        requestBodyUnit.setApiUnitId(id);
        requestBodyUnit.setBodyType("none");
        requestBodyUnitService.createRequestBody(requestBodyUnit);

        //初始化响应结果
        ResponseResultUnit responseResultUnit = new ResponseResultUnit();
        responseResultUnit.setId(id);
        responseResultUnit.setApiUnitId(id);
        responseResultUnit.setHttpCode(200);
        responseResultUnit.setName("成功");
        responseResultUnit.setDataType("json");
        responseResultUnit.setJsonText("{\"type\": \"object\",\"properties\": {}}");
        responseResultService.createResponseResult(responseResultUnit);


        //添加testCase
        TestCase testCase = apiUnitCase.getTestCase();
        testCase.setId(id);
        testCaseService.createTestCase(testCase);

        return id;
    }

    @Override
    public void updateApiUnitCase(@NotNull @Valid ApiUnitCase apiUnitCase) {
        ApiUnitCaseEntity apiUnitCaseEntity = BeanMapper.map(apiUnitCase, ApiUnitCaseEntity.class);

        apiUnitCaseEntity.setTestCaseId(apiUnitCase.getId());
        apiApiUnitCaseDao.updateApiUnitCase(apiUnitCaseEntity);

        testCaseService.updateTestCase(apiUnitCase.getTestCase());
    }

    @Override
    public void deleteApiUnitCase(@NotNull String id) {
        //删除下面instance所有子表
//        apiUnitInstanceService.deleteApiUnitInstanceByApiUnitId(id);

        //定义
        apiApiUnitCaseDao.deleteApiUnitCase(id);
        requestHeaderUnitService.deleteAllRequestHeader(id);
        queryParamUnitService.deleteAllQueryParam(id);
        formParamUnitService.deleteAllFormParam(id);
        formUrlencodedUnitService.deleteAllFormUrlencoded(id);
        jsonParamUnitService.deleteAllJsonParam(id);
        rawParamUnitService.deleteAllRawParam(id);
        preParamUnitService.deleteAllPreParamUnit(id);
        afterParamUnitService.deleteAllAfterParamUnit(id);
        assertUnitService.deleteAllAssertCase(id);
        requestBodyUnitService.deleteRequestBody(id);
        responseHeaderUnitService.deleteAllResponseHeader(id);
        responseResultService.deleteResponseResult(id);

        //历史
        instanceService.deleteAllInstance(id);

    }

    @Override
    public ApiUnitCase findOne(String id) {
        ApiUnitCaseEntity apiUnitCase = apiApiUnitCaseDao.findApiUnitCase(id);

        ApiUnitCase apiUnit = BeanMapper.map(apiUnitCase, ApiUnitCase.class);
        return apiUnit;
    }

    @Override
    public List<ApiUnitCase> findList(List<String> idList) {
        List<ApiUnitCaseEntity> apiUnitCaseList =  apiApiUnitCaseDao.findApiUnitCaseList(idList);

        List<ApiUnitCase> apiUnitList =  BeanMapper.mapList(apiUnitCaseList, ApiUnitCase.class);
        return apiUnitList;
    }

    @Override
    public ApiUnitCase findApiUnitCase(@NotNull String id) {
        ApiUnitCase apiUnitCase = findOne(id);

        TestCase testCase = testCaseService.findTestCase(id);
        apiUnitCase.setTestCase(testCase);

        return apiUnitCase;
    }

    @Override
    public List<ApiUnitCase> findAllApiUnitCase() {
        List<ApiUnitCaseEntity> apiUnitCaseList =  apiApiUnitCaseDao.findAllApiUnitCase();


        List<ApiUnitCase> apiUnitList =  BeanMapper.mapList(apiUnitCaseList, ApiUnitCase.class);

        joinTemplate.joinQuery(apiUnitList);
        return apiUnitList;
    }

    @Override
    public List<ApiUnitCase> findApiUnitCaseList(ApiUnitCaseQuery apiUnitCaseQuery) {
        List<ApiUnitCaseEntity> apiUnitCaseList = apiApiUnitCaseDao.findApiUnitCaseList(apiUnitCaseQuery);

        List<ApiUnitCase> testCases = BeanMapper.mapList(apiUnitCaseList, ApiUnitCase.class);

        joinTemplate.joinQuery(testCases);

        return testCases;
    }

    @Override
    public Boolean isApiUnitBind(String id){
        Boolean apiSceneStepExist = apiSceneStepService.isApiUnitExist(id);

        Boolean caseExist = testPlanCaseService.isCaseExist(id);

        if(apiSceneStepExist||caseExist){
            return true;
        }else {
            return false;
        }
    }


    @Override
    public List<ApiUnitCase> findApiUnitCaseListByTestCase(TestCaseQuery testCaseQuery) {

        List<TestCase> testCaseList = testCaseService.findTestCaseList(testCaseQuery);

        List<ApiUnitCase> apiUnitCaseList=new ArrayList<>();

        if(!ObjectUtils.isEmpty(testCaseList)){
            for (TestCase testCase : testCaseList){
                List<ApiUnitCase> caseList = findApiUnitCaseList(new ApiUnitCaseQuery().setTestCaseId(testCase.getId()));
               if (!ObjectUtils.isEmpty(caseList)){
                   apiUnitCaseList.add(caseList.get(0));
               }
            }
        }

        return apiUnitCaseList;
    }



    @Override
    public Pagination<ApiUnitCase> findApiUnitCasePage(ApiUnitCaseQuery testCaseQuery) {
        Pagination<ApiUnitCaseEntity>  pagination = apiApiUnitCaseDao.findApiUnitCasePage(testCaseQuery);

        List<ApiUnitCase> apiUnitCaseList = BeanMapper.mapList(pagination.getDataList(), ApiUnitCase.class);

        joinTemplate.joinQuery(apiUnitCaseList);

        return PaginationBuilder.build(pagination,apiUnitCaseList);
    }




    @Override
    public ApiUnitCaseDataConstruction findApiUnitCaseExt(ApiUnitCase apiUnitCase) {
        ApiUnitCaseDataConstruction apiUnitCaseDataConstruction = new ApiUnitCaseDataConstruction();

        //header
        RequestHeaderUnitQuery requestHeaderUnitQuery = new RequestHeaderUnitQuery();
        requestHeaderUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<RequestHeaderUnit> requestHeaderList = requestHeaderUnitService.findRequestHeaderList(requestHeaderUnitQuery);
        apiUnitCaseDataConstruction.setHeaderList(requestHeaderList);

        //Query
        QueryParamUnitQuery queryParamUnitQuery = new QueryParamUnitQuery();
        queryParamUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<QueryParamUnit> queryParamList = queryParamUnitService.findQueryParamList(queryParamUnitQuery);
        apiUnitCaseDataConstruction.setQueryParamList(queryParamList);

        //path
        PathParamUnitQuery pathParamUnitQuery = new PathParamUnitQuery();
        pathParamUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<PathParamUnit> pathParamUnitList = pathParamUnitService.findPathParamUnitList(pathParamUnitQuery);
        apiUnitCaseDataConstruction.setPathParamList(pathParamUnitList);

        // body
        RequestBodyUnit bodyType = requestBodyUnitService.findRequestBody(apiUnitCase.getId());
        apiUnitCaseDataConstruction.setRequestBodyUnit(bodyType);

        //封装mediaType
        Map mediaType = jointMediaType(apiUnitCase);
        apiUnitCaseDataConstruction.setMediaTypeMap(mediaType);

        switch (bodyType.getBodyType()){
            case MagicValue.REQUEST_BODY_TYPE_FORMDATA:
                List<FormParamUnit> formParamList = getFormData(apiUnitCase);
                apiUnitCaseDataConstruction.setFormList(formParamList);
                break;
            case MagicValue.REQUEST_BODY_TYPE_FORM_URLENCODED:
                List<FormUrlEncodedUnit> formUrlencodedList = getFormUrlencoded(apiUnitCase);
                apiUnitCaseDataConstruction.setUrlencodedList(formUrlencodedList);
                break;
            case MagicValue.REQUEST_BODY_TYPE_JSON:
                String jsonStr = getJson(apiUnitCase);
                apiUnitCaseDataConstruction.setJsonStr(jsonStr);
                break;
            case MagicValue.REQUEST_BODY_TYPE_RAW:
                RawParamUnit raw = getRaw(apiUnitCase);
                apiUnitCaseDataConstruction.setRawParam(raw);
                break;
        }

        //认证
        AuthParamUnit authParam = authParamUnitService.findAuthParamUnit(apiUnitCase.getId());
        apiUnitCaseDataConstruction.setAuthParam(authParam);

        //获取前置操作
        List<PreParamUnit> preParamUnitList = getPre(apiUnitCase);
        apiUnitCaseDataConstruction.setPreParamUnitList(preParamUnitList);

        //获取后置操作
        List<AfterParamUnit> afterParamUnitList = getAfter(apiUnitCase);
        apiUnitCaseDataConstruction.setAfterParamUnitList(afterParamUnitList);

        //获取断言
        List<HashMap<String, Object>> assertList = getAssert(apiUnitCase);
        apiUnitCaseDataConstruction.setAssertList(assertList);

        return apiUnitCaseDataConstruction;
    }


    /**
     * 获取请求体
     * 获取contentType
     */
    Map jointMediaType(ApiUnitCase apiUnitCase){
        RequestBodyUnit bodyType = requestBodyUnitService.findRequestBody(apiUnitCase.getId());

        //获取raw下面的mediaType
        RawParamUnit rawParamUnit = rawParamUnitService.findRawParam(apiUnitCase.getId());
        String rawMediaType = null;
        if(!ObjectUtils.isEmpty(rawParamUnit)){
            rawMediaType= rawParamUnit.getType();
        }

        Map mediaType = new HashMap();
        if (!ObjectUtils.isEmpty(bodyType)){
            if(bodyType.getBodyType().equals("raw")){
                mediaType.put("mediaType",rawMediaType);
            }else {
                switch (bodyType.getBodyType()){
                    case "formdata":
                        mediaType.put("mediaType","multipart/form-data");
                        break;
                    case "formUrlencoded":
                        mediaType.put("mediaType","application/x-www-form-urlencoded");
                        break;
                    case "json":
                        mediaType.put("mediaType","application/json");
                        break;
                    case "text":
                        mediaType.put("mediaType","text/plain");
                        break;
                    case "xml":
                        mediaType.put("mediaType","application/xml");
                        break;
                    default:
                        mediaType.put("mediaType","application/octet-stream");
                        break;
                }
            }
        }
        return mediaType;
    }


    /**
     *  获取formData
     */
    private List<FormParamUnit>  getFormData (ApiUnitCase apiUnitCase){
        FormParamUnitQuery formParamUnitQuery = new FormParamUnitQuery();
        formParamUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<FormParamUnit> formParamUnitList = formParamUnitService.findFormParamList(formParamUnitQuery);

        return formParamUnitList;
    }

    /**
     * 获取FormUrlDataMap
     */
    private List<FormUrlEncodedUnit>  getFormUrlencoded (ApiUnitCase apiUnitCase){
        FormUrlencodedUnitQuery formUrlencodedUnitQuery = new FormUrlencodedUnitQuery();
        formUrlencodedUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<FormUrlEncodedUnit> formUrlEncodedUnitList = formUrlencodedUnitService.findFormUrlencodedList(formUrlencodedUnitQuery);

        return formUrlEncodedUnitList;
    }

    /**
     * 获取JsonDataMap
     */
    private String getJson(ApiUnitCase apiUnitCase){
        JsonParamUnit jsonParam = jsonParamUnitService.findJsonParam(apiUnitCase.getId());

        JsonGenerator jsonGenerator = new JsonGenerator();
        String bodyStr = jsonGenerator.generateJson(jsonParam.getSchemaText());
        return bodyStr;
    }


    //获取RawDataMap
    private RawParamUnit getRaw(ApiUnitCase apiUnitCase){
        //通过测试步骤方法的id查询
        RawParamUnit rawParamUnit = rawParamUnitService.findRawParam(apiUnitCase.getId());
        return rawParamUnit;
    }


    /**
     * 获取前置操作
     */
    private List<PreParamUnit> getPre(ApiUnitCase apiUnitCase){
        PreParamUnitQuery preParamUnitQuery = new PreParamUnitQuery();
        preParamUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<PreParamUnit> preParamUnitList = preParamUnitService.findPreParamUnitList(preParamUnitQuery);
        if (preParamUnitList == null||preParamUnitList.isEmpty()) {
            return Collections.emptyList();
        }else {
            return preParamUnitList;
        }
    }

    /**
     * 获取脚本操作
     */
    private List<AfterParamUnit> getAfter(ApiUnitCase apiUnitCase) {
        AfterParamUnitQuery afterParamUnitQuery = new AfterParamUnitQuery();
        afterParamUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<AfterParamUnit> afterParamUnitList = afterParamUnitService.findAfterParamUnitList(afterParamUnitQuery);
        if (afterParamUnitList == null||afterParamUnitList.isEmpty()) {
            return Collections.emptyList();
        }else {
            return afterParamUnitList;
        }
    }

    private List<HashMap<String,Object>> getAssert(ApiUnitCase apiUnitCase){
        AssertUnitQuery assertUnitQuery = new AssertUnitQuery();
        assertUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<AssertUnit> assertCaseList = assertUnitService.findAssertCaseList(assertUnitQuery);

        if(assertCaseList==null||assertCaseList.isEmpty()){return null;}

        List<HashMap<String,Object>> assertList = new ArrayList<>();
        for (AssertUnit assertUnit : assertCaseList) {
            HashMap<String, Object> assertItem = new HashMap<>();
            assertItem.put("source",assertUnit.getSource());
            assertItem.put("propertyName",assertUnit.getPropertyName());
            assertItem.put("comparator",assertUnit.getComparator());
            assertItem.put("value",assertUnit.getValue());
            assertList.add(assertItem);
        }

        return assertList;
    }


}