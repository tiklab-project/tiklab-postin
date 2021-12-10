package com.doublekit.apibox;

import com.doublekit.apibox.annotation.EnableApiboxServer;
import com.doublekit.apibox.client.annotation.EnableApiboxClient;
import com.doublekit.beans.starter.annotation.EnableBeans;
import com.doublekit.dal.starter.annotation.EnableDal;
import com.doublekit.dcs.starter.annotation.EnableDcs;
import com.doublekit.dfs.starter.annotation.EnableDfs;
import com.doublekit.dsl.starter.annotation.EnableDsl;
import com.doublekit.dss.starter.annotation.EnableDss;
import com.doublekit.eam.server.annotation.EnableEamServer;
import com.doublekit.message.starter.annotation.EnableMessage;
import com.doublekit.plugin.annotation.EnablePluginServer;
import com.doublekit.privilege.annotation.EnablePrivilegeServer;
import com.doublekit.toolkit.annotation.EnableToolkitServer;
import com.doublekit.user.annotation.EnableUserServer;
import com.doublekit.web.starter.annotation.EnableWeb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * DarthProjectApplication
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
//platform
@EnableBeans
@EnableWeb
@EnableDal
@EnableDsl
@EnableDfs
@EnableDcs
@EnableDss
@EnableMessage
//pcs
@EnableUserServer
@EnableEamServer
@EnablePrivilegeServer
@EnablePluginServer
@EnableToolkitServer
//other
@EnableApiboxClient
@EnableApiboxServer
@PropertySource(value = "classpath:application.properties")
public class ApiboxApplication {

    public static final Logger logger = LoggerFactory.getLogger(ApiboxApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiboxApplication.class, args);
    }

}

