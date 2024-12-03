package io.tiklab.postin.doclet.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.doclet.common.DocletGetModel;
import io.tiklab.postin.doclet.common.JsonSchemaGenerator;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportData {

    /**
     * 接口基础信息
     */
    public static JSONObject getHttpApiJson(JSONObject methodJson, Map<String, String> classMap, String categoryId){

        JSONObject apixJson = new JSONObject();
        apixJson.put("categoryId",categoryId);
        apixJson.put("protocolType",classMap.get("protocol"));
        apixJson.put("path",methodJson.getString("path"));

        JSONObject nodeJson = new JSONObject();
        nodeJson.put("methodType",methodJson.getString("methodType"));
        nodeJson.put("name",methodJson.getString("name"));
        nodeJson.put("parentId",categoryId);

        JSONObject httpApiJson = new JSONObject();
        httpApiJson.put("apix",apixJson);
        httpApiJson.put("path",methodJson.getString("path"));
        httpApiJson.put("node",nodeJson);

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

    public static JSONObject getJson(String apiId, ExecutableElement method) {
        JSONObject json = new JSONObject();
        json.put("id",apiId);
        json.put("apiId",apiId);

        //从内存中获取模型
        //获取接口传入的参数
        List<? extends VariableElement> parameters = method.getParameters();

        //获取模型全称
        String modelFullName = parameters.get(0).asType().toString();


        JSONObject jsonObject = null;
        if(CustomTagsHandler.modelMap.get(modelFullName)!=null){
            jsonObject = CustomTagsHandler.modelMap.get(modelFullName);
        }else {
            jsonObject = JsonSchemaGenerator.generateJsonSchema(modelFullName);
        }

        String jsonText = "{}";
        if(jsonObject!=null) {
            jsonText = jsonObject.toJSONString();
        }

        json.put("jsonText",jsonText);

        return json;
    }


    /**
     * 请求体
     * raw
     */
    public static JSONObject getRawJson(JSONObject methodJson, String apiId, ExecutableElement method) {
        JSONObject rawJson = new JSONObject();
        rawJson.put("id",apiId);
        rawJson.put("apiId",apiId);

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

    /**
     * 获取响应信息
     * @param method
     * @return
     */
    public static  JSONObject getResponseJson(ExecutableElement method) {
        DeclaredType returnType = (DeclaredType) method.getReturnType();
        TypeElement simpleName = (TypeElement) returnType.asElement();
        JSONObject jsonData = DocletGetModel.loopModel(simpleName.toString(),0);

        JSONObject jsonText = jsonToSchema(jsonData);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","成功");
        jsonObject.put("httpCode",200);
        jsonObject.put("dataType","json");
        jsonObject.put("jsonText",jsonText.toJSONString());

        return jsonObject;
    }



    public static JSONObject jsonToSchema(JSONObject json) {
        JSONObject schema = new JSONObject();
//        schema.put("$schema", "http://json-schema.org/draft-04/schema#");
        schema.put("type", "object");

        JSONObject properties = new JSONObject();
        for(String key : json.keySet()) {
            Object value = json.get(key);

            if(value instanceof JSONObject) {
                // 对象类型,递归转换
                properties.put(key, jsonToSchema((JSONObject)value));

            } else {
                // 基本类型
                JSONObject propSchema = new JSONObject();
                propSchema.put("type", getType(value));
                properties.put(key, propSchema);
            }
        }

        schema.put("properties", properties);

        return schema;
    }

    private static String getType(Object value) {

        if (JSONObject.class.equals(value.getClass())) {
            return "object";
        } else if (JSONArray.class.equals(value.getClass())) {
            return "array";
        } else if (Integer.class.equals(value.getClass()) || Long.class.equals(value.getClass())) {
            return "integer";
        } else if (Double.class.equals(value.getClass())) {
            return "number";
        } else if (Boolean.class.equals(value.getClass())) {
            return "boolean";
        }
        return "string";

    }
}
