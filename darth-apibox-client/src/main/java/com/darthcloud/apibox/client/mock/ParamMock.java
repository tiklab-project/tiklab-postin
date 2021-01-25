package com.darthcloud.apibox.client.mock;

import com.darthcloud.apibox.annotation.ApiParam;
import com.darthcloud.apibox.client.model.ApiMethodMeta;
import com.darthcloud.apibox.client.model.ApiParamMeta;
import com.darthcloud.apibox.client.parser.ApiMethodParser;
import com.darthcloud.apibox.client.mock.mocker.BeanMocker;
import com.darthcloud.apibox.client.mock.mocker.PrimitiveMocker;
import com.darthcloud.apibox.client.mock.support.MockUtils;

import java.lang.reflect.Parameter;
import java.util.List;

public class ParamMock {

    private Class<?> targetClass;

    private String methodName;

    private String parameterName;

    public ParamMock() { }

    public ParamMock target(Class targetClass) {
        this.targetClass = targetClass;
        return this;
    }

    public ParamMock method(String methodName){
        this.methodName = methodName;
        return this;
    }

    public ParamMock param(String parameterName){
        this.parameterName = parameterName;
        return this;
    }

    public <T> T get(Class<T> parameterType){
        T egValue = (T) mock(targetClass,methodName,parameterName,parameterType);
        return egValue;
    }

    /**
     * TODO 优化实现
     * @param cls
     * @param methodName
     * @param paramName
     * @return
     */
    <T> T mock(Class cls, String methodName, String paramName, Class<T> paramType){
        ApiParamMeta apiParamMeta = parseApiParamMeta(cls, methodName, paramName);
        if(apiParamMeta == null){
            return null;
        }
        ApiParam apiParam = apiParamMeta.getApiParam();
        Parameter parameter = apiParamMeta.getParameter();

        if(parameter.getType().isPrimitive()){
            if(apiParam.eg() == null || "".equals(apiParam.eg())){
                return null;
            }
        }

        if(MockUtils.isPrimitive(parameter.getType())){
            T egValue = (T) PrimitiveMocker.mock(parameter.getType(), apiParam.eg());
            return egValue;
        }else{
            T egValue = (T) BeanMocker.mock(parameter.getType(), apiParam.eg());
            return egValue;
        }
    }

    ApiParamMeta parseApiParamMeta(Class cls, String methodName, String paramName){
        //TODO 解析整个方法，建立映射表缓存
        List<ApiMethodMeta> apiMethodMetaList = new ApiMethodParser().parseMethodMetas(cls, null);
        if(apiMethodMetaList == null || apiMethodMetaList.size()==0){
            return null;
        }

        for(ApiMethodMeta apiMethodMeta : apiMethodMetaList){
            if(!methodName.equalsIgnoreCase(apiMethodMeta.getMethod().getName())){
                continue;
            }
            List<ApiParamMeta> apiParamMetaList = apiMethodMeta.getApiParamMetaList();
            if(apiParamMetaList == null || apiParamMetaList.size()==0){
                return null;
            }

            for(ApiParamMeta apiParamMeta : apiParamMetaList){
                if(!paramName.equalsIgnoreCase(apiParamMeta.getName())){
                    continue;
                }
                return apiParamMeta;
            }
        }
        return null;
    }

}
