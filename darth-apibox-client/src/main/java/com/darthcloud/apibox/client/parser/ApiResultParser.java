package com.darthcloud.apibox.client.parser;

import com.alibaba.fastjson.JSON;
import com.darthcloud.apibox.client.definer.DefConfig;
import com.darthcloud.apibox.client.definer.def.BeanDefiner;
import com.darthcloud.apibox.client.model.ApiResultMeta;
import com.darthcloud.apibox.client.mock.JMockitForGeneric;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class ApiResultParser {

    public static ApiResultMeta parseResultMetas(Method method){
        ApiResultMeta resultMeta = new ApiResultMeta();
        //ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        //String[] parameterNames  = parameterNameDiscoverer.getParameterNames(method);

        Class returnType = method.getReturnType();
        Type genericReturnType = method.getGenericReturnType();
        //AnnotatedType annotatedType = method.getAnnotatedReturnType();
        //Type returnAnnoType = annotatedType.getType();
        if(genericReturnType instanceof ParameterizedTypeImpl){
            ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
            String type = parameterizedType.toString();
            type = type.replaceAll("<","&lt;");
            type = type.replaceAll(">","&gt;");
            resultMeta.setResultType(type);
        }else{
            resultMeta.setResultType(genericReturnType.getTypeName());
        }
        Object def = getResultDef(genericReturnType);
        if(def != null){
            String textDef = JSON.toJSONString(def,true);
            resultMeta.setTextDef(textDef);
        }
        /*
        Object eg = getResultEgValue(genericReturnType);
        resultMeta.setEg(eg);
        if(eg != null){
            String textEg = JSON.toJSONString(eg,true);
            resultMeta.setTextEg(textEg);
        }
         */
        return resultMeta;
    }

    /**
     * 获取结果扩展定义
     * @param returnType
     * @return
     */
    static Object getResultDef(Type returnType){
        if(returnType == java.lang.String.class){
            return "";
        }else if(returnType == int.class || returnType == java.lang.Integer.class){
            return "";
        }else{
            Map<String,Object> map = BeanDefiner.def(returnType, new DefConfig(DefConfig.TYPE_OUPUT));
            return map;
        }
    }

    /**
     * 获取结果示例值
     * @param returnType
     * @return
     */
    static Object getResultEgValue(Type returnType){
        return JMockitForGeneric.mock(returnType,null);
    }

    static boolean isPrimitive(Class paramType){
        if(paramType.isPrimitive()
                || paramType == java.lang.Integer.class
                || paramType == java.lang.String.class
                || paramType == java.lang.Boolean.class){
            return true;
        }
        return false;
    }
}
