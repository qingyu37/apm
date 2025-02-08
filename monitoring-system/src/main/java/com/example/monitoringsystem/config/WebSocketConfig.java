package com.example.monitoringsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册 WebSocket 端点，setAllowedOrigins 设置允许的来源
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:3000") // 这里填写前端的地址
                .withSockJS(); // 启用 SockJS 作为 WebSocket 的备用方式
        System.out.println("Registering WebSocket endpoints...");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 启用简单消息代理，"/topic" 是广播消息的前缀
        registry.enableSimpleBroker("/topic");
        // 设置应用目的地的前缀
        registry.setApplicationDestinationPrefixes("/app");
    }
}
