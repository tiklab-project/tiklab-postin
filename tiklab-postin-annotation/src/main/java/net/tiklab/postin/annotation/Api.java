package net.tiklab.postin.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
public @interface Api {

    /**
     * 接口名称
     * @return
     */
    String name() default "";

    String desc() default "";
}
