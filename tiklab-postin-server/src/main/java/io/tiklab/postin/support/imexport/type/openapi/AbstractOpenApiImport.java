package io.tiklab.postin.support.imexport.type.openapi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.api.apix.model.ApiRequest;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.model.QueryParam;
import io.tiklab.postin.api.apix.model.RawParam;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.apix.service.ApiRequestService;
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
import io.tiklab.postin.workspace.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * OpenAPI导入的抽象基类
 */
public abstract class AbstractOpenApiImport {

    @Autowired
    protected OpenApiCommonFn openApiCommonFn;

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected ApiResponseService apiResponseService;

    /**
     * 创建分组
     */
    protected HashMap<String, String> createCategories(String workspaceId, JSONObject allJson) {
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
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, e.getMessage());
        }

        return categoryIdAndTag;
    }

    /**
     * 解析数据
     */
    protected void analysisData(JSONObject allJson, HashMap<String, String> categoryIdAndTag, String workspaceId) {
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
                    String apiId = addApi(workspaceId, categoryId, name, path, method, desc);

                    //解析请求参数
                    analysisRequest(methodInfo, apiId, allJson);

                    //解析响应参数
                    analysisResponse(methodInfo, apiId, allJson);
                }
            }
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, e.getMessage());
        }
    }

    /**
     * 添加接口
     */
    protected abstract String addApi(
            String workspaceId,
            String categoryId,
            String name,
            String path,
            String method,
            String desc
    );

    /**
     * 解析请求参数
     */
    protected abstract void analysisRequest(JSONObject methodInfo, String apiId, JSONObject allJson);

    /**
     * 解析请求头 header
     */
    protected abstract void addHeader(JSONObject headerObj, String methodId);

    /**
     * 创建query参数
     */
    protected abstract void addQuery(JSONObject queryObj, String methodId);

    /**
     * 解析 raw
     */
    protected abstract void addRaw(JSONObject bodyObj, String apiId, String rawType, JSONObject allJson);

    /**
     * 处理schema并转换为JSON
     */
    protected abstract String processSchema(JSONObject parameter, JSONObject allJson);

    /**
     * 获取components/schemas
     */
    protected abstract JSONObject getDefinitions(JSONObject allJson);

    /**
     * 处理Schema对象
     */
    protected abstract Object processSchemaObject(JSONObject schema, JSONObject definitions);

    /**
     * 获取schema的类型列表
     */
    protected abstract List<String> getSchemaTypes(JSONObject schema);

    /**
     * 根据类型处理schema
     */
    protected abstract Object processTypeSchema(String type, JSONObject schema, JSONObject definitions);

    /**
     * 处理对象类型的schema
     */
    protected abstract JSONObject processObjectSchema(JSONObject schema, JSONObject definitions);

    /**
     * 处理数组类型的schema
     */
    protected abstract JSONArray processArraySchema(JSONObject schema, JSONObject definitions);

    /**
     * 处理引用
     */
    protected abstract Object processReference(String ref, JSONObject definitions);

    /**
     * 解析响应
     */
    protected void analysisResponse(JSONObject methodInfo, String apiId, JSONObject allJson) {
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
            if (statusCode.equals("default")) {
                apiResponse.setHttpCode(200);
            } else {
                apiResponse.setHttpCode(Integer.parseInt(statusCode));
            }

            if (responseItem.containsKey("content")) {
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
} 