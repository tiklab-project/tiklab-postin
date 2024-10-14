package io.tiklab.postin;

import io.tiklab.postin.annotation.EnablePostIn;
import io.tiklab.core.property.PropertyAndYamlSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * PostInApplication
 */
@SpringBootApplication
@EnablePostIn
@EnableScheduling
@PropertySource(value = {"classpath:application.yaml"},factory = PropertyAndYamlSourceFactory.class)
public class PostInApplication {
    public static final Logger logger = LoggerFactory.getLogger(PostInApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PostInApplication.class, args);
    }

}

