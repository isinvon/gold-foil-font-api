package com.sinvon.goldfoilfontapi.service;

import com.sinvon.goldfoilfontapi.strategy.context.GoldFoilGenerationContext;

import java.io.File;

/**
 * @author : sinvon
 * @since :  2024/12/8 上午1:12
 */
public interface GoldFoilService {
    public File getGoldFoilImage(GoldFoilGenerationContext context);

    public File getGoldFoilHtml(GoldFoilGenerationContext context);

    public File getGoldFoilSvg(GoldFoilGenerationContext context);
}
