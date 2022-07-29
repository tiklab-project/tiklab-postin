package com.tiklab.postlink;

import com.tiklab.utils.property.PropertyAndYamlSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * PostLinkApplication
 */
@SpringBootApplication
@EnablePostLink
@PropertySource(value = "classpath:application-${env:dev}.yaml",factory = PropertyAndYamlSourceFactory.class)
public class PostLinkApplication {

    public static final Logger logger = LoggerFactory.getLogger(PostLinkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PostLinkApplication.class, args);
    }

}

