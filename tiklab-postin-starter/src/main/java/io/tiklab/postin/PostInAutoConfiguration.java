package io.tiklab.postin;


import io.tiklab.integration.starter.EnableIntegration;
import io.tiklab.licence.starter.EnableLicenceServer;
import io.tiklab.messsage.starter.EnableMessage;
import io.tiklab.mysql.starter.EnableMysql;
import io.tiklab.postin.client.EnablePostInClient;
import io.tiklab.dal.starter.annotation.EnableDal;
import io.tiklab.dcs.starter.EnableDcs;
import io.tiklab.dfs.starter.EnableDfs;
import io.tiklab.dsm.starter.annotation.EnableDsm;
import io.tiklab.dss.starter.EnableDss;
import io.tiklab.eam.starter.EnableEam;
import io.tiklab.gateway.starter.EnableGateway;
import io.tiklab.pluginx.starter.EnablePluginServer;
import io.tiklab.privilege.starter.EnablePrivilegeServer;
import io.tiklab.rpc.starter.annotation.EnableRpc;
import io.tiklab.security.stater.EnableSecurity;
import io.tiklab.tks.annotation.EnableTks;
import io.tiklab.todotask.stater.EnableTodoTask;
import io.tiklab.user.starter.EnableUser;
import io.tiklab.web.starter.annotation.EnableWeb;
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
@EnableSecurity
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
