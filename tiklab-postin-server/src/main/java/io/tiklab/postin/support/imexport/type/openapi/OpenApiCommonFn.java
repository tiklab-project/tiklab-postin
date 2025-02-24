package io.tiklab.postin.support.imexport.type.openapi;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;


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


}
