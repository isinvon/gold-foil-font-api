package com.sinvon.goldfoilfontapi.service;

import java.awt.image.BufferedImage;

/**
 * @author : sinvon
 * @since :  2024/12/9 下午5:46
 */
public interface GoldFoilImageUtilsService {
    public BufferedImage createGoldFoilImage(String text, String gradientPos, String fontColorType, boolean isBackground);
}
