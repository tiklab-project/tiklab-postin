package com.darthcloud.apibox.client.definer.bean;

import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.client.definer.DefType;
import com.darthcloud.apibox.client.mock.support.MockUtils;
import com.darthcloud.apibox.client.model.ApiPropertyMeta;
import com.darthcloud.apibox.client.parser.ApiModelParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BeanDefiner {

    private static Logger logger = LoggerFactory.getLogger(BeanDefiner.class);

    public static Map<String,Object> parse(Type beanType, DefType defType){
        Map<String,Object> map = new LinkedHashMap<>();

        List<ApiPropertyMeta> apiPropertyMetaList = ApiModelParser.parsePropertyMetas(beanType, null);
        for(ApiPropertyMeta apiPropertyMeta : apiPropertyMetaList){
            Type fieldType = apiPropertyMeta.getType();
            Type paramType = apiPropertyMeta.getParamType();
            ApiProperty apiProperty = apiPropertyMeta.getApiProperty();
            String key = apiProperty.name();

            //解析不同类型
            Object value = null;
            if(MockUtils.isPrimitive(fieldType)){
                value = PrimitivePropertyDefiner.parse(fieldType,apiProperty,defType);
            }else if(MockUtils.isList(fieldType)){
                logger.info("beanType:{},fieldType:{},paramType:{}",beanType,fieldType,paramType);
                value = CollectionPropertyDefiner.parse(fieldType,paramType, apiProperty, defType);
            }else{
                logger.info("beanType:{},fieldType:{},paramType:{}",beanType,fieldType,paramType);
                value = InnerBeanPropertyDefiner.parse(fieldType, paramType, apiProperty,defType);
            }

            map.put(key,value);
        }
        return map;
    }

}
