package com.doublekit.apibox.client.parser;

import com.alibaba.fastjson.JSON;
import com.doublekit.apibox.annotation.ApiParam;
import com.doublekit.apibox.annotation.ApiParams;
import com.doublekit.apibox.client.mock.JMockit;
import com.doublekit.apibox.client.model.ApiParamMeta;
import com.doublekit.apibox.client.model.TypeMetaEnum;
import com.doublekit.common.exception.ApplicationException;
import com.doublekit.common.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * api参数解析
 */
public class ApiParamParser {

    private static Logger logger = LoggerFactory.getLogger(ApiParamParser.class);

    /**
     * 解析方法参数列表
     * @param method
     * @return
     */
    public List<ApiParamMeta> parseParams(Method method){
        List<ApiParamMeta> apiParamMetaList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        if(parameters == null || parameters.length == 0){
            return apiParamMetaList;
        }

        try {
            ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
            String[] parameterNames  = parameterNameDiscoverer.getParameterNames(method);

            if(method.getDeclaredAnnotation(ApiParams.class) != null){//ApiParams方法头定义方式
                ApiParams apiParams = method.getDeclaredAnnotation(ApiParams.class);
                ApiParam[] apiParamsArr = apiParams.value();
                for(ApiParam apiParam:apiParamsArr){
                    //根据名称查找下标，查找对应的参数
                    String paramName = apiParam.name();
                    int paramIndex = getParamIndex(method, parameterNames, paramName);
                    Parameter parameter = parameters[paramIndex];

                    ApiParamMeta paramMeta = parseParam(apiParam,parameter,paramName);

                    apiParamMetaList.add(paramMeta);
                }
            }else if(method.getDeclaredAnnotation(ApiParam.class) != null){//ApiParam方法头定义方式
                ApiParam apiParam = method.getDeclaredAnnotation(ApiParam.class);

                //根据名称查找下标，查找对应的参数
                String paramName = apiParam.name();
                int paramIndex = getParamIndex(method, parameterNames, paramName);
                Parameter parameter = null;
                try {
                    parameter = parameters[paramIndex];
                } catch (Exception e) {
                    throw new ApplicationException(e);
                }

                ApiParamMeta paramMeta = parseParam(apiParam,parameter,paramName);

                apiParamMetaList.add(paramMeta);
            }else{//ApiParam参数定义方式
                for(int i=0;i<parameters.length;i++){
                    Parameter parameter = parameters[i];
                    ApiParam apiParam = parameter.getDeclaredAnnotation(ApiParam.class);
                    if(apiParam == null){
                        continue;
                    }
                    String paramName = parameterNames[i];

                    ApiParamMeta paramMeta = parseParam(apiParam,parameter,paramName);

                    apiParamMetaList.add(paramMeta);
                }
            }
        } catch (Throwable e) {
            String errorMsg = String.format("prase method params failed,method:%s.",method.getName());
            throw new SystemException(errorMsg,e);
        }

        return apiParamMetaList;
    }

    /**
     * 解析参数项定义
     * @param apiParam
     * @param parameter
     * @param paramName
     * @return
     */
    ApiParamMeta parseParam(ApiParam apiParam, Parameter parameter, String paramName){
        ApiParamMeta paramMeta = new ApiParamMeta();

        try {
            paramMeta.setApiParam(apiParam);
            paramMeta.setParameter(parameter);
            paramMeta.setName(paramName);
            paramMeta.setDesc(apiParam.desc());
            paramMeta.setRequired(apiParam.required());
            Class type = parameter.getType();
            paramMeta.setType(type);
            //获取请求类型,form-data或json等
            RequestBody requestBody = parameter.getDeclaredAnnotation(RequestBody.class);
            if(requestBody != null){
                paramMeta.setParamDataType("json");
            }

            /*
            Type type = null;
            Type paramType = null;
            ParameterizedType parameterizedType = (ParameterizedType)parameter.getParameterizedType();
            type = parameterizedType.getRawType();
            for (Type actualType : parameterizedType.getActualTypeArguments()) {
                paramType = actualType;
            }
            paramMeta.setType(type);
            paramMeta.setParamType(paramType);
             */

            //解析子节点列表
            new ApiTypeParser().parseChildren(paramMeta, TypeMetaEnum.TYPE_INPUT,3);

            //deep = 0;

            if(paramMeta.getChildren() != null && paramMeta.getChildren().size() > 0){
                String textDef = JSON.toJSONString(paramMeta.getChildren(),true);
                paramMeta.setTextDef(textDef);
            }
        } catch (Exception e) {
            String errorMsg = String.format("parse param failed,param:%s.",paramName);
            throw new SystemException(errorMsg,e);
        }

        /*
        Object eg = parseParamEgValue(paramType,apiParam.eg());
        paramMeta.setEg(eg);
        if(eg != null){
            String textEg = JSON.toJSONString(eg,true);
            paramMeta.setTextEg(textEg);
        }
         */

        return paramMeta;
    }

    /**
     * 获取参数名称的参数顺序序号
     * @param method
     * @param parameterNames
     * @param paramName
     * @return
     */
    int getParamIndex(Method method, String[] parameterNames, String paramName){
        for(int i=0;i<parameterNames.length;i++){
            if(paramName.equals(parameterNames[i])){
                return i;
            }
        }

        String className = method.getDeclaringClass().getName();
        String errorMsg = String.format("class:%s,method:%s,param:%s not found.",className,method.getName(),paramName);
        throw new ApplicationException(errorMsg);
    }

    /**
     * 获取参数示例值
     * @param paramType
     * @param eg
     * @return
     */
    Object parseParamEgValue(Class paramType, String eg){
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

    boolean isPrimitive(Class paramType){
        if(paramType.isPrimitive()
                || paramType == java.lang.Integer.class
                || paramType == java.lang.String.class
                || paramType == java.lang.Boolean.class){
            return true;
        }
        return false;
    }

}
