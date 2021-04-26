package com.darthcloud.apibox.config;


import com.darthcloud.apibox.annotation.EnableApiboxServer;
import com.darthcloud.apibox.client.annotation.EnableApiboxClient;
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
@EnableApiboxClient
@EnableApiboxServer
@EnablePluginServer
@ComponentScan({"com.darthcloud.apibox"})
public class ApiboxAutoConfiguration {

}
