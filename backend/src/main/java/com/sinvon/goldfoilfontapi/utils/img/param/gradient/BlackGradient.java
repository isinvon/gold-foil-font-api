package com.sinvon.goldfoilfontapi.utils.img.param.gradient;

import com.sinvon.goldfoilfontapi.enums.GradientPositionType;

import java.awt.*;
import java.util.Random;

/**
 * 黑色渐变参数
 *
 * @author : sinvon
 * @since : 2024年12月9日14:42:32
 */
public class BlackGradient {

    /**
     * 创建改进的渐变效果（黑色光泽渐变）
     *
     * @param width       宽度
     * @param height      高度
     * @param gradientPos 渐变位置类型
     * @return 渐变
     */
    public static Paint createBlackGradient(int width, int height, String gradientPos) {
        // 定义黑色调色板
        Color[] colors = {
                Color.decode("#000000"), // 纯黑
                Color.decode("#1a1a1a"), // 极深灰
                Color.decode("#333333"), // 深灰
                Color.decode("#4d4d4d"), // 中深灰
                Color.decode("#666666"), // 中灰
                Color.decode("#808080"), // 浅灰
                Color.decode("#999999")  // 非常浅的灰
        };

        // fractions 数组与 colors 对应
        float[] fractions = {0f, 0.15f, 0.3f, 0.45f, 0.6f, 0.8f, 1f};
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
