package com.algorithm.sort;

import java.util.Arrays;

public class Quick {
    public static void main(String[] args) {
        int[] arr = { 4, 2, 5, 8, 1, 53, 1, 0, 7, 2 };
        System.out.println(Arrays.toString(arr));
        Quick Func = new Quick();
        Func.Sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void Sort(int[] arr) {
        split(0, arr.length - 1, arr);
    }

    private void swapArr(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 分割，[L,R] -- 左右都闭
    private void split(int L, int R, int[] arr) {
        if (L >= R)
            return;
        swapArr(L + (int) (Math.random() * (R - L + 1)), R, arr);
        int[] mid = pivot(L, R, arr);
        split(L, mid[0] - 1, arr);
        split(mid[1] + 1, R, arr);
    }

    // 荷兰国旗问题 -- 左中右
    private int[] pivot(int L, int R, int[] arr) {
        int pivot = arr[R];
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[L] < pivot) { // 比轴小
                swapArr(++less, L++, arr);
            } else if (arr[L] > pivot) { // 比轴大
                swapArr(--more, L, arr);
            } else
                ++L; // 和轴相等
        }
        swapArr(more, R, arr);
        return new int[] { less + 1, more };
    }
}
