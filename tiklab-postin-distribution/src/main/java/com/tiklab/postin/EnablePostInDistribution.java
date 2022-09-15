package com.tiklab.postin;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PostInDistributionAutoConfiguration.class})
public @interface EnablePostInDistribution {
}
