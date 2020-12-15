package com.darthcloud.apibox.config;

import com.darthcloud.apibox.client.builder.ApiboxBuilder;
import com.darthcloud.apibox.config.annotation.EnableApiboxServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@EnableApiboxServer
public class ApiboxInitAutoConfiguration {

    @Value("${apibox.scan.package}")
    private String scanPackage;

    @Value("${apibox.doc.path}")
    private String docPath;

    @Bean
    @DependsOn("joinQuery")
    public BeanIniter beanIniter(){
        new ApiboxBuilder()
                .scan(scanPackage)
                .docPath(docPath)
                .build();
        return new BeanIniter();
    }

    class BeanIniter{}
}
