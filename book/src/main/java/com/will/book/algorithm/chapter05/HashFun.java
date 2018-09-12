package com.will.book.algorithm.chapter05;

/**
 * Created by 2YSP on 2017/12/24.
 * 散列函数
 */
public class HashFun {

    public static  int hash(String key,int tableSize){
        int hashVal = 0;
        for (int i = 0; i < key.length() ; i++){
            hashVal = hashVal * 37 + key.charAt(i);
        }

        hashVal %= tableSize;
        if (hashVal < 0){
            hashVal += tableSize;
        }

        return hashVal;
    }


    public static void main(String[] args) {
        int hash = hash("hello", 7);
        System.out.println(hash);
        System.out.println(hash("dfdf",7));
    }
}
