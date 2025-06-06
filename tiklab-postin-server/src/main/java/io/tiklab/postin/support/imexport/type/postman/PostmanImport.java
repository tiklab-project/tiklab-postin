package io.tiklab.postin.support.imexport.type.postman;

import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

import io.tiklab.postin.api.http.definition.model.PathParam;
import io.tiklab.postin.api.http.definition.service.PathParamService;
import io.tiklab.postin.common.MagicValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.api.apix.model.ApiRequest;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.model.QueryParam;
import io.tiklab.postin.api.apix.model.RawParam;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.apix.service.ApiRequestService;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.apix.service.QueryParamService;
import io.tiklab.postin.api.apix.service.RawParamService;
import io.tiklab.postin.api.apix.service.RequestHeaderService;
import io.tiklab.postin.api.http.definition.model.FormParam;
import io.tiklab.postin.api.http.definition.model.FormUrlencoded;
import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.postin.api.http.definition.service.FormParamService;
import io.tiklab.postin.api.http.definition.service.FormUrlencodedService;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.common.ErrorCode;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.support.imexport.common.FunctionImport;
import io.tiklab.postin.workspace.model.Workspace;

/**
 * Postman Collection导入器
 * 支持导入Postman collection v2.1格式的数据
 */
@Component
public class PostmanImport {

    private static final Logger logger = LoggerFactory.getLogger(PostmanImport.class);

    @Autowired
    private FunctionImport functionImport;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ApixService apixService;
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

    private String workspaceId;
    private String rootCategoryId;

    /**
     * 解析并导入Postman collection数据
     *
     * @param workspaceId 工作空间ID
     * @param stream Postman collection文件流
     */
    public void analysisPostmanData(String workspaceId, InputStream stream) {
        if (StringUtils.isEmpty(workspaceId)) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,"工作空间ID不能为空");
        }

        this.workspaceId = workspaceId;
        logger.info("开始导入Postman数据到工作空间: {}", workspaceId);

        try {
            JSONObject jsonObject = functionImport.getJsonData(stream);
            if (jsonObject == null) {
                throw new ApplicationException(ErrorCode.IMPORT_ERROR, "无法解析Postman数据");
            }

            // 验证数据格式
            validatePostmanData(jsonObject);

            // 解析collection信息
            JSONObject info = jsonObject.getJSONObject("info");
            String categoryName = getStringOrDefault(info, "name", "导入的Collection");

            // 创建根分组
            this.rootCategoryId = createRootCategory(categoryName);

            // 解析并导入数据
            analysisData(jsonObject, this.rootCategoryId);

        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,
                    "导入Postman数据失败: " + e.getMessage(), e);
        }
    }

    /**
     * 提取描述信息，支持多种格式
     */
    private String extractDescription(JSONObject info) {
        // 尝试从description字段获取
        Object desc = info.get("description");
        if (desc instanceof String) {
            return (String) desc;
        } else if (desc instanceof JSONObject) {
            // 有些Postman导出可能是对象格式
            JSONObject descObj = (JSONObject) desc;
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
     * 验证Postman数据格式
     */
    private void validatePostmanData(JSONObject jsonObject) {
        if (jsonObject == null) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,"Postman数据为空");
        }

        if (!jsonObject.containsKey("info")) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,"无效的Postman collection格式：缺少info字段");
        }

        JSONObject info = jsonObject.getJSONObject("info");
        if (info == null) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,"info字段不能为空");
        }

        String name = info.getString("name");
        if (!StringUtils.hasText(name)) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,"Postman collection名称不能为空");
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
            return "/";
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

        return path.length() == 0 ? "/" : path.toString();
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
        String name = getStringOrDefault(item, "name", "未命名API");
        JSONObject request = item.getJSONObject("request");

        if (request == null) {
            logger.warn("API请求 {} 缺少request信息，跳过", name);
            return;
        }

        String method = getStringOrDefault(request, "method", "GET").toUpperCase();
        String description = extractDescription(item);

        // 解析URL
        UrlInfo urlInfo = parseUrl(request);

        // 创建API
        String apiId = createHttpApi(categoryId, name, urlInfo.path, method, description);

        // 处理请求头
        processRequestHeaders(request, apiId);

        // 处理查询参数
        processQueryParameters(urlInfo, apiId);

        // 处理路径参数
        processPathParameters(urlInfo, apiId);

        // 处理请求体
        processRequestBody(request, apiId);

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

        if (urlObj instanceof String) {
            // 简单字符串URL
            String url = (String) urlObj;
            urlInfo.path = extractPathFromUrl(url);
        } else if (urlObj instanceof JSONObject) {
            // 对象格式URL
            JSONObject urlObject = (JSONObject) urlObj;

            // 解析路径
            JSONArray pathArray = urlObject.getJSONArray("path");
            urlInfo.path = buildPath(pathArray);

            // 解析查询参数
            JSONArray queryArray = urlObject.getJSONArray("query");
            if (queryArray != null) {
                for (int i = 0; i < queryArray.size(); i++) {
                    urlInfo.queryParams.add(queryArray.getJSONObject(i));
                }
            }

            // 解析路径参数
            JSONArray variableArray = urlObject.getJSONArray("variable");
            if (variableArray != null) {
                for (int i = 0; i < variableArray.size(); i++) {
                    urlInfo.pathParams.add(variableArray.getJSONObject(i));
                }
            }
        } else {
            urlInfo.path = "/";
        }

        return urlInfo;
    }

    /**
     * 从URL字符串中提取路径
     */
    private String extractPathFromUrl(String url) {
        if (!StringUtils.hasText(url)) {
            return "/";
        }

        // 移除变量占位符 {{variable}}
        url = url.replaceAll("\\{\\{[^}]+\\}\\}", "");

        // 简单解析，提取路径部分
        try {
            if (url.startsWith("http")) {
                java.net.URL parsedUrl = new java.net.URL(url);
                return StringUtils.hasText(parsedUrl.getPath()) ? parsedUrl.getPath() : "/";
            } else {
                // 相对路径
                int queryIndex = url.indexOf('?');
                if (queryIndex > 0) {
                    url = url.substring(0, queryIndex);
                }
                return url.startsWith("/") ? url : "/" + url;
            }
        } catch (Exception e) {
            logger.warn("解析URL失败: {}, 使用默认路径", url);
            return "/";
        }
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
        if (body == null) {
            updateApiRequest(MagicValue.REQUEST_BODY_TYPE_NONE, apiId);
            return;
        }

        String mode = getStringOrDefault(body, "mode", "none");
        String bodyType = mapBodyType(mode);

        updateApiRequest(bodyType, apiId);

        try {
            switch (bodyType) {
                case MagicValue.REQUEST_BODY_TYPE_FORMDATA:
                    processFormData(body, apiId);
                    break;
                case MagicValue.REQUEST_BODY_TYPE_FORM_URLENCODED:
                    processFormUrlencoded(body, apiId);
                    break;
                case MagicValue.REQUEST_BODY_TYPE_RAW:
                    processRawBody(body, apiId);
                    break;
            }
        } catch (Exception e) {
            logger.warn("处理请求体时出错: {}", e.getMessage());
        }
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
            case "raw" -> MagicValue.REQUEST_BODY_TYPE_RAW;
            case "form-data" -> MagicValue.REQUEST_BODY_TYPE_FORMDATA;
            case "urlencoded" -> MagicValue.REQUEST_BODY_TYPE_FORM_URLENCODED;
            case "file" -> MagicValue.REQUEST_BODY_TYPE_NONE;
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