package com.tiklab.postlink.client;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({com.tiklab.postlink.client.PostLinkClientAutoConfiguration.class})
public @interface EnablePostLinkClient {
}
