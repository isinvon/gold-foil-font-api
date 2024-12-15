package com.sinvon.goldfoilfontapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : sinvon
 * @since : 2024年12月9日12:30:27
 */
@SpringBootApplication
@EnableScheduling
public class GoldFoilFontApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoldFoilFontApiApplication.class, args);
    }
}
