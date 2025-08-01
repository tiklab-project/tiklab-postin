package io.tiklab.postin.config;

import io.tiklab.gateway.config.GatewayConfig;
import io.tiklab.gateway.config.IgnoreConfig;
import io.tiklab.gateway.config.IgnoreConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PostInGatewayAutoConfiguration {

    @Bean
    GatewayConfig gatewayConfig(IgnoreConfig ignoreConfig){
        GatewayConfig gatewayConfig = new GatewayConfig();
        gatewayConfig.setIgnoreConfig(ignoreConfig);

        return gatewayConfig;
    }


    @Bean
    public IgnoreConfig authorConfig(){
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
                        "/message/messageItem/syncUpdateMessage",
                        "/message/messageItem/syncDeleteMessage",
                        "/init/install/findStatus"

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
                        "/docletReport",
                        "/openapi/doc",
                        "/websocket"

                })
                .get();
    }


}
