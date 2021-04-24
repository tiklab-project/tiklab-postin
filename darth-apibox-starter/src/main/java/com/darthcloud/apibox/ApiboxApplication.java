package com.darthcloud.apibox;

import com.darthcloud.apibox.config.ApiboxAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * DarthProjectApplication
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@PropertySource(value = "classpath:application-${env:local}.properties")
@Import(ApiboxAutoConfiguration.class)
public class ApiboxApplication {

    public static final Logger logger = LoggerFactory.getLogger(ApiboxApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiboxApplication.class, args);
    }

}

