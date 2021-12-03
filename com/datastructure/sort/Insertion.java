package com.datastructure.sort;

import java.util.Arrays;

public class Insertion {
    public static void main(String[] args) {
        int[] arr = { 4, 2, 5, 8, 1, 53, 1, 0, 7, 2 };
        System.out.println(Arrays.toString(arr));
        Insertion Func = new Insertion();
        Func.Sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void swapArr(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void Sort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; --j) {
                swapArr(j, j - 1, arr);
            }
        }
    }
}
