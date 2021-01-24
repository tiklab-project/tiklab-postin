package com.darthcloud.apibox.client.definer.bean;

import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.client.definer.DefType;
import com.darthcloud.apibox.client.definer.PropertyDef;
import com.darthcloud.apibox.client.mock.support.MockUtils;
import com.darthcloud.apibox.client.model.ApiPropertyMeta;
import com.darthcloud.apibox.client.parser.ApiModelParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 内部对象属性定义解析
 */
public class InnerBeanPropertyDefiner {

    private static Logger logger = LoggerFactory.getLogger(InnerBeanPropertyDefiner.class);

    public static PropertyDef parse(Type beanType, Type parameterType, ApiProperty apiProperty,DefType defType){
        PropertyDef def = new PropertyDef();
        def.setFieldType(beanType.getTypeName());
        def.setDesc(apiProperty.desc());
        if(defType.getType() == DefType.TYPE_INPUT){
            def.setRequired(apiProperty.required());
        }

        Map<String,Object> items = items(beanType, parameterType, defType);
        if(items != null && items.size() > 0){
            def.setMark(items);
        }
        return def;

    }

    /**
     * 解析bean定义
     * @param beanType
     * @param parameterType
     * @param defType
     * @return
     */
    public static Map<String,Object> items(Type beanType, Type parameterType,DefType defType){
        Map<String,Object> map = new LinkedHashMap<>();

        List<ApiPropertyMeta> apiPropertyMetaList = ApiModelParser.parsePropertyMetas(beanType,null);
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
            }

            map.put(key,value);
        }
        return map;
    }

}
