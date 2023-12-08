package io.thoughtware.postin.config;

import io.thoughtware.eam.author.Authenticator;

import io.thoughtware.eam.client.author.config.AuthorConfig;
import io.thoughtware.eam.client.author.config.AuthorConfigBuilder;

import io.thoughtware.eam.client.author.handler.AuthorHandler;
import io.thoughtware.gateway.router.Router;
import io.thoughtware.gateway.router.RouterBuilder;
import io.thoughtware.gateway.router.config.RouterConfig;
import io.thoughtware.gateway.router.config.RouterConfigBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayFilterAutoConfiguration{

    @Value("${darth.address:null}")
    String authAddress;

    @Value("${darth.embbed.enable:false}")
    Boolean enableEam;

    //路由
    @Bean
    Router router(RouterConfig routerConfig){
        return RouterBuilder.newRouter(routerConfig);
    }

    //路由配置
    @Bean
    RouterConfig routerConfig(){
        return RouterConfigBuilder.instance()
                .preRoute(new String[]{}, authAddress)
                .get();
    }


    //认证filter
    @Bean
    AuthorHandler authorFilter(Authenticator authenticator, AuthorConfig ignoreConfig){
        return new AuthorHandler()
                .setAuthenticator(authenticator)
                .setAuthorConfig(ignoreConfig);
    }

    @Bean
    public AuthorConfig authorConfig(){
        return AuthorConfigBuilder.instance()
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
                        ".map",
                        ".svg",
                        ".txt"
                })
                .ignoreUrls(new String[]{
                        "/",
                        "/passport/valid",
                        "/auth/valid",
                        "/port/reportImport",
                        "/user/dingdingcfg/findId",
                        "/version/getVersion",
                        "/user/wechatcfg/findWechatById",
                        "/eam/dingding/passport/login",
                        "/eam/passport/login",
                        "/eam/wechat/passport/login",
                        "/eam/passport/logout",
                        "/eam/wechat/passport/logout",
                        "/eam/dingding/passport/logout",
                        "/eam/ldap/passport/login",
                        "/eam/ldap/passport/logout",
                        "/eam/portal.html/",
                        "/testInstance/findTestInstanceList",
                        "/appLink/findAppLinkList",
                        "/eam/auth/login",

                })
                .ignorePreUrls(new String[]{
                        "/share",
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
                        "/licence",
                        "/ws",
                        "/socket",
                        "/images",
                        "/request",
                        "/mockx",
                        "/sql",
                        "/port",
                        "/docletReport"
                })
                .get();
    }


}
