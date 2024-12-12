package com.sinvon.goldfoilfontapi;

import com.sinvon.goldfoilfontapi.webview.WebviewLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author : sinvon
 * @since : 2024年12月9日12:30:27
 */
@SpringBootApplication
public class GoldFoilFontApiApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(GoldFoilFontApiApplication.class, args);
        WebviewLauncher webviewLauncher = context.getBean(WebviewLauncher.class);
        webviewLauncher.launchWebview();
    }
}
