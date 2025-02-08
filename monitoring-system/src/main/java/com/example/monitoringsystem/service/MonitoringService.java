package com.example.monitoringsystem.service;

import com.example.monitoringsystem.tool.SystemMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MonitoringService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // 模拟获取监控数据的函数
    private String getMonitoringData() {
        double cpuUsage = SystemMetrics.getCpuUsage();  // 获取CPU使用率
        double memoryUsage = SystemMetrics.getMemoryUsage();  // 获取内存使用率
        double jvmMemoryUsage = SystemMetrics.getJvmMemoryUsage();  // 获取JVM内存使用率

        return String.format("CPU Usage: %.2f%%, OS Memory Usage: %.2f%%, JVM Memory Usage: %.2f%%",
                cpuUsage, memoryUsage, jvmMemoryUsage);
    }

    // 每隔5秒发送一次监控数据
    @Scheduled(fixedRate = 10000)
    public void sendMonitoringData() {
        String data = getMonitoringData();
        // 向 /topic/monitoring 发送消息
        messagingTemplate.convertAndSend("/topic/monitoring", data);
        System.out.println("Sent data: " + data); // 打印到控制台，便于调试
    }
}
