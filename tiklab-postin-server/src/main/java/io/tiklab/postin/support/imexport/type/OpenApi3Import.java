package io.tiklab.postin.support.imexport.type;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.api.apix.model.*;
import io.tiklab.postin.api.apix.service.*;
import io.tiklab.postin.api.http.definition.model.ApiResponse;
import io.tiklab.postin.api.http.definition.model.FormParam;
import io.tiklab.postin.api.http.definition.model.FormUrlencoded;
import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.postin.api.http.definition.service.*;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.support.imexport.common.FunctionImport;
import io.tiklab.postin.workspace.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

/**
 * OpenApi3 导入
 */
@Component
public class OpenApi3Import {

    @Autowired
    FunctionImport functionImport;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ApixService apixService;

    @Autowired
    HttpApiService httpApiService;

    @Autowired
    RequestHeaderService requestHeaderService;

    @Autowired
    QueryParamService queryParamService;

    @Autowired
    ApiRequestService apiRequestService;

    @Autowired
    FormParamService formParamService;

    @Autowired
    FormUrlencodedService formUrlencodedService;

    @Autowired
    RawParamService rawParamService;

    @Autowired
    ApiResponseService apiResponseService;



    /**
     * OpenApi3解析
     */
    public void analysisOpenApi3( String workspaceId, InputStream stream) {

        try{
            JSONObject allJson =functionImport.getJsonData(stream);

            HashMap<String, String> categoryIdAndTag = new HashMap<>();

            //解析相应的字段
            JSONArray tags = allJson.getJSONArray("tags");
            for (Object tag : tags) {
                JSONObject tagInfo = (JSONObject) tag;
                String categoryName = tagInfo.getString("name");

                //导入分组
                Category category = new Category();
                Node node = new Node();
                Workspace workspace1 = new Workspace();
                workspace1.setId(workspaceId);
                node.setWorkspace(workspace1);
                node.setName(categoryName);
                category.setNode(node);
                String categoryId = categoryService.createCategory(category);

                categoryIdAndTag.put(categoryName,categoryId);
            }

            analysisData(allJson,categoryIdAndTag,workspaceId);
        }catch (Exception e){
            throw new ApplicationException("解析失败",e);
        }
    }

    /**
     *  解析数据
     */
    private void analysisData(JSONObject allJson, HashMap<String, String> categoryIdAndTag,String workspaceId){
        JSONObject paths = allJson.getJSONObject("paths");

        //path
        Set<String> keys = paths.keySet();
        for (String key : keys) {
            JSONObject apiInfo = paths.getJSONObject(key);

            // 请求类型
            Set<String> methodKeys = apiInfo.keySet();
            for (String methodKey : methodKeys) {
                JSONObject methodInfo = apiInfo.getJSONObject(methodKey);
                //接口名
                String name = methodInfo.getString("summary");

                //描述
                String desc = "";
                if (methodInfo.containsKey("description") && methodInfo.getString("description") != null) {
                    desc = methodInfo.getString("description");
                }


                String path = key;
                String method = methodKey;
                String tag = methodInfo.getJSONArray("tags").getString(0);
                String categoryId = categoryIdAndTag.get(tag);
                //创建接口
                String apiId = addApi(workspaceId,categoryId, name, path, method, desc);

                //解析请求参数
                analysisRequest(methodInfo,apiId,allJson);

                //解析响应参数
                analysisResponse(methodInfo,apiId,allJson);
            }
        }
    }


    /**
     * 添加接口
     */
    private String addApi(
            String workspaceId,
            String categoryId,
            String name,
            String path,
            String method,
            String desc
    ){
        HttpApi httpApi = new HttpApi();

        Apix apix = new Apix();
        apix.setPath(path);
        apix.setDesc(desc);
        httpApi.setApix(apix);

        Node node = new Node();
        Workspace workspace1 = new Workspace();
        workspace1.setId(workspaceId);
        node.setWorkspace(workspace1);
        node.setName(name);
        node.setParentId(categoryId);
        node.setMethodType(method.toLowerCase());
        httpApi.setNode(node);

        String httpApiId = httpApiService.createHttpApi(httpApi);

        return httpApiId;
    }

    /**
     * 解析请求参数
     * @param methodInfo
     * @param apiId
     * @param allJson
     */
    private void analysisRequest(JSONObject methodInfo, String apiId, JSONObject allJson){
        // parameters
        if(methodInfo.containsKey("parameters")){
            JSONArray parameters = methodInfo.getJSONArray("parameters");
            for (int i = 0; i < parameters.size(); i++) {
                JSONObject parameter = parameters.getJSONObject(i);

                String in = parameter.getString("in");
                switch (in){
                    case "path":
                        break;
                    case "query":
                        addQuery(parameter,apiId);
                        break;
                    case "header":
                        addHeader(parameter,apiId);
                        break;

                }
            }
        }


        //requestBody
        if(methodInfo.containsKey("requestBody")){
            JSONObject requestBody = methodInfo.getJSONObject("requestBody");
            if(requestBody.containsKey("content")){

                JSONObject content = requestBody.getJSONObject("content");

                if(content.keySet().size()==0){
                    addRequest("none",apiId);
                    return;
                }

                for (String mediaType : content.keySet()) {
                    addRequest(mediaType,apiId);

                    switch (mediaType){
                        case "application/x-www-form-urlencoded":
                            addFormUrlencoded(content.getJSONObject(mediaType),apiId,allJson);
                            break;
                        case "multipart/form-data":
                            addFormData(content.getJSONObject(mediaType),apiId);
                            break;
                        default:
                            addRaw(content.getJSONObject(mediaType),apiId,mediaType,allJson);
                            break;
                    }
                }
            }
        }
    }

    /**
     * 解析请求头 header
     */
    private void addHeader(JSONObject headerObj, String methodId){
            RequestHeader requestHeader = new RequestHeader();
            requestHeader.setApiId(methodId);
            requestHeader.setHeaderName(headerObj.getString("name"));
            requestHeader.setRequired(headerObj.getBoolean("required")?1:0);
            if(headerObj.containsKey("description")){
                requestHeader.setDesc(headerObj.getString("description"));
            }

            requestHeaderService.createRequestHeader(requestHeader);

    }

    /**
     * 创建query参数
     */
    private void addQuery(JSONObject queryObj, String methodId){
        QueryParam queryParam = new QueryParam();
        queryParam.setApiId(methodId);
        queryParam.setParamName(queryObj.getString("name"));
        queryParam.setRequired(queryObj.getBoolean("required")?1:0);
        if(queryObj.containsKey("description")) {
            queryParam.setDesc(queryObj.getString("description"));
        }

        queryParamService.createQueryParam(queryParam);
    }

    /**
     * 导入请求参数
     * @param mediaType
     * @param methodId
     */
    public void addRequest(String mediaType,String methodId){
        String bodyType;
        switch (mediaType){
            case "multipart/form-data":
                bodyType= MagicValue.REQUEST_BODY_TYPE_FORMDATA;
                break;
            case "application/x-www-form-urlencoded":
                bodyType = MagicValue.REQUEST_BODY_TYPE_FORM_URLENCODED;
                break;
            default:
                bodyType= "raw";
        }

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setId(methodId);
        apiRequest.setBodyType(bodyType);
        apiRequest.setApiId(methodId);

        apiRequestService.updateApiRequest(apiRequest);
    }

    /**
     * 解析formdata
     * @param formParamObj
     * @param methodId
     */
    private void addFormData(JSONObject formParamObj, String methodId){
        JSONObject schema = formParamObj.getJSONObject("schema");

        if (schema != null && schema.containsKey("properties")) {
            JSONObject properties = schema.getJSONObject("properties");

            for (String key : properties.keySet()) {
                JSONObject field = properties.getJSONObject(key);

                FormParam formParams = new FormParam();

                formParams.setHttp(new HttpApi().setId(methodId));
                formParams.setParamName(key); // 设置参数名称

                // 检查并设置字段类型
                if (field.containsKey("type")) {
                    formParams.setDataType(field.getString("type"));
                } else {
                    formParams.setDataType("string"); // 默认设置为 "string"
                }

                // 检查并设置是否必填
                if (field.containsKey("required")) {
                    formParams.setRequired(field.getBoolean("required") ? 1 : 0);
                } else {
                    formParams.setRequired(0); // 默认为非必填
                }

                // 检查并设置字段描述
                if (field.containsKey("description")) {
                    formParams.setDesc(field.getString("description"));
                } else {
                    formParams.setDesc(""); // 如果没有描述，设置为空字符串
                }

                formParamService.createFormParam(formParams);
            }
        }
    }

    /**
     * 解析formurl
     * @param urlencodedObj
     * @param methodId
     * @param allJson
     */
    private void addFormUrlencoded(JSONObject urlencodedObj, String methodId, JSONObject allJson) {
        JSONObject schema = urlencodedObj.getJSONObject("schema");

        if (schema != null && schema.containsKey("properties")) {
            JSONObject properties = schema.getJSONObject("properties");

            for (String key : properties.keySet()) {
                JSONObject field = properties.getJSONObject(key);

                FormUrlencoded formUrlencoded = new FormUrlencoded();

                formUrlencoded.setHttp(new HttpApi().setId(methodId));
                formUrlencoded.setParamName(key); // 设置参数名称
                formUrlencoded.setDataType(field.getString("type")); // 获取字段的类型

                // 检查并设置是否必填
                if (field.containsKey("required")) {
                    formUrlencoded.setRequired(field.getBoolean("required") ? 1 : 0);
                } else {
                    formUrlencoded.setRequired(0); // 默认为非必填
                }

                // 检查并设置字段描述
                if (field.containsKey("description")) {
                    formUrlencoded.setDesc(field.getString("description"));
                } else {
                    formUrlencoded.setDesc(""); // 如果没有描述，设置为空字符串
                }

                formUrlencodedService.createFormUrlencoded(formUrlencoded);
            }
        }
    }


    /**
     *  解析 raw
     */
    private void addRaw(JSONObject bodyObj, String apiId, String rawType, JSONObject allJson){
        String txt = processSchema(bodyObj, allJson);
        RawParam rawParam = new RawParam();
        rawParam.setRaw(txt);
        rawParam.setType(rawType);
        rawParam.setId(apiId);
        rawParam.setApiId(apiId);

        rawParamService.createRawParam(rawParam);
    }

    /**
     * 解析响应
     * @param methodInfo
     * @param apiId
     * @param allJson
     */
    private void analysisResponse(JSONObject methodInfo, String apiId, JSONObject allJson) {
        JSONObject responses = methodInfo.getJSONObject("responses");
        for (String key : responses.keySet()) {
            JSONObject responseItem = responses.getJSONObject(key);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setName(key);
            apiResponse.setId(apiId);
            apiResponse.setHttpId(apiId);
            if(key.equals("default")){
                apiResponse.setHttpCode(200);
            }else {
                apiResponse.setHttpCode(Integer.parseInt(key));
            }

            if(responseItem.containsKey("content")){
                JSONObject content = responseItem.getJSONObject("content");

                // 获取 content 的第一个 mediaType
                String firstMediaType = content.keySet().iterator().next();

                // 获取第一个 mediaType 对应的 schema
                JSONObject firstSchema = content.getJSONObject(firstMediaType).getJSONObject("schema");
                String txt = processSchema(firstSchema, allJson);

                // 设置并保存 API 响应
                apiResponse.setRawText(txt);
                apiResponse.setDataType("raw");
                apiResponseService.createApiResponse(apiResponse);
            }

        }

    }

    private String processSchema(JSONObject parameter, JSONObject allJson) {
        String processSchema = "";

        if(parameter.containsKey("schema")){
            JSONObject schema = parameter.getJSONObject("schema");

            JSONObject definitions = allJson.getJSONObject("definitions");

            String type = schema.getString("type");
            if(type != null){
                switch (type){
                    case "array":
                        JSONObject items = schema.getJSONObject("items");
                        String itemsRef = items.getString("$ref");
                        JSONObject itemsDefinitionObj = getModelByName(itemsRef,definitions);
                        JSONObject itemsJson = convertDefinitionToJSON(itemsDefinitionObj, definitions);
                        JSONArray jsonArray = new JSONArray();
                        jsonArray.add(itemsJson);
                        processSchema = jsonArray.toJSONString();
                        break;
                    default:
                        processSchema = new JSONObject().toJSONString();
                        break;
                }
            }else {
                String ref = schema.getString("$ref");
                JSONObject definitionObj = getModelByName(ref,definitions);
                JSONObject jsonObject = convertDefinitionToJSON(definitionObj, definitions);
                processSchema = jsonObject.toJSONString();
            }
        }else {
            processSchema = new JSONObject().toJSONString();
        }
        return processSchema;
    }


    /**
     * 递归方法，将定义对象转换为 JSON
     */
    private JSONObject convertDefinitionToJSON(JSONObject schema, JSONObject definitions) {
        JSONObject jsonObject = new JSONObject();

        if (schema.containsKey("properties")) {
            JSONObject properties = schema.getJSONObject("properties");
            for (String key : properties.keySet()) {
                JSONObject property = properties.getJSONObject(key);

                if (property.containsKey("type")) {
                    String type = property.getString("type");

                    if ("object".equals(type) && property.containsKey("$ref")) {
                        String ref = property.getString("$ref");
                        String refDefinitionName = ref.substring(ref.lastIndexOf('/') + 1);

                        if (definitions.containsKey(refDefinitionName)) {
                            JSONObject refDefinition = definitions.getJSONObject(refDefinitionName);
                            jsonObject.put(key, convertDefinitionToJSON(refDefinition, definitions));
                        } else {
                            jsonObject.put(key, new JSONObject()); // 如果引用不存在，使用空对象
                        }
                    } else if ("array".equals(type) && property.containsKey("items")) {
                        JSONObject items = property.getJSONObject("items");
                        if (items.containsKey("$ref")) {
                            String ref = items.getString("$ref");
                            String refDefinitionName = ref.substring(ref.lastIndexOf('/') + 1);

                            if (definitions.containsKey(refDefinitionName)) {
                                JSONObject refDefinition = definitions.getJSONObject(refDefinitionName);
                                jsonObject.put(key, new JSONArray().fluentAdd(convertDefinitionToJSON(refDefinition, definitions)));
                            } else {
                                jsonObject.put(key, new JSONArray()); // 如果引用不存在，使用空数组
                            }
                        } else {
                            jsonObject.put(key, new JSONArray());
                        }
                    } else {
                        jsonObject.put(key, getDefaultValueForType(type));
                    }
                }
            }
        }

        return jsonObject;
    }

    // 获取默认值的方法，根据类型返回默认值
    private Object getDefaultValueForType(String type) {
        switch (type) {
            case "integer":
                return 0;
            case "number":
                return 0.0;
            case "string":
                return "";
            case "boolean":
                return false;
            case "array":
                return new JSONArray();
            case "object":
                return new JSONObject();
            default:
                return null;
        }
    }

    /**
     * 通过$ref 获取对应的模型
     * @param ref
     * @param definitions
     * @return
     */
    private JSONObject getModelByName(String ref, JSONObject definitions) {
        String definitionName = ref.substring(ref.lastIndexOf('/') + 1);
        JSONObject definitionObj = definitions.getJSONObject(definitionName);
        return definitionObj;
    }
}
