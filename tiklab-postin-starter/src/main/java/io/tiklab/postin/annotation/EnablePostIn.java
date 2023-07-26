package io.tiklab.postin.annotation;

import io.tiklab.postin.config.PostInAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PostInAutoConfiguration.class})
public @interface EnablePostIn {
}
