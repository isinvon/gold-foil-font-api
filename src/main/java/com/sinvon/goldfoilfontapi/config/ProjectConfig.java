package com.sinvon.goldfoilfontapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * 项目配置
 *
 * @author : sinvon
 * @since :  2024/12/7 上午1:58
 */
@Configuration
public class ProjectConfig {
    // 获取当前项目路径
    private static final String projectPath = System.getProperty("user.dir");

    // 文件存放的位置
    @Value("${app.profile}")
    public String profile;

    // 图片存放的位置
    @Value("${app.image}")
    public String imagePath;

    // SVG存放的位置
    @Value("${app.svg}")
    public String svgPath;

    // HTML存放的位置
    @Value("${app.html}")
    public String htmlPath;


    /**
     * 获取存放图片的路径(和项目同级)
     *
     * @return 图片路径
     */
    // public String imagePath() {
    //     // 获取项目路径的父级目录, 例如路径为 C:\Users\sinvon\Documents\workspace\gold-foil-font-api, 只获取 C:\Users\sinvon\Documents\workspace
    //     int lastSeparatorIndex = projectPath.lastIndexOf(File.separator);
    //     if (lastSeparatorIndex == -1) {
    //         throw new IllegalStateException("无法找到父目录");
    //     }
    //     String projectParentDir = projectPath.substring(0, lastSeparatorIndex);
    //     return projectParentDir + File.separator + "image";
    // }
}

