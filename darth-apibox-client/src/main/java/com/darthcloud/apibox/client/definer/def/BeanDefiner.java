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

public class BeanDefiner {

    public static Map<String,Object> def(Type modelType, DefConfig defConfig){
        Map<String,Object> map = new LinkedHashMap<>();
        List<ApiPropertyMeta> apiPropertyMetaList = ApiModelParser.parsePropertyMetas(modelType);
        for(ApiPropertyMeta apiPropertyMeta : apiPropertyMetaList){
            Type fieldType = apiPropertyMeta.getType();
            ApiProperty apiProperty = apiPropertyMeta.getApiProperty();
            String key = apiProperty.name();

            Object value = null;
            if(MockUtils.isPrimitive(fieldType)){
                value = getPropertyDef(fieldType,apiProperty,defConfig);
            }else{
                value = InnerBeanDefiner.def(fieldType, defConfig);
            }

            map.put(key,value);
        }
        return map;
    }

    static PropertyDef getPropertyDef(Type filedType, ApiProperty apiProperty, DefConfig defConfig){
        PropertyDef def = new PropertyDef();
        def.setFieldType(filedType.getTypeName());
        def.setDesc(apiProperty.desc());
        if(defConfig.getType() == DefConfig.TYPE_INPUT){
            def.setRequired(apiProperty.required());
        }
        def.setEg(apiProperty.eg());
        return def;
    }
}
