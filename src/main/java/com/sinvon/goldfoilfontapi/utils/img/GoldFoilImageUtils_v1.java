package com.sinvon.goldfoilfontapi.utils.img;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author : sinvon
 * @ClassName : GoldFoilImage_v1
 * @Description : 烫金效果图片
 * @since : 2024年12月7日00:54:48
 */
public class GoldFoilImageUtils_v1 {
    // 创建烫金效果的图片
    public static BufferedImage createGoldFoilImage(String text) {
        int width = 600;
        int height = 200;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // 设定背景为白色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // 设置字体
        Font font = new Font("Arial", Font.BOLD, 50);
        g2d.setFont(font);

        // 创建金色的渐变
        GradientPaint goldGradient = new GradientPaint(0, 0, Color.YELLOW, width, height, Color.ORANGE);
        g2d.setPaint(goldGradient);

        // 绘制文本
        g2d.drawString(text, 50, 100);

        g2d.dispose();
        return image;
    }
}
