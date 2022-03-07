package com.doublekit.apibox.apidef.support;

import com.doublekit.apibox.apidef.model.*;
import com.doublekit.apibox.apidef.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class ApiAllDataFn {
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



    public void getData(String methodId, MethodEx methodEx) {
        //获取请求头中的数据
        getHeaderData(methodId,methodEx);

        //获取查询参数的数据
        getQueryData(methodId,methodEx);

        //获取请求体的类型
        getBodyTypeData(methodId,methodEx);

        //获取前置脚本数据
        getPreScriptData(methodId,methodEx);

        //获取后置脚本的数据
        getAfterScriptData(methodId,methodEx);
    }


    /**
     * 获取请求头中的数据
     * @param methodId
     * @param methodEx
     */
    public void getHeaderData(String methodId, MethodEx methodEx) {
        List<RequestHeader> requestHeaderList = requestHeaderService.findRequestHeaderList(new RequestHeaderQuery().setMethodId(methodId));

        if(CollectionUtils.isNotEmpty(requestHeaderList)){
            methodEx.setRequestHeaderList(requestHeaderList);
        }
    }

    /**
     * 获取查询参数的数据
     * @param methodId
     * @param methodEx
     */
    public void getQueryData(String methodId, MethodEx methodEx) {
        List<QueryParam> queryParamList = queryParamService.findQueryParamList(new QueryParamQuery().setMethodId(methodId));

        if(CollectionUtils.isNotEmpty(queryParamList)){
            methodEx.setQueryParamList(queryParamList);
        }
    }


    /**
     * 获取请求体的类型
     * @param methodId
     * @param methodEx
     */
    public void getBodyTypeData(String methodId, MethodEx methodEx) {
        RequestBodyEx requestBody = requestBodyService.findRequestBody(methodId);
        methodEx.setRequestBody(requestBody);

        String bodyType = requestBody.getBodyType();
        //根据请求体类型获取相应的请求体数据
        getBodyData(bodyType,methodId,methodEx);
    }

    /**
     * 通过请求体类型，获取相应的请求体数据
     * @param bodyType
     * @param methodId
     * @param methodEx
     */
    private void getBodyData(String bodyType, String methodId, MethodEx methodEx) {
        switch (bodyType){
            case "formdata":
                getFormParamData(methodId,methodEx);
                break;
            case "formUrlencoded":
                getFormUrlencodedData(methodId,methodEx);
                break;
            case "json":
                getJsonParamData(methodId,methodEx);
                break;
            case "raw":
                getRawParamData(methodId,methodEx);
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
     * @param methodEx
     */
    private void getFormParamData(String methodId, MethodEx methodEx) {
        List<FormParam> formParamList = formParamService.findFormParamList(new FormParamQuery().setMethodId(methodId));

        if(CollectionUtils.isNotEmpty(formParamList)){
            methodEx.setFormParamList(formParamList);
        }
    }

    /**
     * 获取formurlencoded数据
     * @param methodId
     * @param methodEx
     */
    private void getFormUrlencodedData(String methodId, MethodEx methodEx) {
        List<FormUrlencoded> formUrlencodedList = formUrlencodedService.findFormUrlencodedList(new FormUrlencodedQuery().setMethodId(methodId));

        if(CollectionUtils.isNotEmpty(formUrlencodedList)){
            methodEx.setFormUrlencodedList(formUrlencodedList);
        }
    }

    /**
     * 获取json数据
     * @param methodId
     * @param methodEx
     */
    private void getJsonParamData(String methodId, MethodEx methodEx) {
        List<JsonParam> jsonParamList = jsonParamService.findJsonParamListTree(new JsonParamQuery().setMethodId(methodId));

        if(CollectionUtils.isNotEmpty(jsonParamList)){
            methodEx.setJsonParamList(jsonParamList);
        }
    }

    /**
     * 获取raw数据
     * @param methodId
     * @param methodEx
     */
    private void getRawParamData(String methodId, MethodEx methodEx) {
        RawParam rawParam = rawParamService.findRawParam(methodId);

        if(!ObjectUtils.isEmpty(rawParam)){
            methodEx.setRawParam(rawParam);
        }
    }

    /**
     * 获取前置脚本数据
     * @param methodId
     * @param methodEx
     */
    public void getPreScriptData(String methodId, MethodEx methodEx) {
        PreScript preScript = preScriptService.findPreScript(methodId);

        if(!ObjectUtils.isEmpty(preScript)){
            methodEx.setPreScript(preScript);
        }
    }

    /**
     * 获取后置脚本数据
     * @param methodId
     * @param methodEx
     */
    public void getAfterScriptData(String methodId, MethodEx methodEx) {
        AfterScript afterScript = afterScriptService.findAfterScript(methodId);

        if(!ObjectUtils.isEmpty(afterScript)){
            methodEx.setAfterScript(afterScript);
        }
    }


}
