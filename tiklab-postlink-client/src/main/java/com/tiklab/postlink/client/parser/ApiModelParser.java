package com.tiklab.postlink.client.parser;

import com.tiklab.postlink.annotation.ApiProperty;
import com.tiklab.postlink.client.model.ApiPropertyMeta;
import com.tiklab.core.exception.SystemException;
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
     * @param actualType
     * @return
     */
    public List<ApiPropertyMeta> parseProperties(Type modelType, Type actualType){
        List<ApiPropertyMeta> apiPropertyMetaList = new ArrayList<>();

        Field[] fields = new Field[0];
        try {
            Type beanType = null;
            if(modelType instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType) modelType;
                beanType = parameterizedType.getRawType();
                for (Type type : parameterizedType.getActualTypeArguments()) {}
            }else{
                beanType = modelType;
            }

            //TODO
            if(beanType instanceof TypeVariable){
                TypeVariable typeVariable = ((TypeVariable) beanType);
                return apiPropertyMetaList;
            }

            fields = ((Class)beanType).getDeclaredFields();
            if(fields == null || fields.length == 0){
                return apiPropertyMetaList;
            }
        } catch (Throwable e) {
            String errorMsg = String.format("parse model failed,modelType:%s",modelType);
            throw new SystemException(errorMsg,e);
        }

        for (Field field:fields){
            ApiProperty apiProperty = field.getAnnotation(ApiProperty.class);
            if(apiProperty == null){
                continue;
            }

            Type fieldType = null;
            Type paramType = null;
            try {
                fieldType = null;
                paramType = null;
                Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) genericType;
                    fieldType = parameterizedType.getRawType();
                    for (Type type : parameterizedType.getActualTypeArguments()) {
                        paramType = type;
                    }
                }else if(genericType instanceof TypeVariable){
                    TypeVariable typeVariable = (TypeVariable)genericType;
                    if(actualType != null){
                        fieldType = actualType;
                    }else{
                        continue;
                    }
                }else{
                    fieldType = field.getType();
                }
            } catch (Throwable e) {
                String errorMsg = String.format("parse model property failed,modelType:%s,fieldType:%s",modelType,field.getType());
                throw new SystemException(errorMsg,e);
            }

            apiPropertyMetaList.add(new ApiPropertyMeta(field,apiProperty,fieldType, paramType));
        }

        return apiPropertyMetaList;
    }

}
