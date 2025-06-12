package io.tiklab.postin.client;

import com.alibaba.fastjson.JSONArray;
import io.tiklab.postin.client.builder.PostInBuilder;
import io.tiklab.postin.client.openapi.ParamConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Configuration
@ComponentScan("io.tiklab.postin")
public class PostInClientAutoConfig {

    private static final Logger logger = LoggerFactory.getLogger(PostInClientAutoConfig.class);

    @RestController
    static class DocController {

        private final PostInBuilder postInBuilder;
        private final ParamConfig paramConfig;

        @Autowired
        public DocController(PostInBuilder postInBuilder, ParamConfig paramConfig) {
            this.postInBuilder = postInBuilder;
            this.paramConfig = paramConfig;
        }

        @PostMapping("/openapi/doc")
        public JSONArray getApiDoc() {
            return postInBuilder.scan(paramConfig.getScanPackage()).build();
        }
    }
}