package com.doublekit.apibox.integration.imexport.common;

import com.alibaba.fastjson.JSONObject;
import com.doublekit.apibox.apidef.model.*;
import com.doublekit.apibox.apidef.service.*;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.apibox.category.model.CategoryQuery;
import com.doublekit.apibox.category.service.CategoryService;
import com.doublekit.apibox.integration.imexport.model.*;
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

    public void addMethod(MethodImportVo methodVo){
        Category categoryID = new Category();
        categoryID.setId(methodVo.getCategoryId());

        MethodEx methodEx = new MethodEx();

        methodEx.setCategory(categoryID);
        methodEx.setId(methodVo.getMethodId());
        methodEx.setName(methodVo.getName());
        methodEx.setRequestType(methodVo.getRequestType());
        methodEx.setPath(methodVo.getPath());
        methodEx.setDesc(methodVo.getDesc());

        methodService.createMethod(methodEx);
    }

    public void addHeader(HeaderParamImportVo hVo){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(hVo.getMethodId());

        RequestHeader requestHeader = new RequestHeader();

        requestHeader.setMethod(methodEx);
        requestHeader.setHeaderName(hVo.getName());
        requestHeader.setValue(hVo.getValue());
        requestHeader.setDesc(hVo.getDesc());

        requestHeaderService.createRequestHeader(requestHeader);
    }

    public void addQuery(QueryParamImportVo qVo){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(qVo.getMethodId());

        QueryParam queryParam = new QueryParam();

        queryParam.setMethod(methodEx);
        queryParam.setParamName(qVo.getName());
        queryParam.setValue(qVo.getValue());
        queryParam.setDesc(qVo.getDesc());

        queryParamService.createQueryParam(queryParam);
    }

    public void addBody(String requestBody,String methodId){
        MethodEx requestBodyMethod = new MethodEx();
        requestBodyMethod.setId(methodId);

        RequestBodyEx requestBodyEx = new RequestBodyEx();

        requestBodyEx.setId(methodId);
        requestBodyEx.setBodyType(requestBody);
        requestBodyEx.setMethod(requestBodyMethod);

        requestBodyService.createRequestBody(requestBodyEx);
    }

    public void addFormData(FormDataImportVo fvo){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(fvo.getMethodId());

        FormParam formParams = new FormParam();

        formParams.setMethod(methodEx);
        formParams.setParamName(fvo.getName());
        formParams.setValue(fvo.getValue());
        formParams.setDesc(fvo.getDesc());
        formParams.setDataType(fvo.getType());
        formParams.setRequired(fvo.getRequired());

        formParamService.createFormParam(formParams);
    }

    public void addFormUrlencoded(FormUrlencodedImportVo fUVo){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(fUVo.getMethodId());

        FormUrlencoded formUrlencoded = new FormUrlencoded();

        formUrlencoded.setMethod(methodEx);
        formUrlencoded.setParamName(fUVo.getName());
        formUrlencoded.setValue(fUVo.getValue());
        formUrlencoded.setRequired(fUVo.getRequired());
        formUrlencoded.setDataType(fUVo.getDataType());
        formUrlencoded.setDesc(fUVo.getDesc());

        formUrlencodedService.createFormUrlencoded(formUrlencoded);
    }

    public String addJsonParam(JsonParamImportVo jVo){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(jVo.getMethodId());

        JsonParam jsonParam = new JsonParam();

        jsonParam.setParamName(jVo.getName());
        jsonParam.setValue(jVo.getValue());
        jsonParam.setDataType(jVo.getDataType());
        jsonParam.setRequired(jVo.getRequired());
        jsonParam.setDesc(jVo.getDesc());
        jsonParam.setMethod(methodEx);
        jsonParam.setParent(jVo.getParentId());

        String pid = jsonParamService.createJsonParam(jsonParam);

        return pid;
    }

    public void addRaw(String methodId, String rawType, String rawData){
        MethodEx rawMethod = new MethodEx();
        rawMethod.setId(methodId);

        RawParam rawParam = new RawParam();

        rawParam.setRaw(rawData);
        rawParam.setType(rawType);
        rawParam.setId(methodId);
        rawParam.setMethod(rawMethod);

        rawParamService.createRawParam(rawParam);
    }

    public void addResponseBody(String methodId){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(methodId);

        ResponseResult responseResult = new ResponseResult();

        responseResult.setId(methodId);
        responseResult.setResultType("json");
        responseResult.setMethod(methodEx);

        responseResultService.createResponseResult(responseResult);
    }

    public String addResponseJson(JsonResponseImportVo jRVo){
        MethodEx methodEx = new MethodEx();
        methodEx.setId(jRVo.getMethodId());

        JsonResponse jsonResponse = new JsonResponse();

        jsonResponse.setMethod(methodEx);
        jsonResponse.setParent(jRVo.getParentId());
        jsonResponse.setPropertyName(jRVo.getName());
        jsonResponse.setValue(jRVo.getValue());
        jsonResponse.setDataType(jRVo.getDataType());
        jsonResponse.setRequired(jRVo.getRequired());
        jsonResponse.setDesc(jRVo.getDesc());

        String pid = jsonResponseService.createJsonResponse(jsonResponse);
        return pid;
    }


}
