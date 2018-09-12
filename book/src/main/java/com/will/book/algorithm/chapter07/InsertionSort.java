package com.will.book.algorithm.chapter07;

/**
 * 插入排序(page 187)
 * Created by 2YSP on 2018/1/1.
 */
public class InsertionSort {
    /**
     * Simple insertion sort
     * @param a an array of Comparable items
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {
        int j;

        for (int p = 1; p < a.length; p++) {
            AnyType tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }

        //打印
        for (AnyType anyType : a) {
            System.out.print(anyType + ",");
        }
    }

    public static void main(String[] args) {
        Integer[] a = {8, 34, 10, 89, 23};
        insertionSort(a);
    }
}
