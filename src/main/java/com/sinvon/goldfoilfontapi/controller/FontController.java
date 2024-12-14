package com.sinvon.goldfoilfontapi.controller;

import com.sinvon.goldfoilfontapi.utils.FontResourceUtil;
import com.sinvon.goldfoilfontapi.utils.SystemFontUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * 字体接口
 *
 * @author : sinvon
 * @since :  2024/12/14 下午1:09
 */

@RestController
@RequestMapping("/font")
public class FontController {

    @Autowired
    private FontResourceUtil fontResourceUtil;

    /**
     * 获取系统字体
     *
     * @return 字体数组
     */
    @GetMapping("/getSystemFonts")
    public String[] getSystemFonts() {
        return SystemFontUtils.getSystemFonts();
    }

    @GetMapping("/fontIsExist")
    public ResponseEntity<String> getFont(@RequestParam String fontType) {
        try {
            String fontPath = SystemFontUtils.getFontPathByName(fontType);
            File fontFile = fontResourceUtil.getFontFileFromPath(fontPath);

            if (fontFile != null) {
                // 成功处理文件，返回字体文件信息
                return ResponseEntity.ok("Font file retrieved successfully");
            } else {
                // 返回错误信息
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Font file not found");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while retrieving the font");
        }
    }
}
