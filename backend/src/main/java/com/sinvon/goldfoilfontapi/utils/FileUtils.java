package com.sinvon.goldfoilfontapi.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件工具类
 *
 * @author : sinvon
 * @since :  2024/12/9 下午12:09
 */

public class FileUtils {
    /**
     * 确保文件存在的方法
     *
     * @param filePath 文件路径
     * @return 文件对象
     */
    public static File ensureFile(String filePath) {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    /**
     * 将内容写入指定文件（覆盖写入）
     *
     * @param filePath 文件路径
     * @param content  写入的内容
     */
    public static File writeToFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(ensureFile(filePath))) {
            writer.write(content);
            return new File(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file: " + filePath, e);
        }
    }
}
