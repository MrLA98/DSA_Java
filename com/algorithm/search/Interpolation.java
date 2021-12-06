package com.algorithm.search;

public class Interpolation {
    public static void main(String[] args) {
        int[] arr = { 1, 1, 1, 1, 1, 3, 5, 7, 9, 9, 9, 9, 9, 9, 9, 12, 23, 42, 56, 77 };
        System.out.println("77的位置：" + itpSearch(arr, 0, arr.length - 1, 12));
    }

    public static int itpSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[0] || findVal > arr[arr.length-1])
            return -1;
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        if(findVal > arr[mid])
            return itpSearch(arr, mid+1, right, findVal);
        else if(findVal < arr[mid])
            return itpSearch(arr, left, mid-1, findVal);
        return mid;
    }
}
