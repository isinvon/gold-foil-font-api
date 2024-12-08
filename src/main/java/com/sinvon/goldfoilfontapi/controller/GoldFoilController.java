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

    // test
    @GetMapping("test")
    public String test() {
        return "test";
    }

    // 接口2: 返回图片
    @GetMapping("gold-foil-image")
    public ResponseEntity<Resource> getGoldFoilImage(@RequestParam String text, @RequestParam(required = false, defaultValue = GradientPositionType.RANDOM) String gradientPos) {
        File goldFoilImage = goldFoilService.getGoldFoilImage(text, gradientPos);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new org.springframework.core.io.FileSystemResource(goldFoilImage));
    }

    // 接口3: 返回HTML渲染页面
    @GetMapping("/gold-foil-html")
    public ResponseEntity<Resource> getGoldFoilHtml(@RequestParam String text, @RequestParam(required = false, defaultValue = GradientPositionType.RANDOM) String gradientPos) throws IOException {
        File file = goldFoilService.getGoldFoilHtml(text, gradientPos);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(new org.springframework.core.io.FileSystemResource(file));
    }

    // 接口4: 返回SVG格式
    @GetMapping("gold-foil-svg")
    public ResponseEntity<Resource> getGoldFoilSvg(@RequestParam String text, @RequestParam(required = false, defaultValue = GradientPositionType.RANDOM) String gradientPos) {
        File svgFile = goldFoilService.getGoldFoilSvg(text, gradientPos);
        if (svgFile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(new org.springframework.core.io.FileSystemResource(svgFile));
    }
}

