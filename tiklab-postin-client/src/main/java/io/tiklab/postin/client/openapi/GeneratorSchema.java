package io.tiklab.postin.client.openapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.client.model.ApiParamMeta;
import io.tiklab.postin.client.model.ApiPropertyMeta;
import io.tiklab.postin.client.model.ApiResultMeta;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class GeneratorSchema {

    private static final int MAX_DEPTH = 5;
    // 用于避免循环引用
    private Set<String> processedTypes = new HashSet<>();

    /* ********************* ApiParamMeta 处理 ********************* */

    public JSONObject generateSchema(ApiParamMeta paramMeta) {
        return generateSchema(paramMeta, 0);
    }

    public JSONObject generateSchema(ApiParamMeta paramMeta, int depth) {
        JSONObject schema = new JSONObject();
        String textDef = paramMeta.getTextDef();
        if (textDef != null && !textDef.trim().isEmpty()) {
            JSONArray propsArray = JSON.parseArray(textDef);
            JSONObject properties = new JSONObject();
            for (int i = 0; i < propsArray.size(); i++) {
                JSONObject propDef = propsArray.getJSONObject(i);
                String propName = propDef.getString("name");
                String propDataType = propDef.getString("dataType");
                JSONObject propSchema = generateSchema(propDataType, depth + 1);
                propSchema.put("description", propDef.getString("desc"));
                propSchema.put("required", propDef.getBooleanValue("required"));
                properties.put(propName, propSchema);
            }
            schema.put("type", "object");
            schema.put("properties", properties);
            return schema;
        } else {
            String dataType = paramMeta.getDef() != null ? paramMeta.getDef().toString() : "";
            if (dataType.trim().isEmpty()) {
                schema.put("type", "object");
                return schema;
            }
            return generateSchema(dataType, depth);
        }
    }

    /* ********************* ApiResultMeta 处理 ********************* */

    public JSONObject generateSchema(ApiResultMeta resultMeta) {
        return generateSchema(resultMeta, 0);
    }

    public JSONObject generateSchema(ApiResultMeta resultMeta, int depth) {
        JSONObject schema = new JSONObject();
        // 优先使用 returnTypeText，再使用父类 dataType
        String returnType = resultMeta.getReturnTypeText();
        if (returnType == null || returnType.trim().isEmpty()) {
            returnType = resultMeta.getDataType();
        }
        // 如果返回类型是 io.tiklab.core.Result，则构造 code/message/data 的结构
        if (returnType.startsWith("io.tiklab.core.Result")) {
            schema.put("type", "object");
            JSONObject properties = new JSONObject();

            JSONObject codeSchema = new JSONObject();
            codeSchema.put("type", "integer");
            properties.put("code", codeSchema);

            JSONObject messageSchema = new JSONObject();
            messageSchema.put("type", "string");
            properties.put("message", messageSchema);

            JSONObject dataSchema = new JSONObject();
            // 利用 paramType 生成 data 内部的 schema
            Type paramType = resultMeta.getParamType();
            if (paramType != null) {
                dataSchema = parseType(paramType, depth + 1);
            } else {
                dataSchema.put("type", "object");
            }
            properties.put("data", dataSchema);

            schema.put("properties", properties);
            return schema;
        } else {
            // 非 Result 类型则直接根据 returnType 生成 schema
            return generateSchema(returnType, depth);
        }
    }

    /* ********************* 基于 dataType 字符串生成 Schema ********************* */

    public JSONObject generateSchema(String dataType, int depth) {
        JSONObject schema = new JSONObject();
        if (depth >= MAX_DEPTH) {
            schema.put("type", "object");
            schema.put("description", "Schema for " + dataType);
            return schema;
        }
        if (isPrimitive(dataType)) {
            handlePrimitive(dataType, schema);
            return schema;
        }
        if (isJavaLangType(dataType)) {
            handleJavaLangType(dataType, schema);
            return schema;
        }
        if (isCollectionType(dataType)) {
            return handleCollectionType(dataType, schema, depth);
        }
        if (isMapType(dataType)) {
            return handleMapType(dataType, schema, depth);
        }
        return handleClassType(dataType, schema, depth);
    }

    private boolean isPrimitive(String typeName) {
        return "int".equals(typeName) || "long".equals(typeName) ||
                "short".equals(typeName) || "byte".equals(typeName) ||
                "double".equals(typeName) || "float".equals(typeName) ||
                "boolean".equals(typeName) || "char".equals(typeName);
    }

    private void handlePrimitive(String typeName, JSONObject schema) {
        switch (typeName) {
            case "int":
            case "long":
            case "short":
            case "byte":
                schema.put("type", "integer");
                break;
            case "double":
            case "float":
                schema.put("type", "number");
                break;
            case "boolean":
                schema.put("type", "boolean");
                break;
            case "char":
                schema.put("type", "string");
                break;
            default:
                schema.put("type", "string");
        }
    }

    private boolean isJavaLangType(String typeName) {
        return typeName.startsWith("java.lang.") ||
                "String".equals(typeName) ||
                "Integer".equals(typeName) ||
                "Boolean".equals(typeName) ||
                "Long".equals(typeName) ||
                "Double".equals(typeName) ||
                "Float".equals(typeName) ||
                "Byte".equals(typeName) ||
                "Short".equals(typeName) ||
                "Character".equals(typeName);
    }

    private void handleJavaLangType(String typeName, JSONObject schema) {
        if (typeName.contains("Integer") || typeName.contains("Long") ||
                typeName.contains("Short") || typeName.contains("Byte")) {
            schema.put("type", "integer");
        } else if (typeName.contains("Double") || typeName.contains("Float")) {
            schema.put("type", "number");
        } else if (typeName.contains("Boolean")) {
            schema.put("type", "boolean");
        } else if (typeName.contains("Character") || typeName.contains("String")) {
            schema.put("type", "string");
        } else {
            schema.put("type", "string");
        }
    }

    private boolean isCollectionType(String typeName) {
        return typeName.startsWith("java.util.List") ||
                typeName.startsWith("java.util.Set") ||
                typeName.startsWith("java.util.Collection") ||
                typeName.startsWith("List<") ||
                typeName.startsWith("Set<") ||
                typeName.startsWith("Collection<");
    }

    private JSONObject handleCollectionType(String typeName, JSONObject schema, int depth) {
        schema.put("type", "array");
        int start = typeName.indexOf("<");
        int end = typeName.lastIndexOf(">");
        String genericType = null;
        if (start > 0 && end > start) {
            genericType = typeName.substring(start + 1, end).trim();
        }
        JSONObject itemsSchema = new JSONObject();
        if (genericType != null && !genericType.isEmpty()) {
            itemsSchema = generateSchema(genericType, depth + 1);
        } else {
            itemsSchema.put("type", "object");
        }
        // 保持原有的结构以兼容前端代码：properties.ITEMS
        JSONObject properties = new JSONObject();
        properties.put("ITEMS", itemsSchema);
        schema.put("properties", properties);
        return schema;
    }

    private boolean isMapType(String typeName) {
        return typeName.startsWith("java.util.Map") ||
                typeName.startsWith("java.util.HashMap") ||
                typeName.startsWith("Map<");
    }

    private JSONObject handleMapType(String typeName, JSONObject schema, int depth) {
        schema.put("type", "object");
        int comma = typeName.indexOf(",");
        int end = typeName.lastIndexOf(">");
        String valueType = null;
        if (comma > 0 && end > comma) {
            valueType = typeName.substring(comma + 1, end).trim();
        }
        if (valueType != null && !valueType.isEmpty()) {
            JSONObject additionalProps = generateSchema(valueType, depth + 1);
            schema.put("additionalProperties", additionalProps);
        }
        return schema;
    }

    /**
     * 针对普通类的处理，如果类型为 io.tiklab.core.Result 则构造一个包含 code、message、data 的 JSON Schema
     */
    private JSONObject handleClassType(String typeName, JSONObject schema, int depth) {
        if (processedTypes.contains(typeName)) {
            JSONObject refSchema = new JSONObject();
            refSchema.put("type", "object");
            refSchema.put("description", "Circular reference to " + typeName);
            return refSchema;
        }
        processedTypes.add(typeName);
        schema.put("type", "object");
        
        if (typeName.startsWith("io.tiklab.core.Result")) {
            // 构造 Result 的结构：code, message, data
            JSONObject properties = new JSONObject();

            JSONObject codeSchema = new JSONObject();
            codeSchema.put("type", "integer");
            properties.put("code", codeSchema);

            JSONObject messageSchema = new JSONObject();
            messageSchema.put("type", "string");
            properties.put("message", messageSchema);

            JSONObject dataSchema = new JSONObject();
            // 尝试提取泛型参数 data 部分，例如 io.tiklab.core.Result<java.util.List<...>>
            int start = typeName.indexOf("<");
            int end = typeName.lastIndexOf(">");
            if (start > 0 && end > start) {
                String inner = typeName.substring(start + 1, end).trim();
                dataSchema = generateSchema(inner, depth + 1);
            } else {
                dataSchema.put("type", "object");
            }
            properties.put("data", dataSchema);

            schema.put("properties", properties);
        } else if (depth < MAX_DEPTH - 1) {
            // 尝试通过反射获取类的详细字段信息
            try {
                Class<?> clazz = Class.forName(typeName);
                JSONObject properties = new JSONObject();
                
                // 获取所有声明的字段
                java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
                for (java.lang.reflect.Field field : fields) {
                    String fieldName = field.getName();
                    Class<?> fieldType = field.getType();
                    
                    // 跳过静态字段和合成字段
                    if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) || field.isSynthetic()) {
                        continue;
                    }
                    
                    JSONObject fieldSchema = new JSONObject();
                    String fieldTypeName = fieldType.getName();
                    
                    // 尝试获取ApiProperty注解的描述信息
                    try {
                        io.tiklab.postin.annotation.ApiProperty apiProperty = field.getAnnotation(io.tiklab.postin.annotation.ApiProperty.class);
                        if (apiProperty != null && apiProperty.desc() != null && !apiProperty.desc().trim().isEmpty()) {
                            fieldSchema.put("description", apiProperty.desc());
                        }
                    } catch (Exception e) {
                        // 忽略注解获取异常
                    }
                    
                    // 处理基本类型和包装类型
                    if (fieldType.isPrimitive()) {
                        handlePrimitive(fieldType.getName(), fieldSchema);
                    } else if (fieldTypeName.startsWith("java.lang.") || 
                               fieldTypeName.equals("String") || 
                               fieldTypeName.equals("Integer") || 
                               fieldTypeName.equals("Boolean") || 
                               fieldTypeName.equals("Long") || 
                               fieldTypeName.equals("Double") || 
                               fieldTypeName.equals("Float") || 
                               fieldTypeName.equals("Byte") || 
                               fieldTypeName.equals("Short") || 
                               fieldTypeName.equals("Character")) {
                        handleJavaLangType(fieldTypeName, fieldSchema);
                    } else if (fieldTypeName.startsWith("java.util.List") || 
                               fieldTypeName.startsWith("java.util.Set") || 
                               fieldTypeName.startsWith("java.util.Collection")) {
                        // 处理集合类型 - 保持与handleCollectionType方法一致的结构
                        fieldSchema.put("type", "array");
                        
                        // 尝试获取泛型类型信息
                        JSONObject itemsSchema = new JSONObject();
                        try {
                            java.lang.reflect.Type genericType = field.getGenericType();
                            if (genericType instanceof java.lang.reflect.ParameterizedType) {
                                java.lang.reflect.ParameterizedType paramType = (java.lang.reflect.ParameterizedType) genericType;
                                java.lang.reflect.Type[] actualTypes = paramType.getActualTypeArguments();
                                if (actualTypes.length > 0) {
                                    String genericTypeName = actualTypes[0].getTypeName();
                                    // 递归解析泛型类型
                                    itemsSchema = generateSchema(genericTypeName, depth + 1);
                                } else {
                                    itemsSchema.put("type", "object");
                                }
                            } else {
                                itemsSchema.put("type", "object");
                            }
                        } catch (Exception e) {
                            // 如果获取泛型信息失败，使用默认的object类型
                            itemsSchema.put("type", "object");
                        }
                        
                        // 使用properties.ITEMS结构以保持一致性
                        JSONObject fieldProperties = new JSONObject();
                        fieldProperties.put("ITEMS", itemsSchema);
                        fieldSchema.put("properties", fieldProperties);
                    } else if (fieldTypeName.startsWith("java.util.Map")) {
                        // 处理Map类型
                        fieldSchema.put("type", "object");
                        fieldSchema.put("additionalProperties", new JSONObject().put("type", "object"));
                    } else if (fieldTypeName.equals("java.sql.Timestamp") || 
                               fieldTypeName.equals("java.util.Date")) {
                        fieldSchema.put("type", "string");
                        fieldSchema.put("format", "date-time");
                    } else {
                        // 对于其他复杂类型，递归生成schema
                        fieldSchema = generateSchema(fieldTypeName, depth + 1);
                    }

                    properties.put(fieldName, fieldSchema);
                }
                
                if (!properties.isEmpty()) {
                    schema.put("properties", properties);
                } else {
                    schema.put("description", "Class: " + typeName);
                }
                
            } catch (ClassNotFoundException e) {
                // 如果类无法找到，可能是泛型类型，尝试解析泛型参数
                if (typeName.contains("<") && typeName.contains(">")) {
                    int start = typeName.indexOf("<");
                    int end = typeName.lastIndexOf(">");
                    if (start > 0 && end > start) {
                        String inner = typeName.substring(start + 1, end).trim();
                        JSONObject innerSchema = generateSchema(inner, depth + 1);
                        schema.put("description", "Generic type: " + typeName);
                        // 可以在这里添加更多处理逻辑
                    } else {
                        schema.put("description", "Unable to resolve class: " + typeName);
                    }
                } else {
                    schema.put("description", "Unable to resolve class: " + typeName);
                }
            } catch (Exception e) {
                schema.put("description", "Error processing class: " + typeName + " - " + e.getMessage());
            }
        } else {
            schema.put("description", "Maximum depth reached for: " + typeName);
        }
        
        processedTypes.remove(typeName);
        return schema;
    }

    /* ********************* ApiPropertyMeta 处理 ********************* */

    public JSONObject generateSchema(ApiPropertyMeta propertyMeta, int depth) {
        String dataType = propertyMeta.getDataType() != null 
                ? propertyMeta.getDataType() : "object";
        JSONObject schema = generateSchema(dataType, depth);
        
        // 添加描述信息
        if (propertyMeta.getDesc() != null && !propertyMeta.getDesc().trim().isEmpty()) {
            schema.put("description", propertyMeta.getDesc());
        }
        
        // 添加是否必填信息
        if (propertyMeta.isRequired()) {
            schema.put("required", true);
        }
        
        return schema;
    }

    /* ********************* 解析 Type 对象 ********************* */

    /**
     * 根据 java.lang.reflect.Type 对象生成 JSON Schema
     */
    private JSONObject parseType(Type type, int depth) {
        if (type == null) {
            JSONObject schema = new JSONObject();
            schema.put("type", "object");
            return schema;
        }
        String typeName = type.getTypeName();
        return generateSchema(typeName, depth);
    }
}
