package io.tiklab.postin.config;

import io.tiklab.openapi.config.AllowConfig;
import io.tiklab.openapi.config.AllowConfigBuilder;
import io.tiklab.openapi.config.OpenApiConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                "/workspace/findWorkspaceList"
        };

        return AllowConfigBuilder.instance()
                .allowUrls(s)
                .get();
    }
}
