package io.thoughtware.postin.annotation;

import io.thoughtware.postin.config.PostInAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PostInAutoConfiguration.class})
public @interface EnablePostIn {
}
