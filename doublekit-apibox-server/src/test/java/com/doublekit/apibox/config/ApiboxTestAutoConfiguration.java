package com.doublekit.apibox.config;


import com.doublekit.message.annotation.EnableMessage;
import com.doublekit.privilege.annotation.EnablePrivilegeServer;
import com.doublekit.user.annotation.EnableUserServer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name="apibox.server.enabled",havingValue = "true")
@EnableUserServer
@EnablePrivilegeServer
@EnableMessage
@ComponentScan({"com.doublekit.apibox"})
public class ApiboxTestAutoConfiguration {

}
