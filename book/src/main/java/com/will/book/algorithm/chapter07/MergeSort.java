package com.will.book.algorithm.chapter07;

/**
 * 归并排序
 * Created by 2YSP on 2018/1/1.
 */
public class MergeSort {
    /**
     * Internal method that makes recursive calls
     *
     * @param a         an array of Comparable items
     * @param tmpArray  an array to place the merged result
     * @param left      the left-most index of the subarray.
     * @param right     the right-most index of the subarray.
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a, AnyType[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType[] a) {
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);

        //打印
        for (AnyType anyType : a) {
            System.out.print(anyType + ",");
        }
    }

    /**
     * Internal method that merges two sorted halves of a subarray
     *
     * @param a
     * @param tmpArray
     * @param leftPos
     * @param rightPos
     * @param rightEnd
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>> void
    merge(AnyType[] a, AnyType[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        //Main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }

        while (leftPos <= leftEnd) {//copy rest of first half
            tmpArray[tmpPos++] = a[leftPos++];
        }

        while (rightPos <= rightEnd) {//copy rest of right half
            tmpArray[tmpPos++] = a[rightPos++];
        }

        //Copy tmpArray back
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmpArray[rightEnd];
        }
    }

    public static void main(String[] args) {
        Integer[] a = {8, 34, 10, 89, 23};
        mergeSort(a);
    }
}
