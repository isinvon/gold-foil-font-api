package com.sinvon.goldfoilfontapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务检测
 *
 * @author : sinvon
 * @since :  2024/12/8 上午12:17
 */
@RestController
@RequestMapping("check")
public class CheckController {

    @RequestMapping("")
    public String check() {
        return "ok";
    }

    // 线程性能检测
    @RequestMapping("thread")
    public String checkThread() {
        int count = 100000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        long end = System.currentTimeMillis();
        return "耗时：" + (end - start) + "ms";
    }
}
