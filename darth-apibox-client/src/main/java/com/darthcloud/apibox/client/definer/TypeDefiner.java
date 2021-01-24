package com.darthcloud.apibox.client.definer;

import com.darthcloud.apibox.client.definer.bean.BeanDefiner;
import com.darthcloud.apibox.client.mock.support.MockUtils;

import java.lang.reflect.Type;

public class TypeDefiner {

    public static Object parse(Type type, DefType defType){
        //解析不同类型
        Object value = null;
        if(MockUtils.isPrimitive(type)){
            value = "";
        }else if(MockUtils.isList(type)){
            value = "";
        }else{
            value = BeanDefiner.parse(type, defType);
        }

        return value;
    }

}
