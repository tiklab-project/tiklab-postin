package com.tiklab.postlink.client;


import com.tiklab.postlink.client.builder.PostLinkBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Profile({"local","dev"})
@Configuration
@ComponentScan({"com.tiklab.postlink"})
public class PostLinkClientAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(com.tiklab.postlink.client.PostLinkClientAutoConfiguration.class);

    @Value("${postlink.scan.package:com.tiklab}")
    private String scanPackage;

    //@Bean
    @DependsOn({"joinQuery"})
    public PostLinkIniter postlinkIniter(){
        new PostLinkBuilder()
                .scan(scanPackage)
                .build();
        return new PostLinkIniter();
    }

    class PostLinkIniter {}

}
