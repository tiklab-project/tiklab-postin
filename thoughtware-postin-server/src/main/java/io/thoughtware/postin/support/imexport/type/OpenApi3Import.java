package io.thoughtware.postin.support.imexport.type;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.postin.api.apix.model.*;
import io.thoughtware.postin.api.apix.service.*;
import io.thoughtware.postin.api.http.definition.model.ApiResponse;
import io.thoughtware.postin.api.http.definition.model.FormParam;
import io.thoughtware.postin.api.http.definition.model.FormUrlencoded;
import io.thoughtware.postin.api.http.definition.model.HttpApi;
import io.thoughtware.postin.api.http.definition.service.*;
import io.thoughtware.postin.category.model.Category;
import io.thoughtware.postin.category.service.CategoryService;
import io.thoughtware.postin.common.MagicValue;
import io.thoughtware.postin.node.model.Node;
import io.thoughtware.postin.support.imexport.common.FunctionImport;
import io.thoughtware.postin.workspace.model.Workspace;
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

    @Autowired
    JsonResponseService jsonResponseService;


    private String workspaceIds;


    /**
     * OpenApi3解析
     */
    public void analysisOpenApi3( String workspaceId, InputStream stream) {
        workspaceIds =workspaceId;
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

            analysisData(allJson,categoryIdAndTag);
        }catch (Exception e){
            throw new ApplicationException("解析失败",e);
        }
    }

    /**
     *  解析数据
     */
    private void analysisData(JSONObject allJson, HashMap<String, String> categoryIdAndTag){
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
                String desc = methodInfo.getString("description");

                String path = key;
                String method = methodKey;
                String tag = methodInfo.getJSONArray("tags").getString(0);
                String categoryId = categoryIdAndTag.get(tag);
                //创建接口
                String apiId = addApi(categoryId, name, path, method, desc);

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
             mimeType = methodInfo.getJSONArray("consumes").getString(0);
        }else {
             mimeType = "none";
        }
        addRequest(mimeType,apiId);

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
                case "body":
                    addRaw(parameter,apiId,mimeType,allJson);
                    break;
                case "header":
                    addHeader(parameter,apiId);
                    break;
                case "formData":
                    addFormData(parameter,apiId);
                    break;
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
            queryParam.setRequired(queryObj.getBoolean("required")?1:0);
            queryParam.setDesc(queryObj.getString("description"));

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
            case "application/x-www-form-urlencoded":
                bodyType= MagicValue.REQUEST_BODY_TYPE_FORMDATA;
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
        FormParam formParams = new FormParam();

        formParams.setHttp(new HttpApi().setId(methodId));
        formParams.setParamName(formParamObj.getString("name"));
        formParams.setRequired(formParamObj.getBoolean("required")?1:0);
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
        formUrlencoded.setRequired(urlencodedObj.getBoolean("required")?1:0);
        formUrlencoded.setDesc(urlencodedObj.getString("description"));

        formUrlencodedService.createFormUrlencoded(formUrlencoded);
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
            String txt = processSchema(responseItem, allJson);
            apiResponse.setRawText(txt);
            apiResponse.setDataType("raw");
            apiResponseService.createApiResponse(apiResponse);
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
    private JSONObject convertDefinitionToJSON(JSONObject definition, JSONObject definitions) {
        JSONObject jsonObject = new JSONObject();

        if (definition.containsKey("properties")) {
            JSONObject properties = definition.getJSONObject("properties");
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
