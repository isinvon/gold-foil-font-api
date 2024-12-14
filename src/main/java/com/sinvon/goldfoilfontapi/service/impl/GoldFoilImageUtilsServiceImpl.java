package com.sinvon.goldfoilfontapi.service.impl;

import com.sinvon.goldfoilfontapi.config.ProjectConfig;
import com.sinvon.goldfoilfontapi.enums.FontColorType;
import com.sinvon.goldfoilfontapi.service.GoldFoilImageUtilsService;
import com.sinvon.goldfoilfontapi.service.SpringCoupletBackgroundImageUtilsService;
import com.sinvon.goldfoilfontapi.strategy.context.GoldFoilGenerationContext;
import com.sinvon.goldfoilfontapi.utils.FileUtils;
import com.sinvon.goldfoilfontapi.utils.FontResourceUtil;
import com.sinvon.goldfoilfontapi.utils.RandomColorUtils;
import com.sinvon.goldfoilfontapi.utils.SystemFontUtils;
import com.sinvon.goldfoilfontapi.utils.img.param.gradient.BlackGradient;
import com.sinvon.goldfoilfontapi.utils.img.param.BrushTexture;
import com.sinvon.goldfoilfontapi.utils.img.param.gradient.CommonGradient;
import com.sinvon.goldfoilfontapi.utils.img.param.gradient.GoldGradient;
import com.sinvon.goldfoilfontapi.utils.img.param.gradient.SilverGradient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * GoldFoilFont
 *
 * @author : sinvon
 * @since : 2024/12/7 上午12:51
 */
@Service
public class GoldFoilImageUtilsServiceImpl implements GoldFoilImageUtilsService {

    @Autowired
    private ProjectConfig projectConfig;

    @Autowired
    private FontResourceUtil fontResourceUtil;

    @Autowired
    private SpringCoupletBackgroundImageUtilsService springCoupletBackgroundImageUtilsService;

    /**
     * 创建文字图片
     *
     * @param context 上下文对象
     * @return BufferedImage 图片对象
     */
    public BufferedImage createGoldFoilImage(GoldFoilGenerationContext context) {
        // 注入上下文对象
        String text = context.getText();
        String fontType = context.getFontType();
        String gradientPos = context.getGradientPos();
        String fontColorType = context.getFontColorType();
        String fontCustomColor = context.getFontCustomColor();
        boolean isBackground = context.getIsBackground();
        Boolean isRandomBackground = context.getIsRandomBackground();
        String backgroundColor = context.getBackgroundColor();

        int padding = 50; // 给文本左右留出一定的间距
        int height = 300;

        // 创建一个临时图片来测量文字宽度
        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D tempG2d = tempImage.createGraphics();

        // 加载书法字体
        Font font;
        try {
            if ("三极泼墨体".equals(fontType)) {
                File fontFile = fontResourceUtil.getFontFile("三极泼墨体.ttf");
                font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                font = font.deriveFont(150f); // 设置字体大小
                tempG2d.setFont(font);
            } else {
                String fontPathByName = SystemFontUtils.getFontPathByName(fontType);
                File fontFileFromPath = fontResourceUtil.getFontFileFromPath(fontPathByName);
                font = Font.createFont(Font.TRUETYPE_FONT, fontFileFromPath);
                font = font.deriveFont(150f);
                tempG2d.setFont(font);
            }
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


        // ======== 背景加工判断 ==================start=============
        if (isBackground) { // 如果需要背景
            if (isRandomBackground) { // 如果需要随机背景(随机背景就是春联)
                // 设置背景图片
                try {
                    // 获取背景图片的名字
                    String backgroundImageFilename = projectConfig.backgroundImageFilename;
                    // 获取背景图片的路径
                    String backgroundImagePath = projectConfig.backgroundImagePath;
                    // 拼接成图片的文件路径
                    String backgroundImageFilePath = backgroundImagePath + File.separator + backgroundImageFilename + ".png";
                    // 确保背景图片的路径存在(即文件夹存在)
                    FileUtils.ensureFile(backgroundImageFilePath);
                    // 生成春联背景图片
                    springCoupletBackgroundImageUtilsService.createSpringCoupletBackgroundImage(width, height);
                    // 加载背景图片
                    BufferedImage background = ImageIO.read(new File(backgroundImageFilePath)); // 加载背景图片
                    // 绘制背景图片
                    g2d.drawImage(background, 0, 0, width, height, null); // 绘制背景图片
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else { // 需要背景,但是不随机(即自定义)
                g2d.setColor(Color.decode(backgroundColor));
                g2d.fillRect(0, 0, width, height);
            }

        } else { // 不需要背景
            // 设置背景为透明
            g2d.fillRect(0, 0, width, height);
        }

        // ======== 背景加工判断 =======================end===========

        // 设置字体
        g2d.setFont(font);

        // ======== 字体颜色加工=====================start=============
        switch (fontColorType) {
            case FontColorType.GOLD -> { // 金色字体
                Paint gradient = GoldGradient.createGoldGradient(width, height, gradientPos);
                g2d.setPaint(gradient);
            }
            case FontColorType.BLACK -> { // 纯黑
                g2d.setColor(Color.BLACK);
            }
            case FontColorType.SILVER -> { // 银色字体
                Paint gradient = SilverGradient.createSilverGradient(width, height, gradientPos);
                g2d.setPaint(gradient);
            }
            case FontColorType.BLACK_GRADIENT -> { // 渐变黑
                Paint gradient = BlackGradient.createBlackGradient(width, height, gradientPos);
                g2d.setPaint(gradient);
            }
            case FontColorType.CUSTOM -> { // 自定义颜色
                g2d.setColor(Color.decode(fontCustomColor));
            }
            case FontColorType.CUSTOM_GRADIENT -> { // 自定义渐变色
                Paint gradient = CommonGradient.createGradient(width, height, gradientPos, fontCustomColor);
                g2d.setPaint(gradient);
            }
            case FontColorType.RANDOM -> { // 随机颜色
                Color color = RandomColorUtils.generateRandomColor();
                g2d.setColor(color);
            }
            case FontColorType.RANDOM_GRADIENT -> { // 随机渐变色
                Color color = RandomColorUtils.generateRandomColor();
                Paint gradient = CommonGradient.createGradient(width, height, gradientPos, color.toString());
                g2d.setPaint(gradient);
            }
            default -> { // 默认是金色
                Paint gradient = GoldGradient.createGoldGradient(width, height, gradientPos);
                g2d.setPaint(gradient);
            }
        }
        // ======== 字体颜色加工======================end===============

        // 绘制文字（水平居中绘制）
        int textX = padding;
        int textY = (height + fontMetrics.getAscent() - fontMetrics.getDescent()) / 2; // 垂直居中
        g2d.drawString(text, textX, textY);

        // 叠加改进的纹理效果
        TexturePaint texture = BrushTexture.createImprovedBrushTexture(width, height, 100);
        g2d.setPaint(texture);
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f)); // 控制纹理覆盖比例
        g2d.drawString(text, textX, textY);

        g2d.dispose();
        return image;
    }
}
