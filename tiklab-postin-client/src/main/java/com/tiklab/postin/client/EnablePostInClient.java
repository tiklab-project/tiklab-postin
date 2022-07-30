package com.tiklab.postin.client;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PostInClientAutoConfiguration.class})
public @interface EnablePostInClient {
}
