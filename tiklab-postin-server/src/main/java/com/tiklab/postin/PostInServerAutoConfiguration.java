package com.tiklab.postin;


import com.tiklab.dsm.annotation.SQL;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SQL(modules = {
        "apisql",
        "eg",
        "enum"
})
@ComponentScan({"com.tiklab.postin"})
@ServletComponentScan({"com.tiklab.postin"})
public class PostInServerAutoConfiguration {

}
