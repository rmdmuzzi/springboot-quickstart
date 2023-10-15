package com.raymond.quickstart.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.io.*;
import java.util.function.Predicate;

/**
 * File util
 *
 * @author raymondmuzzi
 * @since 2023-10-14 12:48:17
 */
public class FileUtil {

    public void copy(File from, File to) {
        copyR(from, to, file ->
                StringUtils.isNotBlank(file.getName()) && file.getName().endsWith("jpg"));
    }

    private void copyR(File file, File to, Predicate<File> predicate) {
        // end condition
        if (!file.isDirectory() && predicate.test(file)) {
            // target file
            String targetFileFullPath = to.getAbsolutePath() + File.separator + file.getName();
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFileFullPath))) {
                int len;
                byte[] buffer = new byte[2048];
                while ((len = bis.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Copy file: %s success!", file.getName()));
        }
        else {
            File[] files = file.listFiles();
            if (files != null && file.length() > 0) {
                for (File fileR : files) {
                    copyR(fileR, to, predicate);
                }
            }
        }
    }
}
