package io.tiklab.postin.doclet.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.doclet.starter.DocletApplication;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportData {

    /**
     * 接口基础信息
     */
    public static JSONObject getHttpApiJson(JSONObject methodJson, Map<String, String> classMap, String categoryId, ExecutableElement method){

        JSONObject httpApiJson = new JSONObject();
        JSONObject apixJson = new JSONObject();
        JSONObject categoryJson = new JSONObject();
        categoryJson.put("id",categoryId);
        apixJson.put("category",categoryJson);
        apixJson.put("name",methodJson.getString("name"));
        apixJson.put("method",methodJson.getString("method"));
        apixJson.put("protocol",classMap.get("protocol"));
        httpApiJson.put("apix",apixJson);
        httpApiJson.put("path",methodJson.getString("path"));
        httpApiJson.put("methodType",methodJson.getString("method"));

        return httpApiJson;
    }

    private static JSONObject loopModel( String modelFullName){
        JSONObject jsonObject = new JSONObject();
        try {

            //通过反射获取模型类
            Class<?> paramClass = Class.forName(modelFullName, true, DocletApplication.urlClassLoader);

            //获取模型类中的所有字段
            Field[] fields = paramClass.getDeclaredFields();
            // 遍历字段并获取字段信息
            for (Field field : fields) {
                // 获取字段名
                String fieldName = field.getName();
                // 获取字段类型
                Class<?> fieldType = field.getType();

                if(fieldType.getName().startsWith("java")){
                    jsonObject.put(fieldName,fieldName);
                }else {
                    JSONObject modelJson = loopModel(fieldType.getName());
                    jsonObject.put(fieldName,modelJson);
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error --- 获取Class失败 :"+e);
        }
        return jsonObject;
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
    public static ArrayList<Object> getFormDataJson(JSONObject methodJson, String apiId, ExecutableElement method) {

        JSONArray params = methodJson.getJSONArray("params");

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

            JSONObject jsonObject = loopModel(modelFullName);

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
