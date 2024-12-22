// package com.sinvon.goldfoilfontapi.utils;

// import java.io.File;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// /**
//  * 系统字体工具类，用于获取系统中可用的字体名称和文件路径。
//  *
//  * @author : sinvon
//  * @since : 2024/12/14 下午1:10
//  */
// public class SystemFontUtilsBak {

//     /**
//      * 获取系统中可用的字体名称列表。
//      *
//      * @return 字体名称数组
//      */
//     public static String[] getSystemFonts() {
//         List<String> fontNames = new ArrayList<>();

//         // 获取系统字体目录
//         String[] fontDirs = getFontDirectories();

//         // 遍历字体目录并加载字体文件
//         for (String dir : fontDirs) {
//             File fontDir = new File(dir);
//             if (fontDir.exists() && fontDir.isDirectory()) {
//                 for (File file : fontDir.listFiles()) {
//                     if (file.getName().toLowerCase().endsWith(".ttf")) {
//                         // 去除 .ttf 后缀
//                         String fontName = file.getName().substring(0, file.getName().length() - 4);
//                         fontNames.add(fontName);
//                     }
//                 }
//             }
//         }

//         // 转换为数组并返回
//         return fontNames.toArray(new String[0]);
//     }

//     /**
//      * 根据字体名称获取字体文件路径。
//      *
//      * @param fontName 字体名称
//      * @return 字体文件绝对路径，如果未找到则返回 null
//      */
//     public static String getFontPathByName(String fontName) {
//         // 获取系统字体目录
//         String[] fontDirs = getFontDirectories();

//         // 遍历字体目录并查找匹配的字体文件
//         for (String dir : fontDirs) {
//             File fontDir = new File(dir);
//             if (fontDir.exists() && fontDir.isDirectory()) {
//                 for (File file : fontDir.listFiles()) {
//                     // 如果文件是 .ttf 字体文件，去除后缀并与 fontName 比较
//                     if (file.getName().toLowerCase().endsWith(".ttf")) {
//                         String fileFontName = file.getName().substring(0, file.getName().length() - 4).toLowerCase();
//                         if (fileFontName.equals(fontName.toLowerCase())) {
//                             return file.getAbsolutePath();
//                         }
//                     }
//                 }
//             }
//         }
//         // 如果没有找到，返回 null
//         return null;
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
//     // public static void main(String[] args) {
//     // // getSystemFonts测试用例
//     // // String[] font = SystemFontUtilsBak.getSystemFonts();
//     // // System.out.println(Arrays.toString(font));

//     // // String path =
//     // SystemFontUtilsBak.getFontPathByName("UbuntuSansMono[wght]");
//     // // System.out.println(path);
//     // }
// }
