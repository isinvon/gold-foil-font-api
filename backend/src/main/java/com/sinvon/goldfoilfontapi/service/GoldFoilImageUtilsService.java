package com.sinvon.goldfoilfontapi.service;

import com.sinvon.goldfoilfontapi.strategy.context.GoldFoilGenerationContext;

import java.awt.image.BufferedImage;

/**
 * @author : sinvon
 * @since :  2024/12/9 下午5:46
 */
public interface GoldFoilImageUtilsService {
    /**
     * 生成金 foil 图片
     * @param context 上下文对象
     * @return 图片
     */
    public BufferedImage createGoldFoilImage(GoldFoilGenerationContext context);
}
