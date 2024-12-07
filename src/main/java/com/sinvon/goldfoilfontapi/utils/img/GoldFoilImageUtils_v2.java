package com.sinvon.goldfoilfontapi.utils.img;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author : sinvon
 * @ClassName : GoldFoilImage_v2
 * @Description :
 * <p>
 * 创建金梅毛笔金 foil 艺术字图片
 * 1. 背景为白色
 * 2. 文字颜色为深金
 * </p>
 * @since :  2024/12/7 上午12:56
 */
public class GoldFoilImageUtils_v2 {
    // 成品版本_v1
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

        // 创建多段渐变颜色（指定的颜色）
        GradientPaint gradient = new GradientPaint(
                0, 0, Color.decode("#c2a52e"), // 深金
                width, height, Color.decode("#f8e17c") // 浅金
        );

        // 创建毛笔纹理效果
        TexturePaint texture = createBrushTexture(g2d, width, height);

        // 将渐变与纹理结合
        g2d.setPaint(gradient);
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.8f)); // 设置透明度叠加效果

        // 绘制文字
        g2d.drawString(text, 50, 200);

        // 叠加纹理
        g2d.setPaint(texture);
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.1f)); // 控制纹理覆盖比例
        g2d.drawString(text, 50, 200);

        g2d.dispose();
        return image;
    }

    // 创建毛笔纹理
    private static TexturePaint createBrushTexture(Graphics2D g2d, int width, int height) {
        BufferedImage texture = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D tg2d = texture.createGraphics();

        // 绘制毛笔纹理效果
        tg2d.setColor(Color.decode("#eed46f"));
        tg2d.fillRect(0, 0, 100, 100);

        tg2d.setColor(Color.decode("#c0a03a"));
        for (int i = 0; i < 100; i += 10) {
            tg2d.drawLine(i, 0, i, 100);
            tg2d.drawLine(0, i, 100, i);
        }

        tg2d.dispose();
        return new TexturePaint(texture, new Rectangle(0, 0, 100, 100));
    }
}
