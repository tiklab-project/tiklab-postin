package io.tiklab.postin.support.imexport.type.openapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import io.tiklab.postin.api.apix.service.JsonParamService;
import io.tiklab.postin.api.apix.service.QueryParamService;
import io.tiklab.postin.api.apix.service.RawParamService;
import io.tiklab.postin.api.apix.service.RequestHeaderService;
import io.tiklab.postin.api.http.definition.model.ApiResponse;
import io.tiklab.postin.api.http.definition.model.FormParam;
import io.tiklab.postin.api.http.definition.model.FormUrlencoded;
import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.postin.api.http.definition.model.PathParam;
import io.tiklab.postin.api.http.definition.service.ApiResponseService;
import io.tiklab.postin.api.http.definition.service.FormParamService;
import io.tiklab.postin.api.http.definition.service.FormUrlencodedService;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.postin.api.http.definition.service.PathParamService;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.common.ErrorCode;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.support.imexport.common.FunctionImport;
import io.tiklab.postin.support.imexport.service.OpenApiProcessor;
import io.tiklab.postin.workspace.model.Workspace;

/**
 * OpenAPI 3.1.x 版本的具体处理器
 */
@Component
public class OpenApi31XImport extends AbstractOpenApiImport implements OpenApiProcessor {

    private static Logger logger = LoggerFactory.getLogger(OpenApi31XImport.class);


    @Autowired
    OpenApiCommonFn openApiCommonFn;

    @Autowired
    FunctionImport functionImport;

    @Autowired
    CategoryService categoryService;

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
    JsonParamService jsonParamService;

    @Override
    public void process(JSONObject allJson, String workspaceId) {
        HashMap<String, String> categoryIdAndTag = createCategories(workspaceId, allJson);

        analysisData(allJson,categoryIdAndTag,workspaceId);
    }

    /**
     * 创建分组
     * @param workspaceId
     * @param allJson
     * @return
     */
    public HashMap<String, String> createCategories(String workspaceId, JSONObject allJson) {
        HashMap<String, String> categoryIdAndTag = new HashMap<>();

        try {
            // 检查 tags 是否存在或为空
            JSONArray tags = allJson.getJSONArray("tags");
            if (tags == null || tags.isEmpty()) {
                // 创建一个默认分组
                String defaultCategoryName = openApiCommonFn.DEFAULT_CATEGORY_NAME;

                Category category = new Category();
                Node node = new Node();
                Workspace workspace1 = new Workspace();
                workspace1.setId(workspaceId);
                node.setWorkspace(workspace1);
                node.setName(defaultCategoryName);
                category.setNode(node);

                String defaultCategoryId = categoryService.createCategory(category);
                categoryIdAndTag.put(defaultCategoryName, defaultCategoryId);
            } else {
                // 处理正常的 tags
                for (Object tag : tags) {
                    JSONObject tagInfo = (JSONObject) tag;
                    String categoryName = tagInfo.getString("name");

                    // 导入分组
                    Category category = new Category();
                    Node node = new Node();
                    Workspace workspace1 = new Workspace();
                    workspace1.setId(workspaceId);
                    node.setWorkspace(workspace1);
                    node.setName(categoryName);
                    category.setNode(node);

                    String categoryId = categoryService.createCategory(category);
                    categoryIdAndTag.put(categoryName, categoryId);
                }
            }
        }catch (Exception e){
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,e.getMessage());
        }

        return categoryIdAndTag;
    }


    /**
     *  解析数据
     */
    public void analysisData(JSONObject allJson, HashMap<String, String> categoryIdAndTag,String workspaceId){
        try {
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

                    String tag;
                    JSONArray tagsArray = methodInfo.getJSONArray("tags");
                    if (tagsArray != null && !tagsArray.isEmpty()) {
                        tag = tagsArray.getString(0);
                    } else {
                        tag = openApiCommonFn.DEFAULT_CATEGORY_NAME;
                    }


                    String categoryId = categoryIdAndTag.get(tag);
                    //创建基础数据
                    String apiId = addApi(workspaceId,categoryId, name, path, method, desc);

                    //解析请求参数
                    analysisRequest(methodInfo,apiId,allJson);

                    //解析响应参数
                    analysisResponse(methodInfo,apiId,allJson);

                }
            }
        }catch (Exception e){
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,e.getMessage());
        }
    }




    /**
     * 添加接口
     */
    @Override
    protected String addApi(
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
        apix.setCategoryId(categoryId);
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
    @Override
    protected void analysisRequest(JSONObject methodInfo, String apiId, JSONObject allJson) {
        // parameters
        if(methodInfo.containsKey("parameters")){
            JSONArray parameters = methodInfo.getJSONArray("parameters");
            for (int i = 0; i < parameters.size(); i++) {
                JSONObject parameter = parameters.getJSONObject(i);

                // 解析 $ref
                if (parameter.containsKey("$ref")) {
                    parameter = openApiCommonFn.resolveRef(parameter.getString("$ref"), allJson);
                }

                String in = parameter.getString("in");
                switch (in){
                    case "path":
                        addPath(parameter,apiId);
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

            // 解析 $ref
            if (requestBody.containsKey("$ref")) {
                requestBody = openApiCommonFn.resolveRef(requestBody.getString("$ref"), allJson);
            }


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
                        case "application/json":
                            addJson(content.getJSONObject(mediaType),apiId,allJson);
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
    @Override
    protected void addHeader(JSONObject headerObj, String methodId) {
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
    @Override
    protected void addQuery(JSONObject queryObj, String methodId) {
        QueryParam queryParam = new QueryParam();
        queryParam.setApiId(methodId);
        queryParam.setParamName(queryObj.getString("name"));
        queryParam.setRequired(queryObj.getBoolean("required")?1:0);
        if(queryObj.containsKey("description")) {
            queryParam.setDesc(queryObj.getString("description"));
        }

        queryParamService.createQueryParam(queryParam);
    }


    private void addPath(JSONObject pathObj, String methodId) {
        // 健壮性检查
        if (pathObj == null || methodId == null) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,"Path object or method ID cannot be null");
        }

        // 创建 PathParam 对象
        PathParam pathParam = new PathParam();
        pathParam.setApiId(methodId);

        // 设置 name
        if (pathObj.containsKey("name")) {
            pathParam.setName(pathObj.getString("name"));
        } else {
            throw new IllegalArgumentException("Path parameter must have a 'name' field");
        }

        // 设置 required
        if (pathObj.containsKey("required")) {
            pathParam.setRequired(pathObj.getBoolean("required") ? 1 : 0);
        } else {
            throw new IllegalArgumentException("Path parameter must have a 'required' field");
        }

        // 设置 schema
        if (pathObj.containsKey("schema")) {
            JSONObject schema = pathObj.getJSONObject("schema");

            // 设置 dataType
            if (schema.containsKey("type")) {
                pathParam.setDataType(schema.getString("type"));
            } else {
                throw new IllegalArgumentException("Schema must have a 'type' field");
            }

            // 设置 value (example)
            if (schema.containsKey("example")) {
                pathParam.setValue(schema.getString("example"));
            } else {
                pathParam.setValue("");
            }


        } else {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,"Path parameter must have a 'schema' field");
        }

        // 设置 description（可选）
        if (pathObj.containsKey("description")) {
            pathParam.setDesc(pathObj.getString("description"));
        }

        pathParamService.createPathParam(pathParam);
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
            case "application/json":
                bodyType = MagicValue.REQUEST_BODY_TYPE_JSON;
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
    @Override
    protected void addRaw(JSONObject bodyObj, String apiId, String rawType, JSONObject allJson) {
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
    public void analysisResponse(JSONObject methodInfo, String apiId, JSONObject allJson) {
        JSONObject responses = methodInfo.getJSONObject("responses");
        for (String statusCode : responses.keySet()) {
            JSONObject responseItem = responses.getJSONObject(statusCode);

            // 解析 $ref
            if (responseItem.containsKey("$ref")) {
                responseItem = openApiCommonFn.resolveRef(responseItem.getString("$ref"), allJson);
            }

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setName(statusCode);
            apiResponse.setId(apiId);
            apiResponse.setHttpId(apiId);
            if(statusCode.equals("default")){
                apiResponse.setHttpCode(200);
            }else {
                apiResponse.setHttpCode(Integer.parseInt(statusCode));
            }

            if(responseItem.containsKey("content")){
                JSONObject content = responseItem.getJSONObject("content");

                // 获取 content 的第一个 mediaType
                String firstMediaType = content.keySet().iterator().next();

                // 获取第一个 mediaType 对应的 schema
                String txt = processSchema(content.getJSONObject(firstMediaType), allJson);

                // 设置并保存 API 响应
                apiResponse.setRawText(txt);
                apiResponse.setDataType("raw");
                apiResponseService.createApiResponse(apiResponse);
            }

        }
    }

    /**
     * 处理schema并转换为JSON
     */
    @Override
    protected String processSchema(JSONObject parameter, JSONObject allJson) {
        if (!parameter.containsKey("schema")) {
            return new JSONObject().toJSONString();
        }

        JSONObject schema = parameter.getJSONObject("schema");
        JSONObject definitions = getDefinitions(allJson);

        Object result = processSchemaObject(schema, definitions);
        return result.toString();
    }

    /**
     * 获取components/schemas
     */
    @Override
    protected JSONObject getDefinitions(JSONObject allJson) {
        if (allJson.containsKey("components") &&
                allJson.getJSONObject("components").containsKey("schemas")) {
            return allJson.getJSONObject("components").getJSONObject("schemas");
        }
        return new JSONObject();
    }

    /**
     * 处理Schema对象
     */
    @Override
    protected Object processSchemaObject(JSONObject schema, JSONObject definitions) {
        // 处理引用
        if (schema.containsKey("$ref")) {
            return processReference(schema.getString("$ref"), definitions);
        }

        // 处理类型
        List<String> types = getSchemaTypes(schema);

        // 如果没有指定类型，默认作为对象处理
        if (types.isEmpty()) {
            return processObjectSchema(schema, definitions);
        }

        // 处理多类型情况（3.1.x支持）
        if (types.size() > 1) {
            // 如果包含null，使用非null的类型
            types.remove("null");
            if (types.isEmpty()) {
                return null;
            }
        }

        String mainType = types.get(0);
        return processTypeSchema(mainType, schema, definitions);
    }

    /**
     * 获取schema的类型列表
     */
    @Override
    protected List<String> getSchemaTypes(JSONObject schema) {
        List<String> types = new ArrayList<>();

        if (schema.containsKey("type")) {
            Object typeObj = schema.get("type");
            if (typeObj instanceof String) {
                types.add((String) typeObj);
            } else if (typeObj instanceof JSONArray) {
                // 处理3.1.x的多类型定义
                JSONArray typeArray = (JSONArray) typeObj;
                types.addAll(typeArray.toJavaList(String.class));
            }
        }

        return types;
    }

    /**
     * 根据类型处理schema
     */
    @Override
    protected Object processTypeSchema(String type, JSONObject schema, JSONObject definitions) {
        switch (type) {
            case "object":
                return processObjectSchema(schema, definitions);
            case "array":
                return processArraySchema(schema, definitions);
            default:
                return openApiCommonFn.getDefaultValue(type, schema);
        }
    }

    /**
     * 处理对象类型的schema
     */
    @Override
    protected JSONObject processObjectSchema(JSONObject schema, JSONObject definitions) {
        JSONObject result = new JSONObject();

        if (!schema.containsKey("properties")) {
            return result;
        }

        JSONObject properties = schema.getJSONObject("properties");
        for (String key : properties.keySet()) {
            JSONObject property = properties.getJSONObject(key);
            result.put(key, processSchemaObject(property, definitions));
        }

        return result;
    }

    /**
     * 处理数组类型的schema
     */
    @Override
    protected JSONArray processArraySchema(JSONObject schema, JSONObject definitions) {
        JSONArray result = new JSONArray();

        if (!schema.containsKey("items")) {
            return result;
        }

        JSONObject items = schema.getJSONObject("items");
        result.add(processSchemaObject(items, definitions));

        return result;
    }

    /**
     * 处理引用
     */
    @Override
    protected Object processReference(String ref, JSONObject definitions) {
        String definitionName = ref.substring(ref.lastIndexOf('/') + 1);
        if (!definitions.containsKey(definitionName)) {
            return new JSONObject();
        }

        return processSchemaObject(definitions.getJSONObject(definitionName), definitions);
    }

    /**
     * 处理JSON类型的请求体
     * @param bodyObj 请求体对象
     * @param apiId API ID
     * @param allJson 完整的OpenAPI文档
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

            // 处理$ref引用
            if (schema.containsKey("$ref")) {
                schema = openApiCommonFn.resolveRef(schema.getString("$ref"), allJson);
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
                        property = openApiCommonFn.resolveRef(property.getString("$ref"), allJson);
                    }
                    
                    JSONObject propertySchema = new JSONObject();
                    
                    // 设置类型（处理3.1的多类型）
                    if (property.containsKey("type")) {
                        Object typeObj = property.get("type");
                        if (typeObj instanceof JSONArray) {
                            // 处理多类型
                            JSONArray types = (JSONArray) typeObj;
                            // 移除null类型，使用nullable标记
                            types.remove("null");
                            if (!types.isEmpty()) {
                                propertySchema.put("type", types.getString(0));
                                propertySchema.put("nullable", true);
                            } else {
                                propertySchema.put("type", "string");
                            }
                        } else {
                            propertySchema.put("type", typeObj.toString());
                        }
                    } else {
                        propertySchema.put("type", "string");
                    }
                    
                    // 设置描述
                    if (property.containsKey("description")) {
                        propertySchema.put("description", property.getString("description"));
                    }
                    
                    // 设置是否必填
                    if (property.containsKey("required")) {
                        propertySchema.put("required", property.getBoolean("required"));
                    }
                    
                    // 处理数组类型
                    if ("array".equals(propertySchema.getString("type")) && property.containsKey("items")) {
                        JSONObject items = property.getJSONObject("items");
                        if (items.containsKey("$ref")) {
                            items = openApiCommonFn.resolveRef(items.getString("$ref"), allJson);
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
            logger.error("处理JSON请求体时发生错误: {}", e.getMessage(), e);
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, "处理JSON请求体失败: " + e.getMessage());
        }
    }

}