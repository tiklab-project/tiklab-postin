package net.tiklab.postin.support.imexport.type;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.tiklab.postin.api.http.definition.model.JsonParam;
import net.tiklab.postin.api.http.definition.model.JsonResponse;
import net.tiklab.postin.support.imexport.common.FunctionImport;
import net.tiklab.postin.support.imexport.utils.Md5;
import net.tiklab.postin.support.imexport.model.FormDataImportVo;
import net.tiklab.postin.support.imexport.model.JsonParamImportVo;
import net.tiklab.postin.support.imexport.model.JsonResponseImportVo;
import net.tiklab.postin.support.imexport.model.ApixImportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ReportImport {

    @Autowired
    FunctionImport functionImport;


    public void analysisReportData(String workspaceId,InputStream stream ) throws IOException {
        //文件数据转换为JSONObject
        JSONObject allData =functionImport.getJsonData(stream);
        for(String key:allData.keySet()){
            JSONObject controllerData = allData.getJSONObject(key);
            String controllerName = controllerData.getString("name");
            String categoryId = Md5.getMD5String(controllerData.getString("path"));

            //如果已经导入过了，覆盖之前的导入数据
            functionImport.coverCategory(workspaceId,categoryId,controllerName);

            analysisMethod(controllerData,categoryId);
        }
    }

    //method操作
    private void analysisMethod(JSONObject controllerData, String categoryId){
        JSONArray methodArr = controllerData.getJSONArray("method");
        for(int i = 0;i<methodArr.size();i++){
            JSONObject methodItem = methodArr.getJSONObject(i);
            String path = methodItem.getString("path");
            String methodId = Md5.getMD5String(path+"report");//后面加report,防止与其他导入类型id相同

            ApixImportVo mVo = new ApixImportVo();
            mVo.setCategoryId(categoryId);
            mVo.setMethodId(methodId);
            mVo.setName(methodItem.getString("name"));
            mVo.setRequestType(methodItem.getString("requestType"));
            mVo.setPath(path);
            mVo.setDesc(methodItem.getString("desc"));

            functionImport.addMethod(mVo);

            //解析request response
            analysisRequestResponse(methodItem,methodId);
        }
    }

    //解析request response
    private void analysisRequestResponse(JSONObject methodItem, String methodId){
        //获取请求体类型
        String bodyType = transferBodyType(methodItem.getString("paramDataType"));

        functionImport.addBody(bodyType,methodId);

        JSONArray param = methodItem.getJSONArray("param");
        analysisBodyType(bodyType,param,methodId);


        //响应
        functionImport.addResponseBody(methodId);

        analysisResponseJson(methodItem,methodId);

    }

    //根据请求体类型操作
    private void analysisBodyType(String bodyType, JSONArray param, String methodId){
        switch (bodyType){
            case "formdata":
                analysisFormData(param,methodId);
                break;
            case "json":
                analysisRequestJson(param,methodId);
                break;
        }
    }

    private void analysisFormData(JSONArray param, String methodId){
        for(int i = 0;i<param.size();i++){
            JSONObject formDataItem = param.getJSONObject(i);

            FormDataImportVo formData = new FormDataImportVo();
            formData.setName(formDataItem.getString("name"));
            formData.setType("text");
            formData.setRequired(transferRequired(formDataItem.getString("required")));
            formData.setDesc(formDataItem.getString("desc"));
            formData.setMethodId(methodId);

            functionImport.addFormData(formData);
        }
    }


    private void analysisRequestJson(JSONArray param, String methodId){
        String parentId = null;
        jsonParamLoop(param, methodId,parentId);
    }
    //jsonParam递归
    private void jsonParamLoop(JSONArray param,String methodId,String parentId){
        for(int i = 0;i<param.size();i++){
            JSONObject jsonItem = param.getJSONObject(i);
            String type = transferDataType(jsonItem.getString("dataType"));
            int required= transferRequired(jsonItem.getString("required"));

            JsonParam jsonParam = new JsonParam();
            if(parentId!=null){
                jsonParam.setId(parentId);
            }

            JsonParamImportVo jVo = new JsonParamImportVo();
            jVo.setParentId(jsonParam);
            jVo.setMethodId(methodId);
            jVo.setName(jsonItem.getString("name"));
            jVo.setDataType(type);
            jVo.setRequired(required);
            jVo.setDesc(jsonItem.getString("desc"));

            String pid =functionImport.addJsonParam(jVo);

            //是否有model， 有 递归
            if(jsonItem.containsKey("model")){
                jsonParamLoop(jsonItem.getJSONArray("model"),methodId,pid);
            }
        }
    }


    private void analysisResponseJson(JSONObject methodItem, String methodId){
        JSONObject result = methodItem.getJSONObject("result");
        JSONArray model = result.getJSONArray("model");
        String parentId = null;
        jsonResponseloop(model,methodId,parentId);
    }
    //jsonResponse递归
    private void jsonResponseloop(JSONArray param,String methodId,String parentId){
        for(int i = 0;i<param.size();i++){
            JSONObject jsonItem = param.getJSONObject(i);
            String type = transferDataType(jsonItem.getString("dataType"));
            int required= transferRequired(jsonItem.getString("required"));

            JsonResponse jsonResponse = new JsonResponse();
            if(parentId!=null){
                jsonResponse.setId(parentId);
            }

            JsonResponseImportVo jRVo = new JsonResponseImportVo();
            jRVo.setParentId(jsonResponse);
            jRVo.setMethodId(methodId);
            jRVo.setName(jsonItem.getString("name"));
            jRVo.setDataType(type);
            jRVo.setRequired(required);
            jRVo.setDesc(jsonItem.getString("desc"));


            //获取父级id
            String pid =functionImport.addResponseJson(jRVo);

            //是否有model， 有 递归
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
        return "none";
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
