package com.doublekit.apibox.annotation;

import com.doublekit.apibox.config.ApiboxServerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ApiboxServerAutoConfiguration.class})
public @interface EnableApiboxServer {
}
