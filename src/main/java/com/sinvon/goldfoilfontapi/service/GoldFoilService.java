package com.sinvon.goldfoilfontapi.service;

import java.io.File;

/**
 * @author : sinvon
 * @since :  2024/12/8 上午1:12
 */
public interface GoldFoilService {
    public File getGoldFoilImage(String text, String gradientPos, String fontColorType, Boolean isBackground);

    public File getGoldFoilHtml(String text, String gradientPos, String fontColorType, Boolean isBackground);

    public File getGoldFoilSvg(String text, String gradientPos, String fontColorType, Boolean isBackground);
}
