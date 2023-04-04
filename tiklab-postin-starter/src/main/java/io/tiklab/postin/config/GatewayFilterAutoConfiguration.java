package io.tiklab.postin.config;

import io.tiklab.eam.author.Authenticator;
import io.tiklab.eam.client.author.AuthorHandler;
import io.tiklab.eam.client.author.config.IgnoreConfig;
import io.tiklab.eam.client.author.config.IgnoreConfigBuilder;
import io.tiklab.gateway.GatewayFilter;
import io.tiklab.gateway.router.RouterHandler;
import io.tiklab.gateway.router.config.RouterConfig;
import io.tiklab.gateway.router.config.RouterConfigBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayFilterAutoConfiguration{

    //路由handler
    @Bean
    RouterHandler routerHandler(RouterConfig routerConfig){
        return new RouterHandler()
                .setRouterConfig(routerConfig);
    }

    //网关filter
    @Bean
    GatewayFilter gatewayFilter(RouterHandler routerHandler, AuthorHandler authorHandler){
        return new GatewayFilter()
                .setRouterHandler(routerHandler)
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
                        ".map",
                        ".svg",
                        ".txt"
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
                        "/version/getVersion",
                        "/user/wechatcfg/findWechatById",
                        "/dingding/passport/login",
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
                        "/mockx",
                        "/licence",
                        "/ws",
                        "/socket",
                        "/images",
                        "/request"
                })
                .get();
    }



    //路由转发配置
    @Value("${eas.address:null}")
    String authAddress;


    @Value("${eas.embbed.enable:false}")
    private String easEnable;

    //gateway路由配置
    @Bean
    RouterConfig routerConfig(){
        Boolean isEasEnable = Boolean.parseBoolean(easEnable);
        if (!isEasEnable) {
            return RouterConfigBuilder.instance()
                    .preRoute(new String[]{
                            "/user",
                            "/eam",
                            "/appLink",

                            "/todo/deletetodo",
                            "/todo/updatetodo",
                            "/todo/detailtodo",
                            "/todo/findtodopage",

                            "/message/message",
                            "/message/messageItem",
                            "/message/messageReceiver",

                            "/oplog/deletelog",
                            "/oplog/updatelog",
                            "/oplog/detaillog",
                            "/oplog/findlogpage",
                    }, authAddress)
                    .get();
        }else {
            return RouterConfigBuilder.instance().preRoute(new String[]{ }, authAddress).get();
        }

    }

}
