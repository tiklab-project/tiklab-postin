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

//    /**
//     * jsonSchema 转换为json
//     * @param parameter
//     * @param allJson
//     * @return
//     */
//    public String processSchema(JSONObject parameter, JSONObject allJson) {
//        String processSchema = "";
//
//        if(parameter.containsKey("schema")){
//            JSONObject schema = parameter.getJSONObject("schema");
//
//            JSONObject definitions = allJson.getJSONObject("definitions");
//
//            String type = schema.getString("type");
//            if(type != null){
//                switch (type){
//                    case "array":
//                        JSONObject items = schema.getJSONObject("items");
//                        String itemsRef = items.getString("$ref");
//                        JSONObject itemsDefinitionObj = getModelByName(itemsRef,definitions);
//                        JSONObject itemsJson = convertDefinitionToJSON(itemsDefinitionObj, definitions);
//                        JSONArray jsonArray = new JSONArray();
//                        jsonArray.add(itemsJson);
//                        processSchema = jsonArray.toJSONString();
//                        break;
//                    default:
//                        processSchema = new JSONObject().toJSONString();
//                        break;
//                }
//            }else {
//                String ref = schema.getString("$ref");
//                JSONObject definitionObj = getModelByName(ref,definitions);
//                JSONObject jsonObject = convertDefinitionToJSON(definitionObj, definitions);
//                processSchema = jsonObject.toJSONString();
//            }
//        }else {
//            processSchema = new JSONObject().toJSONString();
//        }
//        return processSchema;
//    }
//
//    /**
//     * 递归方法，将定义对象转换为 JSON
//     */
//    private JSONObject convertDefinitionToJSON(JSONObject schema, JSONObject definitions) {
//        JSONObject jsonObject = new JSONObject();
//
//        if (schema.containsKey("properties")) {
//            JSONObject properties = schema.getJSONObject("properties");
//            for (String key : properties.keySet()) {
//                JSONObject property = properties.getJSONObject(key);
//
//                if (property.containsKey("type")) {
//                    String type = property.getString("type");
//
//                    if ("object".equals(type) && property.containsKey("$ref")) {
//                        String ref = property.getString("$ref");
//                        String refDefinitionName = ref.substring(ref.lastIndexOf('/') + 1);
//
//                        if (definitions.containsKey(refDefinitionName)) {
//                            JSONObject refDefinition = definitions.getJSONObject(refDefinitionName);
//                            jsonObject.put(key, convertDefinitionToJSON(refDefinition, definitions));
//                        } else {
//                            jsonObject.put(key, new JSONObject()); // 如果引用不存在，使用空对象
//                        }
//                    } else if ("array".equals(type) && property.containsKey("items")) {
//                        JSONObject items = property.getJSONObject("items");
//                        if (items.containsKey("$ref")) {
//                            String ref = items.getString("$ref");
//                            String refDefinitionName = ref.substring(ref.lastIndexOf('/') + 1);
//
//                            if (definitions.containsKey(refDefinitionName)) {
//                                JSONObject refDefinition = definitions.getJSONObject(refDefinitionName);
//                                jsonObject.put(key, new JSONArray().fluentAdd(convertDefinitionToJSON(refDefinition, definitions)));
//                            } else {
//                                jsonObject.put(key, new JSONArray()); // 如果引用不存在，使用空数组
//                            }
//                        } else {
//                            jsonObject.put(key, new JSONArray());
//                        }
//                    } else {
//                        jsonObject.put(key, getDefaultValueForType(type));
//                    }
//                }
//            }
//        }
//
//        return jsonObject;
//    }
//
//    // 获取默认值的方法，根据类型返回默认值
//    private Object getDefaultValueForType(String type) {
//        switch (type) {
//            case "integer":
//                return 0;
//            case "number":
//                return 0.0;
//            case "string":
//                return "";
//            case "boolean":
//                return false;
//            case "array":
//                return new JSONArray();
//            case "object":
//                return new JSONObject();
//            default:
//                return null;
//        }
//    }
//
//    /**
//     * 通过$ref 获取对应的模型
//     * @param ref
//     * @param definitions
//     * @return
//     */
//    private JSONObject getModelByName(String ref, JSONObject definitions) {
//        String definitionName = ref.substring(ref.lastIndexOf('/') + 1);
//        JSONObject definitionObj = definitions.getJSONObject(definitionName);
//        return definitionObj;
//    }
}
