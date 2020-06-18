package main.java.com.dll.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * 功能描述 : 测试java调用dll
 *
 * @author dongwei
 * 2018/6/29 11:11
 **/
public class JnaTest {
    public interface TestDll extends Library {
//    public interface TestDll extends StdCallLibrary {
//        TestDll INSTANCE = (TestDll)Native.loadLibrary("DLL",TestDll.class);

        void TA_Init ();

        int TA_CheckQpwd(int accountno,String qpwd);
    }

    public static void main(String[] args) {

//        System.out.println(System.getProperty("java.version"));//输出当前jdk版本号
//        System.out.println(System.getProperty("sun.arch.data.model"));//输出当前jdk所用平台


        int accountno = 263616;
        String qpwd = "134527";
        TestDll INSTANCE = Native.loadLibrary("AIO_API",TestDll.class);
//        TestDll INSTANCE = (TestDll)Native.loadLibrary("D:/Java/JAVA_SURFER/AIO_API.dll",TestDll.class);
        INSTANCE.TA_Init();
        int isSuccess = INSTANCE.TA_CheckQpwd(accountno,qpwd);
        System.out.printf("验证结果:"+isSuccess);
    }
}
