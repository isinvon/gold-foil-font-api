package com.sinvon.goldfoilfontapi.strategy.impl;

import com.sinvon.goldfoilfontapi.config.ProjectConfig;
import com.sinvon.goldfoilfontapi.strategy.GoldFoilGenerationStrategy;
import com.sinvon.goldfoilfontapi.strategy.context.GoldFoilGenerationContext;
import com.sinvon.goldfoilfontapi.utils.svg.PngToSvgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * SVG 生成策略
 *
 * @author : sinvon
 * @since :  2024/12/9 下午12:11
 */
@Service
public class GoldFoilSvgGenerationStrategy implements GoldFoilGenerationStrategy {

    @Autowired
    private ProjectConfig projectConfig;
    @Autowired
    private GoldFoilImageGenerationStrategy imageGenerationStrategy;

    @Override
    public File generate(GoldFoilGenerationContext context) {
        File imageFile = imageGenerationStrategy.generate(context);
        String svgPath = projectConfig.svgPath + File.separator + projectConfig.fileName + ".svg";

        if (PngToSvgUtils.createSvgByPng(imageFile.getAbsolutePath(), svgPath)) {
            return new File(svgPath);
        }
        throw new RuntimeException("Failed to generate gold foil SVG.");
    }
}
