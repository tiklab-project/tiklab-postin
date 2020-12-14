package com.darthcloud.apibox.client.parser;

import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.client.model.ApiPropertyMeta;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * MODEL注解解析 TODO 缓存
 */
public class ApiModelParser {

    /**
     * 解析property注解定义
     * @param modelType
     * @return
     */
    public static List<ApiPropertyMeta> parsePropertyMetas(Type modelType){
        List<ApiPropertyMeta> apiPropertyMetaList = new ArrayList<>();
        Field[] fields = null;
        Type paramType = null;
        if (modelType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) modelType;
            Type rawType = parameterizedType.getRawType();
            //modelType = rawType;
            for (Type type : parameterizedType.getActualTypeArguments()) {
                paramType = type;
            }
            fields = ((Class)rawType).getDeclaredFields();
        }else if(modelType instanceof Class){
            fields = ((Class)modelType).getDeclaredFields();
        }


        if(fields == null || fields.length == 0){
            return apiPropertyMetaList;
        }
        for (Field field:fields){
            Type fieldType;
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                fieldType = paramType;
            }else if(genericType instanceof TypeVariable){
                fieldType = paramType;
            }else{
                fieldType = field.getType();
            }

            ApiProperty apiProperty = field.getAnnotation(ApiProperty.class);
            if(apiProperty != null){
                apiPropertyMetaList.add(new ApiPropertyMeta(fieldType, apiProperty));
            }
        }
        return apiPropertyMetaList;
    }

}
