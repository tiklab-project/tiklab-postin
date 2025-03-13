package io.tiklab.postin.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.client.builder.PostInBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

@Profile({"local","dev"})
@Configuration
@ComponentScan({"io.tiklab.postin"})
public class PostInClientAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(PostInClientAutoConfiguration.class);

    @Value("${postin.scan.package:io.tiklab.postin}")
    private String scanPackage;

    @Value("${postin.enable:true}")
    private String enable;

    @Autowired
    PostInBuilder postInBuilder;

    @Bean
    public PostInIniter postinIniter(){
        if(Objects.equals(enable, "true")){
            JSONArray allmodule = postInBuilder.scan(scanPackage).build();
            return new PostInIniter(allmodule);
        }
        return new PostInIniter(null);
    }

    public static class PostInIniter {
        private final Object docData;

        public PostInIniter(Object docData) {
            this.docData = docData;
        }

        public Object getDocData() {
            return docData;
        }
    }

    @RestController
    public static class DocController {
        private final PostInIniter postInIniter;

        @Autowired
        public DocController(PostInIniter postInIniter) {
            this.postInIniter = postInIniter;
        }

        @PostMapping("/openapi/doc")
        public Object getApiDoc() {
            return postInIniter.getDocData();
        }
    }
}