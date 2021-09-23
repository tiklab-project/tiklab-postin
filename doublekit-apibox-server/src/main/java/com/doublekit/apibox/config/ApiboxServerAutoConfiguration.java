package com.doublekit.apibox.config;


import com.doublekit.datafly.starter.annotation.EnableDataFly;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDataFly(location = {
        "scripts/apibase.sql",
        "scripts/apidef.sql",
        "scripts/apitest.sql",
        "scripts/apimock.sql",
        "scripts/datastru.sql"
})
@ComponentScan({"com.doublekit.apibox"})
public class ApiboxServerAutoConfiguration {

}
