package com.doublekit.apibox.config;


import com.doublekit.datafly.annotation.DataFly;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//扫描sql配置
@Configuration

@ComponentScan({"com.doublekit.apibox"})
@ServletComponentScan({"com.doublekit.apibox"})
public class ApiboxServerAutoConfiguration {

}
