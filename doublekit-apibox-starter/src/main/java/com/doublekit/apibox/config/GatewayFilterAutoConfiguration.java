package com.doublekit.apibox.config;

import com.doublekit.eam.author.Authenticator;
import com.doublekit.eam.client.config.TicketConfig;
import com.doublekit.eam.client.config.TicketConfigBuilder;
import com.doublekit.eam.client.handler.TicketHandler;
import com.doublekit.gateway.filter.GatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayFilterAutoConfiguration {

    @Autowired
    Authenticator authenticator;

    @Bean
    public FilterRegistrationBean ticketFilterRegistration(TicketHandler ticketHandler) {
        GatewayFilter gatewayFilter = new GatewayFilter();
        gatewayFilter.addHandler(ticketHandler);

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(gatewayFilter);
        registration.setName("gatewayFilter");
        registration.addUrlPatterns("/*");
        registration.setOrder(-1);
        return registration;
    }

    @Bean
    public TicketHandler ticketHandler(TicketConfig ticketConfig){
        TicketHandler ticketFilter = new TicketHandler();
        ticketFilter.setTicketConfig(ticketConfig);
        ticketFilter.setAuthenticator(authenticator);

        return ticketFilter;
    }

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
