package com.datastructure.sort;

import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        int[] arr = { 4, 2, 5, 8, 1, 53, 1, 0, 7, 2 };
        System.out.println(Arrays.toString(arr));
        Merge Func = new Merge();
        Func.Sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void Sort(int[] arr){
        split(0, arr.length-1, arr);
    }

    private void split(int L, int R, int[] arr) {
        if(L >= R) return;
        int mid = (R+L) >> 1;
        split(L, mid, arr);
        split(mid + 1, R, arr);
        merge(L, mid, R, arr);
    }

    private void merge(int L, int M, int R, int[] arr) {
        int[] help = new int[R-L+1];
        int k = 0, p1 = L, p2 = M+1;
        while(p1 <= M && p2 <= R){
            help[k++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= M) help[k++] = arr[p1++];
        while(p2 <= R) help[k++] = arr[p2++];
        for(k = 0; k < help.length; ++k){
            arr[L+k] = help[k];
        }
    }
}
