package com.darthcloud.apibox;

import com.darthcloud.apibox.annotation.EnableApiboxServer;
import com.darthcloud.apibox.client.annotation.EnableApiboxClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * DarthProjectApplication
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@PropertySource(value = "classpath:application-${env:local}.properties")
@EnableApiboxClient
@EnableApiboxServer
public class ApiboxDeliverApplication {

    public static final Logger logger = LoggerFactory.getLogger(ApiboxDeliverApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiboxDeliverApplication.class, args);
    }

}

