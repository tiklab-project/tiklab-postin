package io.tiklab.postin.support.imexport.type.openapi;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.api.http.definition.model.ApiResponse;
import io.tiklab.postin.api.http.definition.model.AuthApiKey;
import io.tiklab.postin.api.http.definition.model.AuthBearer;
import io.tiklab.postin.api.http.definition.model.AuthParam;
import io.tiklab.postin.api.http.definition.service.ApiResponseService;
import io.tiklab.postin.api.http.definition.service.AuthApiKeyService;
import io.tiklab.postin.api.http.definition.service.AuthBearerService;
import io.tiklab.postin.api.http.definition.service.AuthParamService;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.common.ErrorCode;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.workspace.model.Workspace;

/**
 * OpenAPI导入的抽象基类
 */
public abstract class AbstractOpenApiImport {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractOpenApiImport.class);

    @Autowired
    protected OpenApiCommonFn openApiCommonFn;

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected ApiResponseService apiResponseService;

    @Autowired
    protected AuthParamService authParamService;

    @Autowired
    protected AuthApiKeyService authApiKeyService;

    @Autowired
    protected AuthBearerService authBearerService;

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

                    //解析安全认证配置
                    analysisSecurity(methodInfo, apiId, allJson);
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

    /**
     * 解析安全认证配置
     * @param methodInfo 方法信息
     * @param apiId API ID
     * @param allJson 完整的OpenAPI文档
     */
    protected void analysisSecurity(JSONObject methodInfo, String apiId, JSONObject allJson) {
        try {
            JSONObject securityInfo = openApiCommonFn.processSecurity(methodInfo, allJson);
            if (securityInfo.isEmpty()) {
                return;
            }

            JSONObject securitySchemes = openApiCommonFn.getSecuritySchemes(allJson);
            if (securitySchemes.isEmpty()) {
                logger.warn("API {} 配置了安全认证但未找到对应的安全方案", apiId);
                return;
            }

            // 处理每个安全方案
            for (String schemeName : securityInfo.keySet()) {
                if (!securitySchemes.containsKey(schemeName)) {
                    logger.warn("未找到安全方案: {}", schemeName);
                    continue;
                }

                JSONObject scheme = securitySchemes.getJSONObject(schemeName);
                String type = scheme.getString("type");

                switch (type) {
                    case MagicValue.AUTHENTICATION_TYPE_APIKEY:
                        processApiKeyAuth(scheme, apiId);
                        break;
                    case OpenApiCommonFn.SECURITY_TYPE_HTTP:
                        String schemeType = scheme.getString("scheme");
                        if (OpenApiCommonFn.SECURITY_SCHEME_BEARER.equalsIgnoreCase(schemeType)) {
                            processBearerAuth(scheme, apiId);
                        }
                        break;
                    default:
                        logger.debug("不支持的安全认证类型: {}", type);
                        break;
                }
            }
        } catch (Exception e) {
            logger.warn("处理安全认证配置时出错: {}", e.getMessage());
        }
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
            logger.debug("添加API Key认证: {} in {}", name, in);
        } catch (Exception e) {
            logger.warn("创建API Key认证失败: {}", e.getMessage());
        }
    }

    /**
     * 处理Bearer Token认证
     */
    private void processBearerAuth(JSONObject scheme, String apiId) {
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
            authBearer.setValue("Bearer "); // 默认格式，用户需要填写token
            authParam.setBearer(authBearer);

            // 保存认证信息
            authParamService.createAuthParam(authParam);
            logger.debug("添加Bearer Token认证");
        } catch (Exception e) {
            logger.warn("创建Bearer Token认证失败: {}", e.getMessage());
        }
    }
} 