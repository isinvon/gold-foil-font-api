package com.sinvon.goldfoilfontapi.utils.svg;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * PNG 转 SVG 工具类
 *
 * @author sinvon
 * @since 2024年12月8日02:21:08
 */
public class PngToSvgUtils {

    /**
     * 将PNG转换为SVG
     *
     * @param inputPngPath  输入的png路径
     * @param outputSvgPath 输出的svg路径
     * @return Boolean
     * @throws Exception 抛出异常
     */
    public static Boolean createSvgByPng(String inputPngPath, String outputSvgPath) {
        // 步骤1：读取PNG文件
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(inputPngPath));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // 步骤2：转换为BufferedImage对象
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        bufferedImage.createGraphics().drawImage(image, 0, 0, null);

        // 步骤3：将BufferedImage对象转换为SVG格式
        // 创建SVG文档
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // 创建SVG画布
        SVGGraphics2D svg = new SVGGraphics2D(document);

        // 设置SVG画布的大小
        svg.setSVGCanvasSize(new java.awt.Dimension(image.getWidth(), image.getHeight()));

        // 绘制图像到SVG
        svg.drawImage(bufferedImage, 0, 0, null);

        // 设置SVG视图框架 (viewBox)
        String viewBox = "0 0 " + image.getWidth() + " " + image.getHeight();
        document.getDocumentElement().setAttribute("viewBox", viewBox);
        document.getDocumentElement().setAttribute("width", String.valueOf(image.getWidth()));
        document.getDocumentElement().setAttribute("height", String.valueOf(image.getHeight()));

        // 判断输出目录，如果不存在则创建输出目录
        File file = new File(outputSvgPath);
        if (!file.exists()) {
            // 创建父目录
            file.getParentFile().mkdirs();
        }

        // 步骤4：保存为SVG文件
        try (OutputStream outputStream = new FileOutputStream(outputSvgPath);
             Writer out = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            svg.stream(out, true); // 使用CSS样式
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 使用示例:
    // public static void main(String[] args) throws Exception {
    //     String inputPngPath = "D:\\gold-foil-font-api\\image\\gold-foil-image.png";
    //     String outputSvgPath = "D:\\gold-foil-font-api\\svg\\gold-foil-image.svg";
    //
    //     // 创建输出目录
    //     File outputFile = new File(outputSvgPath);
    //     if (!outputFile.getParentFile().exists()) {
    //         outputFile.getParentFile().mkdirs();
    //     }
    //     System.out.println("Input PNG path: " + inputPngPath);
    //     System.out.println("Output SVG path: " + outputSvgPath);
    //     boolean result = createSvgByPng(inputPngPath, outputSvgPath);
    //     System.out.println("SVG creation result: " + result);
    // }
}
