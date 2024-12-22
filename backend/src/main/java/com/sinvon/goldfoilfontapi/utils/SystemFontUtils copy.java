// package com.sinvon.goldfoilfontapi.utils;

// import java.awt.*;
// import java.io.File;
// import java.util.HashMap;
// import java.util.Map;

// /**
//  * 系统字体工具类，用于获取系统中可用的字体名称和文件路径。
//  *
//  * @author : sinvon
//  * @since : 2024/12/14 下午1:10
//  */
// public class SystemFontUtils {

//     /**
//      * 获取系统中可用的字体名称列表。
//      *
//      * @return 字体名称数组
//      */
//     public static String[] getSystemFonts() {
//         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//         return ge.getAvailableFontFamilyNames(); // 获取字体家族名称列表
//     }

//     /**
//      * 根据字体名称获取字体文件路径。
//      *
//      * @param fontName 字体名称
//      * @return 字体文件绝对路径，如果未找到则返回 null
//      */
//     public static String getFontPathByName(String fontName) {
//         Map<String, String> fontMap = getSystemFontsWithPaths();
//         return fontMap.getOrDefault(fontName, null);
//     }

//     /**
//      * 获取系统中所有字体及其文件路径的映射。
//      *
//      * @return 字体名称与文件路径的映射表
//      */

//     private static Map<String, String> getSystemFontsWithPaths() {
//         Map<String, String> fontMap = new HashMap<>();
//         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//         String[] fontNames = ge.getAvailableFontFamilyNames();

//         // 获取系统字体目录
//         String[] fontDirs = getFontDirectories();

//         // 遍历字体目录并加载字体文件
//         for (String dir : fontDirs) {
//             File fontDir = new File(dir);
//             if (fontDir.exists() && fontDir.isDirectory()) {
//                 for (File file : fontDir.listFiles()) {
//                     if (file.getName().toLowerCase().endsWith(".ttf")
//                             || file.getName().toLowerCase().endsWith(".otf")) {
//                         try {
//                             // 加载字体
//                             Font font = Font.createFont(Font.TRUETYPE_FONT, file);
//                             String fileFontName = font.getFontName().trim().toLowerCase(); // 强制小写以消除大小写问题

//                             System.out.println("加载字体: " + fileFontName + " | 文件路径: " + file.getAbsolutePath()); // debug

//                             // 增强匹配逻辑，忽略大小写
//                             for (String systemFontName : fontNames) {
//                                 if (systemFontName.trim().toLowerCase().equals(fileFontName)) {
//                                     fontMap.put(systemFontName, file.getAbsolutePath());
//                                     break;
//                                 }
//                             }
//                         } catch (Exception e) {
//                             System.out.println("无法加载字体: " + file.getAbsolutePath() + " | 错误: " + e.getMessage()); // debug
//                         }
//                     }
//                 }
//             }
//         }

//         // 添加未匹配的字体
//         for (String fontName : fontNames) {
//             fontMap.putIfAbsent(fontName, null);
//         }

//         return fontMap;
//     }

//     /**
//      * 获取操作系统对应的字体目录列表。
//      *
//      * @return 字体目录路径数组
//      */
//     private static String[] getFontDirectories() {
//         String os = System.getProperty("os.name").toLowerCase();
//         if (os.contains("win")) {
//             return new String[] { "C:\\Windows\\Fonts" };
//         } else if (os.contains("mac")) {
//             return new String[] { "/System/Library/Fonts", "/Library/Fonts" };
//         } else if (os.contains("nix") || os.contains("nux")) {
//             return new String[] {
//                     "/usr/share/fonts",
//                     "/usr/share/fonts/truetype",
//                     "/usr/share/fonts/opentype",
//                     "/usr/share/fonts/opentype/urw-base35",
//                     System.getProperty("user.home") + "/.fonts",
//                     "/usr/share/fonts/truetype/ubuntu",
//                     "/usr/share/fonts/truetype/dejavu",
//                     "/usr/share/fonts/truetype/droid",
//                     "/usr/share/fonts/truetype/noto",
//                     "/usr/share/fonts/truetype/freefont" // 添加可能存储 FreeMono 的路径
//             };
//         } else {
//             return new String[] {};
//         }
//     }

//     // 测试用例
//     /**
//      * 主方法，用于测试字体路径获取功能。
//      *
//      * @param args 命令行参数
//      */
//     public static void main(String[] args) {
//         // ====== System font list ======
//         String[] fonts = SystemFontUtils.getSystemFonts();
//         System.out.println("系统可用字体列表:");
//         for (String font : fonts) {
//         System.out.println(font);
//         }
//         // ====== find font path by fontName======
//         System.out.println("=========== test ==========");
//         // 测试字体名称
//         // String testFontName = "FreeMono";
//         String testFontName = "FreeSerifBoldItalic";
//         System.out.println("======= 正在查找字体: " + testFontName + "=============");

//         // 获取字体路径
//         String fontPath = SystemFontUtils.getFontPathByName(testFontName);

//         // 输出结果
//         if (fontPath != null) {
//             System.out.println("字体路径: " + fontPath);
//         } else {
//             System.out.println("未找到字体: " + testFontName);
//         }
//     }
// }
