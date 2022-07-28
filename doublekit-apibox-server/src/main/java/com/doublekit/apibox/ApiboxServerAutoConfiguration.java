package com.doublekit.apibox;


import com.doublekit.dsm.annotation.SQL;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SQL(modules = {
        "apisql",
        "eg",
        "enum"
})
@ComponentScan({"com.doublekit.apibox"})
@ServletComponentScan({"com.doublekit.apibox"})
public class ApiboxServerAutoConfiguration {

}
