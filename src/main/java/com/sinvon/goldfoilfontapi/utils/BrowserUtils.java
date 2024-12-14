package com.sinvon.goldfoilfontapi.utils;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * 浏览器工具类
 *
 * @author : sinvon
 * @since :  2024/12/12 下午11:41
 */
public class BrowserUtils {

    /**
     * 打开浏览器
     *
     * @param url
     */
    public static void openBrowser(String url) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("xdg-open " + url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
