// src/api/heartbeatAPI.js
import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 10000, // 设置超时时间
});

// 用于发送心跳请求
export const sendHeartbeat = () => {
    const send = () => {
        apiClient.get('/heartbeat').catch(() => {
            // 错误处理可以留空以避免控制台打印
        });
        // apiClient.get('/heartbeat')
        //     .then(response => console.log("Heartbeat sent"))
        //     .catch(error => console.error("Heartbeat failed", error));
    };

    // 每 1 秒发送一次心跳请求
    const heartbeatInterval = setInterval(send, 1000);

    // 当页面被关闭时，清除心跳请求
    window.addEventListener("beforeunload", () => {
        clearInterval(heartbeatInterval);
    });
};