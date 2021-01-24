package com.darthcloud.apibox.client.parser;

import com.alibaba.fastjson.JSON;
import com.darthcloud.apibox.client.mock.JMockitForGeneric;
import com.darthcloud.apibox.client.mock.support.MockUtils;
import com.darthcloud.apibox.client.model.ApiPropertyMeta;
import com.darthcloud.apibox.client.model.ApiResultMeta;
import com.darthcloud.apibox.client.model.ParamItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ApiResultParser {

    private static Logger logger = LoggerFactory.getLogger(ApiResultParser.class);

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
            Type resultType = parameterizedType.getRawType();
            String type = parameterizedType.toString();
            type = type.replaceAll("<","&lt;");
            type = type.replaceAll(">","&gt;");
            resultMeta.setType(resultType);
        }else{
            resultMeta.setType(genericReturnType);
        }

        //解析子节点列表
        setChildren(resultMeta);

        deep = 0;

        logger.info("resultMeta:{}", resultMeta);

        if(resultMeta.getChildren() != null && resultMeta.getChildren().size() > 0){
            String textDef = JSON.toJSONString(resultMeta.getChildren(),true);
            resultMeta.setTextDef(textDef);
        }

//        Object def = getResultDef(genericReturnType);
//        if(def != null){
//            String textDef = JSON.toJSONString(def,true);
//            resultMeta.setTextDef(textDef);
//        }
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

    static int deep = 0;

    /**
     * 解析子节点列表
     * @param paramItem
     */
    static void setChildren(ParamItem paramItem){
        deep++;
        if(deep > 10){
            return;
        }
        Type type = paramItem.getType();
        Type paramType = paramItem.getParamType();

        if(MockUtils.isPrimitive(type)){
            return;
        }else if(MockUtils.isList(type)){
            if(paramType == null){
                return;
            }else{
                List<ApiPropertyMeta> apiPropertyMetaList = ApiModelParser.parsePropertyMetas(paramType);
                if(apiPropertyMetaList != null && apiPropertyMetaList.size() > 0){
                    paramItem.setChildren(apiPropertyMetaList);

                    for(ApiPropertyMeta apiPropertyMeta:apiPropertyMetaList){
                        setChildren(apiPropertyMeta);
                    }
                }
            }
        }else{
            List<ApiPropertyMeta> apiPropertyMetaList = ApiModelParser.parsePropertyMetas(type);
            if(apiPropertyMetaList != null && apiPropertyMetaList.size() > 0){
                paramItem.setChildren(apiPropertyMetaList);

                for(ApiPropertyMeta apiPropertyMeta:apiPropertyMetaList){
                    setChildren(apiPropertyMeta);
                }
            }
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
