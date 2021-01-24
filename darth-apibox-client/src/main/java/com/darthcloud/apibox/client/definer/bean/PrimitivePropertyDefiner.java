package com.darthcloud.apibox.client.definer.bean;

import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.client.definer.DefType;
import com.darthcloud.apibox.client.definer.PropertyDef;

import java.lang.reflect.Type;

/**
 * 基本类型属性定义解析
 */
public class PrimitivePropertyDefiner {

    public static PropertyDef parse(Type filedType, ApiProperty apiProperty, DefType defType){
        PropertyDef def = new PropertyDef();
        def.setFieldType(filedType.getTypeName());
        def.setDesc(apiProperty.desc());
        if(defType.getType() == DefType.TYPE_INPUT){
            def.setRequired(apiProperty.required());
        }
        return def;
    }
}
