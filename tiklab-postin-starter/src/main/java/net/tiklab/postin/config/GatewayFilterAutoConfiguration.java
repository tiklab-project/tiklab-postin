package net.tiklab.postin.config;

import net.tiklab.eam.author.Authenticator;
import net.tiklab.eam.client.author.AuthorHandler;
import net.tiklab.eam.client.author.config.IgnoreConfig;
import net.tiklab.eam.client.author.config.IgnoreConfigBuilder;
import net.tiklab.gateway.GatewayFilter;
import net.tiklab.gateway.router.RouterHandler;
import net.tiklab.gateway.router.config.RouterConfig;
import net.tiklab.gateway.router.config.RouterConfigBuilder;
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
                        "/version/getVersion",
                        "/user/wechatcfg/findWechatById",
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
                        "/eam/auth/login",
                        "/local-proxy",
                        "/cloud-proxy"

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
                        "/socket",
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
        if (isEasEnable) {
            return RouterConfigBuilder.instance()
                    .preRoute(new String[]{
                        "/user",
                        "/eam",
                        "/message",
                        "/oplog",
                        "/todo",

                    }, authAddress)
                    .get();
        }else {
            return RouterConfigBuilder.instance().preRoute(new String[]{ }, authAddress).get();
        }

    }

}
