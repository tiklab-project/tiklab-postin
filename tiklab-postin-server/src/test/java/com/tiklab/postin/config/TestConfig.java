package com.tiklab.postin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PostInTestAutoConfiguration.class)
public class TestConfig {

}
