package io.thoughtware.postin.support.imexport.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemaParser {

    public static String parseSchema(JSONObject schema, JSONObject allJson) {
        JSONObject definitions = allJson.getJSONObject("definitions");
        return convertSchemaToJson(schema, definitions);
    }

    private static String convertSchemaToJson(JSONObject schema, JSONObject definitions) {
        if (schema == null) return "null";

        if (schema.containsKey("$ref")) {
            return handleReference(schema.getString("$ref"), definitions);
        }

        String type = schema.getString("type");
        if (type == null) {
            // 处理没有明确类型的情况，可能是一个复合schema
            return handleComplexSchema(schema, definitions);
        }

        switch (type) {
            case "object":
                return handleObjectSchema(schema, definitions);
            case "array":
                return handleArraySchema(schema, definitions);
            default:
                return handlePrimitiveSchema(schema);
        }
    }

    private static String handleReference(String ref, JSONObject definitions) {
        String definitionName = ref.substring(ref.lastIndexOf('/') + 1);
        if (definitions.containsKey(definitionName)) {
            JSONObject definition = definitions.getJSONObject(definitionName);
            return convertSchemaToJson(definition, definitions);
        }
        return "null";
    }

    private static String handleComplexSchema(JSONObject schema, JSONObject definitions) {
        // 处理allOf, anyOf, oneOf 等复合schema
        if (schema.containsKey("allOf")) {
            JSONObject mergedSchema = new JSONObject();
            JSONArray allOf = schema.getJSONArray("allOf");
            for (int i = 0; i < allOf.size(); i++) {
                JSONObject subSchema = allOf.getJSONObject(i);
                mergedSchema.putAll(JSON.parseObject(convertSchemaToJson(subSchema, definitions)));
            }
            return mergedSchema.toJSONString();
        }
        // 可以添加对anyOf, oneOf的处理
        return "{}";
    }

    private static String handleObjectSchema(JSONObject schema, JSONObject definitions) {
        JSONObject result = new JSONObject();
        if (schema.containsKey("properties")) {
            JSONObject properties = schema.getJSONObject("properties");
            for (String key : properties.keySet()) {
                JSONObject property = properties.getJSONObject(key);
                result.put(key, JSON.parse(convertSchemaToJson(property, definitions)));
            }
        }
        if (schema.containsKey("additionalProperties")) {
            Object additionalProperties = schema.get("additionalProperties");
            if (additionalProperties instanceof JSONObject) {
                result.put("additionalPropertyExample", JSON.parse(convertSchemaToJson((JSONObject) additionalProperties, definitions)));
            } else if (additionalProperties instanceof Boolean) {
                // 处理 additionalProperties 为 boolean 的情况
                result.put("additionalPropertyExample", additionalProperties);
            }
        }
        return result.toJSONString();
    }

    private static String handleArraySchema(JSONObject schema, JSONObject definitions) {
        JSONArray result = new JSONArray();
        if (schema.containsKey("items")) {
            JSONObject items = schema.getJSONObject("items");
            result.add(JSON.parse(convertSchemaToJson(items, definitions)));
        }
        return result.toJSONString();
    }

    private static String handlePrimitiveSchema(JSONObject schema) {
        String type = schema.getString("type");
        String format = schema.getString("format");
        List<String> enumValues = schema.containsKey("enum") ? schema.getJSONArray("enum").toJavaList(String.class) : null;

        if (enumValues != null && !enumValues.isEmpty()) {
            return JSON.toJSONString(enumValues.get(0));
        }

        switch (type) {
            case "string":
                if ("date-time".equals(format)) {
                    return JSON.toJSONString("1971-01-01T12:00:00Z");
                } else if ("date".equals(format)) {
                    return JSON.toJSONString("1971-01-01");
                } else {
                    return JSON.toJSONString(schema.containsKey("example") ? schema.getString("example") : "example_string");
                }
            case "integer":
                return schema.containsKey("example") ? schema.getString("example") : "0";
            case "number":
                return schema.containsKey("example") ? schema.getString("example") : "0.0";
            case "boolean":
                return schema.containsKey("example") ? schema.getString("example") : "false";
            default:
                return "null";
        }
    }
}