package io.tiklab.postin.config;

import io.tiklab.openapi.config.AllowConfig;
import io.tiklab.openapi.config.AllowConfigBuilder;
import io.tiklab.openapi.config.OpenApiConfig;
import io.tiklab.postin.client.openapi.ParamConfig;
import io.tiklab.postin.client.openapi.ParamConfigBuilder;
import io.tiklab.postin.client.openapi.PostInClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class PostInOpenApiAutoConfiguration {

    @Bean
    OpenApiConfig openApiConfig(AllowConfig allowConfig){
        OpenApiConfig openApiConfig = new OpenApiConfig();
        openApiConfig.setAllowConfig(allowConfig);

        return openApiConfig;
    }

    //开放许可配置
    @Bean
    AllowConfig routerConfig(){
        String[] s =  new String[]{
                "/apx/findApixList",
                "/http/findHttpApi",
                "/workspace/findWorkspace",
                "/workspace/findWorkspaceList",
                "/workspace/findAllWorkspace",
                "/testPlan/findTestPlanList",
                "/testPlan/findTestPlan",
                "/environment/findEnvironmentList",
                "/environment/findEnvironment",
                "/testPlanTestDispatch/execute",
                "/testPlanTestDispatch/exeResult",
                "/docletReport/moduleReport"

        };

        return AllowConfigBuilder.instance()
                .allowUrls(s)
                .get();
    }


    @Bean
    PostInClientConfig postInClientConfig(ParamConfig paramConfig){
        PostInClientConfig config = new PostInClientConfig();
        config.setParamConfig(paramConfig);

        return config;
    }

    @Bean
    ParamConfig paramConfig(){
        //设置请求头，属性名称：属性描述
        HashMap<String,String> headers = new HashMap<>();
        headers.put("accessToken","设置的apiKey");

        return ParamConfigBuilder.instance()
                .setScanPackage("io.tiklab.postin") //设置扫描的包路径
                .prePath("/api")             //设置额外的前缀
                .setHeaders(headers)               //设置请求头
                .get();
    }


}
