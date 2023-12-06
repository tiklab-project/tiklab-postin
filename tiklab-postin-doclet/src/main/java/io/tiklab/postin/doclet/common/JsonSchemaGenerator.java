package io.tiklab.postin.doclet.common;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.doclet.starter.DocletApplication;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonSchemaGenerator {

    public static JSONObject generateJsonSchema(String modelFullName) {
        try {
            //通过反射获取模型类
            Class<?> paramClass = Class.forName(modelFullName, true, DocletApplication.urlClassLoader);

            // 创建 JSON Schema 根节点
            JSONObject schemaMap = new JSONObject();
            schemaMap.put("title", paramClass.getSimpleName());
            schemaMap.put("type", "object");
            schemaMap.put("properties", getPropertiesMap(paramClass));

            return schemaMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map<String, Object> getPropertiesMap(Class<?> clazz) {
        Map<String, Object> properties = new HashMap<>();

        // 处理类中的字段
        for (Field field : clazz.getDeclaredFields()) {
            String fieldName = field.getName();
            Map<String, Object> fieldProperties = new HashMap<>();

            // 添加字段类型
            if (isPrimitiveType(field.getType())) {
                fieldProperties.put("type", getFieldType(field.getType()));
            } else if (isListType(field.getType())) {
                // 处理列表类型
                Type listType = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                Class<?> listGenericType = (Class<?>) listType;
                fieldProperties.put("type", "array");
                fieldProperties.put("items", getPropertiesMap(listGenericType));
            } else {
                // 处理嵌套对象
                fieldProperties.put("$ref", "#/definitions/" + field.getType().getSimpleName());
            }

            // 添加字段描述
//            String description = getCommentDescription(field);
//            if (description != null) {
//                fieldProperties.put("description", description);
//            }

            // 从注释中提取其他注解信息
            addAnnotationsFromComment(field, fieldProperties);

            properties.put(fieldName, fieldProperties);
        }

        // 处理嵌套对象，将定义添加到根节点的 definitions 中
        for (Field field : clazz.getDeclaredFields()) {
            if (!isPrimitiveType(field.getType()) && !isListType(field.getType())) {
                Map<String, Object> nestedSchema = getPropertiesMap(field.getType());
                properties.put("#/definitions/" + field.getType().getSimpleName(), nestedSchema);
            }
        }

        return properties;
    }

    private static boolean isPrimitiveType(Class<?> type) {
        return type.isPrimitive() || type == String.class || type == Integer.class ||
                type == Double.class || type == Float.class || type == Long.class ||
                type == Boolean.class || type.isEnum();
    }

    private static boolean isListType(Class<?> type) {
        return type == List.class;
    }

    private static String getFieldType(Class<?> type) {
        if (type == int.class || type == Integer.class || type == long.class || type == Long.class) {
            return "integer";
        } else if (type == double.class || type == Double.class || type == float.class || type == Float.class) {
            return "number";
        } else if (type == boolean.class || type == Boolean.class) {
            return "boolean";
        } else if (type == String.class) {
            return "string";
        } else {
            // 其他类型均视为对象
            return "object";
        }
    }


    // 从注释中提取其他注解信息
    private static void addAnnotationsFromComment(Field field, Map<String, Object> properties) {
        // TODO: 根据需要从注释中提取其他注解信息并添加到 properties 中
    }
}
