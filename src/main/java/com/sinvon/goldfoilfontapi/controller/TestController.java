package com.sinvon.goldfoilfontapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : sinvon
 * @since :  2024/12/5 下午7:15
 */

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("1")
    public String test() {
        System.out.println("http://localhost:8080/test/1");
        return "test";
    }
    @GetMapping("2")
    public String test2() {
        return "test2";
    }
}
