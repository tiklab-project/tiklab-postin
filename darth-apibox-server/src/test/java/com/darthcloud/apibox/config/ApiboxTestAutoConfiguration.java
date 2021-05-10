package com.darthcloud.apibox.config;


import com.darthcloud.message.annotation.EnableMessageServer;
import com.darthcloud.orga.annotation.EnableOrgaServer;
import com.darthcloud.privilege.annotation.EnablePrivilegeServer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name="apibox.server.enabled",havingValue = "true")
@EnableOrgaServer
@EnablePrivilegeServer
@EnableMessageServer
@ComponentScan({"com.darthcloud.apibox"})
public class ApiboxTestAutoConfiguration {

}
