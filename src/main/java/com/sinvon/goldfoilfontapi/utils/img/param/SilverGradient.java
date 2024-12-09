package com.sinvon.goldfoilfontapi.utils.img.param;

/**
 * 银渐变参数
 *
 * @author : sinvon
 * @since :  2024/12/9 下午2:38
 */

import com.sinvon.goldfoilfontapi.enums.GradientPositionType;

import java.awt.*;
import java.util.Random;

/**
 * 银色渐变参数
 *
 * @author : sinvon
 * @since : 2024/12/9 下午11:18
 */
public class SilverGradient {

    /**
     * 创建改进的渐变效果（银色光泽渐变，仿射光）
     *
     * @param width       宽度
     * @param height      高度
     * @param gradientPos 渐变位置类型
     * @return 渐变
     */
    public static Paint createSilverGradient(int width, int height, String gradientPos) {
        // 定义银色调色板
        Color[] colors = {
                Color.decode("#d7d7d7"), // 浅银色
                Color.decode("#e3e3e3"), // 明亮银色
                Color.decode("#c8c8c8"), // 中灰银色
                Color.decode("#f0f0f0"), // 近白银色
                Color.decode("#b5b5b5"), // 深灰银色
                Color.decode("#e0e0e0"), // 亮灰银色
                Color.decode("#ffffff")  // 纯白色，增加光泽
        };

        // fractions 数组与 colors 对应
        float[] fractions = {0f, 0.17f, 0.34f, 0.51f, 0.68f, 0.85f, 1f};
        Random random = new Random();

        // 根据不同的渐变位置类型选择不同的起始和终止点
        switch (gradientPos) {
            case GradientPositionType.LEFT_TO_RIGHT:
                return new LinearGradientPaint(0, 0, width, 0, fractions, colors, MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.TOP_TO_BOTTOM:
                return new LinearGradientPaint(0, 0, 0, height, fractions, colors, MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.LEFT_TOP_TO_RIGHT_BOTTOM:
                return new LinearGradientPaint(0, 0, width, height, fractions, colors, MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.LEFT_BOTTOM_TO_RIGHT_TOP:
                return new LinearGradientPaint(0, height, width, 0, fractions, colors, MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.RIGHT_TO_LEFT:
                return new LinearGradientPaint(width, 0, 0, 0, fractions, colors, MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.BOTTOM_TO_TOP:
                return new LinearGradientPaint(0, height, 0, 0, fractions, colors, MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.RIGHT_TOP_TO_LEFT_BOTTOM:
                return new LinearGradientPaint(width, 0, 0, height, fractions, colors, MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.RIGHT_BOTTOM_TO_LEFT_TOP:
                return new LinearGradientPaint(width, height, 0, 0, fractions, colors, MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.CIRCULAR:
                return new RadialGradientPaint((float) width / 2, (float) height / 2, Math.min(width, height) / 2.0f, fractions, colors, MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.CIRCULAR_RANDOM:
                // 随机生成圆形渐变的中心点
                int centerX = random.nextInt(width);
                int centerY = random.nextInt(height);
                return new RadialGradientPaint(centerX, centerY, Math.min(width, height) / 2.0f, fractions, colors, MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.RANDOM:
                // 随机生成渐变的起始点和终止点
                int x1 = random.nextInt(width / 2);
                int y1 = random.nextInt(height / 2);
                int x2 = width / 2 + random.nextInt(width / 2);
                int y2 = height / 2 + random.nextInt(height / 2);
                return new LinearGradientPaint(x1, y1, x2, y2, fractions, colors, MultipleGradientPaint.CycleMethod.REFLECT);

            default:
                return null;
        }
    }
}

