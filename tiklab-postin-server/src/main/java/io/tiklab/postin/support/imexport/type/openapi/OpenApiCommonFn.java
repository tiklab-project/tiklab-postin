package io.tiklab.postin.support.imexport.type.openapi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 公共方法
 */
@Component
public class OpenApiCommonFn {

    // 常量定义
    public static final String DEFAULT_CATEGORY_NAME = "OpenApi";
    public static final String OPENAPI_VERSION_3_0 = "3.0";
    public static final String OPENAPI_VERSION_3_1 = "3.1";

    // 安全认证类型常量
    public static final String SECURITY_TYPE_APIKEY = "apiKey";
    public static final String SECURITY_TYPE_HTTP = "http";
    public static final String SECURITY_SCHEME_BEARER = "bearer";

    // 默认值
    public static final int DEFAULT_HTTP_STATUS_CODE = 200;


    public boolean getRequired(JSONObject field, boolean defaultValue) {
        if (!field.containsKey("required")) {
            return defaultValue;
        }
        Boolean required = field.getBoolean("required");
        return required != null ? required : defaultValue;
    }

    public String getDescription(JSONObject field, String defaultValue) {
        return field.containsKey("description") ? field.getString("description") : defaultValue;
    }



    /**
     * 获取默认值
     */
    public Object getDefaultValue(String type, JSONObject schema) {
        // 优先使用schema中定义的默认值
        if (schema.containsKey("default")) {
            return schema.get("default");
        }

        // 否则使用类型默认值
        switch (type) {
            case "integer":
                return 0;
            case "number":
                return 0.0;
            case "string":
                return "";
            case "boolean":
                return false;
            case "null":
                return null;
            default:
                return null;
        }
    }


    public JSONObject resolveRef(String ref, JSONObject allJson) {
        if (!ref.startsWith("#/")) {
            throw new IllegalArgumentException("Invalid reference: " + ref);
        }

        // 解析路径，例如 #/components/schemas/User
        String[] paths = ref.substring(2).split("/");
        JSONObject current = allJson;
        for (String path : paths) {
            if (!current.containsKey(path)) {
                throw new IllegalArgumentException("Reference path not found: " + ref);
            }
            current = current.getJSONObject(path);
        }
        return current;
    }


    /**
     * 解析各种格式的 statusCode 字符串，转换为整数类型的 HTTP 状态码。
     *
     * @param statusCodeStr 原始的状态码字符串，可能为 "200", "default", "x-200:修改成功", "4XX" 等。
     * @return 解析后的整数 HTTP 状态码。如果无法解析，返回一个安全的默认值（如 200）。
     */
    public int parseHttpStatusCode(String statusCodeStr) {
        if (statusCodeStr == null || statusCodeStr.trim().isEmpty()) {
            return DEFAULT_HTTP_STATUS_CODE;
        }

        // 处理 "default"
        if ("default".equalsIgnoreCase(statusCodeStr)) {
            return DEFAULT_HTTP_STATUS_CODE;
        }

        // 尝试直接转换为整数 (处理 "200", "404" 等情况)
        try {
            return Integer.parseInt(statusCodeStr);
        } catch (NumberFormatException e) {

        }

        //  使用正则表达式处理 "x-200:修改成功" 或 "2xx" 等复杂情况
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(statusCodeStr);

        if (matcher.find()) {
            // 提取找到的数字字符串并转换为整数
            String numericPart = matcher.group(0);
            // 如果是 "2XX" 这种，可以替换 "X" 为 "0"
            if (numericPart.length() == 1 && statusCodeStr.toLowerCase().contains("xx")) {
                numericPart = numericPart + "00";
            }
            return Integer.parseInt(numericPart);
        }

        // 如果所有尝试都失败了，返回默认值
        return DEFAULT_HTTP_STATUS_CODE;
    }

    /**
     * 处理OpenAPI的security配置
     * @param methodInfo 方法信息
     * @param allJson 完整的OpenAPI文档
     * @return 安全配置信息
     */
    public JSONObject processSecurity(JSONObject methodInfo, JSONObject allJson) {
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
     * @param allJson 完整的OpenAPI文档
     * @return 安全方案配置
     */
    public JSONObject getSecuritySchemes(JSONObject allJson) {
        if (allJson.containsKey("components") && 
            allJson.getJSONObject("components").containsKey("securitySchemes")) {
            return allJson.getJSONObject("components").getJSONObject("securitySchemes");
        }
        return new JSONObject();
    }

    /**
     * 验证OpenAPI文档的基本结构
     * @param allJson 完整的OpenAPI文档
     * @return 验证结果
     */
    public boolean validateOpenApiDocument(JSONObject allJson) {
        if (allJson == null || allJson.isEmpty()) {
            return false;
        }

        // 检查必需的字段
        if (!allJson.containsKey("openapi") || !allJson.containsKey("info") || !allJson.containsKey("paths")) {
            return false;
        }

        // 检查版本号
        String openApiVersion = allJson.getString("openapi");
        if (openApiVersion == null || openApiVersion.trim().isEmpty()) {
            return false;
        }

        // 检查info字段
        JSONObject info = allJson.getJSONObject("info");
        if (info == null || info.isEmpty()) {
            return false;
        }

        // 检查paths字段
        JSONObject paths = allJson.getJSONObject("paths");
        if (paths == null || paths.isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * 安全获取字符串值
     * @param jsonObject JSON对象
     * @param key 键名
     * @param defaultValue 默认值
     * @return 字符串值
     */
    public String getStringSafely(JSONObject jsonObject, String key, String defaultValue) {
        if (jsonObject == null || !jsonObject.containsKey(key)) {
            return defaultValue;
        }
        String value = jsonObject.getString(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 安全获取JSONArray
     * @param jsonObject JSON对象
     * @param key 键名
     * @return JSONArray，如果不存在或为null则返回空数组
     */
    public JSONArray getJSONArraySafely(JSONObject jsonObject, String key) {
        if (jsonObject == null || !jsonObject.containsKey(key)) {
            return new JSONArray();
        }
        JSONArray array = jsonObject.getJSONArray(key);
        return array != null ? array : new JSONArray();
    }

    /**
     * 安全获取JSONObject
     * @param jsonObject JSON对象
     * @param key 键名
     * @return JSONObject，如果不存在或为null则返回空对象
     */
    public JSONObject getJSONObjectSafely(JSONObject jsonObject, String key) {
        if (jsonObject == null || !jsonObject.containsKey(key)) {
            return new JSONObject();
        }
        JSONObject obj = jsonObject.getJSONObject(key);
        return obj != null ? obj : new JSONObject();
    }

    /**
     * 处理JSON响应Schema
     * @param mediaTypeObj 媒体类型对象
     * @param allJson 完整的OpenAPI文档
     * @param isOpenApi31 是否为OpenAPI 3.1.x版本
     * @return JSON Schema字符串
     */
    public String processJsonSchema(JSONObject mediaTypeObj, JSONObject allJson, boolean isOpenApi31) {
        try {
            // 获取schema
            JSONObject schema = mediaTypeObj.getJSONObject("schema");
            if (schema == null) {
                // 如果没有schema，创建一个空的object schema
                schema = new JSONObject();
                schema.put("type", "object");
                schema.put("properties", new JSONObject());
            }

            // 处理$ref引用
            if (schema.containsKey("$ref")) {
                schema = resolveRef(schema.getString("$ref"), allJson);
            }

            // 直接处理schema，不强制转换为object类型
            return processSchemaRecursively(schema, allJson, isOpenApi31).toJSONString();
            
        } catch (Exception e) {
            return new JSONObject().toJSONString();
        }
    }

    /**
     * 递归处理Schema
     * @param schema 当前schema
     * @param allJson 完整的OpenAPI文档
     * @param isOpenApi31 是否为OpenAPI 3.1.x版本
     * @return 处理后的schema
     */
    private JSONObject processSchemaRecursively(JSONObject schema, JSONObject allJson, boolean isOpenApi31) {
        JSONObject result = new JSONObject();
        
        // 处理$ref引用
        if (schema.containsKey("$ref")) {
            return resolveRef(schema.getString("$ref"), allJson);
        }
        
          // 设置类型
          if (schema.containsKey("type")) {
              String type = schema.getString("type");
              result.put("type", type);
              
              // 处理数组类型：将items转换为properties.ITEMS格式
              if ("array".equals(type) && schema.containsKey("items")) {
                  JSONObject items = schema.getJSONObject("items");
                  JSONObject properties = new JSONObject();
                  properties.put("ITEMS", processSchemaRecursively(items, allJson, isOpenApi31));
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
                  properties.put(key, processSchemaRecursively(property, allJson, isOpenApi31));
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



}
