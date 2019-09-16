package com.ysjt.exam.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.UUID;

public class FileUtils {

    private FileUtils() {
        throw new IllegalStateException("Utility class");
    }


    public static String readFileToString(File file) throws IOException {
        return readFileToString(file,"UTF-8");
    }

    public static String readFileToString(File file,String encoding) throws IOException {
        return org.apache.commons.io.FileUtils.readFileToString(file,encoding);
    }

    public static String readFileToString(File file, Charset charset) throws IOException {
        return org.apache.commons.io.FileUtils.readFileToString(file,charset);
    }

    public static String generateUUIDFileName(String extension) {
        return String.format("%s.%s", UUID.randomUUID().toString(), extension);
    }

    public static File generateUUIDFile(String extension) {
        return new File(generateUUIDFileName(extension));
    }

    public static boolean exist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static void copyFile(String srcPath, String destPath) throws IOException {
        copyFile(new File(srcPath), new File(destPath));
    }

    public static void copyFile(File srcFile, File destFile) throws IOException {
        org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);
    }

    /**
     * Url转换为文件file
     * @param url
     * @return
     */
    public static File copyFile(String url,File tempFile) throws IOException {
        URL imageUrl = new URL(url);
        org.apache.commons.io.FileUtils.copyURLToFile(imageUrl,tempFile);
        return tempFile;
    }

    public static String getFileExtension(String fileName) {
        return fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0 ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;
    }

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        return getFileExtension(fileName);
    }

    public static String getFileExtensionIncludePoint(String fileName) {
        return "." + getFileExtension(fileName);
    }

    public static String getFileExtensionIncludePoint(File file) {
        return "." + getFileExtension(file);
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        return org.apache.commons.io.FileUtils.readFileToByteArray(file);
    }

    public static void forceMkdir(File file) throws IOException {
        org.apache.commons.io.FileUtils.forceMkdir(file);
    }

    public static void forceMkdirParent(File file) throws IOException {
        org.apache.commons.io.FileUtils.forceMkdirParent(file);
    }

    public static void forceDelete(File file) throws IOException {
        if (file.exists()) {
            org.apache.commons.io.FileUtils.forceDelete(file);
        }

    }

    public static void transferTo(MultipartFile multipartFile, File dest) throws IOException {
        forceMkdirParent(dest);
        multipartFile.transferTo(dest);
    }

    public static File transferToUUIDFile(MultipartFile multipartFile, String destPath) throws IOException {
        String extension = getFileExtension(multipartFile.getOriginalFilename());
        String uuidFileName = generateUUIDFileName(extension);
        File dest = new File(destPath, uuidFileName);
        transferTo(multipartFile, dest);
        return dest;
    }


}
