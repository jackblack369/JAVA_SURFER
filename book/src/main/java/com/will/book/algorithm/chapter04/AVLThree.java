package com.will.book.algorithm.chapter04;

/**
 * Created by 2YSP on 2017/12/23.
 * 带有
 */
public class AVLThree<AnyTpe extends Comparable<? super AnyTpe>> {
    
    private static class AvlNode<AnyType>{

        AvlNode(AnyType theElement){
            this(theElement,null,null);
        }

        AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
            this.element = theElement;
            this.left = lt;
            this.right = rt;
            this.height = 0;
        }

        AnyType element;//the data in the node
        AvlNode<AnyType> left;//left child
        AvlNode<AnyType> right;//right child
        int             height;// height
    }

    /**
     *Return the height of node t ,or -1,if null
     */
    private int height(AvlNode<AnyTpe> t){
        return t == null ? -1 : t.height;
    }

    /**
     * 插入子树，返回新的根节点
     * @param x
     * @param t
     * @return
     */
    private AvlNode<AnyTpe> insert(AnyTpe x,AvlNode<AnyTpe> t){
        if (t == null){
            return new AvlNode<>(x,null,null);
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0){
            t.left = insert(x,t.left);
        }else if (compareResult > 0){
            t.right = insert(x,t.right);
        }else {
            //重复 什么也不做
        }
        //重新平衡
        return balance(t);
    }
    //条件:左子树和右子树的高度最多差1
    private static final int ALLOWED_IMBALANCE = 1;

    private AvlNode<AnyTpe> balance(AvlNode<AnyTpe> t){
        if (t == null){
            return t;
        }

        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE){
            if (height(t.left.left) >= height(t.left.right)){
                // 左-左 单旋转
                t = rotateWithLeftChild(t);
            }else {
                //左-右 双旋转
                t = doubleWithLeftChild(t);
            }
        }else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE){
            if (height(t.right.right) >= height(t.right.left)){
                // 右-右 单旋转
                t = rotateWithRightChild(t);
            }else {
                //右-左 双旋转
                t = doubleWithRightChild(t);
            }
        }

        t.height = Math.max(height(t.left),height(t.right))+1;
        return t;
    }

    //先左旋转再右旋转
    private AvlNode<AnyTpe> doubleWithRightChild(AvlNode<AnyTpe> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    //单右旋转
    private AvlNode<AnyTpe> rotateWithRightChild(AvlNode<AnyTpe> k1) {
        AvlNode<AnyTpe> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k2.height = Math.max(height(k1.left),height(k1.right))+1;
        k1.height = Math.max(height(k2.right),k1.height)+1;
        return null;
    }

    /**
     * 先右旋转在左旋转
     * @param k3
     * @return
     */
    private AvlNode<AnyTpe> doubleWithLeftChild(AvlNode<AnyTpe> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Rotate binary tree node with left child
     * return new root
     * @param k2
     * @return
     */
    private AvlNode<AnyTpe> rotateWithLeftChild(AvlNode<AnyTpe> k2) {
        AvlNode<AnyTpe> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left),height(k2.right))+1;
        k1.height = Math.max(height(k1.left),k2.height)+1;//k2 就是k1的右子树
        return k1;
    }

    /**
     * 删除节点
     * @param x
     * @param t
     * @return
     */
    private AvlNode<AnyTpe> remove(AnyTpe x,AvlNode<AnyTpe> t){
        if (t == null){
            return t;
        }

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0){
            t.left = remove(x,t.left);
        }else if (compareResult > 0){
            t.right = remove(x,t.right);
        }else if (t.left != null && t.right != null){// two children
            t.element = findMin(t.right).element;//从右子树中找出最小的节点替换当前要删除的节点
            t.right = remove(t.element,t.right);//删除右子树中需要拿出替换的节点
        }else {
            t = t.left != null ? t.left : t.right;
        }
        return balance(t);
    }

    private AvlNode<AnyTpe> findMin(AvlNode<AnyTpe> t){
//        if( t == null){
//            return null;
//        }else if(t.left == null){
//            return t;
//        }
//        return  findMin(t.left);//一直向左找,递归写法

        if(t != null){//非递归写法
            while (t.left != null){
                t = t.left;
            }
        }
        return t;
    }
}
