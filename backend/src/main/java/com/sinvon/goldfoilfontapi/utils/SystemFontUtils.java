package com.sinvon.goldfoilfontapi.utils;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统字体工具类，用于获取系统中可用的字体名称和文件路径。
 *
 * @author : sinvon
 * @since :  2024/12/14 下午1:10
 */
public class SystemFontUtils {

    /**
     * 获取系统中可用的字体名称列表。
     *
     * @return 字体名称数组
     */
    public static String[] getSystemFonts() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        return ge.getAvailableFontFamilyNames(); // 获取字体家族名称列表
    }

    /**
     * 根据字体名称获取字体文件路径。
     *
     * @param fontName 字体名称
     * @return 字体文件绝对路径，如果未找到则返回 null
     */
    public static String getFontPathByName(String fontName) {
        Map<String, String> fontMap = getSystemFontsWithPaths();
        return fontMap.getOrDefault(fontName, null);
    }

    /**
     * 获取系统中所有字体及其文件路径的映射。
     *
     * @return 字体名称与文件路径的映射表
     */
    private static Map<String, String> getSystemFontsWithPaths() {
        Map<String, String> fontMap = new HashMap<>();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();

        // 获取系统字体目录（根据操作系统）
        String[] fontDirs = getFontDirectories();

        // 遍历字体目录寻找匹配的字体文件
        for (String dir : fontDirs) {
            File fontDir = new File(dir);
            if (fontDir.exists() && fontDir.isDirectory()) {
                for (File file : fontDir.listFiles()) {
                    if (file.getName().toLowerCase().endsWith(".ttf") || file.getName().toLowerCase().endsWith(".otf")) {
                        try {
                            // 读取字体文件并匹配名称
                            Font font = Font.createFont(Font.TRUETYPE_FONT, file);
                            String fontName = font.getFontName();
                            if (!fontMap.containsKey(fontName)) {
                                fontMap.put(fontName, file.getAbsolutePath());
                            }
                        } catch (Exception ignored) {
                            // 忽略无法解析的字体文件
                        }
                    }
                }
            }
        }

        // 确保未匹配到路径的字体也在结果中
        for (String fontName : fontNames) {
            fontMap.putIfAbsent(fontName, null);
        }

        return fontMap;
    }

    /**
     * 获取操作系统对应的字体目录列表。
     *
     * @return 字体目录路径数组
     */
    private static String[] getFontDirectories() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return new String[]{"C:\\Windows\\Fonts"};
        } else if (os.contains("mac")) {
            return new String[]{"/System/Library/Fonts", "/Library/Fonts"};
        } else if (os.contains("nix") || os.contains("nux")) {
            return new String[]{"/usr/share/fonts", "~/.fonts"};
        } else {
            return new String[]{};
        }
    }

    // 测试用例
    // public static void main(String[] args) {
    //     String microsoftYaHei = SystemFontUtils.getFontPathByName("等线");
    //     System.out.println(microsoftYaHei);
    // }
}
