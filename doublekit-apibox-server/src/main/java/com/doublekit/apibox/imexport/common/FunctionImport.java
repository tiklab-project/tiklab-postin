package com.doublekit.apibox.imexport.common;

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

    //添加
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

    public void actBody(String requestBody,String methodId){
        MethodEx requestBodyMethod = new MethodEx();
        requestBodyMethod.setId(methodId);
        RequestBodyEx requestBodyEx = new RequestBodyEx();
        requestBodyEx.setId(methodId);
        requestBodyEx.setBodyType(requestBody);
        requestBodyEx.setMethod(requestBodyMethod);
        requestBodyService.createRequestBody(requestBodyEx);
    }

    public void actFromData(String methodId, String formParamName, String formParamValue, String formParamType, String formParamDesc, int required){
        MethodEx formParamMethod = new MethodEx();
        formParamMethod.setId(methodId);
        FormParam formParams = new FormParam();
        formParams.setParamName(formParamName);
        formParams.setValue(formParamValue);
        formParams.setDesc(formParamDesc);
        formParams.setDataType(formParamType);
        formParams.setMethod(formParamMethod);
        formParams.setRequired(required);
        formParamService.createFormParam(formParams);
    }

    public void actJson(String methodId, String name, String value, String type, int required, String desc ){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(methodId);
        JsonParam jsonParam = new JsonParam();
        jsonParam.setParamName(name);
        jsonParam.setValue(value);
        jsonParam.setDataType(type);
        jsonParam.setRequired(required);
        jsonParam.setDesc(desc);
        jsonParam.setMethod(methodEx);
        jsonParamService.createJsonParam(jsonParam);
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

    public void actResponseJson( String methodId, String name, String dataType, int required,String desc){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(methodId);
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMethod(methodEx);
        jsonResponse.setPropertyName(name);
        jsonResponse.setDataType(dataType);
        jsonResponse.setRequired(required);
        jsonResponse.setDesc(desc);
        jsonResponseService.createJsonResponse(jsonResponse);
    }
}
