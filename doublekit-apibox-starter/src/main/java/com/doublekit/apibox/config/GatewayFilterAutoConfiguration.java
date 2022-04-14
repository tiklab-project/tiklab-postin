package com.doublekit.apibox.config;

import com.doublekit.gateway.author.config.TicketConfig;
import com.doublekit.gateway.author.config.TicketConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayFilterAutoConfiguration {

    @Bean
    public TicketConfig ticketConfig(){
        return TicketConfigBuilder.instance()
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
                        ".ftl"
                })
                .ignoreUrls(new String[]{
                        "/",
                        "/passport/login",
                        "/passport/logout",
                        "/passport/valid",
                        "/auth/valid",
                        "/document/view",
                        "/comment/view",
                        "/share/verifyAuthCode",
                        "/share/judgeAuthCode",
                        "/port/reportImport",
                        "/dingdingcfg/findId",
                        "/dingding/passport/login"

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
                        "/licence"
                })
                .get();
    }
}
