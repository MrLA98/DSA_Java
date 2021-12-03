package com.datastructure.search;

public class Seq {
    public static void main(String[] args) {
        int arr[] = {1,9,11,-1,34,89}; // 无序序列
        System.out.println("11的位置：" + seqSearch(arr, 11));
    }

    public static int seqSearch(int[] arr, int value){
        for(int i = 0; i < arr.length; ++i){
            if(arr[i] == value) return i;
        }
        return -1;
    }
}
