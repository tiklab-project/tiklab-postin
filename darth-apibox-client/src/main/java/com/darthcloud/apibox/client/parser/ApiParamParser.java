package com.darthcloud.apibox.client.parser;

import com.alibaba.fastjson.JSON;
import com.darthcloud.apibox.annotation.ApiParam;
import com.darthcloud.apibox.annotation.ApiParams;
import com.darthcloud.apibox.client.mock.JMockit;
import com.darthcloud.apibox.client.mock.support.MockUtils;
import com.darthcloud.apibox.client.model.ApiParamMeta;
import com.darthcloud.apibox.client.model.ApiPropertyMeta;
import com.darthcloud.apibox.client.model.ParamItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ApiParamParser {

    private static Logger logger = LoggerFactory.getLogger(ApiParamParser.class);

    public static List<ApiParamMeta> parseParamMetas(Method method){
        List<ApiParamMeta> apiParamMetaList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        if(parameters == null || parameters.length == 0){
            return apiParamMetaList;
        }

        ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames  = parameterNameDiscoverer.getParameterNames(method);

        if(method.getDeclaredAnnotation(ApiParams.class) != null){//ApiParams方法头定义方式
            ApiParams apiParams = method.getDeclaredAnnotation(ApiParams.class);
            ApiParam[] apiParamsArr = apiParams.value();
            for(ApiParam apiParam:apiParamsArr){
                //根据名称查找下标，查找对应的参数
                String paramName = apiParam.name();
                int paramIndex = getParamIndex(parameterNames,paramName);
                Parameter parameter = parameters[paramIndex];

                ApiParamMeta paramMeta = parseParamMeta(apiParam,parameter,paramName);

                apiParamMetaList.add(paramMeta);
            }
        }else if(method.getDeclaredAnnotation(ApiParam.class) != null){//ApiParam方法头定义方式
            ApiParam apiParam = method.getDeclaredAnnotation(ApiParam.class);

            //根据名称查找下标，查找对应的参数
            String paramName = apiParam.name();
            int paramIndex = getParamIndex(parameterNames,paramName);
            Parameter parameter = parameters[paramIndex];

            ApiParamMeta paramMeta = parseParamMeta(apiParam,parameter,paramName);

            apiParamMetaList.add(paramMeta);
        }else{//ApiParam参数定义方式
            for(int i=0;i<parameters.length;i++){
                Parameter parameter = parameters[i];
                ApiParam apiParam = parameter.getDeclaredAnnotation(ApiParam.class);
                if(apiParam == null){
                    continue;
                }
                String paramName = parameterNames[i];

                ApiParamMeta paramMeta = parseParamMeta(apiParam,parameter,paramName);

                apiParamMetaList.add(paramMeta);
            }
        }
        return apiParamMetaList;
    }

    static int deep = 0;

    static ApiParamMeta parseParamMeta(ApiParam apiParam, Parameter parameter, String paramName){
        ApiParamMeta paramMeta = new ApiParamMeta();
        paramMeta.setApiParam(apiParam);
        paramMeta.setParameter(parameter);
        paramMeta.setName(paramName);
        paramMeta.setDesc(apiParam.desc());
        paramMeta.setRequired(apiParam.required());
        Class type = parameter.getType();
        paramMeta.setType(type);
//        Type type = null;
//        Type paramType = null;
//        ParameterizedType parameterizedType = (ParameterizedType)parameter.getParameterizedType();
//        type = parameterizedType.getRawType();
//        for (Type actualType : parameterizedType.getActualTypeArguments()) {
//            paramType = actualType;
//        }
//        paramMeta.setType(type);
//        paramMeta.setParamType(paramType);


        //解析子节点列表
        setChildren(paramMeta);

        deep = 0;

        logger.info("paramMeta:{}", paramMeta);

        if(paramMeta.getChildren() != null && paramMeta.getChildren().size() > 0){
            String textDef = JSON.toJSONString(paramMeta.getChildren(),true);
            paramMeta.setTextDef(textDef);
        }
//
//        Object eg = parseParamEgValue(paramType,apiParam.eg());
//        paramMeta.setEg(eg);
//        if(eg != null){
//            String textEg = JSON.toJSONString(eg,true);
//            paramMeta.setTextEg(textEg);
//        }

        return paramMeta;
    }

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
        if(type.getTypeName().contains("CategoryQuery")){
            System.out.println(type);
        }
        Type paramType = paramItem.getParamType();

        if(MockUtils.isPrimitive(type)){
            return;
        }else if(MockUtils.isList(type)){
            if(paramType == null){
                return;
            }else{
                List<ApiPropertyMeta> apiPropertyMetaList = ApiModelParser.parsePropertyMetas(paramType, null);
                if(apiPropertyMetaList != null && apiPropertyMetaList.size() > 0){
                    paramItem.setChildren(apiPropertyMetaList);

                    for(ApiPropertyMeta apiPropertyMeta:apiPropertyMetaList){
                        setChildren(apiPropertyMeta);
                    }
                }
            }
        }else{
            List<ApiPropertyMeta> apiPropertyMetaList = ApiModelParser.parsePropertyMetas(type, paramType);
            if(apiPropertyMetaList != null && apiPropertyMetaList.size() > 0){
                paramItem.setChildren(apiPropertyMetaList);

                for(ApiPropertyMeta apiPropertyMeta:apiPropertyMetaList){
                    setChildren(apiPropertyMeta);
                }
            }
        }
    }

    static int getParamIndex(String[] parameterNames,String paramName){
        for(int i=0;i<parameterNames.length;i++){
            if(paramName.equals(parameterNames[i])){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取参数示例值
     * @param paramType
     * @param eg
     * @return
     */
    static Object parseParamEgValue(Class paramType, String eg){
        if(paramType == java.lang.String.class){
            Object egValue = JMockit.mock(paramType,eg);
            return egValue;
        }else if(paramType == int.class || paramType == java.lang.Integer.class){
            Object egValue = JMockit.mock(paramType,eg);
            return egValue;
        }else{
            Object egValue = JMockit.mock(paramType);
            return egValue;
        }
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
