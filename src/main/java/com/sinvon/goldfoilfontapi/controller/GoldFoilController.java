package com.sinvon.goldfoilfontapi.controller;

import com.sinvon.goldfoilfontapi.enums.GradientPositionType;
import com.sinvon.goldfoilfontapi.service.GoldFoilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private GoldFoilService goldFoilService;

    /**
     * 返回PNG格式
     *
     * @param text        要显示在图像上的文本内容。
     * @param gradientPos 渐变效果的位置，可选值为GradientPositionType中的常量，
     *                    默认值为GradientPositionType.RANDOM。
     * @return 包含生成的PNG图像的ResponseEntity对象。
     */
    @GetMapping("gold-foil-image")
    public ResponseEntity<Resource> getGoldFoilImage(@RequestParam String text, @RequestParam(required = false, defaultValue = GradientPositionType.RANDOM) String gradientPos) {
        File goldFoilImage = goldFoilService.getGoldFoilImage(text, gradientPos);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new org.springframework.core.io.FileSystemResource(goldFoilImage));
    }

    /**
     * 返回HTML渲染页面
     *
     * @param text        要显示在页面上的文本内容。
     * @param gradientPos 渐变效果的位置，可选值为GradientPositionType中的常量，
     *                    默认值为GradientPositionType.RANDOM。
     * @return 包含生成的HTML页面的ResponseEntity对象。
     */
    @GetMapping("/gold-foil-html")
    public ResponseEntity<Resource> getGoldFoilHtml(@RequestParam String text, @RequestParam(required = false, defaultValue = GradientPositionType.RANDOM) String gradientPos) {
        File file = goldFoilService.getGoldFoilHtml(text, gradientPos);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(new org.springframework.core.io.FileSystemResource(file));
    }

    /**
     * 返回SVG格式
     *
     * @param text        要显示在图像上的文本内容。
     * @param gradientPos 渐变效果的位置，可选值为GradientPositionType中的常量，
     *                    默认值为GradientPositionType.RANDOM。
     * @return 包含生成的SVG图像的ResponseEntity对象。
     */
    @GetMapping("gold-foil-svg")
    public ResponseEntity<Resource> getGoldFoilSvg(@RequestParam String text, @RequestParam(required = false, defaultValue = GradientPositionType.RANDOM) String gradientPos) {
        File svgFile = goldFoilService.getGoldFoilSvg(text, gradientPos);
        if (svgFile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(new org.springframework.core.io.FileSystemResource(svgFile));
    }
}

