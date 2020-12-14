package com.darthcloud.apibox.client.parser;

import com.alibaba.fastjson.JSON;
import com.darthcloud.apibox.annotation.ApiParam;
import com.darthcloud.apibox.annotation.ApiParams;
import com.darthcloud.apibox.client.definer.def.BeanDefiner;
import com.darthcloud.apibox.client.model.ApiParamMeta;
import com.darthcloud.apibox.client.definer.DefConfig;
import com.darthcloud.apibox.client.mock.JMockit;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiParamParser {

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

                ApiParamMeta paramMeta = buildParamMeta(apiParam,parameter,paramName);

                apiParamMetaList.add(paramMeta);
            }
        }else if(method.getDeclaredAnnotation(ApiParam.class) != null){//ApiParam方法头定义方式
            ApiParam apiParam = method.getDeclaredAnnotation(ApiParam.class);

            //根据名称查找下标，查找对应的参数
            String paramName = apiParam.name();
            int paramIndex = getParamIndex(parameterNames,paramName);
            Parameter parameter = parameters[paramIndex];

            ApiParamMeta paramMeta = buildParamMeta(apiParam,parameter,paramName);

            apiParamMetaList.add(paramMeta);
        }else{//ApiParam参数定义方式
            for(int i=0;i<parameters.length;i++){
                Parameter parameter = parameters[i];
                ApiParam apiParam = parameter.getDeclaredAnnotation(ApiParam.class);
                if(apiParam == null){
                    continue;
                }
                String paramName = parameterNames[i];

                ApiParamMeta paramMeta = buildParamMeta(apiParam,parameter,paramName);

                apiParamMetaList.add(paramMeta);
            }
        }
        return apiParamMetaList;
    }

    static ApiParamMeta buildParamMeta(ApiParam apiParam,Parameter parameter,String paramName){
        ApiParamMeta paramMeta = new ApiParamMeta();
        paramMeta.setApiParam(apiParam);
        paramMeta.setParameter(parameter);
        paramMeta.setName(paramName);
        Class paramType = parameter.getType();
        paramMeta.setParamType(paramType.getTypeName());
        paramMeta.setDesc(apiParam.desc());
        paramMeta.setRequired(apiParam.required());
        //获取参数定义
        Object def = getParamDef(paramType);
        paramMeta.setDef(def);
        if(def != null){
            String textDef = JSON.toJSONString(def,true);
            paramMeta.setTextDef(textDef);
        }
        //获取参数示例值
        Object eg = getParamEgValue(paramType,apiParam.eg());
        paramMeta.setEg(eg);
        if(eg != null){
            String textEg = JSON.toJSONString(eg,true);
            paramMeta.setTextEg(textEg);
        }

        return paramMeta;
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
     * 获取参数定义
     * @param paramType
     * @return
     */
    static Object getParamDef(Class paramType){
        if(paramType == java.lang.String.class){
            return "";
        }else if(paramType == int.class || paramType == java.lang.Integer.class){
            return "";
        }else{
            Map<String,Object> map = BeanDefiner.def(paramType, new DefConfig(DefConfig.TYPE_INPUT));
            return map;
        }
    }

    /**
     * 获取参数示例值
     * @param paramType
     * @param eg
     * @return
     */
    static Object getParamEgValue(Class paramType, String eg){
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
