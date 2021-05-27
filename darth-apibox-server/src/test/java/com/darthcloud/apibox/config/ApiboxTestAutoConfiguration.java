package com.darthcloud.apibox.config;


import com.darthcloud.message.annotation.EnableMessageServer;
import com.darthcloud.privilege.annotation.EnablePrivilegeServer;
import com.darthcloud.user.annotation.EnableUserServer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name="apibox.server.enabled",havingValue = "true")
@EnableUserServer
@EnablePrivilegeServer
@EnableMessageServer
@ComponentScan({"com.darthcloud.apibox"})
public class ApiboxTestAutoConfiguration {

}
