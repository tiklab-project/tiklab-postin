package com.darthcloud.apibox;

import com.darthcloud.apibox.annotation.EnableApiboxServer;
import com.darthcloud.apibox.client.annotation.EnableApiboxClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * DarthProjectApplication
 */
@SpringBootApplication
@PropertySource(value = "classpath:application-${env:local}.properties")
@EnableApiboxClient
@EnableApiboxServer
public class ApiboxInlineApplication {

    public static final Logger logger = LoggerFactory.getLogger(ApiboxInlineApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiboxInlineApplication.class, args);
    }

}

