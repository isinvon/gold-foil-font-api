package com.sinvon.goldfoilfontapi.webview.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * WebviewProperties 配置
 * <p>
 * 注意:
 * 不允许使用Lombok来作为@Data/@Getter/@Setter, 否则会报错:
 * </p>
 *
 * @author : sinvon
 * @since :  2024/12/12 下午2:17
 */

@Configuration
@ConfigurationProperties(prefix = "webview")
public class WebviewProperties {

    private String title;
    private String devUrl;
    private String prodUrl;
    private int width;
    private int height;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDevUrl() {
        return devUrl;
    }

    public void setDevUrl(String devUrl) {
        this.devUrl = devUrl;
    }

    public String getProdUrl() {
        return prodUrl;
    }

    public void setProdUrl(String prodUrl) {
        this.prodUrl = prodUrl;
    }

    // @PostConstruct // 在 Bean 初始化完成后执行
    // public void debugProperties() {
    //     System.out.println("WebviewProperties Initialized:");
    //     System.out.println("Title: " + title);
    //     System.out.println("URL: " + url);
    //     System.out.println("Width: " + width);
    //     System.out.println("Height: " + height);
    // }
}

