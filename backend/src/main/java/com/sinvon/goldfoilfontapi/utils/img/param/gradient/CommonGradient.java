package com.sinvon.goldfoilfontapi.utils.img.param.gradient;

import com.sinvon.goldfoilfontapi.enums.GradientPositionType;
import com.sinvon.goldfoilfontapi.utils.img.param.utils.ColorPaletteGeneratorUtils;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * 通用渐变工具类 (生成通用的渐变参数)，调用ColorPaletteGeneratorUtils生成渐变色
 *
 * @author : sinvon
 * @since :  2024/12/13 下午4:07
 */
public class CommonGradient {

    /**
     * 创建通用渐变效果
     *
     * @param width       宽度
     * @param height      高度
     * @param gradientPos 渐变方向
     * @param baseColor   基础颜色，用于生成调色板
     * @return 渐变
     */
    public static Paint createGradient(int width, int height, String gradientPos, String baseColor) {
        // 使用 ColorPaletteGeneratorUtils 生成颜色调色板(总共七个色)
        List<Color> colors = ColorPaletteGeneratorUtils.generateColorPalette(baseColor);

        // 修正 fractions 数组，使长度与 colors 匹配
        float[] fractions = {0f, 0.17f, 0.34f, 0.51f, 0.68f, 0.85f, 1f};
        Random random = new Random();

        // 根据不同的渐变位置类型选择不同的起始和终止点
        switch (gradientPos) {
            case GradientPositionType.LEFT_TO_RIGHT:
                return new LinearGradientPaint(0, 0, width, 0, fractions, colors.toArray(new Color[0]), MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.TOP_TO_BOTTOM:
                return new LinearGradientPaint(0, 0, 0, height, fractions, colors.toArray(new Color[0]), MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.LEFT_TOP_TO_RIGHT_BOTTOM:
                return new LinearGradientPaint(0, 0, width, height, fractions, colors.toArray(new Color[0]), MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.LEFT_BOTTOM_TO_RIGHT_TOP:
                return new LinearGradientPaint(0, height, width, 0, fractions, colors.toArray(new Color[0]), MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.RIGHT_TO_LEFT:
                return new LinearGradientPaint(width, 0, 0, 0, fractions, colors.toArray(new Color[0]), MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.BOTTOM_TO_TOP:
                return new LinearGradientPaint(0, height, 0, 0, fractions, colors.toArray(new Color[0]), MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.RIGHT_TOP_TO_LEFT_BOTTOM:
                return new LinearGradientPaint(width, 0, 0, height, fractions, colors.toArray(new Color[0]), MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.RIGHT_BOTTOM_TO_LEFT_TOP:
                return new LinearGradientPaint(width, height, 0, 0, fractions, colors.toArray(new Color[0]), MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.CIRCULAR:
                return new RadialGradientPaint((float) width / 2, (float) height / 2, Math.min(width, height) / 2.0f, fractions, colors.toArray(new Color[0]), MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.CIRCULAR_RANDOM:
                // 随机生成圆形渐变的中心点
                int centerX = random.nextInt(width);
                int centerY = random.nextInt(height);
                return new RadialGradientPaint(centerX, centerY, Math.min(width, height) / 2.0f, fractions, colors.toArray(new Color[0]), MultipleGradientPaint.CycleMethod.REFLECT);

            case GradientPositionType.RANDOM:
                // 随机生成渐变的起始点和终止点
                int x1 = random.nextInt(width / 2);
                int y1 = random.nextInt(height / 2);
                int x2 = width / 2 + random.nextInt(width / 2);
                int y2 = height / 2 + random.nextInt(height / 2);
                return new LinearGradientPaint(x1, y1, x2, y2, fractions, colors.toArray(new Color[0]), MultipleGradientPaint.CycleMethod.REFLECT);

            default:
                return null;
        }
    }
}