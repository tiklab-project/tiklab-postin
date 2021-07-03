package com.doublekit.apibox.client.annotation;

import com.doublekit.apibox.client.config.ApiboxClientAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ApiboxClientAutoConfiguration.class})
public @interface EnableApiboxClient {
}
