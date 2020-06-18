package com;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class JavaGeneric {

    @Test
    public void test1() {
        //限定通配符的上边界
        ArrayList<? extends Number> collection1 = new ArrayList<Integer>();//编译通过
        //! ArrayList<? extends Number > collection2= new ArrayList<String>();//编译不通过

        //限定通配符的下边界
        ArrayList<? super Integer> collection3 = new ArrayList<Number>();//编译通过
        //! ArrayList<? super Integer > collection4= new ArrayList<String>();//编译不通过
    }

    /**
     * 而java的泛型基本上完全在编译器中实现，用于编译器执行类型检查和类型判断，
     * 然后生成普通的非泛型的字节码，这种实现技术为“擦除”(erasure)。
     * <p>
     * 泛型是提供给javac编译器使用的，限定集合的输入类型，
     * 编译器编译带类型说明的集合时会去掉“类型”信息。
     */
    @Test
    public void testType() {
        ArrayList<Integer> collection1 = new ArrayList<Integer>();
        ArrayList<String> collection2 = new ArrayList<String>();
        //两者class类型一样,即字节码一致
        System.out.println(collection1.getClass() == collection2.getClass());

        //class均为java.util.ArrayList,并无实际类型参数信息
        System.out.println(collection1.getClass().getName());
        System.out.println(collection2.getClass().getName());
    }

    /**
     * 使用反射可跳过编译器，往某个泛型集合加入其它类型数据。
     *
     * 只有引用类型才能作为泛型方法的实际参数(注意)
     */
    @Test
    public void genericTest() {
        swap(new String[]{"111","222"},0,1);//编译通过
        //！swap(new int[]{1,2},0,1); 编译不通过,因为int不是引用类型
        swap(new Integer[]{1,2},0,1);//编译通过
    }

    /**
     * 交换数组a的第i个和第j个元素
     * fixme 这里返回类型void之前的 <T> 起到了修饰该方法为一个泛型方法的作用
     *
     * <T>这个T是个修饰符的功能，表示是个泛型方法，就像有static修饰的方法是个静态方法一样。
     * 另外
     * <T> 不是返回值，是因为你传入的参数有个T，它是泛型，所以在public后面要加个<T>，表示传入参数有泛型。
     * 如果没有<T>,会提示 "cannot resolve symbol 'T'"
     *
     */
    public static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 基本类型有时可以作为实参，因为有自动装箱和拆箱。
     */
    public void test2(){
        testType();
        int a = biggerOne(3,5);
        //int 和 double,取交为Number
        Number b = biggerOne(3,5.5);
        //String和int 取交为Object
        Object c = biggerOne("1",2);
    }

    //从x,y中返回y
    public static <T> T biggerOne(T x,T y){
        return y;
    }
}
