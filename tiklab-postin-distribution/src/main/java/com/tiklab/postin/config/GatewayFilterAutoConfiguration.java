package com.tiklab.postin.config;

import com.tiklab.eam.author.Authenticator;
import com.tiklab.eam.client.author.AuthorHandler;
import com.tiklab.eam.client.author.config.IgnoreConfig;
import com.tiklab.eam.client.author.config.IgnoreConfigBuilder;
import com.tiklab.gateway.GatewayFilter;
import com.tiklab.gateway.router.config.RouterConfig;
import com.tiklab.gateway.router.config.RouterConfigBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayFilterAutoConfiguration{

    //网关filter
    @Bean
    GatewayFilter gatewayFilter(AuthorHandler authorHandler){
        return new GatewayFilter()
                .addHandler(authorHandler);
    }

    //认证handler
    @Bean
    AuthorHandler authorHandler(Authenticator authenticator, IgnoreConfig ignoreConfig){
        return new AuthorHandler()
                .setAuthenticator(authenticator)
                .setIgnoreConfig(ignoreConfig);

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
                        "/user/dingdingcfg/findId",
                        "/user/wechatcfg/findWechatById",
                        "/version/getVersion",
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
                        "/eam/auth/login"

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
    @Value("${eas.address:null}")
    String authAddress;


    //gateway路由配置
    @Bean
    RouterConfig routerConfig(){
        return RouterConfigBuilder.instance()
                .preRoute(new String[]{
                        "/user",
                        "/eam",
                        "/message",

                }, authAddress)
                .get();
    }

}
