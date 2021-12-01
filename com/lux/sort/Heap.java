package com.lux.sort;

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
    private void heapify(int arr[], int i, int len) {
        int leftChild = 2 * i + 1;
        int temp = arr[i];
        while (leftChild < len) {
            // 找到i、i的左孩子、右孩子中最大的数的下标large
            int large = leftChild + 1 < len && arr[leftChild + 1] > arr[leftChild] ? leftChild + 1 : leftChild; // 左右中较大的
            large = arr[large] > temp ? large : i;
            // i就是最大的话直接退出
            if (large == i)
                break;
            // 否则，最大的那个应该在i的位置
            arr[i] = arr[large];
            // 要对large所在的子节点为头部的部分进行调整
            i = large;
            leftChild = 2 * i + 1;
        }
        arr[i] = temp;
    }
}
