package com.darthcloud.apibox.config;

import com.darthcloud.apibox.client.builder.ApiboxBuilder;
import com.darthcloud.apibox.config.annotation.EnableApiboxServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@EnableApiboxServer
@PropertySource(value = "classpath:application-${env:local}.properties")
public class ApiboxInitAutoConfiguration {

    @Value("${apibox.scan.package}")
    private String scanPackage;

    @Value("${apibox.doc.path}")
    private String docPath;

    @Bean
    @DependsOn({"joinQuery","dataInitializerForApibox"})
    @Profile("local")
    public ApiboxIniter apiboxIniter(){
        new ApiboxBuilder()
                .scan(scanPackage)
                .docPath(docPath)
                .build();
        return new ApiboxIniter();
    }

    class ApiboxIniter {}
}
