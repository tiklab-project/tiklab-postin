package io.tiklab.postin;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PostInAutoConfiguration.class})
public @interface EnablePostIn {
}
