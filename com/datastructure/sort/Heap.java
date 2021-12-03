package com.datastructure.sort;

import java.util.Arrays;

public class Heap {
    public static void main(String[] args) {
        int arr[] = { 4, 6, 8, 5, 9 };
        Heap Func = new Heap();
        System.out.println(Arrays.toString(arr));
        Func.Sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void Sort(int[] arr) {
        // 将数组变成大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; --i) {
            heapify(arr, i, arr.length);
        }
        // 将堆顶元素与末尾元素交换，堆尺寸-1，再调整堆
        for (int j = arr.length - 1; j > 0; --j) {
            swapArr(0, j, arr);
            heapify(arr, 0, j);
        }
    }

    private void swapArr(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 将一个顺序二叉树（数组）调整为一个大顶堆
    private void heapify(int[] arr, int i, int len) {
        int temp = arr[i]; // 保存该节点
        int leftChild = 2 * i + 1; // 左孩子
        while (leftChild < len) {
            // 先找出左右孩子里最大的对于下标
            int large = leftChild + 1 < len && arr[leftChild + 1] > arr[leftChild] ? 
                        leftChild + 1 : leftChild;
            // 左孩子和父结点中更大的那个
            large = arr[large] > temp ? large : i;
            if (large == i) break; // 父亲大就直接退出，完成
            // 否则，更大的那个孩子应该被放在父亲的位置
            arr[i] = arr[large];
            // 接下来要以large位置为头继续进行调整
            i = large;
            leftChild = 2 * i + 1;
        }
        arr[i] = temp; // ！！！！最终停下来的这个位置i，用来存放最开始的头节点
    }
}
