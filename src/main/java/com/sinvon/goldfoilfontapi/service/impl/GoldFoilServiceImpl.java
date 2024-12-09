package com.sinvon.goldfoilfontapi.service.impl;

import com.sinvon.goldfoilfontapi.service.GoldFoilService;
import com.sinvon.goldfoilfontapi.strategy.impl.GoldFoilHtmlGenerationStrategy;
import com.sinvon.goldfoilfontapi.strategy.impl.GoldFoilImageGenerationStrategy;
import com.sinvon.goldfoilfontapi.strategy.impl.GoldFoilSvgGenerationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * GoldFoilService的实现类
 *
 * @author : sinvon
 * @since :  2024/12/8 上午1:12
 */

@Service
public class GoldFoilServiceImpl implements GoldFoilService {

    @Autowired
    private GoldFoilImageGenerationStrategy imageStrategy;
    @Autowired
    private GoldFoilSvgGenerationStrategy svgStrategy;
    @Autowired
    private GoldFoilHtmlGenerationStrategy htmlStrategy;

    @Override
    public File getGoldFoilImage(String text, String gradientPos, String fontColorType) {
        return imageStrategy.generate(text, gradientPos, fontColorType);
    }

    @Override
    public File getGoldFoilSvg(String text, String gradientPos, String fontColorType) {
        return svgStrategy.generate(text, gradientPos, fontColorType);
    }

    @Override
    public File getGoldFoilHtml(String text, String gradientPos, String fontColorType) {
        return htmlStrategy.generate(text, gradientPos, fontColorType);
    }
}
