package com.will.book.algorithm.chapter05;

/**
 * Created by 2YSP on 2017/12/24.
 * 不用链表的散列表：平方探测法
 */
public class QuadraticProbingHashTable<AnyType> {
    private static final int DEFAULT_TABLE_SIZE = 11;
    private HashEntry<AnyType>[] array;//the array of elements
    private int currentSize;// the number of occupied cells

    private static class HashEntry<AnyType> {

        public AnyType element;
        public boolean isActive;// false if marked deleted

        public HashEntry(AnyType e) {
            this(e, true);
        }

        public HashEntry(AnyType e, boolean b) {
            element = e;
            isActive = b;
        }
    }

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    /**
     * find an item in the hash table
     *
     * @param x the item to search for
     * @return the matching item
     */
    public boolean contains(AnyType x) {
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    /**
     * Method that performs quadratic probing resolution
     *
     * @param x
     * @return
     */
    private int findPos(AnyType x) {
        int offset = 1;
        int currentPos = myhash(x);
        //条件很经典：如果当前位置有东西，并且不等于它就继续寻找,且条件顺序不可更改
        while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length) {
                currentPos -= array.length;
            }
        }

        return currentPos;
    }

    public void insert(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) {
            return;
        }

        array[currentPos] = new HashEntry<AnyType>(x, true);

        if (currentSize > array.length / 2) {
            rehash();
        }
    }

    /**
     * 扩展 hash table
     */
    private void rehash() {
        HashEntry<AnyType>[] oldArray = array;
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;

        //copy table over
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null && oldArray[i].isActive) {
                insert(oldArray[i].element);
            }
        }
    }

    public void remove(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) {
            array[currentPos].isActive = false;
        }
    }

    private void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    /**
     * allocate 分配
     *
     * @param size
     */
    private void allocateArray(int size) {
        array = new HashEntry[nextPrime(size)];
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

    private int myhash(AnyType x) {
        int hashVal = x.hashCode();

        hashVal %= array.length;//取模

        if (hashVal < 0) {
            hashVal += array.length;
        }

        return hashVal;
    }


}
