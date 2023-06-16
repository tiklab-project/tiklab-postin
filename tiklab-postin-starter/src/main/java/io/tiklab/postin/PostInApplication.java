package io.tiklab.postin;

import io.tiklab.core.property.PropertyAndYamlSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * PostInApplication
 */
@SpringBootApplication
@EnablePostIn
@PropertySource(value = {"classpath:application.yaml"},factory = PropertyAndYamlSourceFactory.class)
public class PostInApplication {

    public static final Logger logger = LoggerFactory.getLogger(PostInApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PostInApplication.class, args);
    }

}
