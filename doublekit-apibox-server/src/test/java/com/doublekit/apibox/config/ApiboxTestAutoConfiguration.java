package com.doublekit.apibox.config;


import com.doublekit.message.annotation.EnableMessage;
import com.doublekit.privilege.annotation.EnablePrivilegeServer;
import com.doublekit.user.annotation.EnableUserServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMessage
@EnableUserServer
@EnablePrivilegeServer
@ComponentScan({"com.doublekit.apibox"})
public class ApiboxTestAutoConfiguration {

}
