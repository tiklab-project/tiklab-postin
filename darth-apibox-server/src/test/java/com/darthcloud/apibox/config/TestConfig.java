package com.darthcloud.apibox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ApiboxTestAutoConfiguration.class)
public class TestConfig {

}

