package com.sinvon.goldfoilfontapi.utils;

import com.sinvon.goldfoilfontapi.config.ProjectConfig;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * 春联背景图片生成工具
 *
 * @author sinvon
 * @since 2024年12月9日16:44:56
 */
public class SpringCoupletBackgroundImageUtils {

    private static Random rand = new Random();

    public static void createSpringCoupletBackgroundImage(int width, int height) {
        ProjectConfig projectConfig = new ProjectConfig();

        try {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            // 设置抗锯齿和渲染提示
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 填充背景色（随机选择颜色）
            g2d.setColor(randomColor());
            g2d.fillRect(0, 0, width, height);

            // 绘制随机元素
            drawRandomElements(g2d);

            // 释放图形资源
            g2d.dispose();

            // 背景图片的全路径名
            String backgroundImagePathAndFilename = projectConfig.backgroundImagePath + File.separator + projectConfig.backgroundImageFilename + ".png";
            // 确保背景图片保存路径存在
            FileUtils.ensureFile(backgroundImagePathAndFilename);
            // 保存为 PNG 图像
            ImageIO.write(image, "png", new File(backgroundImagePathAndFilename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 随机选择颜色
    private static Color randomColor() {
        // 可以根据需要定制颜色池
        Color[] colors = {
                new Color(0xF2DA83), new Color(0xF7D53B), new Color(0xBC2C2B), new Color(0x960204),
                new Color(0xDA7E0C), new Color(0xF39094), new Color(0xE7163F), new Color(0xF0CB46)
        };
        return colors[rand.nextInt(colors.length)];
    }

    // 随机绘制图案
    private static void drawRandomElements(Graphics2D g2d) {
        // 随机选择吉祥符号
        if (rand.nextBoolean()) {
            drawLuckySymbol(g2d);
        }

        // 随机选择动植物
        if (rand.nextBoolean()) {
            drawAnimalOrPlant(g2d);
        }

        // 随机选择象征物品
        if (rand.nextBoolean()) {
            drawSymbolicItems(g2d);
        }

        // 随机选择自然景象
        if (rand.nextBoolean()) {
            drawNatureScenes(g2d);
        }

        // 随机选择节日特色
        if (rand.nextBoolean()) {
            drawFestivalElements(g2d);
        }

        // 随机绘制吉祥的动物（如龙、凤、鱼、蝙蝠等）
        if (rand.nextBoolean()) {
            drawLuckyAnimals(g2d);
        }
    }

    // 绘制吉祥符号：例如"福"字的图形（不包含文字）
    private static void drawLuckySymbol(Graphics2D g2d) {
        g2d.setColor(new Color(0xF39094)); // 红色
        g2d.fillRoundRect(100, 100, 100, 100, 50, 50); // 绘制类似的吉祥符号形状
    }

    // 绘制动植物图案（例如梅花）
    private static void drawAnimalOrPlant(Graphics2D g2d) {
        g2d.setColor(new Color(0xF0CB46)); // 黄色
        g2d.fillOval(300, 200, 100, 100); // 绘制梅花
    }

    // 绘制象征物品（例如金元宝）
    private static void drawSymbolicItems(Graphics2D g2d) {
        g2d.setColor(new Color(0xDA7E0C)); // 橙色
        g2d.fillRoundRect(500, 300, 100, 100, 50, 50); // 绘制金元宝
    }

    // 绘制自然景象（例如山水画）
    private static void drawNatureScenes(Graphics2D g2d) {
        g2d.setColor(new Color(0xBC2C2B)); // 红色
        g2d.drawLine(0, 500, 800, 500); // 绘制简单的山水线条
        g2d.drawLine(0, 520, 800, 520); // 绘制山水的另一层线条
    }

    // 绘制节日特色（例如烟花）
    private static void drawFestivalElements(Graphics2D g2d) {
        g2d.setColor(new Color(0xF39094)); // 粉红色
        g2d.fillOval(600, 400, 50, 50); // 绘制烟花
        g2d.fillOval(650, 420, 50, 50); // 绘制另一颗烟花
    }

    // 绘制吉祥的动物（例如龙、凤、蝙蝠等）
    private static void drawLuckyAnimals(Graphics2D g2d) {
        // 绘制一只简化版的龙（象征龙的形状）
        g2d.setColor(new Color(0x960204)); // 深红色
        g2d.fillOval(200, 400, 100, 100); // 绘制龙的身体

        // 绘制一只简化版的凤凰
        g2d.setColor(new Color(0xF2DA83)); // 黄色
        g2d.fillOval(400, 400, 100, 100); // 绘制凤凰的身体

        // 绘制简化版的蝙蝠
        g2d.setColor(new Color(0xF77B6A)); // 橙色
        g2d.fillArc(600, 300, 100, 100, 0, 180); // 绘制蝙蝠翅膀
    }
}
