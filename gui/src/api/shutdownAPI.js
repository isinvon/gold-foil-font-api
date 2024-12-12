// src/api/shutdownAPI.js
import axios from "axios";

const apiClient = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 10000,
});

export const shutdownRequest = async () => {
    // 仅绑定一次监听器，防止每次调用时重复绑定
    if (!window._isVisibilityChangeBound) {
        window.addEventListener('visibilitychange', function () {
            if (document.visibilityState === 'hidden') {
                // 页面变为不可见时通知后端
                apiClient.get('/shutdown')
                    .catch(error => console.error('Error:', error));
            }
        });
        window._isVisibilityChangeBound = true;  // 标记已经绑定过事件
    }
};
