package com.doublekit.apibox;

import com.doublekit.apibox.annotation.EnableApibox;
import com.doublekit.eam.client.annotation.EnableEamClient;
import com.doublekit.eam.server.annotation.EnableEamServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * ApiboxApplication
 */
@SpringBootApplication
@EnableApibox
@EnableEamServer
@EnableEamClient
@PropertySource(value = "classpath:application-${env:local}.properties")
public class ApiboxApplication {

    public static final Logger logger = LoggerFactory.getLogger(ApiboxApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiboxApplication.class, args);
    }

}

