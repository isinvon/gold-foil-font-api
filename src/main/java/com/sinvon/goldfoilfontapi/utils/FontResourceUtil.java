package com.sinvon.goldfoilfontapi.utils;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
public class FontResourceUtil {

    private final ResourceLoader resourceLoader;

    public FontResourceUtil(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public File getFontFile(String fontFileName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:font/" + fontFileName);

        if (!resource.exists()) {
            throw new IOException("Font file not found: " + fontFileName);
        }

        // 创建临时文件
        File tempFile = Files.createTempFile("font_", "_" + fontFileName).toFile();
        tempFile.deleteOnExit(); // JVM 退出时删除临时文件

        try (InputStream inputStream = resource.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead); // 修复: offset 应始终为 0
            }
        }

        return tempFile; // 返回临时文件
    }
}