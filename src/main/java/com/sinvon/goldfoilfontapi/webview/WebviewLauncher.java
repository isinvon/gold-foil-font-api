package com.sinvon.goldfoilfontapi.webview;


import com.sinvon.goldfoilfontapi.webview.config.WebviewProperties;
import dev.webview.webview_java.Webview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author : sinvon
 * @since :  2024/12/12 下午2:12
 */

@Component
public class WebviewLauncher {

    @Autowired
    private WebviewProperties webviewProperties;

    public void launchWebview() {
        Webview webview = new Webview(true);
        webview.setTitle(webviewProperties.getTitle());
        webview.setSize(webviewProperties.getWidth(), webviewProperties.getHeight());
        webview.loadURL(webviewProperties.getDevUrl());
        webview.run();
        webview.close();
    }
}
