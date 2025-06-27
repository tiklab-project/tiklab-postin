package io.tiklab.postin.config;

import io.tiklab.dal.boot.starter.annotation.EnableDal;
import io.tiklab.dcs.boot.starter.annotation.EnableDcsClient;
import io.tiklab.dcs.boot.starter.annotation.EnableDcsServer;
import io.tiklab.dsm.boot.starter.annotation.EnableDsm;
import io.tiklab.eam.boot.starter.annotation.EnableEamClient;
import io.tiklab.eam.boot.starter.annotation.EnableEamServer;
import io.tiklab.gateway.boot.starter.annotation.EnableGateway;
import io.tiklab.install.spring.boot.starter.EnableInstallServer;
import io.tiklab.licence.boot.starter.annotation.EnableLicenceServer;
import io.tiklab.messsage.boot.starter.annotation.EnableMessageServer;
import io.tiklab.openapi.boot.starter.annotation.EnableOpenApi;
import io.tiklab.postgresql.spring.boot.starter.EnablePostgresql;
import io.tiklab.postin.EnablePostInServer;
import io.tiklab.postin.client.EnablePostInClient;
import io.tiklab.privilege.boot.starter.annotation.EnablePrivilegeServer;
import io.tiklab.rpc.boot.starter.annotation.EnableRpc;
import io.tiklab.security.boot.stater.annotation.EnableSecurityServer;
import io.tiklab.toolkit.boot.starter.annotation.EnableToolkit;
import io.tiklab.user.boot.starter.annotation.EnableUserClient;
import io.tiklab.user.boot.starter.annotation.EnableUserServer;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;


@Configuration
@EnableToolkit
@EnableInstallServer
@EnablePostgresql
//@EnableDfsClient
//@EnableDfsServer
//@EnableDssClient
//@EnableDssServer
@EnableDcsClient
@EnableDcsServer
@EnableDal
@EnableRpc
@EnableGateway
@EnableOpenApi
@EnableDsm
//
@EnableMessageServer
@EnableSecurityServer
@EnableUserServer
@EnableUserClient
@EnableEamServer
@EnableEamClient
@EnableLicenceServer
@EnablePrivilegeServer

//other
@EnablePostInServer
@EnablePostInClient
public class PostInAutoConfiguration {

    @Bean
    public MultipartConfigElement multipartConfig() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件大小5GB
        factory.setMaxFileSize(DataSize.ofGigabytes(5L));
        //设置总上传数据大小5GB
        factory.setMaxRequestSize(DataSize.ofGigabytes(5L));

        return factory.createMultipartConfig();
    }
}
