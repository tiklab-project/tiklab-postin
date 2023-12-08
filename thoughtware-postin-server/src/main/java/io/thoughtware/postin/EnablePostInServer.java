package io.thoughtware.postin;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PostInServerAutoConfiguration.class})
public @interface EnablePostInServer {
}
