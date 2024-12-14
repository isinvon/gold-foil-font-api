package com.sinvon.goldfoilfontapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 退出程序
 *
 * @author : sinvon
 * @since :  2024/12/12 下午11:12
 */
@RestController
class HeartbeatController {
    // 存储上次心跳时间的变量
    private long lastHeartbeatTime = System.currentTimeMillis();

    // 心跳接口
    @GetMapping("/heartbeat")
    public String receiveHeartbeat() {
        // 更新心跳时间
        lastHeartbeatTime = System.currentTimeMillis();
        return "Heartbeat received with GET";
    }

    // 支持 POST 请求（用于 sendBeacon）
    @PostMapping("/heartbeat")
    public ResponseEntity<String> postHeartbeat() {
        lastHeartbeatTime = System.currentTimeMillis();
        return ResponseEntity.ok("Heartbeat received with POST");
    }

    // 定时检查心跳
    @Scheduled(fixedRate = 100)  // 每 100ms 检查一次
    public void checkHeartbeat() {
        long currentTime = System.currentTimeMillis();
        // 如果超过 4秒 没有收到心跳请求，则执行关闭操作
        if (currentTime - lastHeartbeatTime > 4000) {
            // System.out.println("No heartbeat received in the last 3 seconds. Shutting down..."); // debug
            // exit(1);  // 强制退出( JVM 在关闭时需要完成一些资源清理, 延迟较大)
            Runtime.getRuntime().halt(1);  // 强制退出，不进行任何清理工作, 由于不涉及数据,所以无所谓了╮(╯_╰)╭
        }
    }
}
