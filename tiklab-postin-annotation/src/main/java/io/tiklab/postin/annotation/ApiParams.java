package io.tiklab.postin.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD})
@Retention(RUNTIME)
public @interface ApiParams {

    /**
     * 参数集合
     * @return
     */
    ApiParam[] value();
}
