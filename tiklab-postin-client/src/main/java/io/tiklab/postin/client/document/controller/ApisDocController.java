package io.tiklab.postin.client.document.controller;

import io.tiklab.postin.client.builder.ApiMetaContext;
import io.tiklab.postin.client.model.ApiMeta;
import io.tiklab.postin.client.model.ApiMethodMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@Controller
@RequestMapping("/apis")
public class ApisDocController {

    private static Logger logger = LoggerFactory.getLogger(ApisDocController.class);


    @RequestMapping(path="/list",method = RequestMethod.GET)
    public String list(Model model){
        List<ApiMeta> apiMetaList =  ApiMetaContext.getApiMetaList();
        model.addAttribute("apis", apiMetaList);
        return "ApiList";
    }

    @RequestMapping(path="/detail/{apiName}/{methodName}",method = RequestMethod.GET)
    public String detail(Model model, @PathVariable String apiName,@PathVariable String methodName){
        //查找类定义
        Map<String,ApiMeta> apiMetaMap = ApiMetaContext.getApiMetaMap();
        ApiMeta apiMeta = apiMetaMap.get(apiName);

        //查找方法定义
        List<ApiMethodMeta> apiMethodMetaList = apiMeta.getApiMethodMetaList();
        ApiMethodMeta apiMethodMeta = null;

        for(ApiMethodMeta methodMeta:apiMethodMetaList){
            if(methodMeta.getName().equals(methodName)){
                apiMethodMeta = methodMeta;
            }
        }
        model.addAttribute("method", apiMethodMeta);
        return "ApiDetail";
    }


}
