package com.doublekit.apibox.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface ApiProperty {

    /**
     * 属性名称
     * @return
     */
    String name() default "";

    String desc() default "";

    boolean required() default false;

    String eg() default "";
}
