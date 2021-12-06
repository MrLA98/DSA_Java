package com.algorithm.search;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2,5,0,3,7,12,6,5,9,22,13,43,52,14};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(bSearchRecur(arr, 0, arr.length-1, 0));
        System.out.println(bSearch(arr, 0));
    }

    public static int bSearchRecur(int[] arr, int L, int R, int target) {
        if (L > R)
            return -1;
        int mid = (L + R) >> 1;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return bSearchRecur(arr, L, mid - 1, target);
        } else {
            return bSearchRecur(arr, mid + 1, R, target);
        }
    }

    public static int bSearch(int[] arr, int target) {
        int L = 0, R = arr.length - 1;
        while (L <= R) {
            int mid = (L + R) >> 1;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return -1;
    }
}
