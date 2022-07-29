package com.tiklab.postlink.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PostLinkTestAutoConfiguration.class)
public class TestConfig {

}

