package com.example.monitoringsystem.controller;

import com.example.monitoringsystem.service.MonitoringService;
import com.example.monitoringsystem.service.WebSocketSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {

    @Autowired
    private WebSocketSender webSocketSender;
    @Autowired
    private MonitoringService monitoringService;

    // 模拟监控数据
    @GetMapping("/sendMonitoringData")
    public String sendMonitoringData() {
        /*// 这里你可以获取实时监控数据并传递给前端
        String message = "实时监控数据" + System.currentTimeMillis();
        
        // 调用 sendData 方法
        webSocketSender.sendData("/topic/monitoring", message);*/

        // 调用 sendMonitoringData 方法
        monitoringService.sendMonitoringData();
        return "发送监控数据到WebSocket!";
    }
}
