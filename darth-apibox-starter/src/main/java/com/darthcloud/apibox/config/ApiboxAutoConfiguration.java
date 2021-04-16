package com.darthcloud.apibox.config;


import com.darthcloud.apibox.annotation.EnableApiboxServer;
import com.darthcloud.apibox.client.builder.ApiboxBuilder;
import com.darthcloud.message.annotation.EnableMessageServer;
import com.darthcloud.orga.annotation.EnableOrgaServer;
import com.darthcloud.plugin.annotation.EnablePluginServer;
import com.darthcloud.privilege.annotation.EnablePrivilegeServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;

@Configuration
@EnableOrgaServer
@EnablePrivilegeServer
@EnableMessageServer
@EnableApiboxServer
@EnablePluginServer
@PropertySource(value = "classpath:application-${env:local}.properties")
@ComponentScan({"com.darthcloud.apibox"})
public class ApiboxAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(ApiboxAutoConfiguration.class);

    @Value("${apibox.scan.package}")
    private String scanPackage;

    @Value("${apibox.doc.path}")
    private String docPath;

    @Bean
    @DependsOn({"joinQuery","dataInitializerForApibox"})
    @Profile("local")
    public ApiboxIniter apiboxIniter(){
        new ApiboxBuilder()
                .scan(scanPackage)
                .docPath(docPath)
                .build();
        return new ApiboxIniter();
    }

    class ApiboxIniter {}

}
