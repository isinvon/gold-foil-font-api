package com.sinvon.goldfoilfontapi.service.impl;

import com.sinvon.goldfoilfontapi.service.GoldFoilService;
import com.sinvon.goldfoilfontapi.strategy.context.GoldFoilGenerationContext;
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
    public File getGoldFoilImage(GoldFoilGenerationContext context) {
        return imageStrategy.generate(context);
    }

    @Override
    public File getGoldFoilSvg(GoldFoilGenerationContext context) {
        return svgStrategy.generate(context);
    }

    @Override
    public File getGoldFoilHtml(GoldFoilGenerationContext context) {
        return htmlStrategy.generate(context);
    }
}
