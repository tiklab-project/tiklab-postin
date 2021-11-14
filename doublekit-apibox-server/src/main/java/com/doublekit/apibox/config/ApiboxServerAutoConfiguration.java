package com.doublekit.apibox.config;


import com.doublekit.datafly.annotation.DataFly;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//扫描sql配置
@Configuration
@DataFly(modules = {
        "apibase",
        "apidef",
        "apitest",
        "apimock",
        "datastru",
        "apiStatus"
})
@ComponentScan({"com.doublekit.apibox"})
public class ApiboxServerAutoConfiguration {

}
