package com.will.book.algorithm.chapter04;

import java.nio.BufferUnderflowException;

/**
 * Created by 2YSP on 2017/12/2.
 * 二叉查找树
 */
public class BinarySearchThree<AnyTpe extends Comparable<? super AnyTpe>> {

    /********二叉树节点类********/
    private static class BinaryNode<AnyType>{
        BinaryNode(AnyType theElement){
            this(theElement,null,null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt , BinaryNode<AnyType> rt){
            this.element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element; // the data in the node
        BinaryNode<AnyType> left;// left child
        BinaryNode<AnyType> right; // right child
    }

    private BinaryNode<AnyTpe> root;

    public BinarySearchThree(){
        root = null;
    }

    public void makeEmpty(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(AnyTpe x){
        return contains(x,root);
    }

    public AnyTpe findMin(){
        if (isEmpty()){
            throw  new BufferUnderflowException();
        }
        return findMin(root).element;
    }

    private AnyTpe findMax(){
        if (isEmpty()){
            throw new BufferUnderflowException();
        }
        return findMax(root).element;
    }

    public void insert(AnyTpe x){
        root = insert(x,root);
    }

    public void remove(AnyTpe x){
        root = remove(x,root);
    }

    public void printThree(){
        if(isEmpty()){
            System.out.println("Empty tree");
        }else {
            printTree(root);
        }
    }


    /**
     * Internal method to find an item in a subtree
     * @param x is item to search for
     * @param t the node that roots the subtree
     * @return true if the item is found , false otherwise
     */
    private boolean contains(AnyTpe x, BinaryNode<AnyTpe> t) {
        if(t == null){
            return false;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0){
            return contains(x,t.left);
        }else if(compareResult > 0){
            return contains(x,t.right);
        }
        return true;//match2
    }

    /**
     * Internal method to find the smallest item in a subtree
     * @param t the node that roots the subtree
     * @return node containing the smallest item
     */
    private BinaryNode<AnyTpe> findMin(BinaryNode<AnyTpe> t){
        if( t == null){
            return null;
        }else if(t.left == null){
            return t;
        }
        return  findMin(t.left);//一直向左找,递归实现
    }


    private BinaryNode<AnyTpe> findMax(BinaryNode<AnyTpe> t){
        if ( t != null){
            while (t.right != null){//非递归实现
                t = t.right;
            }
        }
        return t;
    }

    /**
     * Internal method to insert into a subtree
     * @param x the item to insert
     * @param t the node that roots the subtree
     * @return the new root of the subtree
     */
    private BinaryNode<AnyTpe> insert(AnyTpe x,BinaryNode<AnyTpe> t){
        if (t == null){
            return new BinaryNode<>(x,null,null);
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult > 0){
            t.right = insert(x,t.right);
        }else if(compareResult < 0){
            t.left = insert(x,t.left);
        }else {
            //Duplicate do nothing
        }
        return t;
    }

    /**
     * Internal method to remove from a subtree
     * @param x the item to remove
     * @param t the node that roots the subtree
     * @return the new root of the subtree
     */
    private BinaryNode<AnyTpe> remove(AnyTpe x,BinaryNode<AnyTpe> t){
        if(t == null){
            return null;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0){
            t.left = remove(x,t.left);
        }else if (compareResult > 0){
            t.right = remove(x,t.right);
        }else if (t.right != null && t.left != null){// two children
            t.element = findMin(t.right).element;
            t.right = remove(t.element,t.right);
        }else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private void printTree(BinaryNode<AnyTpe> t){
        if (t != null){
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }


}
