package com.sinvon.goldfoilfontapi.utils.img.param;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 笔刷纹理
 *
 * @author : sinvon
 * @since :  2024/12/7 下午11:23
 */
public class BrushTexture {

    /**
     * 创建改进的笔刷纹理
     *
     * @param width     文字宽度
     * @param height    文字高度
     * @param intensity 笔刷强度 (值越大笔刷越明显,噪点越明显)
     * @return TexturePaint
     */
    public static TexturePaint createImprovedBrushTexture(int width, int height, int intensity) {
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
}
