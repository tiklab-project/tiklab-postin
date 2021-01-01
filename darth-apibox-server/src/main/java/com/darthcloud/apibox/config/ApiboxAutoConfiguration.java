package com.darthcloud.apibox.config;


import com.darthcloud.apibox.apxmethod.entity.ApxMethodPo;
import com.darthcloud.apibox.apxmethod.model.ApxMethod;
import com.darthcloud.apibox.category.entity.CategoryPo;
import com.darthcloud.apibox.category.model.Category;
import com.darthcloud.apibox.requestparam.model.RequestParam;
import com.darthcloud.apibox.responseresult.entity.ResponseResultPo;
import com.darthcloud.apibox.responseresult.model.ResponseResult;
import com.darthcloud.apibox.workspace.entity.WorkspacePo;
import com.darthcloud.apibox.workspace.model.Workspace;
import com.darthcloud.beans.register.BeanMapperMetaRegister;
import com.darthcloud.cache.client.annotation.EnableDcsClient;
import com.darthcloud.cache.server.annotation.EnableDcsServer;
import com.darthcloud.dal.DataInitializer;
import com.darthcloud.dal.annotation.EnableDal;
import com.darthcloud.dfs.client.annotation.EnableDfsClient;
import com.darthcloud.dfs.server.annotation.EnableDfsServer;
import com.darthcloud.dss.client.annotation.EnableDssClient;
import com.darthcloud.dss.server.annotation.EnableDssServer;
import com.darthcloud.framework.annotation.EnableFramework;
import com.darthcloud.orga.config.annotation.EnableOrgaServer;
import com.darthcloud.rpc.client.annotation.EnableRpcClient;
import com.darthcloud.rpc.server.annotation.EnableRpcServer;
import com.darthcloud.web.annotation.EnableWeb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(name="apibox.server.enabled",havingValue = "true")
//基础组件
@EnableFramework
@EnableWeb
@EnableRpcClient
@EnableRpcServer
@EnableDal
@EnableDfsClient
@EnableDfsServer
@EnableDcsClient
@EnableDcsServer
@EnableDssClient
@EnableDssServer
//业务组件
@EnableOrgaServer
@ComponentScan({"com.darthcloud.apibox"})
public class ApiboxAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(ApiboxAutoConfiguration.class);

    @Bean
    public DataInitializer dataInitializerForApibox(DataSource dataSource) {
        return new DataInitializer(dataSource,new String[]{
                "scripts/Workspace.sql",
                "scripts/Node.sql",
                "scripts/Category.sql",
                "scripts/ApxMethod.sql",
                "scripts/RequestParam.sql",
                "scripts/ResponseResult.sql"
        });
    }

    @Bean
    public BeanIniter beanIniterForApibox(){
        BeanMapperMetaRegister.getInstance()
                .add(Workspace.class, WorkspacePo.class)
                .add(Category.class, CategoryPo.class)
                .add(ApxMethod.class, ApxMethodPo.class)
                .add(RequestParam.class,RequestParam.class)
                .add(ResponseResult.class, ResponseResultPo.class)
        ;
        return new BeanIniter();
    }

    class BeanIniter{}
}
