package com.doublekit.apibox;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ApiboxAutoConfiguration.class})
public @interface EnableApibox {
}
