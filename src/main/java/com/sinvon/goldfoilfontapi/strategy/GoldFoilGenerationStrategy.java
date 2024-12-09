package com.sinvon.goldfoilfontapi.strategy;

import java.io.File;

/**
 * 策略接口
 *
 * @author : sinvon
 * @since :  2024/12/9 下午12:06
 */
public interface GoldFoilGenerationStrategy {
    File generate(String text, String gradientPos);
}
