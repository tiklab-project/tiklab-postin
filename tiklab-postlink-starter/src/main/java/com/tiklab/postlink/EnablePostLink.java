package com.tiklab.postlink;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PostLinkAutoConfiguration.class})
public @interface EnablePostLink {
}
