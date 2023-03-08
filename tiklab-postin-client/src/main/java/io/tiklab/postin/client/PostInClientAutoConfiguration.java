package io.tiklab.postin.client;


import io.tiklab.postin.client.builder.PostInBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Profile({"local","dev"})
@Configuration
@ComponentScan({"io.tiklab.postin"})
public class PostInClientAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(PostInClientAutoConfiguration.class);

    @Value("${postin.scan.package:io.tiklab}")
    private String scanPackage;

    //@Bean
    @DependsOn({"joinQuery"})
    public PostInIniter postinIniter(){
        new PostInBuilder()
                .scan(scanPackage)
                .build();
        return new PostInIniter();
    }

    class PostInIniter {}

}
