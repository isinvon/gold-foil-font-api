package com.sinvon.goldfoilfontapi.utils.img.param.utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 颜色调色板生成工具
 *
 * <p>
 * >>> 说明: 颜色调色板生成工具，根据输入的基础颜色，生成7个颜色, 颜色区域均在自己的色域中
 * </p>
 * <p>
 * >>> 比如输入(#a1853c)黄色为基础，则生成7个颜色为：
 * #957b37
 * #957b37
 * #957b37
 * #a1853c
 * #b49443
 * #bea052
 * #c5aa64
 * 这些颜色都是相似的, 只是深浅不同, 以此作为渐变色色板
 * </p>
 *
 * @author : sinvon
 * @since :  2024/12/13 下午4:18
 */

public class ColorPaletteGeneratorUtils {

    // public static void main(String[] args) {
    //     String baseColorHex = "#a1853c"; // 输入的基础颜色
    //     List<Color> colorPalette = generateColorPalette(baseColorHex);
    //
    //     // 输出七个颜色的色块
    //     for (Color color : colorPalette) {
    //         printColorBlock(color);
    //     }
    // }

    /**
     * 打印色块到终端
     *
     * @param color 要生成的颜色
     */
    public static void printColorBlock(Color color) {
        // 获取RGB颜色的十六进制表示
        String hexColor = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());

        // ANSI转义序列设置背景色
        String backgroundColor = String.format("\033[48;2;%d;%d;%dm", color.getRed(), color.getGreen(), color.getBlue());
        String reset = "\033[0m"; // 重置颜色
        String block = "    "; // 色块的宽度

        // 打印色块并显示颜色的Hex值
        System.out.println(backgroundColor + block + " " + hexColor + reset);
    }

    /**
     * 生成七个颜色的调色板
     *
     * @param baseColorHex 输入的颜色 (格式要求如: #999292)
     * @return 调色板颜色列表
     */
    public static List<Color> generateColorPalette(String baseColorHex) {
        // 将输入的颜色转换为HSL（色调、饱和度、亮度）
        Color baseColor = Color.decode(baseColorHex);
        float[] hsl = rgbToHsl(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue());

        List<Color> colors = new ArrayList<>();

        // 基于输入颜色生成7个颜色，重点调整亮度（L）和饱和度（S）
        for (int i = -3; i <= 3; i++) {
            // 色调保持不变，调整亮度
            float newLightness = Math.min(1.0f, Math.max(0.4f, hsl[2] + i * 0.05f)); // 控制亮度范围，避免过暗或过亮
            Color newColor = hslToRgb(hsl[0], hsl[1], newLightness);
            colors.add(newColor);
        }

        return colors;
    }

    /**
     * RGB转HSL
     *
     * @param r red
     * @param g green
     * @param b blue
     * @return hsl
     */
    public static float[] rgbToHsl(int r, int g, int b) {
        float red = r / 255f;
        float green = g / 255f;
        float blue = b / 255f;

        float max = Math.max(red, Math.max(green, blue));
        float min = Math.min(red, Math.min(green, blue));
        float h = 0, s = 0, l = (max + min) / 2;

        if (max != min) {
            float delta = max - min;
            s = l > 0.5 ? delta / (2 - max - min) : delta / (max + min);
            if (max == red) {
                h = (green - blue) / delta + (green < blue ? 6 : 0);
            } else if (max == green) {
                h = (blue - red) / delta + 2;
            } else {
                h = (red - green) / delta + 4;
            }
            h /= 6;
        }

        return new float[]{h * 360, s, l};
    }

    /**
     * HSL转RGB
     *
     * @param h 色调
     * @param s 饱和度
     * @param l 亮度
     * @return
     */
    public static Color hslToRgb(float h, float s, float l) {
        float c = (1 - Math.abs(2 * l - 1)) * s;
        float x = c * (1 - Math.abs((h / 60) % 2 - 1));
        float m = l - c / 2;

        float r = 0, g = 0, b = 0;

        if (h >= 0 && h < 60) {
            r = c;
            g = x;
            b = 0;
        } else if (h >= 60 && h < 120) {
            r = x;
            g = c;
            b = 0;
        } else if (h >= 120 && h < 180) {
            r = 0;
            g = c;
            b = x;
        } else if (h >= 180 && h < 240) {
            r = 0;
            g = x;
            b = c;
        } else if (h >= 240 && h < 300) {
            r = x;
            g = 0;
            b = c;
        } else if (h >= 300 && h < 360) {
            r = c;
            g = 0;
            b = x;
        }

        int red = Math.round((r + m) * 255);
        int green = Math.round((g + m) * 255);
        int blue = Math.round((b + m) * 255);

        return new Color(red, green, blue);
    }
}
