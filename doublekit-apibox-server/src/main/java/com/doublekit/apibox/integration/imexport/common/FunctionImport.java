package com.doublekit.apibox.integration.imexport.common;

import com.alibaba.fastjson.JSONObject;
import com.doublekit.apibox.apidef.model.*;
import com.doublekit.apibox.apidef.service.*;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.apibox.category.model.CategoryQuery;
import com.doublekit.apibox.category.service.CategoryService;
import com.doublekit.apibox.workspace.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class FunctionImport {

    @Autowired
    CategoryService categoryService;

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
    ResponseResultService responseResultService;

    @Autowired
    JsonResponseService jsonResponseService;

    //获取导入文件数据
    public JSONObject getJsonData(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuffer sb = new StringBuffer("");
        String temp = null;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        String jsonString = sb.toString();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        return jsonObject;
    }

    //覆盖
    public void coverCategory(String workspaceId, String categoryId, String categoryName){
        delCategory(workspaceId,categoryId);
        addCategroy(workspaceId,categoryId,categoryName);
    }

    //遍历是否有相同的categoryId，有就删除
    private void delCategory(String workspaceId,String categoryId){
        List<Category> categoryListTree = categoryService.findCategoryListTree(new CategoryQuery().setWorkspaceId(workspaceId));
        for(Category category:categoryListTree){
            String categoryDataId = category.getId();
            if(categoryDataId.equals(categoryId)){
                categoryService.deleteCategory(categoryId);
            }
        }
    }

    private void addCategroy(String workspaceId, String categoryId, String categoryName){
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        Workspace workspace = new Workspace();
        workspace.setId(workspaceId);
        category.setWorkspace(workspace);
        categoryService.createCategory(category);
    }

    public void addMethod(String methodId,String methodName,String requestType,String path,Category categoryId,String desc){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(methodId);
        methodEx.setName(methodName);
        methodEx.setRequestType(requestType);
        methodEx.setPath(path);
        methodEx.setCategory(categoryId);
        methodEx.setDesc(desc);
        methodService.createMethod(methodEx);
    }

    public void addHeader(String headerName, String headerValue, String headerDesc, MethodEx headerMethod){
        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setHeaderName(headerName);
        requestHeader.setDesc(headerDesc);
        requestHeader.setValue(headerValue);
        requestHeader.setMethod(headerMethod);
        requestHeaderService.createRequestHeader(requestHeader);
    }

    public void addQuery(String queryName, String queryValue, String queryDesc, MethodEx queryMethod){
        QueryParam queryParam = new QueryParam();
        queryParam.setParamName(queryName);
        queryParam.setValue(queryValue);
        queryParam.setDesc(queryDesc);
        queryParam.setMethod(queryMethod);
        queryParamService.createQueryParam(queryParam);
    }

    public void actBody(String requestBody,String methodId){
        MethodEx requestBodyMethod = new MethodEx();
        requestBodyMethod.setId(methodId);
        RequestBodyEx requestBodyEx = new RequestBodyEx();
        requestBodyEx.setId(methodId);
        requestBodyEx.setBodyType(requestBody);
        requestBodyEx.setMethod(requestBodyMethod);
        requestBodyService.createRequestBody(requestBodyEx);
    }

    public void actFormData(String methodId, String formParamName, String formParamValue, String formParamType, String formParamDesc, int required){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(methodId);
        FormParam formParams = new FormParam();
        formParams.setParamName(formParamName);
        formParams.setValue(formParamValue);
        formParams.setDesc(formParamDesc);
        formParams.setDataType(formParamType);
        formParams.setMethod(methodEx);
        formParams.setRequired(required);
        formParamService.createFormParam(formParams);
    }

    public void actFormUrlencoded(String methodId, String name, String value, String dataType, String desc, int required){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(methodId);
        FormUrlencoded formUrlencoded = new FormUrlencoded();
        formUrlencoded.setParamName(name);
        formUrlencoded.setValue(value);
        formUrlencoded.setRequired(required);
        formUrlencoded.setDataType(dataType);
        formUrlencoded.setDesc(desc);
        formUrlencoded.setMethod(methodEx);

        formUrlencodedService.createFormUrlencoded(formUrlencoded);
    }

    public String actJson(String methodId, String name, String value, String type, int required, String desc, JsonParam pId){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(methodId);
        JsonParam jsonParam = new JsonParam();
        jsonParam.setParamName(name);
        jsonParam.setValue(value);
        jsonParam.setDataType(type);
        jsonParam.setRequired(required);
        jsonParam.setDesc(desc);
        jsonParam.setMethod(methodEx);
        jsonParam.setParent(pId);
        String pid = jsonParamService.createJsonParam(jsonParam);
        return pid;
    }

    public void actRaw(String methodId, String rawType, String rawData){
        MethodEx rawMethod = new MethodEx();
        rawMethod.setId(methodId);
        RawParam rawParam = new RawParam();
        rawParam.setRaw(rawData);
        rawParam.setType(rawType);
        rawParam.setId(methodId);
        rawParam.setMethod(rawMethod);
        rawParamService.createRawParam(rawParam);
    }

    public void actResponseBody(String methodId){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(methodId);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setId(methodId);
        responseResult.setResultType("json");
        responseResult.setMethod(methodEx);
        responseResultService.createResponseResult(responseResult);
    }

    public String actResponseJson( String methodId, String name, String dataType, int required,String desc,JsonResponse pId){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(methodId);
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMethod(methodEx);
        jsonResponse.setPropertyName(name);
        jsonResponse.setDataType(dataType);
        jsonResponse.setRequired(required);
        jsonResponse.setDesc(desc);
        jsonResponse.setParent(pId);
        String pid = jsonResponseService.createJsonResponse(jsonResponse);
        return pid;
    }


}
