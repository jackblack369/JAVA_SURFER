//package com.dll.jni;
//
//import org.xvolks.jnative.exceptions.NativeException;
//
//import java.io.File;
//
//public class Demo {
//    public int getInfo() throws NativeException, IllegalAccessException {
//
//        String path = getClass().getResource(File.separator).getPath();
//        path = path.substring(1, path.length());
//        System.out.println(path);   //得到DLL文件的路径
//
//        String ip = "192.168.0.48"; //服务器IP
//        int port = 221;             //端口
//        int intrcpt = 1;            //数据压缩方式传送,1为采用;0为不采用
//        //方法1 传入Dll文件的路径
//        //int info = AppSvrTestConnect.TestConnectFromDllPath(path, ip, port, intrcpt);
//
//        //方法2 Dll文件已经放在JRE\bin目录下面
//        int info = JniTest.TestConnectFromDllPath(ip, port, intrcpt);
//
//        //1为成功，0为失败
//        if (info == 1) {
//            System.out.println("应用服务器可用。");
//        } else {
//            System.out.println("应用服务器不可用，请检查IP地址和端口是否正确。");
//        }
//
//        return info;
//    }
//}