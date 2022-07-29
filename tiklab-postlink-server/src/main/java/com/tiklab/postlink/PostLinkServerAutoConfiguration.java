package com.tiklab.postlink;


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
@ComponentScan({"com.tiklab.postlink"})
@ServletComponentScan({"com.tiklab.postlink"})
public class PostLinkServerAutoConfiguration {

}
