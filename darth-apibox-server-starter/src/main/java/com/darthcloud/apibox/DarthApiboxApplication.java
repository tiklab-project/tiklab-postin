package com.darthcloud.apibox;

import com.darthcloud.apibox.config.ApiboxInitAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * DarthProjectApplication
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import(ApiboxInitAutoConfiguration.class)
public class DarthApiboxApplication {

    public static final Logger logger = LoggerFactory.getLogger(DarthApiboxApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DarthApiboxApplication.class, args);
    }

}

