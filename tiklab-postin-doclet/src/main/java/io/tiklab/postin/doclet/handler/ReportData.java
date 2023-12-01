package io.tiklab.postin.doclet.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.doclet.common.DocletGetModel;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportData {

    /**
     * 接口基础信息
     */
    public static JSONObject getHttpApiJson(JSONObject methodJson, Map<String, String> classMap, String categoryId){

        JSONObject httpApiJson = new JSONObject();
        JSONObject apixJson = new JSONObject();
        JSONObject categoryJson = new JSONObject();
        categoryJson.put("id",categoryId);
        apixJson.put("category",categoryJson);
        apixJson.put("name",methodJson.getString("name"));
        apixJson.put("methodType",methodJson.getString("methodType"));
        apixJson.put("protocolType",classMap.get("protocol"));
        apixJson.put("path",methodJson.getString("path"));
        httpApiJson.put("apix",apixJson);
//        httpApiJson.put("path",methodJson.getString("path"));
        httpApiJson.put("methodType",methodJson.getString("methodType"));

        return httpApiJson;
    }


    /**
     * 接口请求体基础信息
     * @param methodJson
     */
    public static JSONObject getApiRequest(JSONObject methodJson) {
        JSONObject apiRequest = new JSONObject();
        String bodyType = methodJson.getString("request-type");
        if(bodyType.equals("json")){
            apiRequest.put("bodyType","raw");
        }else {
            apiRequest.put("bodyType",bodyType);
        }

        return apiRequest;
    }

    /**
     * 请求体
     * formdata类型
     */
    public static ArrayList<Object> getFormDataJson(JSONObject methodJson, String apiId) {

        JSONArray params = methodJson.getJSONArray("params");

        if(params==null){
            System.out.println(methodJson.getString("path")+"--- maybe annotation definition error ");
            return null;
        }

        ArrayList<Object> arrayList = new ArrayList<>();
        for(Object param:params){
            JSONObject paramJson = (JSONObject) param;

            JSONObject formParam = new JSONObject();
            JSONObject http = new JSONObject();
            http.put("id",apiId);
            formParam.put("http",http);
            formParam.put("paramName",paramJson.getString("name"));
            formParam.put("dataType",paramJson.getString("dataType"));
            formParam.put("value",paramJson.getString("value"));
            formParam.put("desc",paramJson.getString("desc"));

            arrayList.add(formParam);
        }
        return arrayList;
    }

    /**
     * 请求体
     * formUrlencoded类型
     */
    public static ArrayList<Object> getFormUrlList(JSONObject methodMap, String apiId) {
        JSONArray params = methodMap.getJSONArray("param");

        if(params==null){
            System.out.println(methodMap.getString("path")+"--- maybe annotation definition error");
            return null;
        }

        ArrayList<Object> arrayList = new ArrayList<>();

        for(Object param:params){
            JSONObject paramJson = (JSONObject) param;

            JSONObject formUrlencoded = new JSONObject();
            JSONObject http = new JSONObject();
            http.put("id",apiId);
            formUrlencoded.put("http",http);
            formUrlencoded.put("paramName",paramJson.getString("name"));
            formUrlencoded.put("dataType",paramJson.getString("dataType"));
            formUrlencoded.put("value",paramJson.getString("value"));

            arrayList.add(formUrlencoded);
        }

        return arrayList;
    }

    /**
     * 请求体
     * raw
     */
    public static JSONObject getRawJson(JSONObject methodJson, String apiId, ExecutableElement method) {
        JSONObject rawJson = new JSONObject();
        rawJson.put("id",apiId);

        JSONObject http = new JSONObject();
        http.put("id",apiId);
        rawJson.put("http",http);

        if("json".equals(methodJson.getString("request-type"))){
            //从内存中获取模型
            //获取接口传入的参数
            List<? extends VariableElement> parameters = method.getParameters();

            //获取模型全称
            String modelFullName = parameters.get(0).asType().toString();


            JSONObject jsonObject;
            if(CustomTagsHandler.modelMap.get(modelFullName)!=null){
                jsonObject = CustomTagsHandler.modelMap.get(modelFullName);
            }else {
                jsonObject = DocletGetModel.loopModel(modelFullName,0);
            }

            String jsonText = "{}";
            if(jsonObject!=null) {
                jsonText = jsonObject.toJSONString();
            }
            rawJson.put("raw",jsonText);
            rawJson.put("type","application/json");

        }else {
            rawJson.put("raw",methodJson.getString("param"));
            rawJson.put("type","text/plain");
        }

        return rawJson;
    }




}
