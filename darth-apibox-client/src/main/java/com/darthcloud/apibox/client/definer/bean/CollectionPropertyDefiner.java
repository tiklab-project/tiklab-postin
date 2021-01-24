package com.darthcloud.apibox.client.definer.bean;

import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.client.definer.DefType;
import com.darthcloud.apibox.client.definer.PropertyDef;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 集合属性定义解析
 */
public class CollectionPropertyDefiner {

    public static PropertyDef parse(Type collectionType, Type paramType, ApiProperty apiProperty, DefType defType){
        PropertyDef def = new PropertyDef();
        def.setFieldType(collectionType.getTypeName());
        def.setDesc(apiProperty.desc());
        if(defType.getType() == DefType.TYPE_INPUT){
            def.setRequired(apiProperty.required());
        }

        List items = items(collectionType, paramType, defType);
        if(items != null && items.size() > 0){
            def.setMark(items);
        }

        return def;
    }

    /**
     * 解析集合定义
     * @param collectionType
     * @param paramType
     * @param defType
     * @return
     */
    public static List items(Type collectionType, Type paramType, DefType defType){
        List list = new ArrayList();

        Object def = null;//TypeDefiner.parse(paramType,new DefType(DefType.TYPE_INPUT));
        if(def != null){
            list.add(def);
        }

        return list;
    }

}
