package net.tiklab.postin;


import net.tiklab.licence.starter.EnableLicenceServer;
import net.tiklab.oplog.stater.EnableLog;
import net.tiklab.postin.client.EnablePostInClient;
import net.tiklab.dal.starter.annotation.EnableDal;
import net.tiklab.dcs.starter.EnableDcs;
import net.tiklab.dfs.starter.EnableDfs;
import net.tiklab.dsm.starter.annotation.EnableDsm;
import net.tiklab.dss.starter.EnableDss;
import net.tiklab.eam.starter.EnableEam;
import net.tiklab.gateway.starter.EnableGateway;
import net.tiklab.message.starter.EnableMessage;
import net.tiklab.pluginx.starter.EnablePluginServer;
import net.tiklab.privilege.stater.EnablePrivilegeServer;
import net.tiklab.rpc.starter.annotation.EnableRpc;
import net.tiklab.tks.annotation.EnableTks;
import net.tiklab.todotask.stater.EnableTodoTask;
import net.tiklab.toolkit.EnableToolkitServer;
import net.tiklab.user.starter.EnableUser;
import net.tiklab.web.starter.annotation.EnableWeb;
import net.tiklab.widget.starter.EnableWidget;
import org.springframework.context.annotation.Configuration;

@Configuration
//platform
@EnableTks
@EnableWeb
@EnableDal
@EnableDsm
@EnableDfs
@EnableDcs
@EnableDss
@EnableRpc
@EnableMessage
@EnableGateway
@EnableLog
@EnableTodoTask
//pcs
@EnableWidget
@EnableUser
@EnableEam
@EnablePrivilegeServer
@EnablePluginServer
@EnableToolkitServer
@EnableLicenceServer
//other
@EnablePostInServer
@EnablePostInClient
public class PostInAutoConfiguration {

}
