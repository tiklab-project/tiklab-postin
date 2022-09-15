package com.tiklab.postin;

import com.tiklab.utils.property.PropertyAndYamlSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * PostInApplication
 */
@SpringBootApplication
@EnablePostInDistribution
@PropertySource(value = {"classpath:application.yaml"},factory = PropertyAndYamlSourceFactory.class)
public class PostInDistributionApplication {

    public static final Logger logger = LoggerFactory.getLogger(PostInDistributionApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PostInDistributionApplication.class, args);
    }

}

