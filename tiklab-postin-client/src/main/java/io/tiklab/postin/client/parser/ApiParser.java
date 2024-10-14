package io.tiklab.postin.client.parser;

import io.tiklab.core.resolver.AnnotationResourceResolver;
import io.tiklab.postin.client.model.ApiMeta;
import io.tiklab.core.exception.SystemException;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.client.model.ApiMethodMeta;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * api meta parser
 */
public class ApiParser {

    public List<ApiMeta> parseApiMeta(String basePackage){
        List<ApiMeta> apiMetaList = new ArrayList();

        Set<Class> classSet = AnnotationResourceResolver.resolve(basePackage, Api.class);
        if(classSet == null || classSet.size()==0){
            return apiMetaList;
        }

        //parse api meta
        for(Class aClass:classSet){
            ApiMeta apiMeta = new ApiMeta();

            try {
                Api api = (Api)aClass.getAnnotation(Api.class);
                apiMeta.setApi(api);
                apiMeta.setCls(aClass);
                apiMeta.setName(api.name());
                apiMeta.setDesc(api.desc());

                RequestMapping requestMapping = (RequestMapping)aClass.getDeclaredAnnotation(RequestMapping.class);
                if(requestMapping != null){
                    String[] urls = requestMapping.path();
                    if(urls != null && urls.length > 0){
                        apiMeta.setPath(urls[0]);
                    }
                    String[] vls = requestMapping.value();
                    if(vls != null && vls.length > 0){
                        apiMeta.setPath(vls[0]);
                    }
                }

                //parse method meta
                List<ApiMethodMeta> apiMethodMetaList = new ApiMethodParser().parseMethods(aClass, apiMeta);
                apiMeta.setApiMethodMetaList(apiMethodMetaList);
            } catch (Exception e) {
                String errorMsg = String.format("parse api failed,clz:%s",aClass.getName());
                throw new SystemException(errorMsg,e);
            }

            apiMetaList.add(apiMeta);
        }

        return apiMetaList;
    }
}
