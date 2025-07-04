package io.tiklab.postin.support.imexport.type.openapi;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 公共方法
 */
@Component
public class OpenApiCommonFn {

    public String DEFAULT_CATEGORY_NAME = "OpenApi";


    public boolean getRequired(JSONObject field, boolean defaultValue) {
        return field.containsKey("required") ? field.getBoolean("required") : defaultValue;
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
        // 定义一个安全的默认值
        final int DEFAULT_CODE = 200;

        if (statusCodeStr == null || statusCodeStr.trim().isEmpty()) {
            return DEFAULT_CODE;
        }

        // 1. 处理 "default"
        if ("default".equalsIgnoreCase(statusCodeStr)) {
            return DEFAULT_CODE;
        }

        // 2. 尝试直接转换为整数 (处理 "200", "404" 等情况)
        try {
            return Integer.parseInt(statusCodeStr);
        } catch (NumberFormatException e) {
            // 如果直接转换失败，说明是复合格式，继续下一步处理
        }

        // 3. 使用正则表达式处理 "x-200:修改成功" 或 "2xx" 等复杂情况
        //    这个正则表达式会从字符串中提取第一个出现的数字序列
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

        // 4. 如果所有尝试都失败了，返回默认值
        return DEFAULT_CODE;
    }



}
