package net.tiklab.postin;


import net.tiklab.integration.starter.EnableIntegration;
import net.tiklab.licence.starter.EnableLicenceServer;
import net.tiklab.logging.stater.EnableLog;
import net.tiklab.messsage.starter.EnableMessage;
import net.tiklab.mysql.starter.EnableMysql;
import net.tiklab.postin.client.EnablePostInClient;
import net.tiklab.dal.starter.annotation.EnableDal;
import net.tiklab.dcs.starter.EnableDcs;
import net.tiklab.dfs.starter.EnableDfs;
import net.tiklab.dsm.starter.annotation.EnableDsm;
import net.tiklab.dss.starter.EnableDss;
import net.tiklab.eam.starter.EnableEam;
import net.tiklab.gateway.starter.EnableGateway;
import net.tiklab.pluginx.starter.EnablePluginServer;
import net.tiklab.privilege.stater.EnablePrivilegeServer;
import net.tiklab.rpc.starter.annotation.EnableRpc;
import net.tiklab.tks.annotation.EnableTks;
import net.tiklab.todotask.stater.EnableTodoTask;
import net.tiklab.user.starter.EnableUser;
import net.tiklab.web.starter.annotation.EnableWeb;
import org.springframework.context.annotation.Configuration;

@Configuration
//platform
@EnableTks
@EnableWeb
@EnableMysql
@EnableDal
@EnableDsm
@EnableDfs
@EnableDcs
@EnableDss
@EnableRpc
@EnableMessage
@EnableGateway

//pcs
@EnableLog
@EnableIntegration
@EnableTodoTask
@EnableUser
@EnableEam
@EnablePrivilegeServer
@EnablePluginServer
@EnableLicenceServer
//other
@EnablePostInServer
@EnablePostInClient
public class PostInAutoConfiguration {

}
