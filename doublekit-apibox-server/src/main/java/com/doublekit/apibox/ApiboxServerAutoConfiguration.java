package com.doublekit.apibox;


import com.doublekit.datafly.annotation.DataFly;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@DataFly(modules = {
        "apisql"
})
@ComponentScan({"com.doublekit.apibox"})
@ServletComponentScan({"com.doublekit.apibox"})
public class ApiboxServerAutoConfiguration {

}
