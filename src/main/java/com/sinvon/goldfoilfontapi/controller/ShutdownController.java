package com.sinvon.goldfoilfontapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.System.exit;

/**
 * 退出程序
 *
 * @author : sinvon
 * @since :  2024/12/12 下午11:12
 */

@RestController
class ShutdownController {
    @GetMapping("/shutdown")
    public void shutdown() {
        // 强制关闭整个程序
        exit(0);
    }
}