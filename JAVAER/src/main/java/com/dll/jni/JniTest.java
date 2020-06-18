//package com.dll.jni;
//
//import org.xvolks.jnative.JNative;
//import org.xvolks.jnative.Type;
//import org.xvolks.jnative.exceptions.NativeException;
//
//public class JniTest {
//
//    /**
//     * 测试应用服务器连接状态
//     *
//     *  TestConnect
//     * @param ip 应用服务器IP
//     * @param port 端口
//     * @param intrcpt  是否采用数据压缩方式 1 :true 0:false
//     * @return int 1 :成功 0:失败
//     * @throws NativeException
//     * @throws IllegalAccessException
//     */
//    private static final int TestConnect(String ip, int port, int intrcpt)throws NativeException, IllegalAccessException {
//        JNative n = null;
//        try {
//            n = new JNative("TestAppSvr.dll", "TestConnect");
//            n.setRetVal(Type.INT);
//            int i = 0;
//            n.setParameter(i++, Type.STRING, ip);
//            n.setParameter(i++, Type.INT, "" + port);
//            n.setParameter(i++, Type.INT, "" + intrcpt);
//            n.invoke();
//            return Integer.parseInt(n.getRetVal());
//        } finally {
//            if (n != null)
//                n.dispose();
//        }
//    }
//    /**
//     * 指定Dll文件路径,动态加载本地链接库,测试应用服务器连接状态
//     * setDllPath
//     * @param path Dll文件的路径,不包含DLL名称 例如：windows - d:\test\test\ unix - root/test/test/
//     * @param ip 应用服务器IP
//     * @param port 端口
//     * @param intrcpt  是否采用数据压缩方式 1 :true 0:false
//     * @return int 1 :成功 0:失败
//     * @throws NativeException
//     * @throws IllegalAccessException
//     */
//    public static final int TestConnectFromDllPath(String path,String ip, int port, int intrcpt) throws NativeException, IllegalAccessException{
//        path += "TestAppSvr.dll";
//        System.load(path);
//        return TestConnect(ip,port,intrcpt);
//    }
//    /**
//     * Dll文件放在JRE\bin目录下面，ClassLoader就能通过System.loadLibrary()动态加载本地链接库
//     * TestConnectFromDllPath
//     * @param ip 应用服务器IP
//     * @param port 端口
//     * @param intrcpt  是否采用数据压缩方式 1 :true 0:false
//     * @return int 1 :成功 0:失败
//     * @throws NativeException
//     * @throws IllegalAccessException
//     */
//    public static final int TestConnectFromDllPath(String ip, int port, int intrcpt) throws NativeException, IllegalAccessException{
//        System.loadLibrary("TestAppSvr");
//        return TestConnect(ip,port,intrcpt);
//    }
//}
