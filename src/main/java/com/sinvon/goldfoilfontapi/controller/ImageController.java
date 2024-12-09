package com.sinvon.goldfoilfontapi.controller;

import com.sinvon.goldfoilfontapi.config.ProjectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * 图片接口
 *
 * @author : sinvon
 * @since :  2024/12/7 下午11:37
 */
@RestController
@RequestMapping("/img")
class ImageController {
    @Autowired
    private ProjectConfig projectConfig;

    /**
     * 提供静态图片访问
     *
     * @return ResponseEntity
     */
    @GetMapping("gold-foil-image.png")
    public ResponseEntity<Resource> getImage() {
        String imagePath = projectConfig.imagePath;
        File file = new File(imagePath);
        if (!file.exists()) {
            return null;
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new org.springframework.core.io.FileSystemResource(imagePath + File.separator + projectConfig.fileName + ".png"));
    }
}
