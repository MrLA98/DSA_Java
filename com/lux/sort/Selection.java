package com.lux.sort;

import java.util.Arrays;

public class Selection {
    public static void main(String[] args) {
        int[] arr = { 3, 5, 2, 7, 1, 5, 3, 6, 2 };
        Selection Func = new Selection();
        System.out.println("before sort: " + Arrays.toString(arr));
        Func.Sort(arr);
        System.out.println("after sort: " + Arrays.toString(arr));
    }

    public void Sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            int min = i;
            for (int j = i + 1; j < arr.length; ++j) {
                min = arr[j] < arr[min] ? j : min;
            }
            swapArr(min, i, arr);
        }
    }

    public void swapArr(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
