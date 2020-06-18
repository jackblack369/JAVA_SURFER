package com.file;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * @author dongwei
 * 2018/8/22 11:19
 **/
public class FileNameUtil {



    public static void main(String[] args) {
        File file = new File("D:\\Temp\\match\\20180512195343000707.png");
        String fileName = file.getName();
        String result=  fileName.substring(0, fileName.lastIndexOf("."));

        String fileNameWithOutExt = FilenameUtils.removeExtension(fileName);
        System.out.println(fileNameWithOutExt);
    }
}
