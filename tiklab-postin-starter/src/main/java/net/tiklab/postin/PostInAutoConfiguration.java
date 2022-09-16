package net.tiklab.postin;


import net.tiklab.eas.boot.EnableEas;
import net.tiklab.licence.starter.EnableLicenceServer;
import net.tiklab.postin.client.EnablePostInClient;
import net.tiklab.beans.starter.annotation.EnableBeans;
import net.tiklab.dal.starter.annotation.EnableDal;
import net.tiklab.dcs.starter.EnableDcs;
import net.tiklab.dfs.starter.EnableDfs;
import net.tiklab.dsm.starter.annotation.EnableDsm;
import net.tiklab.dss.starter.EnableDss;
import net.tiklab.eam.starter.EnableEam;
import net.tiklab.gateway.starter.EnableGateway;
import net.tiklab.join.starter.annotation.EnableJoin;
import net.tiklab.message.starter.EnableMessage;
import net.tiklab.pluginx.starter.EnablePluginServer;
import net.tiklab.privilege.stater.EnablePrivilegeServer;
import net.tiklab.rpc.starter.annotation.EnableRpc;
import net.tiklab.toolkit.EnableToolkitServer;
import net.tiklab.user.starter.EnableUser;
import net.tiklab.web.starter.annotation.EnableWeb;
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
@EnableEas
@EnablePrivilegeServer
@EnablePluginServer
@EnableToolkitServer
@EnableLicenceServer
//other
@EnablePostInServer
@EnablePostInClient
//@EnableSwagger2
public class PostInAutoConfiguration {

}
