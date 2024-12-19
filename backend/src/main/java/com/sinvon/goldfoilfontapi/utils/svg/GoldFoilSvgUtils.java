package com.sinvon.goldfoilfontapi.utils.svg;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 烫金字体SVG工具类
 *
 * @author : sinvon
 * @since :  2024/12/8 上午2:04
 */
public class GoldFoilSvgUtils {

    /**
     * 将SVG文件转换为字符串
     *
     * @param svgFilePath SVG文件路径
     * @return SVG文件内容
     */
    public static String svgFileToString(String svgFilePath) {
        try {
            File svgFile = new File(svgFilePath);
            return new String(Files.readAllBytes(svgFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建HTML页面，嵌入SVG内容
     *
     * @param svgContent SVG内容
     * @return HTML页面
     */
    public static String SvgTextToHtml(String svgContent) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>\n");
        htmlBuilder.append("<html lang=\"en\">\n");
        htmlBuilder.append("<head>\n");
        htmlBuilder.append("    <meta charset=\"UTF-8\">\n");
        htmlBuilder.append("    <title>SVG in HTML</title>\n");
        htmlBuilder.append("</head>\n");
        htmlBuilder.append("<body>\n");
        htmlBuilder.append("    ").append(svgContent).append("\n"); // 嵌入 SVG 内容
        htmlBuilder.append("</body>\n");
        htmlBuilder.append("</html>");
        return htmlBuilder.toString();
    }
}
