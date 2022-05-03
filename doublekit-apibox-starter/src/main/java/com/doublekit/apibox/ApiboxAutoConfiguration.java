package com.doublekit.apibox;


import com.doublekit.apibox.client.EnableApiboxClient;
import com.doublekit.beans.starter.annotation.EnableBeans;
import com.doublekit.dal.starter.annotation.EnableDal;
import com.doublekit.dsm.starter.annotation.EnableDsm;
import com.doublekit.dss.starter.EnableDss;
import com.doublekit.gateway.starter.EnableGateway;
import com.doublekit.join.starter.annotation.EnableJoin;
import com.doublekit.licence.EnableLicenceServer;
import com.doublekit.message.starter.EnableMessage;
import com.doublekit.pluginx.EnablePluginServer;
import com.doublekit.portal.EnablePortalClient;
import com.doublekit.portal.EnablePortalServer;
import com.doublekit.privilege.EnablePrivilegeServer;
import com.doublekit.rpc.starter.annotation.EnableRpc;
import com.doublekit.toolkit.EnableToolkitServer;
import com.doublekit.web.starter.annotation.EnableWeb;
import org.springframework.context.annotation.Configuration;

@Configuration
//platform
@EnableBeans
@EnableJoin
@EnableWeb
@EnableDal
@EnableDsm
@EnableDss
@EnableRpc
@EnableMessage
@EnableGateway
//pcs
@EnablePortalServer
@EnablePortalClient
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
