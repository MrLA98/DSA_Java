package com.lux.search;

import java.util.Arrays;

public class Fibonacci {
    public static void main(String[] args) {
        int[] arr = { 1, 1, 1, 1, 1, 3, 5, 7, 9, 9, 9, 9, 9, 9, 9, 12, 23, 42, 56, 77 };
        System.out.println("3的位置：" + fibSearch(arr, 3));
    }

    // 获得一个斐波那契数列
    public static int[] getFib() {
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < 20; ++i) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;
        int f[] = getFib();
        // 找到对应的k
        while (high > f[k] - 1)
            ++k;
        // 扩充数组，多出来的用最高位来填上
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = high + 1; i < temp.length; ++i)
            temp[i] = arr[high];
        // 迭代
        while (low <= high) {
            int mid = low + f[k - 1] - 1;
            // f[k] = f[k-1] + f[k-2]
            // 所以左侧大小是f[k-1],右侧大小是f[k-2]
            if (key < temp[mid]) {
                high = mid - 1;
                --k;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else{
                if(mid < high) return mid;
                else return high;
            }
        }
        return -1;
    }
}
