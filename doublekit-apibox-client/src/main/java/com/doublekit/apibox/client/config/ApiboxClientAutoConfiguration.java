package com.doublekit.apibox.client.config;


import com.doublekit.apibox.client.builder.ApiboxBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Profile({"local","dev"})
@Configuration
@ComponentScan({"com.doublekit.apibox"})
public class ApiboxClientAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(ApiboxClientAutoConfiguration.class);

    @Value("${apibox.scan.package:com.doublekit}")
    private String scanPackage;

    //@Bean
    @DependsOn({"joinQuery"})
    public ApiboxIniter apiboxIniter(){
        new ApiboxBuilder()
                .scan(scanPackage)
                .build();
        return new ApiboxIniter();
    }

    class ApiboxIniter {}

}
