package io.tiklab.postin.autotest.common.wsTest.controller;

import io.tiklab.postin.autotest.common.wsTest.service.WebSocketServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WebSocketRegisterApi implements WebSocketConfigurer {
    public static final Logger logger = LoggerFactory.getLogger(WebSocketRegisterApi.class);

    @Bean
    public ServletServerContainerFactoryBean createWsContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        int bufferSize = 50 * 1024 * 1024; // 设置缓冲区大小为 50 MB
        container.setMaxTextMessageBufferSize(bufferSize);
        container.setMaxBinaryMessageBufferSize(bufferSize);
        return container;
    }

    /**
     * 注册handle
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketServiceImpl(), "/websocket")
                .addInterceptors()
                .setAllowedOrigins("*");
    }



}