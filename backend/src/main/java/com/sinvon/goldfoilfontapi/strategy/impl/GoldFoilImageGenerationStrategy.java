package com.sinvon.goldfoilfontapi.strategy.impl;

import com.sinvon.goldfoilfontapi.config.ProjectConfig;
import com.sinvon.goldfoilfontapi.service.GoldFoilImageUtilsService;
import com.sinvon.goldfoilfontapi.strategy.GoldFoilGenerationStrategy;
import com.sinvon.goldfoilfontapi.strategy.context.GoldFoilGenerationContext;
import com.sinvon.goldfoilfontapi.utils.FileUtils;
import com.sinvon.goldfoilfontapi.service.impl.GoldFoilImageUtilsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Image 生成策略
 *
 * @author : sinvon
 * @since :  2024/12/9 下午12:06
 */
@Service
public class GoldFoilImageGenerationStrategy implements GoldFoilGenerationStrategy {

    @Autowired
    private ProjectConfig projectConfig;

    @Autowired
    private GoldFoilImageUtilsService goldFoilImageUtilsService;

    @Override
    public File generate(GoldFoilGenerationContext context) {
        try {
            BufferedImage image = goldFoilImageUtilsService.createGoldFoilImage(context);
            String imagePath = projectConfig.imagePath + File.separator + projectConfig.fileName + ".png";
            File imageFile = FileUtils.ensureFile(imagePath);
            ImageIO.write(image, "PNG", imageFile);
            return imageFile;
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate gold foil image.", e);
        }
    }
}
