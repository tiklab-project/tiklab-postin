package io.tiklab.postin.support.imexport.type.swagger;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

import io.tiklab.postin.api.http.definition.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.api.apix.model.ApiRequest;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.model.JsonParam;
import io.tiklab.postin.api.apix.model.QueryParam;
import io.tiklab.postin.api.apix.model.RawParam;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.apix.service.ApiRequestService;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.apix.service.JsonParamService;
import io.tiklab.postin.api.apix.service.QueryParamService;
import io.tiklab.postin.api.apix.service.RawParamService;
import io.tiklab.postin.api.apix.service.RequestHeaderService;
import io.tiklab.postin.api.http.definition.service.ApiResponseService;
import io.tiklab.postin.api.http.definition.service.AuthParamService;
import io.tiklab.postin.api.http.definition.service.FormParamService;
import io.tiklab.postin.api.http.definition.service.FormUrlencodedService;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.postin.api.http.definition.service.JsonResponseService;
import io.tiklab.postin.api.http.definition.service.PathParamService;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.common.ErrorCode;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.support.imexport.common.FunctionImport;
import io.tiklab.postin.workspace.model.Workspace;

/**
 * swagger2 导入
 */
@Component
public class Swagger2Import {

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
    PathParamService pathParamService;

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

    @Autowired
    JsonResponseService jsonResponseService;

    @Autowired
    AuthParamService authParamService;

    @Autowired
    JsonParamService jsonParamService;


    private String workspaceIds;

    /**
     * 创建分类的通用方法
     * @param categoryName 分类名称
     * @param workspaceId 工作空间ID
     * @param categoryIdAndTag 分类映射表
     * @return 分类ID
     */
    private String createCategoryIfNotExists(String categoryName, String workspaceId, HashMap<String, String> categoryIdAndTag) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return null;
        }
        
        String trimmedName = categoryName.trim();
        
        // 检查是否已经存在该分类
        if (categoryIdAndTag.containsKey(trimmedName)) {
            return categoryIdAndTag.get(trimmedName);
        }
        
        // 创建新分类
        Category category = new Category();
        Node node = new Node();
        Workspace workspace = new Workspace();
        workspace.setId(workspaceId);
        node.setWorkspace(workspace);
        node.setName(trimmedName);
        category.setNode(node);
        
        String categoryId = categoryService.createCategory(category);
        categoryIdAndTag.put(trimmedName, categoryId);
        
        return categoryId;
    }

    /**
     * swagger2解析
     */
    public void analysisSwagger2( String workspaceId, InputStream stream) {
        workspaceIds =workspaceId;
        try{
            JSONObject allJson =functionImport.getJsonData(stream);

            HashMap<String, String> categoryIdAndTag = new HashMap<>();

            //解析相应的字段
            JSONArray tags = allJson.getJSONArray("tags");
            if (tags != null) {
                for (Object tag : tags) {
                    JSONObject tagInfo = (JSONObject) tag;
                    String categoryName = tagInfo.getString("name");
                    
                    // 使用通用方法创建分类
                    createCategoryIfNotExists(categoryName, workspaceId, categoryIdAndTag);
                }
            }

            analysisData(allJson,categoryIdAndTag);
        }catch (Exception e){
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,e.getMessage());
        }
    }

    /**
     *  解析数据
     */
    private void analysisData(JSONObject allJson, HashMap<String, String> categoryIdAndTag){
        JSONObject paths = allJson.getJSONObject("paths");
        if (paths == null) {
            return;
        }

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
                if (name == null || name.trim().isEmpty()) {
                    name = methodKey.toUpperCase() + " " + key; // 使用HTTP方法和路径作为默认名称
                }
                //描述
                String desc = methodInfo.getString("description");
                if (desc == null) {
                    desc = ""; // 避免null描述
                }

                String path = key;
                String method = methodKey;
                String tag = null;
                JSONArray methodTags = methodInfo.getJSONArray("tags");
                if (methodTags != null && !methodTags.isEmpty()) {
                    tag = methodTags.getString(0);
                }
                String categoryId = categoryIdAndTag.get(tag);
                // 如果没有找到对应的分类，则创建分类
                if (categoryId == null) {
                    if (tag != null && !tag.trim().isEmpty()) {
                        // 如果有tag，创建对应分类
                        categoryId = createCategoryIfNotExists(tag, workspaceIds, categoryIdAndTag);
                    } else {
                        // 如果没有tag，创建默认分类
                        categoryId = createCategoryIfNotExists("默认分类", workspaceIds, categoryIdAndTag);
                    }
                }
                //创建接口
                String apiId = addApi(categoryId, name, path, method, desc);

                //解析请求参数
                analysisRequest(methodInfo,apiId,allJson);

                //解析响应参数
                analysisResponse(methodInfo,apiId,allJson);

                //解析认证信息
                analysisSecurity(methodInfo,apiId,allJson);
            }
        }
    }


    /**
     * 添加接口
     */
    private String addApi(String categoryId,String name,String path,String method,String desc){
        HttpApi httpApi = new HttpApi();

        Apix apix = new Apix();
        apix.setPath(path);
        apix.setDesc(desc);
        httpApi.setApix(apix);

        Node node = new Node();
        Workspace workspace1 = new Workspace();
        workspace1.setId(workspaceIds);
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
        //请求类型
        String mimeType;
        if(methodInfo.containsKey("consumes")){
            JSONArray consumes = methodInfo.getJSONArray("consumes");
            if (consumes != null && !consumes.isEmpty()) {
                mimeType = consumes.getString(0);
            } else {
                mimeType = "none";
            }
        }else {
             mimeType = "none";
        }
        addRequest(mimeType,apiId);

        JSONArray parameters = methodInfo.getJSONArray("parameters");
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
            JSONObject parameter = parameters.getJSONObject(i);

            String in = parameter.getString("in");
            switch (in){
                case "path":
                    addPathParam(parameter,apiId);
                    break;
                case "query":
                    addQuery(parameter,apiId);
                    break;
                case "header":
                    addHeader(parameter,apiId);
                    break;
                case "body":
                    if ("application/json".equals(mimeType)) {
                        addJson(parameter, apiId, allJson);
                    } else {
                        addRaw(parameter,apiId,mimeType,allJson);
                    }
                    break;
                case "formData":
                    // 根据consumes类型决定如何处理formData参数
                    if ("application/x-www-form-urlencoded".equals(mimeType)) {
                        // 如果consumes是form-urlencoded，但参数in是formData，按form-urlencoded处理
                        addFormUrlencoded(parameter,apiId);
                    } else {
                        // 默认按multipart/form-data处理
                        addFormData(parameter,apiId);
                    }
                    break;
            }
            }
        }
    }

    /**
     * 解析路径参数 path
     */
    private void addPathParam(JSONObject pathParamObj, String methodId){
        String paramName = pathParamObj.getString("name");
        String paramType = pathParamObj.getString("type");
        String paramDesc = pathParamObj.getString("description");

        PathParam pathParam = new PathParam();
        pathParam.setApiId(methodId);
        pathParam.setName(paramName);
        pathParam.setDataType(paramType);
        pathParam.setDesc(paramDesc);
        pathParamService.createPathParam(pathParam);
    }

    /**
     * 解析请求头 header
     */
    private void addHeader(JSONObject headerObj, String methodId){
            RequestHeader requestHeader = new RequestHeader();
            requestHeader.setApiId(methodId);
            requestHeader.setHeaderName(headerObj.getString("name"));
            requestHeader.setRequired(getBooleanSafely(headerObj, "required") ? 1 : 0);
            requestHeader.setDesc(headerObj.getString("description"));

            requestHeaderService.createRequestHeader(requestHeader);

    }

    /**
     * 创建query参数
     */
    private void addQuery(JSONObject queryObj, String methodId){
            QueryParam queryParam = new QueryParam();
            queryParam.setApiId(methodId);
            queryParam.setParamName(queryObj.getString("name"));
            queryParam.setRequired(getBooleanSafely(queryObj, "required") ? 1 : 0);
            queryParam.setDesc(queryObj.getString("description"));
            
            // 处理默认值和参数类型
            String defaultValue = queryObj.getString("default");
            String paramType = queryObj.getString("type");

            if (defaultValue != null || paramType != null) {
                String currentDesc = queryParam.getDesc();
                if (currentDesc == null) currentDesc = "";
                
                StringBuilder enhancedDesc = new StringBuilder(currentDesc);
                if (defaultValue != null) {
                    enhancedDesc.append(" [默认值: ").append(defaultValue).append("]");
                }
                if (paramType != null) {
                    enhancedDesc.append(" [类型: ").append(paramType).append("]");
                }
                queryParam.setDesc(enhancedDesc.toString());
            }

            queryParamService.createQueryParam(queryParam);

    }

    /**
     * 导入请求参数
     * @param consumes
     * @param methodId
     */
    public void addRequest(String consumes,String methodId){
        String bodyType;
        switch (consumes){
            case "multipart/form-data":
                bodyType= MagicValue.REQUEST_BODY_TYPE_FORMDATA;
                break;
            case "application/x-www-form-urlencoded":
                bodyType = MagicValue.REQUEST_BODY_TYPE_FORM_URLENCODED;
                break;
            case "application/json":
                bodyType = MagicValue.REQUEST_BODY_TYPE_JSON;
                break;
            default:
                bodyType= MagicValue.REQUEST_BODY_TYPE_RAW;
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
        FormParam formParams = new FormParam();

        formParams.setHttp(new HttpApi().setId(methodId));
        formParams.setParamName(formParamObj.getString("name"));
        formParams.setRequired(getBooleanSafely(formParamObj, "required") ? 1 : 0);
        formParams.setDataType(formParamObj.getString("type"));
        formParams.setDesc(formParamObj.getString("description"));

        formParamService.createFormParam(formParams);
    }

    /**
     * 解析formurl
     * @param urlencodedObj
     * @param methodId
     */
    private void addFormUrlencoded(JSONObject urlencodedObj, String methodId){
        FormUrlencoded formUrlencoded = new FormUrlencoded();

        formUrlencoded.setHttp(new HttpApi().setId(methodId));
        formUrlencoded.setParamName(urlencodedObj.getString("name"));
        formUrlencoded.setDataType(urlencodedObj.getString("type"));
        formUrlencoded.setRequired(getBooleanSafely(urlencodedObj, "required") ? 1 : 0);
        formUrlencoded.setDesc(urlencodedObj.getString("description"));

        formUrlencodedService.createFormUrlencoded(formUrlencoded);
    }

    /**
     * 处理JSON类型的请求体
     * @param bodyObj 请求体对象
     * @param apiId API ID
     * @param allJson 完整的Swagger文档
     */
    private void addJson(JSONObject bodyObj, String apiId, JSONObject allJson) {
        try {
            // 获取schema
            JSONObject schema = bodyObj.getJSONObject("schema");
            if (schema == null) {
                // 如果没有schema，创建一个空的object schema
                schema = new JSONObject();
                schema.put("type", "object");
                schema.put("properties", new JSONObject());
            }

            // 构建正确的schema格式
            JSONObject jsonSchema = new JSONObject();
            jsonSchema.put("type", "object");
            
            // 获取properties
            JSONObject properties = new JSONObject();
            if (schema.containsKey("properties")) {
                JSONObject schemaProperties = schema.getJSONObject("properties");
                for (String key : schemaProperties.keySet()) {
                    JSONObject property = schemaProperties.getJSONObject(key);
                    
                    // 处理属性的$ref引用
                    if (property.containsKey("$ref")) {
                        property = getModelByName(property.getString("$ref"), allJson.getJSONObject("definitions"));
                    }
                    
                    JSONObject propertySchema = new JSONObject();
                    
                    // 设置类型
                    if (property.containsKey("type")) {
                        propertySchema.put("type", property.getString("type"));
                    } else {
                        propertySchema.put("type", "string");
                    }
                    
                    // 设置描述
                    if (property.containsKey("description")) {
                        propertySchema.put("description", property.getString("description"));
                    }
                    
                    // 设置是否必填
                    if (property.containsKey("required")) {
                        propertySchema.put("required", getBooleanSafely(property, "required"));
                    }
                    
                    // 处理数组类型
                    if ("array".equals(propertySchema.getString("type")) && property.containsKey("items")) {
                        JSONObject items = property.getJSONObject("items");
                        if (items.containsKey("$ref")) {
                            items = getModelByName(items.getString("$ref"), allJson.getJSONObject("definitions"));
                        }
                        propertySchema.put("items", items);
                    }
                    
                    properties.put(key, propertySchema);
                }
            }
            
            jsonSchema.put("properties", properties);
            
            // 创建JsonParam对象
            JsonParam jsonParam = new JsonParam();
            jsonParam.setApiId(apiId);
            jsonParam.setId(apiId);
            jsonParam.setJsonText(jsonSchema.toJSONString());
            
            // 保存JSON参数
            jsonParamService.updateJsonParam(jsonParam);
            
        } catch (Exception e) {
            // 如果JSON处理失败，回退到Raw处理
            addRaw(bodyObj, apiId, "application/json", allJson);
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
        if (responses != null) {
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

            // 检查响应是否包含schema，如果有则判断是否为JSON类型
            if (responseItem.containsKey("schema")) {
                JSONObject schema = responseItem.getJSONObject("schema");
                String responseType = determineResponseType(schema, allJson);
                
                if ("json".equals(responseType)) {
                    // JSON响应，进行结构化处理
                    String jsonSchema = processJsonResponseSchema(schema, allJson);
                    apiResponse.setDataType("json");
                    apiResponse.setJsonText(jsonSchema);
                } else {
                    // 其他类型响应，使用原始文本
                    String txt = processSchema(responseItem, allJson);
                    apiResponse.setDataType("raw");
                    apiResponse.setRawText(txt);
                }
            } else {
                // 没有schema，使用原始文本
                String txt = processSchema(responseItem, allJson);
                apiResponse.setDataType("raw");
                apiResponse.setRawText(txt);
            }
            
            apiResponseService.createApiResponse(apiResponse);
            }
        }
    }

    private String processSchema(JSONObject parameter, JSONObject allJson) {
        String processSchema = "";

        if(parameter.containsKey("schema")){
            JSONObject schema = parameter.getJSONObject("schema");

            JSONObject definitions = allJson.getJSONObject("definitions");
            if (definitions == null) {
                // 如果没有definitions，直接处理schema中的内联定义
                return processInlineSchema(schema);
            }

            String type = schema.getString("type");
            if(type != null){
                switch (type){
                    case "array":
                        JSONObject items = schema.getJSONObject("items");
                        if (items != null) {
                            String itemsRef = items.getString("$ref");
                            if (itemsRef != null) {
                                JSONObject itemsDefinitionObj = getModelByName(itemsRef,definitions);
                                JSONObject itemsJson = convertDefinitionToJSON(itemsDefinitionObj, definitions);
                                JSONArray jsonArray = new JSONArray();
                                jsonArray.add(itemsJson);
                                processSchema = jsonArray.toJSONString();
                            } else {
                                processSchema = new JSONArray().toJSONString();
                            }
                        } else {
                            processSchema = new JSONArray().toJSONString();
                        }
                        break;
                    default:
                         processSchema = new JSONObject().toJSONString();
                        break;
                }
            }else {
                String ref = schema.getString("$ref");
                if (ref != null) {
                    JSONObject definitionObj = getModelByName(ref,definitions);
                    JSONObject jsonObject = convertDefinitionToJSON(definitionObj, definitions);
                    processSchema = jsonObject.toJSONString();
                } else {
                    processSchema = new JSONObject().toJSONString();
                }
            }
        }else {
            processSchema = new JSONObject().toJSONString();
        }
        return processSchema;
    }


    /**
     * 递归方法，将定义对象转换为 JSON
     */
    private JSONObject convertDefinitionToJSON(JSONObject definition, JSONObject definitions) {
        JSONObject jsonObject = new JSONObject();

        if (definition != null && definition.containsKey("properties")) {
            JSONObject properties = definition.getJSONObject("properties");
            if (properties != null) {
                for (String key : properties.keySet()) {
                    JSONObject property = properties.getJSONObject(key);

                if (property.containsKey("type")) {
                    String type = property.getString("type");

                    if ("object".equals(type) && property.containsKey("$ref")) {
                        String ref = property.getString("$ref");
                        if (ref != null) {
                            JSONObject refDefinition = getModelByName(ref, definitions);
                            if (refDefinition != null && !refDefinition.isEmpty()) {
                                jsonObject.put(key, convertDefinitionToJSON(refDefinition, definitions));
                            } else {
                                jsonObject.put(key, new JSONObject()); // 如果引用不存在，使用空对象
                            }
                        } else {
                            jsonObject.put(key, new JSONObject());
                        }
                    } else if ("array".equals(type) && property.containsKey("items")) {
                        JSONObject items = property.getJSONObject("items");
                        if (items != null && items.containsKey("$ref")) {
                            String ref = items.getString("$ref");
                            if (ref != null) {
                                JSONObject refDefinition = getModelByName(ref, definitions);
                                if (refDefinition != null && !refDefinition.isEmpty()) {
                                    jsonObject.put(key, new JSONArray().fluentAdd(convertDefinitionToJSON(refDefinition, definitions)));
                                } else {
                                    jsonObject.put(key, new JSONArray()); // 如果引用不存在，使用空数组
                                }
                            } else {
                                jsonObject.put(key, new JSONArray());
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
        if (ref == null || definitions == null) {
            return new JSONObject();
        }
        
        // 处理复杂引用，如 #/definitions/User/properties/id
        if (ref.startsWith("#/definitions/")) {
            String path = ref.substring("#/definitions/".length());
            String[] pathParts = path.split("/");
            
            if (pathParts.length == 1) {
                // 简单引用，如 #/definitions/User
                return definitions.getJSONObject(pathParts[0]);
            } else if (pathParts.length >= 3 && "properties".equals(pathParts[1])) {
                // 属性引用，如 #/definitions/User/properties/id
                JSONObject definition = definitions.getJSONObject(pathParts[0]);
                if (definition != null && definition.containsKey("properties")) {
                    JSONObject properties = definition.getJSONObject("properties");
                    return properties.getJSONObject(pathParts[2]);
                }
            }
        }
        
        // 回退到原来的简单处理方式
        int lastSlashIndex = ref.lastIndexOf('/');
        if (lastSlashIndex == -1 || lastSlashIndex == ref.length() - 1) {
            return new JSONObject();
        }
        
        String definitionName = ref.substring(lastSlashIndex + 1);
        JSONObject definitionObj = definitions.getJSONObject(definitionName);
        return definitionObj != null ? definitionObj : new JSONObject();
    }

    /**
     * 处理内联schema（没有definitions的情况）
     * @param schema schema对象
     * @return JSON字符串
     */
    private String processInlineSchema(JSONObject schema) {
        if (schema == null) {
            return new JSONObject().toJSONString();
        }
        
        String type = schema.getString("type");
        if (type == null) {
            return new JSONObject().toJSONString();
        }
        
        switch (type) {
            case "object":
                if (schema.containsKey("properties")) {
                    JSONObject properties = schema.getJSONObject("properties");
                    JSONObject result = new JSONObject();
                    if (properties != null) {
                        for (String key : properties.keySet()) {
                            JSONObject property = properties.getJSONObject(key);
                            String propType = property.getString("type");
                            result.put(key, getDefaultValueForType(propType));
                        }
                    }
                    return result.toJSONString();
                } else {
                    return new JSONObject().toJSONString();
                }
            case "array":
                return new JSONArray().toJSONString();
            case "string":
                return "\"\"";
            case "integer":
                return "0";
            case "number":
                return "0.0";
            case "boolean":
                return "false";
            default:
                return new JSONObject().toJSONString();
        }
    }

    /**
     * 解析安全认证配置
     * @param methodInfo 方法信息
     * @param apiId API ID
     * @param allJson 完整的Swagger文档
     */
    private void analysisSecurity(JSONObject methodInfo, String apiId, JSONObject allJson) {
        try {
            JSONObject securityInfo = processSecurity(methodInfo, allJson);
            if (securityInfo.isEmpty()) {
                return;
            }

            JSONObject securityDefinitions = getSecurityDefinitions(allJson);
            if (securityDefinitions.isEmpty()) {
                return;
            }

            // 处理每个安全方案
            for (String schemeName : securityInfo.keySet()) {
                if (!securityDefinitions.containsKey(schemeName)) {
                    continue;
                }

                JSONObject scheme = securityDefinitions.getJSONObject(schemeName);
                String type = scheme.getString("type");

                switch (type) {
                    case "apiKey":
                        processApiKeyAuth(scheme, apiId);
                        break;
                    // case "basic":
                    case "oauth2":
                        // Basic和OAuth2认证都映射为Bearer类型
                        processBearerAuth(scheme, apiId, type);
                        break;
                    default:
                        // 其他不支持的认证类型，映射为Bearer类型
                        processBearerAuth(scheme, apiId, type);
                        break;
                }
            }
        } catch (Exception e) {
            // 认证解析失败不影响整体导入，只记录警告
        }
    }

    /**
     * 处理Swagger2的security配置
     * @param methodInfo 方法信息
     * @param allJson 完整的Swagger文档
     * @return 安全配置信息
     */
    private JSONObject processSecurity(JSONObject methodInfo, JSONObject allJson) {
        JSONObject securityInfo = new JSONObject();
        
        // 优先使用方法级别的security，如果没有则使用全局security
        JSONArray securityArray = null;
        if (methodInfo.containsKey("security")) {
            securityArray = methodInfo.getJSONArray("security");
        } else if (allJson.containsKey("security")) {
            securityArray = allJson.getJSONArray("security");
        }
        
        if (securityArray != null && !securityArray.isEmpty()) {
            // 取第一个security配置
            JSONObject security = securityArray.getJSONObject(0);
            for (String schemeName : security.keySet()) {
                JSONArray scopes = security.getJSONArray(schemeName);
                securityInfo.put(schemeName, scopes);
            }
        }
        
        return securityInfo;
    }

    /**
     * 获取安全方案配置
     * @param allJson 完整的Swagger文档
     * @return 安全方案配置
     */
    private JSONObject getSecurityDefinitions(JSONObject allJson) {
        if (allJson.containsKey("securityDefinitions")) {
            return allJson.getJSONObject("securityDefinitions");
        }
        return new JSONObject();
    }

    /**
     * 处理API Key认证
     */
    private void processApiKeyAuth(JSONObject scheme, String apiId) {
        try {
            String name = scheme.getString("name");
            String in = scheme.getString("in");

            // 创建AuthParam主记录
            AuthParam authParam = new AuthParam();
            authParam.setId(apiId);
            authParam.setApiId(apiId);
            authParam.setType(MagicValue.AUTHENTICATION_TYPE_APIKEY);

            // 创建AuthApiKey子记录
            AuthApiKey authApiKey = new AuthApiKey();
            authApiKey.setId(apiId);
            authApiKey.setApiId(apiId);
            authApiKey.setName(name);
            authApiKey.setValue(""); // 默认空值，用户需要手动填写
            authApiKey.setApikeyIn(in);
            authParam.setApiKey(authApiKey);

            // 保存认证信息
            authParamService.createAuthParam(authParam);
        } catch (Exception e) {
            // 认证创建失败不影响整体导入
        }
    }

    /**
     * 处理Bearer类型认证（包括Basic、OAuth2等）
     */
    private void processBearerAuth(JSONObject scheme, String apiId, String originalType) {
        try {
            // 创建AuthParam主记录
            AuthParam authParam = new AuthParam();
            authParam.setId(apiId);
            authParam.setApiId(apiId);
            authParam.setType(MagicValue.AUTHENTICATION_TYPE_BEARER);

            // 创建AuthBearer子记录
            AuthBearer authBearer = new AuthBearer();
            authBearer.setId(apiId);
            authBearer.setApiId(apiId);
            authBearer.setName("Authorization");
            
            // 根据原始认证类型设置默认值
            String defaultValue;
            switch (originalType) {
                // case "basic":
                //     defaultValue = "Basic "; // Basic认证格式
                    // break;
                case "oauth2":
                    defaultValue = "Bearer "; // OAuth2认证格式
                    break;
                default:
                    defaultValue = "Bearer "; // 默认Bearer格式
                    break;
            }
            authBearer.setValue(defaultValue);
            authParam.setBearer(authBearer);

            // 保存认证信息
            authParamService.createAuthParam(authParam);
        } catch (Exception e) {
            // 认证创建失败不影响整体导入
        }
    }

    /**
     * 判断响应类型是否为JSON
     * @param schema 响应schema
     * @param allJson 完整的Swagger文档
     * @return 响应类型
     */
    private String determineResponseType(JSONObject schema, JSONObject allJson) {
        if (schema == null) {
            return "raw";
        }
        
        // 处理$ref引用
        if (schema.containsKey("$ref")) {
            JSONObject definitions = allJson.getJSONObject("definitions");
            if (definitions != null) {
                JSONObject refSchema = getModelByName(schema.getString("$ref"), definitions);
                if (refSchema != null) {
                    return determineResponseType(refSchema, allJson);
                }
            }
        }
        
        // 检查类型
        String type = schema.getString("type");
        if ("object".equals(type) || "array".equals(type)) {
            return "json";
        }
        
        // 如果没有明确的type，但有properties，也认为是JSON
        if (schema.containsKey("properties")) {
            return "json";
        }
        
        return "raw";
    }

    /**
     * 处理JSON响应Schema
     * @param schema 响应schema
     * @param allJson 完整的Swagger文档
     * @return JSON Schema字符串
     */
    private String processJsonResponseSchema(JSONObject schema, JSONObject allJson) {
        try {
            // 处理$ref引用
            if (schema.containsKey("$ref")) {
                JSONObject definitions = allJson.getJSONObject("definitions");
                if (definitions != null) {
                    schema = getModelByName(schema.getString("$ref"), definitions);
                }
            }
            
            if (schema == null) {
                return new JSONObject().toJSONString();
            }
            
            // 构建JSON Schema
            JSONObject jsonSchema = new JSONObject();
            
            // 设置类型
            if (schema.containsKey("type")) {
                String type = schema.getString("type");
                jsonSchema.put("type", type);
                
                // 处理数组类型：将items转换为properties.ITEMS格式（与OpenAPI保持一致）
                if ("array".equals(type) && schema.containsKey("items")) {
                    JSONObject items = schema.getJSONObject("items");
                    
                    // 处理items的$ref引用
                    if (items.containsKey("$ref")) {
                        JSONObject definitions = allJson.getJSONObject("definitions");
                        if (definitions != null) {
                            items = getModelByName(items.getString("$ref"), definitions);
                        }
                    }
                    
                    // 将数组的items转换为properties.ITEMS格式
                    JSONObject properties = new JSONObject();
                    properties.put("ITEMS", processSchemaRecursively(items, allJson));
                    jsonSchema.put("properties", properties);
                    jsonSchema.put("required", new JSONArray().fluentAdd("ITEMS"));
                    
                    // 处理描述
                    if (schema.containsKey("description")) {
                        jsonSchema.put("description", schema.getString("description"));
                    }
                    
                    return jsonSchema.toJSONString();
                }
            } else {
                jsonSchema.put("type", "object");
            }
            
            // 处理properties（非数组类型）
            if (schema.containsKey("properties")) {
                JSONObject properties = new JSONObject();
                JSONObject schemaProperties = schema.getJSONObject("properties");
                
                for (String key : schemaProperties.keySet()) {
                    JSONObject property = schemaProperties.getJSONObject(key);
                    properties.put(key, processSchemaRecursively(property, allJson));
                }
                
                jsonSchema.put("properties", properties);
            }
            
            // 处理required字段
            if (schema.containsKey("required")) {
                jsonSchema.put("required", schema.get("required"));
            }
            
            // 处理描述
            if (schema.containsKey("description")) {
                jsonSchema.put("description", schema.getString("description"));
            }
            
            return jsonSchema.toJSONString();
            
        } catch (Exception e) {
            return new JSONObject().toJSONString();
        }
    }

    /**
     * 递归处理Schema（与OpenAPI保持一致）
     * @param schema 当前schema
     * @param allJson 完整的Swagger文档
     * @return 处理后的schema
     */
    private JSONObject processSchemaRecursively(JSONObject schema, JSONObject allJson) {
        JSONObject result = new JSONObject();
        
        if (schema == null) {
            return result;
        }
        
        // 处理$ref引用
        if (schema.containsKey("$ref")) {
            JSONObject definitions = allJson.getJSONObject("definitions");
            if (definitions != null) {
                return getModelByName(schema.getString("$ref"), definitions);
            }
            return result;
        }
        
        // 设置类型
        if (schema.containsKey("type")) {
            String type = schema.getString("type");
            result.put("type", type);
            
            // 处理数组类型：将items转换为properties.ITEMS格式
            if ("array".equals(type) && schema.containsKey("items")) {
                JSONObject items = schema.getJSONObject("items");
                
                // 处理items的$ref引用
                if (items.containsKey("$ref")) {
                    JSONObject definitions = allJson.getJSONObject("definitions");
                    if (definitions != null) {
                        items = getModelByName(items.getString("$ref"), definitions);
                    }
                }
                
                JSONObject properties = new JSONObject();
                properties.put("ITEMS", processSchemaRecursively(items, allJson));
                result.put("properties", properties);
                result.put("required", new JSONArray().fluentAdd("ITEMS"));
                return result; // 数组类型处理完毕，直接返回
            }
        }
        
        // 处理properties（非数组类型或数组类型但没有items字段）
        if (schema.containsKey("properties")) {
            JSONObject properties = new JSONObject();
            JSONObject schemaProperties = schema.getJSONObject("properties");
            
            for (String key : schemaProperties.keySet()) {
                JSONObject property = schemaProperties.getJSONObject(key);
                properties.put(key, processSchemaRecursively(property, allJson));
            }
            
            result.put("properties", properties);
        }
        
        // 处理required字段
        if (schema.containsKey("required")) {
            result.put("required", schema.get("required"));
        }
        
        // 处理描述
        if (schema.containsKey("description")) {
            result.put("description", schema.getString("description"));
        }
        
        // 处理其他字段
        for (String key : schema.keySet()) {
            if (!result.containsKey(key) && !"$ref".equals(key)) {
                result.put(key, schema.get(key));
            }
        }
        
        return result;
    }

    /**
     * 安全获取Boolean值，避免空指针异常
     * @param jsonObject JSON对象
     * @param key 键名
     * @return Boolean值，如果为null则返回false
     */
    private boolean getBooleanSafely(JSONObject jsonObject, String key) {
        Boolean value = jsonObject.getBoolean(key);
        return value != null && value;
    }
}
