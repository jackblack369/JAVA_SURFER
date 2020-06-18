//package com.dll.jni;
//
//import org.xvolks.jnative.JNative;
//import org.xvolks.jnative.Type;
//import org.xvolks.jnative.exceptions.NativeException;
//
///**
// * 功能描述 :
// *
// * @author dongwei
// * 2018/6/29 16:41
// **/
//public class JniTest2 {
//
//    private static final int TA_CheckQpwd(int accountno, String qpwd)throws NativeException, IllegalAccessException {
//        JNative n = null;
//        try {
//            n = new JNative("AIO_API.dll", "TA_CheckQpwd");
//            n.setRetVal(Type.INT);
//            n.setParameter(0, accountno);
//            n.setParameter(1, qpwd);
//            n.invoke();
//            return Integer.parseInt(n.getRetVal());
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return 2;
//    }
//
//    public static final int TestCheck(int accountno, String qpwd) throws NativeException, IllegalAccessException{
////        String myLibraryPath = System.getProperty("user.dir");
////        System.setProperty("java.library.path", myLibraryPath);
//
//                System.loadLibrary("AIO_API");
////        System.load("D:/Java/JAVA_SURFER/DLL/AIO_API.dll");
////        System.load("C:/Windows/System32/AIO_API.dll");
//        return TA_CheckQpwd(accountno,qpwd);
//    }
//
//
//
//
//    public static void main(String[] args) throws NativeException, IllegalAccessException{
//        int accountno = 263616;
//        String qpwd = "134527";
//
//        int info = TestCheck(accountno,qpwd);
//
//        if (info == 0) {
//            System.out.println("验证成功！");
//        } else {
//            System.out.println("验证失败！");
//        }
//    }
//}
