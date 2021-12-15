package com.doublekit.apibox.client.parser;

import com.alibaba.fastjson.JSON;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.client.model.ApiMeta;
import com.doublekit.apibox.client.model.ApiMethodMeta;
import com.doublekit.apibox.client.model.ApiParamMeta;
import com.doublekit.apibox.client.model.ApiResultMeta;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * api method parser
 */
public class ApiMethodParser {

    /**
     * parse method metas
     * @param clz
     * @param apiMeta
     * @return
     */
    public List<ApiMethodMeta> parseMethodMetas(Class clz, ApiMeta apiMeta){
        List<ApiMethodMeta> apiMethodMetaList = new ArrayList<>();
        Method[] methods = clz.getDeclaredMethods();
        if(methods == null  || methods.length==0){
            return apiMethodMetaList;
        }
        for(Method method:methods){
            ApiMethod apiMethod = method.getDeclaredAnnotation(ApiMethod.class);
            if(apiMethod == null){
                continue;
            }

            ApiMethodMeta methodMeta = new ApiMethodMeta();
            methodMeta.setMethod(method);
            methodMeta.setApiMethod(apiMethod);
            methodMeta.setName(apiMethod.name());
            methodMeta.setDesc(apiMethod.desc());
            RequestMapping requestMapping = (RequestMapping)method.getDeclaredAnnotation(RequestMapping.class);
            if(requestMapping != null){
                String path = null;
                String[] urls = requestMapping.path();
                if(urls != null && urls.length > 0){
                    path = urls[0];
                }
                String[] vls = requestMapping.value();
                if(vls != null && vls.length > 0){
                    path = vls[0];
                }
                if(apiMeta != null && !StringUtils.isEmpty(apiMeta.getPath())){
                    path = apiMeta.getPath().concat(path);
                }
                methodMeta.setPath(path);

                RequestMethod[] requestMethods = requestMapping.method();
                if(requestMethods != null && requestMethods.length > 0){
                    RequestMethod requestMethod = requestMethods[0];
                    methodMeta.setRequestType(requestMethod.name());
                }else{
                    methodMeta.setRequestType("GET");
                }
            }

            //解析参数
            List<ApiParamMeta> paramMetaList = new ApiParamParser().parseParamMetas(method);
            methodMeta.setApiParamMetaList(paramMetaList);

            Map<String,Object> paramEgMap = getParamEgMap(paramMetaList);
            methodMeta.setParamEg(JSON.toJSONString(paramEgMap));

            String dataType = getMethodDataType(paramMetaList);
            methodMeta.setParamDataType(dataType);

            //解析返回结果
            ApiResultMeta resultMeta = new ApiResultParser().parseResultMetas(method);
            methodMeta.setApiResultMeta(resultMeta);

            //add to list
            apiMethodMetaList.add(methodMeta);
        }
        return apiMethodMetaList;
    }

    /**
     * 获取方法请求格式类型
     * @param paramMetaList
     * @return
     */
    String getMethodDataType(List<ApiParamMeta> paramMetaList){
        String dataType = "form-data";
        if(CollectionUtils.isEmpty(paramMetaList)){
            return dataType;
        }

        for(ApiParamMeta apiParamMeta:paramMetaList){
            if("json".equals(apiParamMeta.getParamDataType())){
                dataType = "json";
                break;
            }
        }
        return dataType;
    }

    Map<String,Object> getParamEgMap(List<ApiParamMeta> paramMetaList){
        Map<String,Object> paramEgMap = new HashMap<>();
        if(paramMetaList != null && paramMetaList.size() > 0){
            for(ApiParamMeta paramMeta:paramMetaList){
                paramEgMap.put(paramMeta.getName(),paramMeta.getEg());
            }
        }
        return paramEgMap;
    }

}
