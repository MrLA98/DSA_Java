package com.lux.sort;

import java.util.Arrays;

// 冒泡排序以及其优化
// 相邻两元素逆序就交换
public class Bubble {
    public static void main(String[] args) {
        int[] arr = new int[8];
        for(int i = 0; i < 8; ++i){
            arr[i] = (int)(Math.random()*8000000);
        }
        System.out.println("排序前：" + Arrays.toString(arr));
        Bubble bubble = new Bubble();
        bubble.bubbleSort2(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    // 冒泡排序1
    public void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    swapArr(j, j + 1, arr);
                }
            }
        }
    }

    // 冒泡排序2
    public void bubbleSort2(int[] arr) {
        for (int i = arr.length - 1; i > 0; --i) {
            boolean flag = false; // 标识位
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    flag = true; // 有交换就置为true
                    swapArr(j, j + 1, arr);
                }
            }
            // System.out.println("第"+(arr.length-i)+"次："+Arrays.toString(arr));
            if (!flag)
                break; // 是假说明没交换过，就直接跳出循环
        }
    }

    // 数组内元素交换
    public void swapArr(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}