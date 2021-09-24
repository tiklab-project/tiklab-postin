package com.doublekit.apibox.config;


import com.doublekit.apibox.annotation.EnableApiboxServer;
import com.doublekit.apibox.client.annotation.EnableApiboxClient;
import com.doublekit.beans.starter.annotation.EnableBeans;
import com.doublekit.dal.starter.annotation.EnableDal;
import com.doublekit.datafly.starter.annotation.EnableDataFly;
import com.doublekit.dcs.starter.annotation.EnableDcs;
import com.doublekit.dfs.starter.annotation.EnableDfs;
import com.doublekit.dsl.starter.annotation.EnableDsl;
import com.doublekit.dss.starter.annotation.EnableDss;
import com.doublekit.eam.annotation.EnableEamServer;
import com.doublekit.message.starter.annotation.EnableMessage;
import com.doublekit.plugin.annotation.EnablePluginServer;
import com.doublekit.privilege.annotation.EnablePrivilegeServer;
import com.doublekit.toolkit.annotation.EnableToolkitServer;
import com.doublekit.user.annotation.EnableUserServer;
import com.doublekit.web.starter.annotation.EnableWeb;
import org.springframework.context.annotation.Configuration;

@Configuration
//platform
@EnableBeans
@EnableWeb
@EnableDal
@EnableDsl
@EnableDfs
@EnableDcs
@EnableDss
@EnableMessage
@EnableDataFly
//pcs
@EnableUserServer
@EnableEamServer
@EnablePrivilegeServer
@EnablePluginServer
@EnableToolkitServer
//other
@EnableApiboxServer
@EnableApiboxClient
public class ApiboxAutoConfiguration {

}
