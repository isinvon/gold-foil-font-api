package com.sinvon.goldfoilfontapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : sinvon
 * @since :  2024/12/11 下午12:06
 */
@Configuration
public class WebConfig {

    @Autowired
    private ProjectConfig projectConfig;

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 添加 API 接口 CORS 配置
                registry.addMapping("/api/**")
                        .allowedOrigins(projectConfig.frontendHost)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true); // 如果需要发送 Cookie
                // 添加心跳接口的 CORS 配置
                registry.addMapping("/heartbeat")
                        .allowedOrigins(projectConfig.frontendHost)
                        .allowedMethods("GET");
                // 添加系统字体获取的 CORS 配置
                registry.addMapping("/font/getSystemFonts")
                        .allowedOrigins(projectConfig.frontendHost)
                        .allowedMethods("GET");
            }
        };
    }
}
