package com.darthcloud.apibox.config;

import com.darthcloud.apibox.client.builder.FeniksBuilder;
import com.darthcloud.apibox.config.annotation.EnableFeniksServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@EnableFeniksServer
public class ApiboxInitAutoConfiguration {

    @Value("${feniks.scan.package}")
    private String scanPackage;

    @Value("${feniks.doc.path}")
    private String docPath;

    @Bean
    @DependsOn("joinQuery")
    public BeanIniter beanIniter(){
        new FeniksBuilder()
                .scan(scanPackage)
                .docPath(docPath)
                .build();
        return new BeanIniter();
    }

    class BeanIniter{}
}
