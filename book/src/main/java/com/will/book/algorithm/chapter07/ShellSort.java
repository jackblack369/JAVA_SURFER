package com.will.book.algorithm.chapter07;

/**
 * 使用希尔增量的希尔排序
 * Created by 2YSP on 2018/1/1.
 */
public class ShellSort {

    public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType[] a) {
        int j;
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                AnyType tmp = a[i];
                for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;

            }
        }

        //打印
        for (AnyType anyType : a) {
            System.out.print(anyType + ",");
        }
    }

    public static void main(String[] args) {
        Integer[] a = {8, 34, 10, 89, 23};
        shellSort(a);
    }
}
