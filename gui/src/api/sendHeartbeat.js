// src/api/heartbeatAPI.js
import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 10000, // 设置超时时间
});

// 用于发送心跳请求
export const sendHeartbeat = () => {
    const send = () => {
        if (document.visibilityState === 'visible') {
            // 页面可见时发送正常心跳请求
            apiClient.get('/heartbeat').catch(() => {
                // 错误处理可以留空以避免控制台打印
            });
        } else {
            // 页面隐藏时使用 sendBeacon
            navigator.sendBeacon('http://localhost:8080/heartbeat');
        }
    };

    // 每 0.5 秒发送一次心跳请求
    const heartbeatInterval = setInterval(send, 500);

    // 页面关闭或刷新时，确保发送心跳
    window.addEventListener("beforeunload", () => {
        clearInterval(heartbeatInterval);
        navigator.sendBeacon('http://localhost:8080/heartbeat');
    });
};
