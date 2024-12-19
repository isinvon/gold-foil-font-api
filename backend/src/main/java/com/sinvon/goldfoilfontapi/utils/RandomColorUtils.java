package com.sinvon.goldfoilfontapi.utils;

import java.awt.*;
import java.util.Random;

/**
 * 随机生成颜色工具类
 *
 * @author : sinvon
 * @since :  2024/12/13 下午5:21
 */
public class RandomColorUtils {
    private static final Random RANDOM = new Random();

    /**
     * 生成一个随机的 #222222 格式的 Color 对象
     *
     * @return 随机的 Color 对象
     */
    public static Color generateRandomColor() {
        StringBuilder hexColor = new StringBuilder("#");
        for (int i = 0; i < 6; i++) {
            // 生成一个介于 2 和 9 之间的随机整数
            int randomDigit = RANDOM.nextInt(8) + 2; // 2 到 9
            // 将整数转换为字符并添加到字符串中
            hexColor.append(randomDigit);
        }
        // 将十六进制字符串转换为 Color 对象
        return Color.decode(hexColor.toString());
    }
}
