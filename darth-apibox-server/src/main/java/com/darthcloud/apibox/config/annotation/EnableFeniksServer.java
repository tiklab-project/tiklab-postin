package com.darthcloud.apibox.config.annotation;

import com.darthcloud.apibox.config.FeniksAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({FeniksAutoConfiguration.class})
public @interface EnableFeniksServer {
}
