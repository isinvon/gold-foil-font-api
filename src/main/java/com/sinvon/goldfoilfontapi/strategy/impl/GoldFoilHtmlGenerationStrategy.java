package com.sinvon.goldfoilfontapi.strategy.impl;

import com.sinvon.goldfoilfontapi.config.ProjectConfig;
import com.sinvon.goldfoilfontapi.strategy.GoldFoilGenerationStrategy;
import com.sinvon.goldfoilfontapi.utils.FileUtils;
import com.sinvon.goldfoilfontapi.utils.svg.GoldFoilSvgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * HTML 生成策略
 *
 * @author : sinvon
 * @since :  2024/12/9 下午12:11
 */
@Service
public class GoldFoilHtmlGenerationStrategy implements GoldFoilGenerationStrategy {

    @Autowired
    private ProjectConfig projectConfig;
    @Autowired
    private GoldFoilSvgGenerationStrategy svgGenerationStrategy;

    @Override
    public File generate(String text, String gradientPos) {
        File svgFile = svgGenerationStrategy.generate(text, gradientPos);
        String svgContent = GoldFoilSvgUtils.svgFileToString(svgFile.getAbsolutePath());
        String htmlContent = GoldFoilSvgUtils.SvgTextToHtml(svgContent);

        String htmlPath = projectConfig.htmlPath + File.separator + projectConfig.fileName + ".html";
        return FileUtils.writeToFile(htmlPath, htmlContent);
    }
}
