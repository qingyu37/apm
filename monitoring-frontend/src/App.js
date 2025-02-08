import React, { useState, useEffect } from 'react';
import SockJS from 'sockjs-client'; // 引入 SockJS 客户端
import { Client } from '@stomp/stompjs'; // 引入 STOMP 客户端

const App = () => {
  const [monitoringData, setMonitoringData] = useState('等待数据...'); // 用于存储从后端获取的监控数据

  useEffect(() => {
    // 创建 SockJS 实例，指定后端的 WebSocket URL (确保是后端监听的 10086 端口)
    const socket = new SockJS('http://localhost:10086/ws'); 
    const stompClient = new Client({
      webSocketFactory: () => socket, // 使用 SockJS 作为 WebSocket 工厂
      onConnect: () => {
        console.log('Connected to WebSocket');
        // 订阅后端发送的监控数据路径
        stompClient.subscribe('/topic/monitoring', (message) => {
          if (message.body) {
            console.log('Received message: ', message.body);
            setMonitoringData(message.body); // 将收到的消息设置到状态中
          }
        });
      },
      onWebSocketError: (error) => {
        console.error('WebSocket error: ', error);
      },
    });

    stompClient.activate(); // 激活 STOMP 客户端

    return () => {
      // 组件卸载时，关闭连接
      stompClient.deactivate();
    };
  }, []);

  return (
    <div>
      <h1>WebSocket Monitoring</h1>
      <p>实时监控数据: {monitoringData}</p>
    </div>
  );
};

export default App;
