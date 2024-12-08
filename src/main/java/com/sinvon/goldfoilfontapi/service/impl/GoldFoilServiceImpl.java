package com.sinvon.goldfoilfontapi.service.impl;

import com.sinvon.goldfoilfontapi.config.ProjectConfig;
import com.sinvon.goldfoilfontapi.service.GoldFoilService;
import com.sinvon.goldfoilfontapi.utils.img.GoldFoilImageUtils;
import com.sinvon.goldfoilfontapi.utils.svg.GoldFoilSvgUtils;
import com.sinvon.goldfoilfontapi.utils.svg.PngToSvgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author : sinvon
 * @since :  2024/12/8 上午1:12
 */

@Service
public class GoldFoilServiceImpl implements GoldFoilService {

    @Autowired
    private ProjectConfig projectConfig;

    /**
     * 创建GoldFoil图片
     *
     * @param text 需要生成的文本
     * @return 图片文件
     */
    @Override
    public File getGoldFoilImage(String text, String gradientPos) {
        try {
            BufferedImage image = GoldFoilImageUtils.createGoldFoilImage(text, gradientPos);
            // 获取图片存储的文件夹
            String imagePath = projectConfig.imagePath;
            // 判断image文件夹是否存在，不存在则创建
            File imageDir = new File(imagePath);
            if (!imageDir.exists()) {
                imageDir.mkdirs();
            }
            // 存放为image/gold-foil-image.png
            File file = new File(imagePath + File.separator + "gold-foil-image.png");
            ImageIO.write(image, "PNG", file);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File getGoldFoilHtml(String text, String gradientPos) {
        // 先创建图片, 然后将图片转换为svg
        String outSvgPath = projectConfig.svgPath + File.separator + "gold-foil-image.svg";
        String imageAbsolutePath = getGoldFoilImage(text, gradientPos).getAbsolutePath();
        Boolean b = PngToSvgUtils.createSvgByPng(imageAbsolutePath, outSvgPath);
        if (b) {
            // 将svg文件形式转换成String形式
            String svgString = GoldFoilSvgUtils.svgFileToString(outSvgPath);
            String htmlString = GoldFoilSvgUtils.SvgTextToHtml(svgString);
            // 将html文件写入到文件
            try {
                File file = new File(projectConfig.htmlPath + File.separator + "gold-foil-image.html");
                // 判断存储HTML的父文件夹是否存在，不存在则创建
                File htmlDir = new File(projectConfig.htmlPath);
                if (!htmlDir.exists()) {
                    htmlDir.mkdirs();
                }
                // 保存到本地
                java.io.FileWriter writer = new java.io.FileWriter(file);
                writer.write(htmlString);
                writer.close();
                return file;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public File getGoldFoilSvg(String text, String gradientPos) {
        String outSvgPath = projectConfig.svgPath + File.separator + "gold-foil-image.svg";
        Boolean b = PngToSvgUtils.createSvgByPng(getGoldFoilImage(text, gradientPos).getAbsolutePath(), outSvgPath);
        if (b) {
            return new File(outSvgPath);
        }
        return null;
    }
}
