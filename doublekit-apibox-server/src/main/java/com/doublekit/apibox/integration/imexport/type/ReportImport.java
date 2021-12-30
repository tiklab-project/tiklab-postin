package com.doublekit.apibox.integration.imexport.type;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.doublekit.apibox.apidef.model.JsonParam;
import com.doublekit.apibox.apidef.model.JsonResponse;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.apibox.integration.imexport.common.FunctionImport;
import com.doublekit.apibox.integration.imexport.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ReportImport {

    @Autowired
    FunctionImport functionImport;


    public void reportAnalysisData(String workspaceId,InputStream stream ) throws IOException {
        //文件数据转换为JSONObject
        JSONObject allData =functionImport.getJsonData(stream);
        for(String key:allData.keySet()){
            JSONObject controllerData = allData.getJSONObject(key);
            String controllerName = controllerData.getString("name");
            String categoryId = Md5.getMD5String(controllerData.getString("path"));
            functionImport.coverCategory(workspaceId,categoryId,controllerName);

            actMethod(controllerData,categoryId);
        }
    }

    //method操作
    private void actMethod(JSONObject controllerData, String categoryId){
        JSONArray methodArr = controllerData.getJSONArray("method");
        for(int i = 0;i<methodArr.size();i++){
            JSONObject methodItem = methodArr.getJSONObject(i);
            String name = methodItem.getString("name");
            String path = methodItem.getString("path");
            String requestType =methodItem.getString("requestType");
            String desc = methodItem.getString("desc");
            Category categoryID = new Category();
            categoryID.setId(categoryId);
            //根据path Md5加密，获取id
            String methodId = Md5.getMD5String(path);

            functionImport.addMethod(methodId,name,requestType,path,categoryID,desc);

            actRequestResponse(methodItem,methodId);
        }
    }

    private void actRequestResponse(JSONObject methodItem, String methodId){
        //获取请求体类型
        String bodyType = transferBodyType(methodItem.getString("paramDataType"));

        functionImport.actBody(bodyType,methodId);

        JSONArray param = methodItem.getJSONArray("param");

        actBodyType(bodyType,param,methodId);

        //响应
        functionImport.actResponseBody(methodId);

        actResponseJson(methodItem,methodId);

    }

    //根据请求体类型操作
    private void actBodyType(String bodyType, JSONArray param, String methodId){
        switch (bodyType){
            case "formdata":
                actFormData(param,methodId);
                break;
            case "json":
                actRequestJson(param,methodId);
                break;
        }
    }

    private void actFormData(JSONArray param, String methodId){
        for(int i = 0;i<param.size();i++){
            JSONObject formDataItem = param.getJSONObject(i);
            String name = formDataItem.getString("name");
            String value = null;
            String desc = formDataItem.getString("desc");
            String type = "text";
            int required= transferRequired(formDataItem.getString("required"));

            functionImport.actFormData(methodId,name,value,desc,type,required);
        }
    }

    private void actRequestJson(JSONArray param, String methodId){
        String parentId = null;
        jsonParamLoop(param, methodId,parentId);
    }

    //jsonParam递归
    private void jsonParamLoop(JSONArray param,String methodId,String parentId){
        for(int i = 0;i<param.size();i++){
            JSONObject jsonItem = param.getJSONObject(i);
            String name = jsonItem.getString("name");
            String value = null;
            String desc = jsonItem.getString("desc");
            String type = transferDataType(jsonItem.getString("dataType"));
            int required= transferRequired(jsonItem.getString("required"));

            JsonParam jsonParam = new JsonParam();
            if(parentId!=null){
                jsonParam.setId(parentId);
            }

            String pid =functionImport.actJson(methodId,name,value,type, required,desc,jsonParam);

            if(jsonItem.containsKey("model")){
                jsonParamLoop(jsonItem.getJSONArray("model"),methodId,pid);
            }
        }
    }

    private void actResponseJson(JSONObject methodItem, String methodId){
        JSONObject result = methodItem.getJSONObject("result");
        JSONArray model = result.getJSONArray("model");
        String parentId = null;
        jsonResponseloop(model,methodId,parentId);
    }

    //jsonParam递归
    private void jsonResponseloop(JSONArray param,String methodId,String parentId){
        for(int i = 0;i<param.size();i++){
            JSONObject jsonItem = param.getJSONObject(i);
            String name = jsonItem.getString("name");
            String desc = jsonItem.getString("desc");
            String type = transferDataType(jsonItem.getString("dataType"));
            int required= transferRequired(jsonItem.getString("required"));

            JsonResponse jsonResponse = new JsonResponse();
            if(parentId!=null){
                jsonResponse.setId(parentId);
            }

            //获取父级id
            String pid =functionImport.actResponseJson(methodId,name,type, required,desc,jsonResponse);

            if(jsonItem.containsKey("model")){
                jsonResponseloop(jsonItem.getJSONArray("model"),methodId,pid);
            }
        }
    }

    //转换请求体类型：json formdata
    private String transferBodyType(String bodyType){
        switch (bodyType){
            case "json":
                return "json";
            case "form-data":
                return "formdata";
        }
        return null;
    }

    //转换类型
    private String transferDataType(String dataType){
        switch (dataType){
            case "java.lang.String":
                return "string";
            case "java.lang.Integer":
                return "int";
            case "int":
                return "int";
            case "java.lang.Void":
                return "null";
            case "java.util.List":
                return "array";
        }
        return "object";
    }

    //转换required
    private Integer transferRequired(String re){
        int required = 0;
        if(re=="true"){
            required=1;
        }else {
            required=0;
        }
        return required;
    }

}
