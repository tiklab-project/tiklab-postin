package io.tiklab.postin.config;

import io.tiklab.postin.client.openapi.PostInClientOpenApiConfig;
import io.tiklab.postin.client.openapi.ProcessParamConfig;
import io.tiklab.postin.client.openapi.ProcessParamConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class PostInClientOpenApiAutoConfiguration {

    @Bean
    PostInClientOpenApiConfig postInClientOpenApiConfig(ProcessParamConfig processParamConfig){
        PostInClientOpenApiConfig config = new PostInClientOpenApiConfig();
        config.setProcessParamConfig(processParamConfig);

        return config;
    }

    @Bean
    ProcessParamConfig processParamConfig(){
        //设置请求头，属性名称：属性描述
        HashMap<String,String> headers = new HashMap<>();
        headers.put("accessToken","设置的apiKey");

        return ProcessParamConfigBuilder.instance()
                .prePath("/api") //设置额外的前缀
                .setHeaders(headers)
                .get();
    }


}