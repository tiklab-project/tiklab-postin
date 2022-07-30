package com.tiklab.postin.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD})
@Retention(RUNTIME)
public @interface ApiMethod {

    /**
     * 方法名称
     * @return
     */
    String name() default "";

    /**
     * 方法描述
     * @return
     */
    String desc() default "";
}
