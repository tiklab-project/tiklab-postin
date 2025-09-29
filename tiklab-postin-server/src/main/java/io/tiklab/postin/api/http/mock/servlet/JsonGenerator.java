package io.tiklab.postin.api.http.mock.servlet;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.javafaker.Faker;

@Service
public class JsonGenerator {

    private Faker faker = new Faker();

    /**
     * 生成raw 文本
     * @param rawText
     * @return
     */
    public String generateRaw(String rawText) {
        if(rawText!=null){
            return rawText;
        }else {
            return faker.lorem().paragraph();
        }
    }


    /**
     * jsonschema 生成 json数据
     * @param schemaStr
     * @return
     */
    public  String generateJson(String schemaStr) {
        JSONObject schemaObject = JSONObject.parseObject(schemaStr);

        Object generatedData = generateData(schemaObject);

        if (generatedData instanceof JSONObject jsonObject) {
            return jsonObject.toJSONString();
        } else if (generatedData instanceof JSONArray jsonArray) {
            return jsonArray.toJSONString();
        } else {
            // 对于基本类型，直接转换为JSON字符串
            return JSONObject.toJSONString(generatedData);
        }
    }

    private  <T> T generateData(JSONObject schema) {
        if (schema == null) {
            return null;
        }
        
        String type = schema.getString("type");
        if (type == null) {
            type = "string"; // 默认类型
        }

        if ("object".equals(type)) {
            JSONObject jsonObject = new JSONObject();
            JSONObject properties = schema.getJSONObject("properties");
            if (properties != null) {
                for (String propKey : properties.keySet()) {
                    if (propKey != null) {
                        JSONObject propSchema = properties.getJSONObject(propKey);
                        jsonObject.put(propKey, generateData(propSchema));
                    }
                }
            }
            return (T) jsonObject;
        } else if ("array".equals(type)) {
            JSONObject properties = schema.getJSONObject("properties");
            JSONObject itemsSchema = null;
            if (properties != null) {
                itemsSchema = properties.getJSONObject("ITEMS");
            }
            if (itemsSchema == null) {
                // 如果没有找到ITEMS，创建一个默认的字符串类型schema
                itemsSchema = new JSONObject();
                itemsSchema.put("type", "string");
            }
            
            JSONArray array = new JSONArray();
            int length = faker.random().nextInt(5) + 1;
            for (int i = 0; i < length; i++) {
                array.add(generateData(itemsSchema));
            }
            return (T) array;
        } else {
            JSONObject mockObj = schema.getJSONObject("mock");
            if (mockObj != null) {
                String mockType = mockObj.getString("mock");
                return (T) generateMockValue(mockType);
            } else {
                return (T) generateType(type);
            }
        }
    }

    /**
     * 数据类型自动生成数据
     */
    private <T> Object generateType(String type) {
        if (type == null) {
            return faker.lorem().word();
        }
        
        switch(type) {
            case "integer":
                return faker.number().numberBetween(1, 100);
            case "number":
                return faker.number().randomNumber();
            case "string":
                return faker.lorem().word();
            case "boolean":
                return faker.bool().bool();
            default:
                return faker.name();
        }

    }
    /**
     * mock 数据
     * @param mockType
     * @return
     */
    private Object generateMockValue(String mockType) {
        if (mockType == null) {
            return faker.lorem().word();
        }
        
        switch(mockType) {
            case "@ip":
                return faker.internet().ipV4Address();
            case "@name":
                return faker.name().fullName();
            case "@integer":
                return faker.number().randomNumber();
            case "@first":
                return faker.name().firstName();
            case "@last":
                return faker.name().lastName();
            case "@city":
                return faker.address().city();
            case "@country":
                return faker.address().country();
            case "@email":
                return faker.internet().emailAddress();
            case "@domain":
                return faker.internet().domainName();
            case "@date":
                return faker.date().birthday();
            case "@company":
                return faker.company().name();
            case "@title":
                return faker.name().title();
            case "@phone":
                return faker.phoneNumber().cellPhone();
            case "@address":
                return faker.address().fullAddress();
            case "@sentence":
                return faker.lorem().sentence();
            case "@paragraph":
                return faker.lorem().paragraph();
            case "@id":
                return faker.idNumber().valid();
            case "@url":
                return faker.internet().url();
            case "@word":
                return faker.lorem().word();
            case "@words":
                return faker.lorem().words();
            case "@image":
                return faker.internet().image();
            case "@timezone":
                return faker.address().timeZone();
            case "@gender":
                return faker.demographic().sex();
            case "@uuid":
                return faker.internet().uuid();
            default:
                return mockType;
        }
    }


}