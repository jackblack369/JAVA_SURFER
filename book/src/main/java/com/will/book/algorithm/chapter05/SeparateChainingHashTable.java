package com.will.book.algorithm.chapter05;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 2YSP on 2017/12/24.
 * 分离链接法 散列
 */
public class SeparateChainingHashTable<AnyType> {

    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<AnyType>[] theLists;//右list集合构成的数组
    private int currentSize;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }
    }

    /**
     * insert into the hash table.If the item is already present,
     * then do nothing.
     *
     * @param x the item to insert
     */
    public void insert(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (!whichList.contains(x)) {
            whichList.add(x);

            if (++currentSize > theLists.length) {
                rehash();//扩容
            }
        }
    }

    /**
     * Remove from hash table
     *
     * @param x
     */
    public void remove(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    public boolean contains(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }

    /**
     * Make the hash table logically empty
     */
    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        currentSize = 0;
    }


    private void rehash() {
        List<AnyType>[] oldLists = theLists;
        //create new double-sized,empty table
        theLists = new List[nextPrime(2 * theLists.length)];
        for (int j = 0; j < theLists.length; j++) {
            theLists[j] = new LinkedList<>();
        }

        //copy table over 数据复制过去
        currentSize = 0;
        for (List<AnyType> list : oldLists) {
            for (AnyType item : list) {
                insert(item);
            }
        }
    }

    private int myhash(AnyType x) {
        int hashVal = x.hashCode();

        hashVal %= theLists.length;//取模

        if (hashVal < 0) {
            hashVal += theLists.length;
        }

        return hashVal;
    }


    /**
     * Internal method to find a prime number(素数) at least as large as n
     *
     * @param n the starting number (must be positive(正数))
     * @return a prime number larger than or equal to n
     */
    private static int nextPrime(int n) {
        if (n % 2 == 0) {
            n++;
        }
        for (; !isPrime(n); n += 2) {

        }
        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     *
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime(int n) {
        if (n == 2 || n == 3) {
            return true;
        }

        if (n == 1 || n % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SeparateChainingHashTable<Integer> H = new SeparateChainingHashTable<>();
        long startTime = System.currentTimeMillis();

        final int NUMS = 2000000;
        final int GAP = 37;

        System.out.println("Checking... (no more output means success)");

        for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
            H.insert(i);
        for (int i = 1; i < NUMS; i += 2)
            H.remove(i);

        for (int i = 2; i < NUMS; i += 2)
            if (!H.contains(i))
                System.out.println("Find fails " + i);

        for (int i = 1; i < NUMS; i += 2) {
            if (H.contains(i))
                System.out.println("OOPS!!! " + i);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Elapsed time: " + (endTime - startTime));
    }
}
