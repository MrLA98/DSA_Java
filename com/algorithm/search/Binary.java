package com.algorithm.search;

import java.util.ArrayList;
import java.util.List;

public class Binary {

    public static void main(String[] args) {
        int[] arr = { 1, 1, 1, 1, 1, 3, 5, 7, 9, 9, 9, 9, 9, 9, 9, 12, 23, 42, 56, 77 };
        System.out.println("77的位置：" + binarySearch(arr, 0, arr.length - 1, 77));
        List<Integer> aList = binarySearch2(arr, 0, arr.length - 1, 1);
        System.out.println("1的位置：" + aList);
    }

    // 只找一个，找到一个就返回
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right)
            return -1;
        int mid = (left + right) >> 1; // 中点
        if (findVal > arr[mid])
            return binarySearch(arr, mid + 1, right, findVal);
        else if (findVal < arr[mid])
            return binarySearch(arr, left, mid - 1, findVal);
        else
            return mid;
    }

    // 找到所有的
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        if (left > right)
            return new ArrayList<>();
        int mid = (left + right) >> 1; // 中点
        if (findVal > arr[mid])
            return binarySearch2(arr, mid + 1, right, findVal);
        else if (findVal < arr[mid])
            return binarySearch2(arr, left, mid - 1, findVal);
        else {
            List<Integer> res = new ArrayList<Integer>();
            int temp = mid - 1; // 先前找
            while (temp >= 0 && arr[temp] == findVal) {
                temp--;
            }
            res.add(temp + 1); // 左边界
            temp = mid + 1; // 向后找
            while (temp < arr.length && arr[temp] == findVal) {
                temp++;
            }
            res.add(temp - 1); // 右边界
            return res;
        }
    }
}
