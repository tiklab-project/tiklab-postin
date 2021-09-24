package com.doublekit.apibox.config;


import com.doublekit.datafly.starter.annotation.DataFly;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@DataFly(locations = {
        "scripts/apibase.sql",
        "scripts/apidef.sql",
        "scripts/apitest.sql",
        "scripts/apimock.sql",
        "scripts/datastru.sql"
})
@ComponentScan({"com.doublekit.apibox"})
public class ApiboxServerAutoConfiguration {

}
