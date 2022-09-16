package net.tiklab.postin.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER,METHOD})
@Retention(RUNTIME)
public @interface ApiParam {

    /**
     * 参数名称
     * @return
     */
    String name() default "";

    String desc() default "";

    boolean required() default false;

    /**
     * 示例值
     * @return
     */
    String eg() default "";
}
