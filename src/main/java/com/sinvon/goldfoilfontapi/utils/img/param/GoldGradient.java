package com.sinvon.goldfoilfontapi.utils.img.param;

import com.sinvon.goldfoilfontapi.enums.GradientPositionType;

import java.awt.*;
import java.util.Random;

/**
 * 黄金渐变参数
 *
 * @author : sinvon
 * @since :  2024/12/7 下午11:18
 */
public class GoldGradient {

    /**
     * 创建改进的渐变效果
     * @param width 宽度
     * @param height 高度
     * @return 渐变
     */
    public static Paint createGoldGradient(int width, int height) {
        Color[] colors = {
                Color.decode("#c2a52e"),
                Color.decode("#e8c657"),
                Color.decode("#ead26d"),
                Color.decode("#eed46f"),
                Color.decode("#c0a03a"),
                Color.decode("#d9b35b"),
                Color.decode("#f8e17c")
        };

        // 修正 fractions 数组，使长度与 colors 匹配
        float[] fractions = {0f, 0.17f, 0.34f, 0.51f, 0.68f, 0.85f, 1f};

        // 返回指定的光泽的渐变
        // return new LinearGradientPaint(
        //         // 0, 0, width, height, // 从左上角到右下角
        //         // (float) width / 2, 0, (float) width / 2, height, // 从顶部中心到底部中心
        //         // 0, (float) height / 2, width, (float) height / 2, // 从左侧中心到右侧中心
        //         // (float) width / 4, (float) height / 4, (float) (width * 3) / 4, (float) (height * 3) / 4, // 从中间集中到一个小区域
        //         Math.min(width, height) / 2.0f, // 渐变半径
        //         fractions, // 修正后的 fractions
        //         colors,
        //         MultipleGradientPaint.CycleMethod.REFLECT
        // );

        // 随机生成渐变的起始点和终止点
        Random random = new Random();
        int x1 = random.nextInt(width / 2);
        int y1 = random.nextInt(height / 2);
        int x2 = width / 2 + random.nextInt(width / 2);
        int y2 = height / 2 + random.nextInt(height / 2);

        // 返回随机光泽的渐变
        return new LinearGradientPaint(
                x1, y1, x2, y2, // 随机起始点和终止点
                fractions,
                colors,
                MultipleGradientPaint.CycleMethod.REFLECT
        );
    }
}
