package com.will.book.algorithm.chapter01;

/**
 * Created by 2YSP on 2017/9/25.
 */
public class Demo1 {

    public static void main(String[] args) {
        printOut(76234);
    }

    /**
     * 递归练习：n%10 = n - [n/10]*10,[x]是小于或等于x的最大整数
     * 打印出正整数N
     * @param n
     */
    public static void printOut(int n ){
        if (n >= 10){
            printOut(n/10);
        }
        printDigit(n%10);

    }

    private static void printDigit(int i) {
        System.out.println(i);
    }
}
