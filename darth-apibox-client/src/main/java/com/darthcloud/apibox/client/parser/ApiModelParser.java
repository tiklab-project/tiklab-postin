package com.darthcloud.apibox.client.parser;

import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.client.builder.ApiboxBuilder;
import com.darthcloud.apibox.client.model.ApiPropertyMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static Logger logger = LoggerFactory.getLogger(ApiModelParser.class);

    /**
     * 解析property注解定义
     * @param modelType
     * @return
     */
    public static List<ApiPropertyMeta> parsePropertyMetas(Type modelType){
        List<ApiPropertyMeta> apiPropertyMetaList = new ArrayList<>();
        Type beanType = null;
        if (modelType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) modelType;
            beanType = parameterizedType.getRawType();
        }else if(modelType instanceof TypeVariable){
            TypeVariable typeVariable = (TypeVariable)modelType;
            beanType = (Class)typeVariable.getGenericDeclaration();
        }else{
            beanType = modelType;
        }

        if(beanType.getTypeName().contains("PageParam")){
            System.out.println(beanType);
        }

        Field[] fields = ((Class)beanType).getDeclaredFields();
        if(fields == null || fields.length == 0){
            return apiPropertyMetaList;
        }

        for (Field field:fields){
            ApiProperty apiProperty = field.getAnnotation(ApiProperty.class);
            if(apiProperty == null){
                continue;
            }
            if(field.getType() == List.class){
                logger.info("{}",field);
            }
            Type fieldType = null;
            Type paramType = null;
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                fieldType = parameterizedType.getRawType();
                for (Type type : parameterizedType.getActualTypeArguments()) {
                    paramType = type;
                }
                //logger.info("{}",fieldType);
            }else if(genericType instanceof TypeVariable){
                TypeVariable typeVariable = (TypeVariable)genericType;
                fieldType = (Class)typeVariable.getGenericDeclaration();
                //logger.info("{}",fieldType);
                continue;
            }else{
                fieldType = field.getType();
                //logger.info("{}",fieldType);
            }


            apiPropertyMetaList.add(new ApiPropertyMeta(field,apiProperty,fieldType, paramType));
        }
        return apiPropertyMetaList;
    }

}
