package com.will.book.algorithm.chapter01;

/**
 * Created by 2YSP on 2017/9/27.
 * 泛型
 */
public class FindMaxDemo {

    public static Comparable findMax(Comparable[] arr){
        int maxIndex = 0;

        for(int i = 0 ; i<arr.length ; i++){
            if (arr[i].compareTo(arr[maxIndex]) > 0){
                maxIndex = i;
            }
        }

        return arr[maxIndex];
    }

    public static void main(String[] args) {
        String[] st1 = {"Joe","Bob","Bill","Zeke"};

        System.out.println(findMax(st1));

        char c1 = 'J';

        char c2 = 'Z';
        System.out.println(c1-c2);
    }
}
