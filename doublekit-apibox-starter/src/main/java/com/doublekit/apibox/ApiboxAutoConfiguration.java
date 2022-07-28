package com.doublekit.apibox;


import com.doublekit.apibox.client.EnableApiboxClient;
import com.doublekit.beans.starter.annotation.EnableBeans;
import com.doublekit.dal.starter.annotation.EnableDal;
import com.doublekit.dcs.starter.EnableDcs;
import com.doublekit.dfs.starter.EnableDfs;
import com.doublekit.dsm.starter.annotation.EnableDsm;
import com.doublekit.dss.starter.EnableDss;
import com.doublekit.eam.starter.EnableEam;
import com.doublekit.gateway.starter.EnableGateway;
import com.doublekit.join.starter.annotation.EnableJoin;
import com.doublekit.licence.starter.EnableLicenceServer;
import com.doublekit.message.starter.EnableMessage;
import com.doublekit.pluginx.starter.EnablePluginServer;
import com.doublekit.privilege.stater.EnablePrivilegeServer;
import com.doublekit.rpc.starter.annotation.EnableRpc;
import com.doublekit.toolkit.EnableToolkitServer;
import com.doublekit.user.starter.EnableUser;
import com.doublekit.web.starter.annotation.EnableWeb;
import org.springframework.context.annotation.Configuration;

@Configuration
//platform
@EnableBeans
@EnableJoin
@EnableWeb
@EnableDal
@EnableDsm
@EnableDfs
@EnableDcs
@EnableDss
@EnableRpc
@EnableMessage
@EnableGateway
//pcs
@EnableUser
@EnableEam
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
