package com.tiklab.postlink;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PostLinkServerAutoConfiguration.class})
public @interface EnablePostLinkServer {
}
