package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 功能描述 :
 *
 * @author dongwei
 * 2018/8/9 12:10
 **/
public class FileSizeUtil {

    /**
     * 换算字节（一个汉字占3字节,一个英文字母一个字节）
     *
     * @param size 字节byte数
     * @return
     */
    public static String setSize(long size) {
        //定义GB的计算常量
        int GB = 1024 * 1024 * 1024;
        //定义MB的计算常量
        int MB = 1024 * 1024;
        //定义KB的计算常量
        int KB = 1024;
        //格式化小数
        DecimalFormat df = new DecimalFormat("0.00");
        String resultSize = "";
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = df.format(size / (float) GB) + "GB   ";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = df.format(size / (float) MB) + "MB   ";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = df.format(size / (float) KB) + "KB   ";
        } else {
            resultSize = size + "B   ";
        }
        return resultSize;
    }


    /**
     * 获取文件长度
     *
     * @param file
     */
    public static String getFileSize(File file) {
        if (file.exists() && file.isFile()) {
            String fileName = file.getName();
            long fileLen = file.length();
            String result = setSize(fileLen);
            return result;
        }
        return null;
    }
}
