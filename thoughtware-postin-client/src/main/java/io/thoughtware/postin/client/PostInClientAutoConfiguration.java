package io.thoughtware.postin.client;


import io.thoughtware.postin.client.builder.PostInBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Objects;

@Profile({"local","dev"})
@Configuration
@ComponentScan({"io.thoughtware.postin"})
public class PostInClientAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(PostInClientAutoConfiguration.class);

    @Value("${postin.scan.package:io.thoughtware}")
    private String scanPackage;

    @Value("${postin.enable:false}")
    private String enable;

    @Autowired
    PostInBuilder postInBuilder;

    @Bean
    public PostInIniter postinIniter(){
        if(Objects.equals(enable, "true")){
            postInBuilder.scan(scanPackage).build();
        }

        return null;
    }

    class PostInIniter {}

}
