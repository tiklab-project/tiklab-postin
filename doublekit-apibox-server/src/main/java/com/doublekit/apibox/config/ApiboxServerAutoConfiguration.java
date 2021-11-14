package com.doublekit.apibox.config;


import com.doublekit.datafly.annotation.DataFly;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//扫描sql配置
@Configuration
@DataFly(locations = {
        "scripts/apibase.sql",
        "scripts/apidef.sql",
        "scripts/apitest.sql",
        "scripts/apimock.sql",
        "scripts/datastru.sql",
        "scripts/apiStatus.sql"
})
@ComponentScan({"com.doublekit.apibox"})
public class ApiboxServerAutoConfiguration {

}
