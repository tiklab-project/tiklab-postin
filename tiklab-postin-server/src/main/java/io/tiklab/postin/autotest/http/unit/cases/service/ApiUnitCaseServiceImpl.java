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
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    RawParamUnitService rawParamUnitService;

    @Autowired
    FormParamUnitService formParamUnitService;

    @Autowired
    FormUrlencodedUnitService formUrlencodedUnitService;

    @Autowired
    JsonParamUnitService jsonParamUnitService;

    @Autowired
    PreScriptUnitService preScriptUnitService;

    @Autowired
    AfterScriptUnitService afterScriptUnitService;

    @Autowired
    ResponseResultUnitUnitServiceImpl responseResultService;

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

        //接口定义
        apiApiUnitCaseDao.deleteApiUnitCase(id);
        requestHeaderUnitService.deleteAllRequestHeader(id);
        queryParamUnitService.deleteAllQueryParam(id);
        formParamUnitService.deleteAllFormParam(id);
        formUrlencodedUnitService.deleteAllFormUrlencoded(id);
        jsonParamUnitService.deleteAllJsonParam(id);
        rawParamUnitService.deleteAllRawParam(id);
        preScriptUnitService.deletePreScript(id);
        afterScriptUnitService.deleteAfterScript(id);
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

        //封装请求头数据
        Map headerMap = requestHeaderUnitService.jointHeader(apiUnitCase);
        //封装param参数
        QueryParamUnitQuery queryParamUnitQuery = new QueryParamUnitQuery();
        queryParamUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<QueryParamUnit> queryParamList = queryParamUnitService.findQueryParamList(queryParamUnitQuery);
        //封装mediaType
        Map mediaType = jointMediaType(apiUnitCase);
        //封装请求体数据
        String body = bodyConstruction(apiUnitCase);
        //获取前置脚本
        String preScript = getPreScript(apiUnitCase);
        //获取后置脚本
        String afterScript = getAfterScript(apiUnitCase);
        //获取断言
        List<HashMap<String, Object>> assertList = getAssert(apiUnitCase);

        ApiUnitCaseDataConstruction apiUnitCaseDataConstruction = new ApiUnitCaseDataConstruction();
        apiUnitCaseDataConstruction.setHeaderMap(headerMap);
        apiUnitCaseDataConstruction.setQueryParamList(queryParamList);
        apiUnitCaseDataConstruction.setMediaTypeMap(mediaType);
        apiUnitCaseDataConstruction.setBody(body);
        apiUnitCaseDataConstruction.setPreScript(preScript);
        apiUnitCaseDataConstruction.setAfterScript(afterScript);
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
     *请求体参数拼接
     * @param apiUnitCase 用例步骤
     *
     */
    private String bodyConstruction(ApiUnitCase apiUnitCase){
        RequestBodyUnit bodyType = requestBodyUnitService.findRequestBody(apiUnitCase.getId());
       
//        HashMap<String, Object> map = new HashMap<>();
        String bodyStr = "";

        if (!ObjectUtils.isEmpty(bodyType)){
            switch (bodyType.getBodyType()){
                case "formdata":
                    return getFormData(apiUnitCase,bodyStr);

                case "formUrlencoded":
                    return getFormUrlencoded(apiUnitCase,bodyStr);

                case "json":
                    bodyStr=getJson(apiUnitCase);
                    return bodyStr;

                case "raw":
                    return getRaw(apiUnitCase,bodyStr);

            }
        }

        return null;
    }


    /**
     *  获取formData
     */
    private String getFormData (ApiUnitCase apiUnitCase,String bodyStr){
        FormParamUnitQuery formParamUnitQuery = new FormParamUnitQuery();
        formParamUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<FormParamUnit> formParamUnitList = formParamUnitService.findFormParamList(formParamUnitQuery);

        if (CollectionUtils.isNotEmpty(formParamUnitList)){
            for (int i = 0; i < formParamUnitList.size(); i++) {
                FormParamUnit formParamUnit = formParamUnitList.get(i);
                bodyStr += formParamUnit.getParamName() + "=" + formParamUnit.getValue();
                if (i < formParamUnitList.size() - 1) {
                    bodyStr += "&";
                }
            }
        }

        return bodyStr;
    }

    /**
     * 获取FormUrlDataMap
     */
    private String getFormUrlencoded (ApiUnitCase apiUnitCase, String bodyStr){
        FormUrlencodedUnitQuery formUrlencodedUnitQuery = new FormUrlencodedUnitQuery();
        formUrlencodedUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<FormUrlEncodedUnit> formUrlEncodedUnitList = formUrlencodedUnitService.findFormUrlencodedList(formUrlencodedUnitQuery);

        if (CollectionUtils.isNotEmpty(formUrlEncodedUnitList)){

            for (int i = 0; i < formUrlEncodedUnitList.size(); i++) {
                FormUrlEncodedUnit formUrlencodedUnit = formUrlEncodedUnitList.get(i);
                bodyStr += formUrlencodedUnit.getParamName() + "=" + formUrlencodedUnit.getValue();
                if (i < formUrlEncodedUnitList.size() - 1) {
                    bodyStr += "&";
                }
            }
        }

        return bodyStr;
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
    private String getRaw(ApiUnitCase apiUnitCase, String bodyStr){

        //通过测试步骤方法的id查询
        RawParamUnit rawParamUnit = rawParamUnitService.findRawParam(apiUnitCase.getId());
        if(!ObjectUtils.isEmpty(rawParamUnit)){
            String raw = rawParamUnit.getRaw();
            bodyStr = raw;
        }

        return bodyStr;
    }



    /**
     * 获取前置脚本
     */
    private String getPreScript(ApiUnitCase apiUnitCase){
        PreScriptUnit preScriptUnit = preScriptUnitService.findPreScript(apiUnitCase.getId());
        if (preScriptUnit == null|| preScriptUnit.getScriptex()==null) {
            return null;
        }
        return preScriptUnit.getScriptex();
    }


    /**
     * 获取脚本
     */
    private String getAfterScript(ApiUnitCase apiUnitCase) {
        AfterScriptUnit afterScriptUnit = afterScriptUnitService.findAfterScript(apiUnitCase.getId());
        if (afterScriptUnit == null|| afterScriptUnit.getScriptex()==null) {
            return null;
        }

        return afterScriptUnit.getScriptex();
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