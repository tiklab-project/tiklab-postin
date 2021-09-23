package com.doublekit.apibox.annotation;

import com.doublekit.apibox.config.ApiboxAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ApiboxAutoConfiguration.class})
public @interface EnableApibox {
}
