package io.tiklab.postin.client;

import io.tiklab.core.property.PropertyAndYamlSourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnablePostInClient
@PropertySource(value = {"classpath:application.yaml"},factory = PropertyAndYamlSourceFactory.class)
public class PostInClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(PostInClientApplication.class, args);
    }

}
