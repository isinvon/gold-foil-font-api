package com.sinvon.goldfoilfontapi.controller;

import com.sinvon.goldfoilfontapi.config.ProjectConfig;
import com.sinvon.goldfoilfontapi.utils.img.GoldFoilImageUtils_v3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author : sinvon
 * @since :  2024/12/5 下午7:06
 */
@RestController
@RequestMapping("/api")
class GoldFoilController {
    @Autowired
    private ProjectConfig projectConfig;

    // test
    @GetMapping("test")
    public String test() {
        return "test";
    }

    // 烫金效果字体绘制的参数
    @GetMapping("params")
    public ResponseEntity<String> getFontParams(@RequestParam String text) {
        // 这里只是示例，实际中你可以返回更多的参数
        String params = "Text: " + text + ", Font: Gold Foil, Color: Golden";
        return ResponseEntity.ok(params);
    }

    // 接口2: 返回图片
    @GetMapping("gold-foil-image")
    public ResponseEntity<Resource> getGoldFoilImage(@RequestParam String text) throws IOException {
        BufferedImage image = GoldFoilImageUtils_v3.createGoldFoilImage(text);

        String imgPath = projectConfig.imagePath();
        File file = new File(imgPath + "/" + "gold-foil-image.png");
        ImageIO.write(image, "PNG", file);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new org.springframework.core.io.FileSystemResource(file));
    }

    // 接口3: 返回HTML渲染页面
    @GetMapping("/gold-foil-html")
    public String getGoldFoilHtml(@RequestParam String text) throws IOException {
        BufferedImage image = GoldFoilImageUtils_v3.createGoldFoilImage(text);
        String imagePath = "gold-foil-image.png";

        // 保存为文件
        File file = new File(imagePath);
        ImageIO.write(image, "PNG", file);

        // 返回HTML页面
        return "<html><body><h1>Gold Foil Font</h1><img src='/images/gold-foil-image.png' /></body></html>";
    }

    // 接口4: 返回SVG格式
    @GetMapping("gold-foil-svg")
    public ResponseEntity<String> getGoldFoilSvg(@RequestParam String text) {
        String svgContent = createGoldFoilSvg(text);
        return ResponseEntity.ok(svgContent);
    }

    // 接口5: 返回JSON数据
    @GetMapping("gold-foil-json")
    public ResponseEntity<String> getGoldFoilJson(@RequestParam String text) {
        // 假设返回json格式
        String json = "{ \"text\": \"" + text + "\", \"font\": \"Gold Foil\" }";
        return ResponseEntity.ok(json);
    }

    // 创建烫金字体的SVG格式
    private String createGoldFoilSvg(String text) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <svg xmlns="http://www.w3.org/2000/svg" width="800" height="300">
                    <defs>
                        <linearGradient id="gold-gradient" x1="0%" y1="0%" x2="100%" y2="100%">
                            <stop offset="0%" style="stop-color:#c2a52e;stop-opacity:1"/>
                            <stop offset="50%" style="stop-color:#e8c657;stop-opacity:1"/>
                            <stop offset="100%" style="stop-color:#f8e17c;stop-opacity:1"/>
                        </linearGradient>
                        <filter id="gold-glow" x="-50%" y="-50%" width="200%" height="200%">
                            <feGaussianBlur in="SourceAlpha" stdDeviation="4" result="blur"/>
                            <feOffset in="blur" dx="4" dy="4" result="offsetBlur"/>
                            <feMerge>
                                <feMergeNode in="offsetBlur"/>
                                <feMergeNode in="SourceGraphic"/>
                            </feMerge>
                        </filter>
                    </defs>
                    <text x="50" y="200" font-family="Arial" font-size="150" fill="url(#gold-gradient)" filter="url(#gold-glow)">
                        """ + text + """
                    </text>
                </svg>
                """;
    }

}

@Controller
@RequestMapping("/images")
class ImageController {

    // 提供静态图片访问
    @GetMapping("gold-foil-image.png")
    public ResponseEntity<Resource> getImage() {
        File file = new File("gold-foil-image.png");
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new org.springframework.core.io.FileSystemResource(file));
    }
}
