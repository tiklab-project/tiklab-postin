package io.tiklab.postin.support.imexport.type.postman;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
import io.tiklab.postin.api.http.definition.model.AuthApiKey;
import io.tiklab.postin.api.http.definition.model.AuthBearer;
import io.tiklab.postin.api.http.definition.model.AuthParam;
import io.tiklab.postin.api.http.definition.model.FormParam;
import io.tiklab.postin.api.http.definition.model.FormUrlencoded;
import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.postin.api.http.definition.model.PathParam;
import io.tiklab.postin.api.http.definition.service.AuthParamService;
import io.tiklab.postin.api.http.definition.service.FormParamService;
import io.tiklab.postin.api.http.definition.service.FormUrlencodedService;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.postin.api.http.definition.service.PathParamService;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.common.ErrorCode;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.support.environment.model.EnvVariable;
import io.tiklab.postin.support.environment.service.EnvVariableService;
import io.tiklab.postin.support.imexport.common.FunctionImport;
import io.tiklab.postin.workspace.model.Workspace;

/**
 * Postman Collection导入器
 * 支持导入Postman collection v2.1格式的数据
 */
@Component
public class PostmanImport {

    private static final Logger logger = LoggerFactory.getLogger(PostmanImport.class);
    
    // Postman 2.1 常量
    private static final String DEFAULT_API_NAME = "未命名API";
    private static final String DEFAULT_CATEGORY_NAME = "导入的Collection";
    private static final String DEFAULT_PATH = "/";
    private static final String DEFAULT_METHOD = "GET";
    
    // 请求体类型映射
    private static final String BODY_MODE_RAW = "raw";
    private static final String BODY_MODE_FORMDATA = "formdata";
    private static final String BODY_MODE_URLENCODED = "urlencoded";
    private static final String BODY_MODE_FILE = "file";
    private static final String BODY_MODE_NONE = "none";
    
    // 认证类型 - 使用MagicValue中定义的常量
    private static final String AUTH_TYPE_BEARER = MagicValue.AUTHENTICATION_TYPE_BEARER;
    private static final String AUTH_TYPE_APIKEY = MagicValue.AUTHENTICATION_TYPE_APIKEY;
    
    // 全局变量存储
    private JSONArray globalVariables;
    // Collection级别的认证信息
    private JSONObject collectionAuth;

    @Autowired
    private FunctionImport functionImport;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private HttpApiService httpApiService;
    @Autowired
    private RequestHeaderService requestHeaderService;
    @Autowired
    private QueryParamService queryParamService;
    @Autowired
    private PathParamService pathParamService;
    @Autowired
    private ApiRequestService apiRequestService;
    @Autowired
    private FormParamService formParamService;
    @Autowired
    private FormUrlencodedService formUrlencodedService;
    @Autowired
    private RawParamService rawParamService;
    @Autowired
    private AuthParamService authParamService;

    @Autowired
    EnvVariableService envVariableService;

    private String workspaceId;
    private String rootCategoryId;

    /**
     * 解析并导入Postman collection数据
     *
     * @param workspaceId 工作空间ID
     * @param stream Postman collection文件流
     */
    public void analysisPostmanData(String workspaceId, InputStream stream) {
        validateWorkspaceId(workspaceId);
        this.workspaceId = workspaceId;
        logger.info("开始导入Postman数据到工作空间: {}", workspaceId);

        try {
            JSONObject jsonObject = parseJsonData(stream);
            validatePostmanData(jsonObject);

            // 解析全局变量和认证信息
            parseGlobalVariables(jsonObject);
            parseCollectionAuth(jsonObject);
            
            // 创建根分组并导入数据
            String categoryName = getStringOrDefault(jsonObject.getJSONObject("info"), "name", DEFAULT_CATEGORY_NAME);
            this.rootCategoryId = createRootCategory(categoryName);
            analysisData(jsonObject, this.rootCategoryId);

            logger.info("Postman数据导入完成，共处理工作空间: {}", workspaceId);
        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,
                    "导入Postman数据失败: " + e.getMessage(), e);
        }
    }

    /**
     * 解析全局变量
     */
    private void parseGlobalVariables(JSONObject jsonObject) {
        this.globalVariables = jsonObject.getJSONArray("variable");
        if (this.globalVariables != null) {
            logger.info("发现 {} 个全局变量", this.globalVariables.size());
            
            // 将Postman的全局变量保存到环境变量系统
            for (int i = 0; i < this.globalVariables.size(); i++) {
                try {
                    JSONObject variableObj = this.globalVariables.getJSONObject(i);
                    if (variableObj == null) {
                        continue;
                    }
                    
                    String name = getStringOrDefault(variableObj, "key", "");
                    String value = getStringOrDefault(variableObj, "value", "");
                    String type = getStringOrDefault(variableObj, "type", "string");
                    
                    if (StringUtils.hasText(name)) {
                        EnvVariable envVariable = new EnvVariable();
                        envVariable.setWorkspaceId(workspaceId);
                        envVariable.setName(name);
                        envVariable.setValue(value);
                        envVariable.setDesc("从Postman导入的全局变量，类型: " + type);
                        
                        // 保存环境变量
                        envVariableService.createEnvVariable(envVariable);
                        logger.debug("保存全局变量: {} = {}", name, value);
                    }
                } catch (Exception e) {
                    logger.warn("保存全局变量时出错: {}", e.getMessage());
                }
            }
        }
    }

    /**
     * 解析Collection级别的认证信息
     */
    private void parseCollectionAuth(JSONObject jsonObject) {
        this.collectionAuth = jsonObject.getJSONObject("auth");
        if (this.collectionAuth != null && !this.collectionAuth.isEmpty()) {
            logger.info("发现Collection级别的认证信息");
        }
    }

    /**
     * 提取描述信息，支持多种格式
     */
    private String extractDescription(JSONObject info) {
        // 尝试从description字段获取
        Object desc = info.get("description");
        if (desc instanceof String str) {
            return str;
        } else if (desc instanceof JSONObject descObj) {
            // 有些Postman导出可能是对象格式
            return getStringOrDefault(descObj, "content", "");
        }
        return "";
    }

    /**
     * 安全获取字符串值，如果不存在或为null则返回默认值
     */
    private String getStringOrDefault(JSONObject obj, String key, String defaultValue) {
        if (obj == null || !obj.containsKey(key)) {
            return defaultValue;
        }
        String value = obj.getString(key);
        return StringUtils.hasText(value) ? value.trim() : defaultValue;
    }

    /**
     * 安全获取布尔值，如果不存在或为null则返回默认值
     */
    private boolean getBooleanOrDefault(JSONObject obj, String key, boolean defaultValue) {
        if (obj == null || !obj.containsKey(key)) {
            return defaultValue;
        }
        Boolean value = obj.getBoolean(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 验证工作空间ID
     */
    private void validateWorkspaceId(String workspaceId) {
        if (!StringUtils.hasText(workspaceId)) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, "工作空间ID不能为空");
        }
    }

    /**
     * 解析JSON数据
     */
    private JSONObject parseJsonData(InputStream stream) {
        try {
            JSONObject jsonObject = functionImport.getJsonData(stream);
            if (jsonObject == null) {
                throw new ApplicationException(ErrorCode.IMPORT_ERROR, "无法解析Postman数据");
            }
            return jsonObject;
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, "解析Postman数据失败: " + e.getMessage(), e);
        }
    }

    /**
     * 验证Postman数据格式
     */
    private void validatePostmanData(JSONObject jsonObject) {
        if (jsonObject == null) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, "Postman数据为空");
        }

        if (!jsonObject.containsKey("info")) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, "无效的Postman collection格式：缺少info字段");
        }

        JSONObject info = jsonObject.getJSONObject("info");
        if (info == null) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, "info字段不能为空");
        }

        String name = info.getString("name");
        if (!StringUtils.hasText(name)) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, "Postman collection名称不能为空");
        }
    }

    /**
     * 创建根分组
     */
    private String createRootCategory(String name) {
        try {
            Category category = new Category();
            Node node = new Node();

            Workspace workspace = new Workspace();
            workspace.setId(this.workspaceId);

            node.setWorkspace(workspace);
            node.setName(name);

            category.setNode(node);

            return categoryService.createCategory(category);
        } catch (Exception e) {
            logger.error("创建根分组失败: {}", e.getMessage());
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, "创建根分组失败", e);
        }
    }

    /**
     * 构建URL路径，支持路径参数
     */
    private String buildPath(JSONArray pathArray) {
        if (pathArray == null || pathArray.isEmpty()) {
            return DEFAULT_PATH;
        }

        StringBuilder path = new StringBuilder();
        for (int i = 0; i < pathArray.size(); i++) {
            String segment = pathArray.getString(i);
            if (StringUtils.hasText(segment)) {
                // 处理路径参数，将:param转换为{param}
                if (segment.startsWith(":")) {
                    segment = "{" + segment.substring(1) + "}";
                }
                path.append("/").append(segment);
            }
        }

        return path.length() == 0 ? DEFAULT_PATH : path.toString();
    }

    /**
     * 解析数据项
     */
    private void analysisData(JSONObject jsonObject, String categoryId) {
        JSONArray items = jsonObject.getJSONArray("item");
        if (items == null || items.isEmpty()) {
            logger.info("没有找到item数据");
            return;
        }

        logger.info("开始处理 {} 个项目", items.size());
        for (int i = 0; i < items.size(); i++) {
            try {
                JSONObject item = items.getJSONObject(i);
                processItem(item, categoryId);
            } catch (Exception e) {
                logger.error("处理第 {} 个项目时出错: {}", i + 1, e.getMessage());
                // 继续处理其他项目，不因为单个项目失败而中断整个导入
            }
        }
    }

    /**
     * 处理单个项目（可能是文件夹或API请求）
     */
    private void processItem(JSONObject item, String parentCategoryId) {
        if (item == null) {
            logger.warn("跳过空的item");
            return;
        }

        String name = getStringOrDefault(item, "name", "未命名");

        // 如果包含子项目，则创建子分组
        if (item.containsKey("item")) {
            logger.info("处理文件夹: {}", name);
            String childCategoryId = createChildCategory(name,  parentCategoryId);

            JSONArray childItems = item.getJSONArray("item");
            if (childItems != null) {
                for (int i = 0; i < childItems.size(); i++) {
                    JSONObject childItem = childItems.getJSONObject(i);
                    processItem(childItem, childCategoryId);
                }
            }
        }

        // 如果包含请求信息，则创建API
        if (item.containsKey("request")) {
            logger.info("处理API请求: {}", name);
            processApiRequest(item, parentCategoryId);
        }
    }

    /**
     * 创建子分组
     */
    private String createChildCategory(String name, String parentCategoryId) {
        try {
            Category category = new Category();
            Node node = new Node();

            Workspace workspace = new Workspace();
            workspace.setId(this.workspaceId);

            node.setWorkspace(workspace);
            node.setName(name);
            node.setParentId(parentCategoryId);

            category.setNode(node);

            return categoryService.createCategory(category);
        } catch (Exception e) {
            logger.error("创建子分组失败: {}", e.getMessage());
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, "创建子分组失败: " + name, e);
        }
    }

    /**
     * 处理API请求
     */
    private void processApiRequest(JSONObject item, String categoryId) {
        String name = getStringOrDefault(item, "name", DEFAULT_API_NAME);
        JSONObject request = item.getJSONObject("request");

        if (request == null) {
            logger.warn("API请求 {} 缺少request信息，跳过", name);
            return;
        }

        try {
            String method = validateAndNormalizeMethod(getStringOrDefault(request, "method", DEFAULT_METHOD));
            String description = extractDescription(item);

            // 解析URL
            UrlInfo urlInfo = parseUrl(request);
            validateUrlInfo(urlInfo, name);

            // 创建API
            String apiId = createHttpApi(categoryId, name, urlInfo.path, method, description);

            // 处理各种参数
            processRequestHeaders(request, apiId);
            processQueryParameters(urlInfo, apiId);
            processPathParameters(urlInfo, apiId);
            processRequestBody(request, apiId);
            processAuthentication(request, apiId);

            logger.debug("成功处理API请求: {} {}", method, urlInfo.path);
        } catch (Exception e) {
            logger.error("处理API请求失败: {}, 错误: {}", name, e.getMessage());
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, "处理API请求失败: " + name, e);
        }
    }

    /**
     * 验证并标准化HTTP方法
     */
    private String validateAndNormalizeMethod(String method) {
        if (!StringUtils.hasText(method)) {
            return DEFAULT_METHOD;
        }
        
        String normalizedMethod = method.toUpperCase();
        // 验证是否为有效的HTTP方法
        if (!isValidHttpMethod(normalizedMethod)) {
            logger.warn("无效的HTTP方法: {}, 使用默认方法GET", method);
            return DEFAULT_METHOD;
        }
        
        return normalizedMethod;
    }

    /**
     * 检查是否为有效的HTTP方法
     */
    private boolean isValidHttpMethod(String method) {
        return "GET".equals(method) || "POST".equals(method) || "PUT".equals(method) ||
               "DELETE".equals(method) || "PATCH".equals(method) || "HEAD".equals(method) ||
               "OPTIONS".equals(method);
    }

    /**
     * 验证URL信息
     */
    private void validateUrlInfo(UrlInfo urlInfo, String apiName) {
        if (urlInfo == null) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, "URL信息为空: " + apiName);
        }
        
        if (!StringUtils.hasText(urlInfo.path)) {
            logger.warn("API {} 的路径为空，使用默认路径", apiName);
            urlInfo.path = DEFAULT_PATH;
        }
    }

    /**
     * URL信息封装类
     */
    private static class UrlInfo {
        String path;
        List<JSONObject> queryParams = new ArrayList<>();
        List<JSONObject> pathParams = new ArrayList<>();
    }

    /**
     * 解析URL信息，包括路径、查询参数和路径参数
     */
    private UrlInfo parseUrl(JSONObject request) {
        UrlInfo urlInfo = new UrlInfo();
        Object urlObj = request.get("url");

        if (urlObj instanceof String url) {
            // 简单字符串URL
            urlInfo.path = extractPathFromUrl(url);
        } else if (urlObj instanceof JSONObject urlObject) {
            // 对象格式URL - Postman 2.1标准格式
            
            // 优先使用raw字段，如果没有则构建
            String rawUrl = getStringOrDefault(urlObject, "raw", "");
            if (StringUtils.hasText(rawUrl)) {
                urlInfo.path = extractPathFromUrl(rawUrl);
            } else {
                // 解析路径数组
                JSONArray pathArray = urlObject.getJSONArray("path");
                urlInfo.path = buildPath(pathArray);
            }

            // 解析查询参数
            parseQueryParams(urlObject, urlInfo);
            
            // 解析路径参数
            parsePathParams(urlObject, urlInfo);
        } else {
            urlInfo.path = DEFAULT_PATH;
        }

        return urlInfo;
    }

    /**
     * 解析查询参数
     */
    private void parseQueryParams(JSONObject urlObject, UrlInfo urlInfo) {
        JSONArray queryArray = urlObject.getJSONArray("query");
        if (queryArray != null) {
            for (int i = 0; i < queryArray.size(); i++) {
                JSONObject queryObj = queryArray.getJSONObject(i);
                if (queryObj != null) {
                    urlInfo.queryParams.add(queryObj);
                }
            }
        }
    }

    /**
     * 解析路径参数
     */
    private void parsePathParams(JSONObject urlObject, UrlInfo urlInfo) {
        JSONArray variableArray = urlObject.getJSONArray("variable");
        if (variableArray != null) {
            for (int i = 0; i < variableArray.size(); i++) {
                JSONObject variableObj = variableArray.getJSONObject(i);
                if (variableObj != null) {
                    urlInfo.pathParams.add(variableObj);
                }
            }
        }
    }

    /**
     * 从URL字符串中提取路径
     */
    private String extractPathFromUrl(String url) {
        if (!StringUtils.hasText(url)) {
            return DEFAULT_PATH;
        }

        // 移除Postman变量占位符 {{variable}}
        url = url.replaceAll("\\{\\{[^}]+\\}\\}", "");

        try {
            String path;
            if (url.startsWith("http")) {
                // 完整URL，提取路径部分
                java.net.URL parsedUrl = new java.net.URL(url);
                path = parsedUrl.getPath();
            } else {
                // 相对路径或简单路径
                path = extractPathFromRelativeUrl(url);
            }
            
            // 处理路径参数，将:param转换为{param}
            if (StringUtils.hasText(path)) {
                path = convertPathParameters(path);
            }
            
            return StringUtils.hasText(path) ? path : DEFAULT_PATH;
        } catch (java.net.MalformedURLException e) {
            logger.warn("解析URL失败: {}, 使用默认路径", url);
            return DEFAULT_PATH;
        }
    }

    /**
     * 从相对URL中提取路径
     */
    private String extractPathFromRelativeUrl(String url) {
        // 移除查询参数
        int queryIndex = url.indexOf('?');
        if (queryIndex > 0) {
            url = url.substring(0, queryIndex);
        }
        
        // 移除锚点
        int anchorIndex = url.indexOf('#');
        if (anchorIndex > 0) {
            url = url.substring(0, anchorIndex);
        }
        
        // 确保路径以/开头
        return url.startsWith("/") ? url : "/" + url;
    }

    /**
     * 转换路径参数，将:param格式转换为{param}格式
     */
    private String convertPathParameters(String path) {
        if (!StringUtils.hasText(path)) {
            return path;
        }

        // 使用正则表达式将:param转换为{param}
        // 匹配:后面跟着字母、数字、下划线的参数名
        return path.replaceAll(":([a-zA-Z_][a-zA-Z0-9_]*)", "{$1}");
    }

    /**
     * 创建HTTP API
     */
    private String createHttpApi(String categoryId, String name, String path, String method, String description) {
        try {
            HttpApi httpApi = new HttpApi();

            Apix apix = new Apix();
            apix.setPath(path);
            if (StringUtils.hasText(description)) {
                apix.setDesc(description);
            }
            httpApi.setApix(apix);

            Node node = new Node();
            Workspace workspace = new Workspace();
            workspace.setId(this.workspaceId);

            node.setWorkspace(workspace);
            node.setName(name);
            node.setParentId(categoryId);
            node.setMethodType(method.toLowerCase());
            httpApi.setNode(node);

            return httpApiService.createHttpApi(httpApi);
        } catch (Exception e) {
            logger.error("创建API失败: {}", e.getMessage());
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, "创建API失败: " + name, e);
        }
    }

    /**
     * 处理请求头
     */
    private void processRequestHeaders(JSONObject request, String apiId) {
        if (!request.containsKey("header")) {
            return;
        }

        JSONArray headers = request.getJSONArray("header");
        if (headers == null) {
            return;
        }

        for (int i = 0; i < headers.size(); i++) {
            try {
                JSONObject headerObj = headers.getJSONObject(i);

                // 跳过禁用的请求头
                if (getBooleanOrDefault(headerObj, "disabled", false)) {
                    continue;
                }

                String headerName = getStringOrDefault(headerObj, "key", "");
                if (!StringUtils.hasText(headerName)) {
                    continue;
                }

                RequestHeader requestHeader = new RequestHeader();
                requestHeader.setApiId(apiId);
                requestHeader.setHeaderName(headerName);
                requestHeader.setValue(getStringOrDefault(headerObj, "value", ""));
                requestHeader.setRequired(1); // 默认必需
                requestHeader.setDesc(getStringOrDefault(headerObj, "description", ""));

                requestHeaderService.createRequestHeader(requestHeader);
            } catch (Exception e) {
                logger.warn("处理请求头时出错: {}", e.getMessage());
            }
        }
    }

    /**
     * 处理查询参数
     */
    private void processQueryParameters(UrlInfo urlInfo, String apiId) {

        if (urlInfo.queryParams == null || urlInfo.queryParams.isEmpty()) {
            return;
        }

        for (JSONObject queryObj : urlInfo.queryParams) {
            try {
                // 跳过禁用的查询参数
                if (getBooleanOrDefault(queryObj, "disabled", false)) {
                    continue;
                }

                String paramName = getStringOrDefault(queryObj, "key", "");
                if (!StringUtils.hasText(paramName)) {
                    continue;
                }

                QueryParam queryParam = new QueryParam();
                queryParam.setApiId(apiId);
                queryParam.setParamName(paramName);
                queryParam.setValue(getStringOrDefault(queryObj, "value", ""));
                // 修复逻辑：disabled为false表示启用，启用时required为1
                queryParam.setRequired(getBooleanOrDefault(queryObj, "disabled", false) ? 0 : 1);
                queryParam.setDesc(getStringOrDefault(queryObj, "description", ""));

                queryParamService.createQueryParam(queryParam);
            } catch (Exception e) {
                logger.warn("处理查询参数时出错: {}", e.getMessage());
            }
        }
    }

    /**
     * 处理路径参数
     */
    private void processPathParameters(UrlInfo urlInfo, String apiId) {
        if (urlInfo.pathParams == null || urlInfo.pathParams.isEmpty()) {
            return;
        }
        for (JSONObject pathObj : urlInfo.pathParams) {
            try {
                String paramName = getStringOrDefault(pathObj, "key", "");
                String paramValue = getStringOrDefault(pathObj, "value", "");
                String description = getStringOrDefault(pathObj, "description", "");

                PathParam pathParam = new PathParam();
                pathParam.setApiId(apiId);
                pathParam.setName(paramName);
                pathParam.setDataType("string");
                pathParam.setValue(paramValue);
                pathParam.setDesc(description);
                pathParamService.createPathParam(pathParam);
            } catch (Exception e) {
                logger.warn("处理路径参数时出错: {}", e.getMessage());
            }
        }
    }

    /**
     * 处理请求体
     */
    private void processRequestBody(JSONObject request, String apiId) {
        if (!request.containsKey("body")) {
            updateApiRequest(MagicValue.REQUEST_BODY_TYPE_NONE, apiId);
            return;
        }

        JSONObject body = request.getJSONObject("body");
        if (body == null || body.isEmpty()) {
            updateApiRequest(MagicValue.REQUEST_BODY_TYPE_NONE, apiId);
            return;
        }

        String mode = getStringOrDefault(body, "mode", "none");
        String bodyType = mapBodyType(mode);

        updateApiRequest(bodyType, apiId);

        try {
            switch (bodyType) {
                case MagicValue.REQUEST_BODY_TYPE_FORMDATA -> processFormData(body, apiId);
                case MagicValue.REQUEST_BODY_TYPE_FORM_URLENCODED -> processFormUrlencoded(body, apiId);
                case MagicValue.REQUEST_BODY_TYPE_RAW -> processRawBody(body, apiId);
                default -> logger.debug("未处理的请求体类型: {}", bodyType);
            }
        } catch (Exception e) {
            logger.warn("处理请求体时出错: {}", e.getMessage());
        }
    }

    /**
     * 处理认证信息
     */
    private void processAuthentication(JSONObject request, String apiId) {
        JSONObject auth = null;
        
        // 优先使用请求级别的认证，如果没有则使用Collection级别的认证
        if (request.containsKey("auth")) {
            auth = request.getJSONObject("auth");
        } else if (this.collectionAuth != null) {
            auth = this.collectionAuth;
        }
        
        if (auth == null || auth.isEmpty()) {
            return;
        }

        String authType = getStringOrDefault(auth, "type", "");
        if (!StringUtils.hasText(authType)) {
            return;
        }

        try {
            switch (authType.toLowerCase()) {
                case AUTH_TYPE_BEARER -> processBearerAuth(auth, apiId);
                case AUTH_TYPE_APIKEY -> processApiKeyAuth(auth, apiId);
                default -> logger.debug("不支持的认证类型: {}, 仅支持bearer和apikey", authType);
            }
        } catch (Exception e) {
            logger.warn("处理认证信息时出错: {}", e.getMessage());
        }
    }

    /**
     * 处理Bearer Token认证
     */
    private void processBearerAuth(JSONObject auth, String apiId) {
        JSONArray bearerArray = auth.getJSONArray("bearer");
        if (bearerArray == null || bearerArray.isEmpty()) {
            return;
        }

        for (int i = 0; i < bearerArray.size(); i++) {
            JSONObject bearerObj = bearerArray.getJSONObject(i);
            if (bearerObj == null) {
                continue;
            }

            String key = getStringOrDefault(bearerObj, "key", "");
            String value = getStringOrDefault(bearerObj, "value", "");
            
            if ("token".equals(key) && StringUtils.hasText(value)) {
                try {
                    // 创建AuthParam主记录
                    AuthParam authParam = new AuthParam();
                    authParam.setApiId(apiId);
                    authParam.setType(MagicValue.AUTHENTICATION_TYPE_BEARER);

                    // 创建AuthBearer子记录
                    AuthBearer authBearer = new AuthBearer();
                    authBearer.setApiId(apiId);
                    authBearer.setName("Authorization");
                    authBearer.setValue("Bearer " + resolveVariable(value));
                    authParam.setBearer(authBearer);

                    // 保存认证信息
                    authParamService.createAuthParam(authParam);
                    logger.debug("添加Bearer Token认证");
                } catch (Exception e) {
                    logger.warn("创建Bearer Token认证失败: {}", e.getMessage());
                }
                break;
            }
        }
    }

    /**
     * 处理API Key认证
     */
    private void processApiKeyAuth(JSONObject auth, String apiId) {
        JSONArray apiKeyArray = auth.getJSONArray("apikey");
        if (apiKeyArray == null || apiKeyArray.isEmpty()) {
            return;
        }

        String key = "";
        String value = "";
        String in = "header"; // 默认在header中

        for (int i = 0; i < apiKeyArray.size(); i++) {
            JSONObject apiKeyObj = apiKeyArray.getJSONObject(i);
            if (apiKeyObj == null) {
                continue;
            }

            String fieldKey = getStringOrDefault(apiKeyObj, "key", "");
            String fieldValue = getStringOrDefault(apiKeyObj, "value", "");
            
            switch (fieldKey) {
                case "key" -> key = fieldValue;
                case "value" -> value = fieldValue;
                case "in" -> in = fieldValue;
                default -> logger.debug("未知的API Key字段: {}", fieldKey);
            }
        }

        if (StringUtils.hasText(key) && StringUtils.hasText(value)) {
            try {
                // 创建AuthParam主记录
                AuthParam authParam = new AuthParam();
                authParam.setApiId(apiId);
                authParam.setType(MagicValue.AUTHENTICATION_TYPE_APIKEY);

                // 创建AuthApiKey子记录
                AuthApiKey authApiKey = new AuthApiKey();
                authApiKey.setApiId(apiId);
                authApiKey.setName(key);
                authApiKey.setValue(resolveVariable(value));
                authApiKey.setApikeyIn(in);
                authParam.setApiKey(authApiKey);

                // 保存认证信息
                authParamService.createAuthParam(authParam);
                logger.debug("添加API Key认证: {} in {}", key, in);
            } catch (Exception e) {
                logger.warn("创建API Key认证失败: {}", e.getMessage());
            }
        }
    }

    /**
     * 解析变量引用，将{{variable}}替换为实际值
     */
    private String resolveVariable(String value) {
        if (!StringUtils.hasText(value)) {
            return value;
        }

        // 处理{{variable}}格式的变量引用
        if (value.contains("{{") && value.contains("}}")) {
            try {
                // 使用正则表达式提取变量名
                java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\{\\{([^}]+)\\}\\}");
                java.util.regex.Matcher matcher = pattern.matcher(value);
                
                StringBuffer result = new StringBuffer();
                while (matcher.find()) {
                    String variableName = matcher.group(1);
                    String variableValue = getVariableValue(variableName);
                    matcher.appendReplacement(result, java.util.regex.Matcher.quoteReplacement(variableValue));
                }
                matcher.appendTail(result);
                
                return result.toString();
            } catch (Exception e) {
                logger.warn("解析变量引用失败: {}, 使用原始值", e.getMessage());
                return value;
            }
        }

        return value;
    }

    /**
     * 获取变量值，直接从已解析的全局变量中获取
     */
    private String getVariableValue(String variableName) {
        if (!StringUtils.hasText(variableName)) {
            return "{{" + variableName + "}}";
        }

        // 直接从已解析的全局变量中查找
        if (this.globalVariables != null) {
            for (int i = 0; i < this.globalVariables.size(); i++) {
                try {
                    JSONObject variableObj = this.globalVariables.getJSONObject(i);
                    if (variableObj == null) {
                        continue;
                    }
                    
                    String name = getStringOrDefault(variableObj, "key", "");
                    String value = getStringOrDefault(variableObj, "value", "");
                    
                    if (variableName.equals(name) && StringUtils.hasText(value)) {
                        logger.debug("找到全局变量: {} = {}", variableName, value);
                        return value;
                    }
                } catch (Exception e) {
                    logger.debug("解析全局变量时出错: {}", e.getMessage());
                }
            }
        }

        // 如果找不到，返回原始变量引用
        return "{{" + variableName + "}}";
    }


    /**
     * 更新API请求信息
     */
    private void updateApiRequest(String bodyType, String apiId) {
        try {
            ApiRequest apiRequest = new ApiRequest();
            apiRequest.setId(apiId);
            apiRequest.setBodyType(bodyType);
            apiRequest.setApiId(apiId);

            apiRequestService.updateApiRequest(apiRequest);
        } catch (Exception e) {
            logger.warn("更新API请求信息失败: {}", e.getMessage());
        }
    }

    /**
     * 处理form-data类型请求体
     */
    private void processFormData(JSONObject body, String apiId) {
        JSONArray formDataArray = body.getJSONArray("formdata");
        if (formDataArray == null) {
            return;
        }

        for (int i = 0; i < formDataArray.size(); i++) {
            try {
                JSONObject formDataObj = formDataArray.getJSONObject(i);

                // 跳过禁用的参数
                if (getBooleanOrDefault(formDataObj, "disabled", false)) {
                    continue;
                }

                String paramName = getStringOrDefault(formDataObj, "key", "");
                if (!StringUtils.hasText(paramName)) {
                    continue;
                }

                FormParam formParam = new FormParam();
                formParam.setHttp(new HttpApi().setId(apiId));
                formParam.setParamName(paramName);
                formParam.setValue(getStringOrDefault(formDataObj, "value", ""));
                formParam.setDataType(getStringOrDefault(formDataObj, "type", "text"));
                formParam.setDesc(getStringOrDefault(formDataObj, "description", ""));
                formParam.setRequired(1); // 默认必需

                formParamService.createFormParam(formParam);
            } catch (Exception e) {
                logger.warn("处理form-data参数时出错: {}", e.getMessage());
            }
        }
    }

    /**
     * 处理x-www-form-urlencoded类型请求体
     */
    private void processFormUrlencoded(JSONObject body, String apiId) {
        JSONArray urlencodedArray = body.getJSONArray("urlencoded");
        if (urlencodedArray == null) {
            return;
        }

        for (int i = 0; i < urlencodedArray.size(); i++) {
            try {
                JSONObject urlencodedObj = urlencodedArray.getJSONObject(i);

                // 跳过禁用的参数
                if (getBooleanOrDefault(urlencodedObj, "disabled", false)) {
                    continue;
                }

                String paramName = getStringOrDefault(urlencodedObj, "key", "");
                if (!StringUtils.hasText(paramName)) {
                    continue;
                }

                FormUrlencoded formUrlencoded = new FormUrlencoded();
                formUrlencoded.setHttp(new HttpApi().setId(apiId));
                formUrlencoded.setParamName(paramName);
                formUrlencoded.setValue(getStringOrDefault(urlencodedObj, "value", ""));
                formUrlencoded.setDataType("string");
                formUrlencoded.setRequired(1); // 默认必需
                formUrlencoded.setDesc(getStringOrDefault(urlencodedObj, "description", ""));

                formUrlencodedService.createFormUrlencoded(formUrlencoded);
            } catch (Exception e) {
                logger.warn("处理form-urlencoded参数时出错: {}", e.getMessage());
            }
        }
    }

    /**
     * 处理raw类型请求体
     */
    private void processRawBody(JSONObject body, String apiId) {
        String rawData = getStringOrDefault(body, "raw", "");
        if (!StringUtils.hasText(rawData)) {
            return;
        }

        try {
            String contentType = determineRawContentType(body);

            RawParam rawParam = new RawParam();
            rawParam.setRaw(rawData);
            rawParam.setType(contentType);
            rawParam.setId(apiId);
            rawParam.setApiId(apiId);

            rawParamService.createRawParam(rawParam);
        } catch (Exception e) {
            logger.warn("处理raw请求体时出错: {}", e.getMessage());
        }
    }

    /**
     * 确定raw类型的Content-Type
     */
    private String determineRawContentType(JSONObject body) {
        if (!body.containsKey("options")) {
            return "text/plain";
        }

        JSONObject options = body.getJSONObject("options");
        if (options == null || !options.containsKey("raw")) {
            return "text/plain";
        }

        JSONObject rawOptions = options.getJSONObject("raw");
        if (rawOptions == null) {
            return "text/plain";
        }

        String language = getStringOrDefault(rawOptions, "language", "text");
        return mapRawLanguageToContentType(language);
    }

    /**
     * 映射请求体类型
     */
    private String mapBodyType(String postmanBodyType) {
        if (!StringUtils.hasText(postmanBodyType)) {
            return MagicValue.REQUEST_BODY_TYPE_NONE;
        }

        return switch (postmanBodyType.toLowerCase()) {
            case BODY_MODE_RAW -> MagicValue.REQUEST_BODY_TYPE_RAW;
            case BODY_MODE_FORMDATA -> MagicValue.REQUEST_BODY_TYPE_FORMDATA;
            case BODY_MODE_URLENCODED -> MagicValue.REQUEST_BODY_TYPE_FORM_URLENCODED;
            case BODY_MODE_FILE, BODY_MODE_NONE -> MagicValue.REQUEST_BODY_TYPE_NONE;
            default -> MagicValue.REQUEST_BODY_TYPE_NONE;
        };
    }

    /**
     * 映射raw语言类型到Content-Type
     */
    private String mapRawLanguageToContentType(String language) {
        if (!StringUtils.hasText(language)) {
            return "text/plain";
        }

        return switch (language.toLowerCase()) {
            case "json" -> "application/json";
            case "html" -> "text/html";
            case "xml" -> "text/xml";
            case "javascript" -> "application/javascript";
            case "text" -> "text/plain";
            default -> "text/plain";
        };
    }
}