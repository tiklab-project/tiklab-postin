package com.tiklab.postlink.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 实体注解定义
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface ApiModel {

    /**
     * 模型名称
     * @return
     */
    String name() default "";
}
