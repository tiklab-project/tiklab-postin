package com.darthcloud.apibox.client.annotation;

import com.darthcloud.apibox.client.config.ApiboxClientAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ApiboxClientAutoConfiguration.class})
public @interface EnableApiboxClient {
}
