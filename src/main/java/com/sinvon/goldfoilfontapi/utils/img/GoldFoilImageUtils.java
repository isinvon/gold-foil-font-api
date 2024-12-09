package com.sinvon.goldfoilfontapi.utils.img;

import com.sinvon.goldfoilfontapi.utils.img.param.BrushTexture;
import com.sinvon.goldfoilfontapi.utils.img.param.GoldGradient;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 金梅毛碑文字GoldFoilFont
 *
 * @author : sinvon
 * @since : 2024/12/7 上午12:51
 */
public class GoldFoilImageUtils {

    public static BufferedImage createGoldFoilImage(String text, String gradientPos) {
        int width = 800;
        int height = 300;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // 设置抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 设置背景为白色
        // g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // 加载书法字体
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("三极泼墨体.ttf"));
            font = font.deriveFont(150f); // 设置字体大小
            g2d.setFont(font);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return image;
        }

        // 创建渐变填充，使用指定颜色范围
        Paint gradient = GoldGradient.createGoldGradient(width, height, gradientPos);
        g2d.setPaint(gradient);

        // 绘制文字
        g2d.drawString(text, 50, 200);

        // 叠加改进的纹理效果
        TexturePaint texture = BrushTexture.createImprovedBrushTexture(width, height, 150);
        g2d.setPaint(texture);
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f)); // 控制纹理覆盖比例
        g2d.drawString(text, 50, 200);

        g2d.dispose();
        return image;
    }
}
