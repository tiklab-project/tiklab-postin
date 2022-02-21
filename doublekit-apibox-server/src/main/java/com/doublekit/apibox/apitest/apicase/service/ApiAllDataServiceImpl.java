package com.doublekit.apibox.apitest.apicase.service;

import com.doublekit.apibox.apidef.model.*;
import com.doublekit.apibox.apidef.service.*;
import com.doublekit.apibox.apitest.apicase.model.ApiAllData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ApiAllDataServiceImpl implements ApiAllDataService {


    @Autowired
    MethodService methodService;

    @Autowired
    RequestHeaderService requestHeaderService;

    @Autowired
    QueryParamService queryParamService;

    @Autowired
    RequestBodyService requestBodyService;

    @Autowired
    FormParamService formParamService;

    @Autowired
    FormUrlencodedService formUrlencodedService;

    @Autowired
    JsonParamService jsonParamService;

    @Autowired
    RawParamService rawParamService;

    @Autowired
    BinaryParamService binaryParamService;

    @Autowired
    PreScriptService preScriptService;

    @Autowired
    AfterScriptService afterScriptService;



    @Override
    public ApiAllData findApiAllData(String methodId) {

        ApiAllData apiAllData = new ApiAllData();

        //获取method中的数据
        MethodEx method = methodService.findMethod(methodId);
        apiAllData.setRequestType(method.getRequestType());
        apiAllData.setPath(method.getPath());

        //获取请求头中的数据
        getHeaderData(methodId,apiAllData);

        //获取查询参数的数据
        getQueryData(methodId,apiAllData);

        //获取请求体的类型
        getBodyTypeData(methodId,apiAllData);

        //获取前置脚本数据
        getPreScriptData(methodId,apiAllData);

        //获取后置脚本的数据
        getAfterScriptData(methodId,apiAllData);


        return apiAllData;
    }


    /**
     * 获取请求头中的数据
     * @param methodId
     * @param apiAllData
     */
    private void getHeaderData(String methodId, ApiAllData apiAllData) {
        List<RequestHeader> requestHeaderList = requestHeaderService.findRequestHeaderList(new RequestHeaderQuery().setMethodId(methodId));

        apiAllData.setRequestHeaderList(requestHeaderList);
    }

    /**
     * 获取查询参数的数据
     * @param methodId
     * @param apiAllData
     */
    private void getQueryData(String methodId, ApiAllData apiAllData) {
        List<QueryParam> queryParamList = queryParamService.findQueryParamList(new QueryParamQuery().setMethodId(methodId));

        apiAllData.setQueryParamList(queryParamList);
    }


    /**
     * 获取请求体的类型
     * @param methodId
     * @param apiAllData
     */
    private void getBodyTypeData(String methodId, ApiAllData apiAllData) {
        RequestBodyEx requestBody = requestBodyService.findRequestBody(methodId);
        String bodyType = requestBody.getBodyType();
        apiAllData.setBodyType(bodyType);

        //根据请求体类型获取相应的请求体数据
        getBodyData(bodyType,methodId,apiAllData);
    }

    /**
     * 通过请求体类型，获取相应的请求体数据
     * @param bodyType
     * @param methodId
     * @param apiAllData
     */
    private void getBodyData(String bodyType, String methodId, ApiAllData apiAllData) {
        switch (bodyType){
            case "formdata":
                getFormParamData(methodId,apiAllData);
                break;
            case "formUrlencoded":
                getFormUrlencodedData(methodId,apiAllData);
                break;
            case "json":
                getJsonParamData(methodId,apiAllData);
                break;
            case "raw":
                getRawParamData(methodId,apiAllData);
                break;
            case "binary":
                //问题
                break;
            default:
                break;
        }
    }

    /**
     * 获取formdata数据
     * @param methodId
     * @param apiAllData
     */
    private void getFormParamData(String methodId, ApiAllData apiAllData) {
        List<FormParam> formParamList = formParamService.findFormParamList(new FormParamQuery().setMethodId(methodId));

        apiAllData.setFormParamList(formParamList);
    }

    /**
     * 获取formurlencoded数据
     * @param methodId
     * @param apiAllData
     */
    private void getFormUrlencodedData(String methodId, ApiAllData apiAllData) {
        List<FormUrlencoded> formUrlencodedList = formUrlencodedService.findFormUrlencodedList(new FormUrlencodedQuery().setMethodId(methodId));

        apiAllData.setFormUrlencodedList(formUrlencodedList);

    }

    /**
     * 获取json数据
     * @param methodId
     * @param apiAllData
     */
    private void getJsonParamData(String methodId, ApiAllData apiAllData) {
        List<JsonParam> jsonParamList = jsonParamService.findJsonParamListTree(new JsonParamQuery().setMethodId(methodId));

        apiAllData.setJsonParamList(jsonParamList);
    }

    /**
     * 获取raw数据
     * @param methodId
     * @param apiAllData
     */
    private void getRawParamData(String methodId, ApiAllData apiAllData) {
        RawParam rawParam = rawParamService.findRawParamData(methodId);

        if(!ObjectUtils.isEmpty(rawParam)){
            apiAllData.setRawParam(rawParam);
        }
    }

    /**
     * 获取前置脚本数据
     * @param methodId
     * @param apiAllData
     */
    private void getPreScriptData(String methodId, ApiAllData apiAllData) {
        PreScript preScript = preScriptService.findPreScript(methodId);

        if(!ObjectUtils.isEmpty(preScript)){
            apiAllData.setPreScript(preScript.getScriptex());
        }
    }

    /**
     * 获取后置脚本数据
     * @param methodId
     * @param apiAllData
     */
    private void getAfterScriptData(String methodId, ApiAllData apiAllData) {
        AfterScript afterScript = afterScriptService.findAfterScript(methodId);

        if(!ObjectUtils.isEmpty(afterScript)){
            apiAllData.setAfterScript(afterScript.getScriptex());
        }
    }

}
