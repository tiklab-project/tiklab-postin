package com.darthcloud.apibox.client.parser;

import com.darthcloud.apibox.annotation.Api;
import com.darthcloud.apibox.client.model.ApiMeta;
import com.darthcloud.apibox.client.model.ApiMethodMeta;
import com.darthcloud.utils.scanner.AnnotationScannerUtil;
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

        Set<Class> apiClzSet = AnnotationScannerUtil.scan(basePackage, Api.class);
        if(apiClzSet == null || apiClzSet.size()==0){
            return apiMetaList;
        }

        //parse api meta
        for(Class apiClz:apiClzSet){
            ApiMeta apiMeta = new ApiMeta();

            Api api = (Api)apiClz.getAnnotation(Api.class);
            apiMeta.setApi(api);
            apiMeta.setCls(apiClz);
            apiMeta.setName(api.name());
            apiMeta.setDesc(api.desc());

            RequestMapping requestMapping = (RequestMapping)apiClz.getDeclaredAnnotation(RequestMapping.class);
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
            List<ApiMethodMeta> apiMethodMetaList = new ApiMethodParser().parseMethodMetas(apiClz, apiMeta);
            apiMeta.setApiMethodMetaList(apiMethodMetaList);

            apiMetaList.add(apiMeta);
        }

        return apiMetaList;
    }
}
