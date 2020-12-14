package com.darthcloud.apibox.client.definer.def;

import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.client.definer.PropertyDef;
import com.darthcloud.apibox.client.model.ApiPropertyMeta;
import com.darthcloud.apibox.client.parser.ApiModelParser;
import com.darthcloud.apibox.client.definer.DefConfig;
import com.darthcloud.apibox.client.mock.support.MockUtils;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InnerBeanDefiner {

    public static Map<String,Object> def(Type paramType, DefConfig defConfig){
        Map<String,Object> map = new LinkedHashMap<>();
        List<ApiPropertyMeta> apiPropertyMetaList = ApiModelParser.parsePropertyMetas(paramType);
        for(ApiPropertyMeta apiPropertyMeta : apiPropertyMetaList){
            Type fieldType = apiPropertyMeta.getType();
            ApiProperty apiProperty = apiPropertyMeta.getApiProperty();
            String key = apiProperty.name();

            Object value = null;
            if(MockUtils.isPrimitive(fieldType)){
                value = getPropertyDef(fieldType,apiProperty,defConfig);
            }

            map.put(key,value);
        }
        return map;
    }

    static PropertyDef getPropertyDef(Type fieldType, ApiProperty apiProperty, DefConfig defConfig){
        PropertyDef def = new PropertyDef();
        def.setFieldType(fieldType.getTypeName());
        def.setDesc(apiProperty.desc());
        if(defConfig.getType() == DefConfig.TYPE_INPUT){
            def.setRequired(apiProperty.required());
        }
        def.setEg(apiProperty.eg());
        return def;
    }
}
