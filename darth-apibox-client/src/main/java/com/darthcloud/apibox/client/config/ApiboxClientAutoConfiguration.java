package com.darthcloud.apibox.client.config;


import com.darthcloud.apibox.client.builder.ApiboxBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Profile({"local"})
@Configuration
@ComponentScan({"com.darthcloud.apibox"})
public class ApiboxClientAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(ApiboxClientAutoConfiguration.class);

    @Value("${apibox.scan.package:com.darthcloud}")
    private String scanPackage;

    @Bean
    @DependsOn({"joinQuery"})
    public ApiboxIniter apiboxIniter(){
        new ApiboxBuilder()
                .scan(scanPackage)
                .build();
        return new ApiboxIniter();
    }

    class ApiboxIniter {}

}
