package com.example.monitoringsystem.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketSender {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketSender(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendData(String destination, Object message) {
        // 发送消息到指定的目的地（即前端订阅的路径）
        messagingTemplate.convertAndSend(destination, message);
        System.out.println("发送数据 " + message + " 到 " + destination);
    }
}
