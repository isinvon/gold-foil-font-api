package com.sinvon.goldfoilfontapi.service.impl;

import com.sinvon.goldfoilfontapi.config.ProjectConfig;
import com.sinvon.goldfoilfontapi.service.GoldFoilService;
import com.sinvon.goldfoilfontapi.utils.img.GoldFoilImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author : sinvon
 * @since :  2024/12/8 上午1:12
 */

@Service
public class GoldFoilServiceImpl implements GoldFoilService {

    @Autowired
    private ProjectConfig projectConfig;

    /**
     * 创建GoldFoil图片
     * @param text 需要生成的文本
     * @return 图片文件
     */
    @Override
    public File createGoldFoilImage(String text) {
        try {
            BufferedImage image = GoldFoilImageUtils.createGoldFoilImage(text);
            // 获取图片存储的文件夹
            String imagePath = projectConfig.imagePath;
            // 判断image文件夹是否存在，不存在则创建
            File imageDir = new File(imagePath);
            if (!imageDir.exists()) {
                imageDir.mkdirs();
            }
            // 存放为image/gold-foil-image.png
            File file = new File(imagePath + File.separator + "gold-foil-image.png");
            ImageIO.write(image, "PNG", file);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
