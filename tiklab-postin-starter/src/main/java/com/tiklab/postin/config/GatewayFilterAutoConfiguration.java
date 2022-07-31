package com.tiklab.postin.config;

import com.tiklab.eam.server.author.config.IgnoreConfig;
import com.tiklab.eam.server.author.config.IgnoreConfigBuilder;
import com.tiklab.eam.server.handler.AuthorHandler;
import com.tiklab.gateway.config.GatewayConfig;
import com.tiklab.gateway.config.GatewayConfigBuilder;
import com.tiklab.gateway.router.config.RouterConfig;
import com.tiklab.gateway.router.config.RouterConfigBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayFilterAutoConfiguration {

    @Autowired
    AuthorHandler authorHandler;

    @Bean
    GatewayConfig gatewayConfig(){
        return GatewayConfigBuilder.addHandler(authorHandler);
    }

    @Bean
    public IgnoreConfig ignoreConfig(){
        return IgnoreConfigBuilder.instance()
                .ignoreTypes(new String[]{
                        ".ico",
                        ".jpg",
                        ".jpeg",
                        ".png",
                        ".gif",
                        ".html",
                        ".js",
                        ".css",
                        ".json",
                        ".xml",
                        ".ftl",
                        ".map"
                })
                .ignoreUrls(new String[]{
                        "/",
                        "/passport/valid",
                        "/auth/valid",
                        "/document/view",
                        "/comment/view",
                        "/share/verifyAuthCode",
                        "/share/judgeAuthCode",
                        "/port/reportImport",
                        "/dingdingcfg/findId",
                        "/version/getVersion",
                        "/wechatcfg/findWechatById",
                        "/dingding/passport/login",
                        "/passport/login",
                        "/wechat/passport/login",
                        "/passport/logout",
                        "/wechat/passport/logout",
                        "/dingding/passport/logout",
                        "/ldap/passport/login",
                        "/ldap/passport/logout",
                        "/portal.html/",
                        "/testInstance/findTestInstanceList",
                        "/appLink/findAppLinkList",

                })
                .ignorePreUrls(new String[]{
                        "/service",
                        "/apis",
                        "/report/reportTest",
                        "/file",
                        "/plugin",
                        "/authConfig",
                        "/app",
                        "/swagger-resources",
                        "/webjars",
                        "/csrf",
                        "/v2",
                        "/mockx",
                        "/licence",
                        "/ws",
                        "/socket"
                })
                .get();
    }

    //路由转发配置
    //@Value("${project.address:null}")
    String projectUrl;

    //@Bean
    RouterConfig routerConfig(){
        return RouterConfigBuilder.instance()
                .route(new String[]{
                        "/project/findAllProject"
                }, projectUrl)
                .get();
    }
}
