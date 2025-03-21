package io.tiklab.postin.client.builder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.client.model.ApiMeta;
import io.tiklab.postin.client.model.ApiMethodMeta;
import io.tiklab.postin.client.model.ApiParamMeta;
import io.tiklab.postin.client.model.ApiPropertyMeta;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class GenerateOpenApi {
    @Value("${postin.report.workspaceId:bd26c6ec5c6e}")
    private String workspaceId;

    @Value("${postin.report.server:http://192.168.10.17:8090}")
    private String server;

    // 用于避免循环引用（如果需要）
    private Set<String> processedTypes = new HashSet<>();

    public JSONArray generateTree(Map<String, ApiMeta> apiMetaMap){

        JSONArray treeArray = new JSONArray();


        //循环一次就是一个模块，模块下有接口
        for (Map.Entry<String, ApiMeta> entry : apiMetaMap.entrySet()) {


            ApiMeta controllerMap = entry.getValue();
            String categoryName = controllerMap.getName();
            String classFullName = controllerMap.getCls().getPackage().getName();

            //获取分组id
            String categoryId = getIdByMd5(categoryName+classFullName);

            //构造树形父级分组
            JSONObject categoryJson = new JSONObject();
            categoryJson.put("id",categoryId);
            categoryJson.put("name",categoryName);
            categoryJson.put("parentId",null);
            categoryJson.put("type","category");
            categoryJson.put("workspaceId",workspaceId);

            JSONArray children = new JSONArray();
            for(ApiMethodMeta apiMethodMeta:controllerMap.getApiMethodMetaList()){
                //通过路径md5 生成一个 id
                String path = apiMethodMeta.getPath();
                String apiId = getIdByMd5(path);

                if(apiId==null){continue;}

                JSONObject apiJson = new JSONObject();
                apiJson.put("id",apiId);
                apiJson.put("apix",generateApixModel(apiMethodMeta, apiId, categoryId));
                JSONObject node = generateNode(apiMethodMeta, apiId, categoryId);
                apiJson.put("node",node);
                JSONObject apiRequest = generateApiRequest(apiMethodMeta,apiId);
                apiJson.put("request",apiRequest);

                if(Objects.equals(apiRequest.getString("bodyType"), "formdata")){
                    JSONArray formDataList = apiGenerateFormData(apiMethodMeta,apiId);
                    apiJson.put("formList",formDataList);
                }
                if(Objects.equals(apiRequest.getString("bodyType"), "json")){
                    JSONObject jsonObject = apiGenerateJson(apiMethodMeta,apiId);
                    apiJson.put("jsonParam",jsonObject);
                }

                JSONArray apiResponseList = generateApiResponse(apiMethodMeta, apiId);
                apiJson.put("responseResultList",apiResponseList);


                children.add(apiJson);
            }

            categoryJson.put("children",children);
            treeArray.add(categoryJson);
        }

        return treeArray;

    }

    /**
     * 生成apix模型
     * @param apiMethodMeta
     * @param apiId
     * @param categoryId
     * @return
     */
    private JSONObject generateApixModel(ApiMethodMeta apiMethodMeta, String apiId,String categoryId){
        JSONObject apix = new JSONObject();
        apix.put("id",apiId);
        apix.put("name",apiMethodMeta.getName());
        apix.put("protocolType","http");
        apix.put("path",apiMethodMeta.getPath());
        apix.put("categoryId",categoryId);
        return apix;
    }

    /**
     * 生成node模型
     * @param apiMethodMeta
     * @param apiId
     * @param categoryId
     * @return
     */
    private JSONObject generateNode(ApiMethodMeta apiMethodMeta, String apiId,String categoryId){
        JSONObject node = new JSONObject();
        node.put("id",apiId);
        node.put("name",apiMethodMeta.getName());
        node.put("parentId",categoryId);
        node.put("type","http");
        node.put("methodType",apiMethodMeta.getRequestType().toLowerCase());

        return node;
    }

    /**
     * 生成apiRequest模型
     */
    public JSONObject generateApiRequest(ApiMethodMeta apiMethodMeta,String apiId) {
        JSONObject apiRequest = new JSONObject();
        String bodyType = apiMethodMeta.getParamDataType();
        apiRequest.put("id",apiId);
        apiRequest.put("apiId",apiId);

        if("json".equals(bodyType)){
            apiRequest.put("bodyType","json");
        }

        //这里获取的bodyTyp为form-data字符串，postin为formdata
        if("form-data".equals(bodyType)) {
            apiRequest.put("bodyType","formdata");
        }

        return apiRequest;
    }

    /**
     * 生成formData模型
     * @param apiMethodMeta
     * @param apiId
     * @return
     */
    private JSONArray apiGenerateFormData(ApiMethodMeta apiMethodMeta, String apiId){
        if(apiMethodMeta.getApiParamMetaList()!=null){

            JSONArray formDataList = new JSONArray();
            for(ApiParamMeta apiParamMeta:apiMethodMeta.getApiParamMetaList()){
                JSONObject formData = new JSONObject();
                String id = UUID.randomUUID().toString().substring(0, 12);
                formData.put("id",id);
                formData.put("httpId",apiId);
                formData.put("paramName",apiParamMeta.getName());
                formData.put("required",apiParamMeta.isRequired()?1:0);
                String dataType = handleJavaLangType(apiParamMeta.getDataType());
                formData.put("dataType",dataType);
                formData.put("desc",apiParamMeta.getEg());
                formDataList.add(formData);
            }
           return formDataList;
        }

        return null;
    }

    /**
     * 生成json模型
     * @param apiMethodMeta
     * @param apiId
     * @return
     */
    private JSONObject apiGenerateJson(ApiMethodMeta apiMethodMeta,String apiId){
        JSONObject json = new JSONObject();
        json.put("id",apiId);
        json.put("apiId",apiId);

        ApiParamMeta apiParamMeta = apiMethodMeta.getApiParamMetaList().get(0);
        GeneratorSchema schema = new GeneratorSchema();
        JSONObject jsonSchema = schema.generateSchema(apiParamMeta);
        json.put("jsonText",jsonSchema.toJSONString());

        return json;
    }

    private JSONArray generateApiResponse(ApiMethodMeta apiMethodMeta,String apiId){
        JSONArray responseResultList = new JSONArray();

        JSONObject responseResult = new JSONObject();
        responseResult.put("id",UUID.randomUUID().toString().substring(0, 12));
        responseResult.put("httpId",apiId);
        responseResult.put("httpCode",200);
        responseResult.put("dataType","json");
        responseResult.put("name","成功");
        GeneratorSchema schema = new GeneratorSchema();
        JSONObject resultSchema = schema.generateSchema(apiMethodMeta.getApiResultMeta());
        responseResult.put("jsonText",resultSchema.toJSONString());


        responseResultList.add(responseResult);
        return responseResultList;
    }


    /**
     * 数据类型
     * @param typeName
     * @return
     */
    private String handleJavaLangType(String typeName) {
        if (typeName.contains("Integer") || typeName.contains("Long") || typeName.contains("Short") || typeName.contains("Byte")) {
            return "integer";
        } else if (typeName.contains("Double") || typeName.contains("Float")) {
            return "number";
        } else if (typeName.contains("Boolean")) {
            return "boolean";
        } else if (typeName.contains("Character") || typeName.contains("String")) {
            return "string";
        } else {
            return "string";
        }
    }


    /**
     * 使用md5 获取id
     */
    public String getIdByMd5(String data) {
        // 如果 data 为空，则自动生成一个 UUID 字符串
        if (data == null || data.isEmpty()) {
            data = UUID.randomUUID().toString();
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md5.digest(data.getBytes(StandardCharsets.UTF_8));
            // 格式化为 32 位十六进制字符串
            String hashString = String.format("%032x", new BigInteger(1, hashBytes));
            // 返回前 12 个字符作为 ID
            return hashString.substring(0, 12);
        } catch (NoSuchAlgorithmException e) {
            // 根据实际情况选择抛出异常或其他处理方式
            throw new RuntimeException("MD5 算法不可用", e);
        }
    }


}
