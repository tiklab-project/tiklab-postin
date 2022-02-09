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
import com.doublekit.eam.client.annotation.EnableEamClient;
import com.doublekit.eam.server.annotation.EnableEamServer;
import com.doublekit.licence.annotation.EnableLicenceServer;
import com.doublekit.message.annotation.EnableMessage;
import com.doublekit.plugin.annotation.EnablePluginServer;
import com.doublekit.privilege.annotation.EnablePrivilegeServer;
import com.doublekit.rpc.starter.annotation.EnableRpc;
import com.doublekit.toolkit.annotation.EnableToolkitServer;
import com.doublekit.user.annotation.EnableUserServer;
import com.doublekit.web.starter.annotation.EnableWeb;
import org.springframework.context.annotation.Configuration;

@Configuration
//platform
@EnableBeans
@EnableWeb
@EnableDal
@EnableDataFly
@EnableDsl
@EnableDfs
@EnableDcs
@EnableDss
@EnableRpc
@EnableMessage
//pcs
@EnableUserServer
@EnableEamServer
@EnableEamClient
@EnablePrivilegeServer
@EnablePluginServer
@EnableToolkitServer
@EnableLicenceServer
//other
@EnableApiboxServer
@EnableApiboxClient
//@EnableSwagger2
public class ApiboxAutoConfiguration {

}
