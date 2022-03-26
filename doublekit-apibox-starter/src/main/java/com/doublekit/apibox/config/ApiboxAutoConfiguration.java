package com.doublekit.apibox.config;


import com.doublekit.apibox.annotation.EnableApiboxServer;
import com.doublekit.apibox.client.annotation.EnableApiboxClient;
import com.doublekit.beans.starter.annotation.EnableBeans;
import com.doublekit.dal.starter.annotation.EnableDal;
import com.doublekit.datafly.starter.annotation.EnableDataFly;
import com.doublekit.dcs.starter.annotation.EnableDcs;
import com.doublekit.dfs.starter.annotation.EnableDfs;
import com.doublekit.dss.starter.annotation.EnableDss;
import com.doublekit.eam.starter.annotation.EnableEamClient;
import com.doublekit.eam.starter.annotation.EnableEamServer;
import com.doublekit.join.starter.annotation.EnableJoin;
import com.doublekit.licence.starter.annotation.EnableLicenceServer;
import com.doublekit.message.starter.annotation.EnableMessage;
import com.doublekit.plugin.starter.annotation.EnablePluginServer;
import com.doublekit.privilege.starter.annotation.EnablePrivilegeServer;
import com.doublekit.rpc.starter.annotation.EnableRpc;
import com.doublekit.toolkit.starter.annotation.EnableToolkitServer;
import com.doublekit.user.starter.annotation.EnableUserServer;
import com.doublekit.web.starter.annotation.EnableWeb;
import org.springframework.context.annotation.Configuration;

@Configuration
//platform
@EnableBeans
@EnableJoin
@EnableWeb
@EnableDal
@EnableDataFly
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
