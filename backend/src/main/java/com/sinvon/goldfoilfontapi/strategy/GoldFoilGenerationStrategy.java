package com.sinvon.goldfoilfontapi.strategy;

import com.sinvon.goldfoilfontapi.strategy.context.GoldFoilGenerationContext;

import java.io.File;

/**
 * 策略接口
 *
 * @author : sinvon
 * @since :  2024/12/9 下午12:06
 */
public interface GoldFoilGenerationStrategy {
    File generate(GoldFoilGenerationContext context);
}
