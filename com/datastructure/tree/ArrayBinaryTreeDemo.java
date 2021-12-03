package com.datastructure.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        ArrayBinaryTree abTree = new ArrayBinaryTree(arr);
        abTree.preOrder(0);
    }
}

class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("empty tree");
            return;
        }
        System.out.println(arr[index]);
        if ((2 * index + 1) < arr.length)
            preOrder(2 * index + 1);
        if ((2 * index + 2) < arr.length)
            preOrder(2 * index + 2);
    }
}
