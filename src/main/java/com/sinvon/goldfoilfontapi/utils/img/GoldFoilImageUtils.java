package com.sinvon.goldfoilfontapi.utils.img;

import com.sinvon.goldfoilfontapi.enums.FontColorType;
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

    /**
     * 创建金梅毛碑文字图片
     * @param text 文本
     * @param gradientPos 渐变位置
     * @return 图片
     */
    public static BufferedImage createGoldFoilImage(String text, String gradientPos, String fontColorType) {
        int padding = 50; // 给文本左右留出一定的间距
        int height = 300;

        // 创建一个临时图片来测量文字宽度
        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D tempG2d = tempImage.createGraphics();

        // 加载书法字体
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("三极泼墨体.ttf"));
            font = font.deriveFont(150f); // 设置字体大小
            tempG2d.setFont(font);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return tempImage;
        }

        // 获取字体度量对象
        FontMetrics fontMetrics = tempG2d.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text); // 计算文本宽度

        tempG2d.dispose();

        // 计算图片宽度（加上左右间距）
        int width = textWidth + padding * 2;

        // 创建最终图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // 设置抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 设置背景为透明
        g2d.fillRect(0, 0, width, height);

        // 设置字体
        g2d.setFont(font);

        // 创建渐变填充，使用指定颜色范围
        Paint gradient = GoldGradient.createGoldGradient(width, height, gradientPos);
        g2d.setPaint(gradient);

        // 绘制文字（水平居中绘制）
        int textX = padding;
        int textY = (height + fontMetrics.getAscent() - fontMetrics.getDescent()) / 2; // 垂直居中
        g2d.drawString(text, textX, textY);

        // 叠加改进的纹理效果
        TexturePaint texture = BrushTexture.createImprovedBrushTexture(width, height, 150);
        g2d.setPaint(texture);
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f)); // 控制纹理覆盖比例
        g2d.drawString(text, textX, textY);

        g2d.dispose();
        return image;
    }
}
