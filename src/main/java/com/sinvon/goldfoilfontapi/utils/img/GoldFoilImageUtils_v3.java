package com.sinvon.goldfoilfontapi.utils.img;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author : sinvon
 * @description : 金梅毛碑文字GoldFoilFont
 * @since : 2024/12/7 上午12:51
 */
public class GoldFoilImageUtils_v3 {

    public static BufferedImage createGoldFoilImage(String text) {
        int width = 800;
        int height = 300;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // 设置抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 设置背景为白色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // 加载书法字体
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("金梅毛碑楷.ttf"));
            font = font.deriveFont(150f); // 设置字体大小
            g2d.setFont(font);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return image;
        }

        // 创建渐变填充，使用指定颜色范围
        Paint gradient = createGoldGradient(width, height);
        g2d.setPaint(gradient);

        // 绘制文字
        g2d.drawString(text, 50, 200);

        // 叠加改进的纹理效果
        TexturePaint texture = createImprovedBrushTexture(width, height, 100);
        g2d.setPaint(texture);
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f)); // 控制纹理覆盖比例
        g2d.drawString(text, 50, 200);

        g2d.dispose();
        return image;
    }

    // 创建改进的渐变效果
    private static Paint createGoldGradient(int width, int height) {
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

        return new LinearGradientPaint(
                0, 0, width, height,
                fractions, // 修正后的 fractions
                colors,
                MultipleGradientPaint.CycleMethod.REFLECT
        );
    }

    private static TexturePaint createImprovedBrushTexture(int width, int height, int intensity) {
        BufferedImage textureImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = textureImage.createGraphics();

        // 提高随机性的贴图生成
        Random random = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int alpha = random.nextInt(intensity + 1); // 确保在0到intensity范围内
                alpha = Math.min(255, alpha); // 确保alpha在合法范围
                int gray = random.nextInt(256);
                Color color = new Color(gray, gray, gray, alpha);
                textureImage.setRGB(x, y, color.getRGB());
            }
        }
        g2d.dispose();

        return new TexturePaint(textureImage, new Rectangle(0, 0, width, height));
    }

    // 生成 Perlin 噪声
    private static double generatePerlinNoise(double x, double y) {
        int n = (int) x + (int) y * 57;
        n = (n << 13) ^ n;
        return (1.0 - ((n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0);
    }
}
