package com.lux.sort;

import java.util.Arrays;

public class Shell {
    public static void main(String[] args) {
        int[] arr = {4,2,5,8,1,53,1,0,7,2};
        System.out.println(Arrays.toString(arr));
        Shell Func = new Shell();
        Func.shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    private void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 基于交换的希尔排序
    public void shellSort(int[] arr)
    {
        for(int gap = arr.length/2; gap > 0; gap /= 2){
            for(int i = gap; i < arr.length; ++i){
                for(int j = i; j >= gap && arr[j] < arr[j-gap]; j -= gap){
                    swap(j, j-gap, arr);
                }
            }
        }
    }

    // 基于移动的希尔排序
    public void shellSort2(int[] arr){
        for(int gap = arr.length/2; gap > 0; gap /= 2){
            for(int i = gap; i < arr.length; ++i){
                int j = i;
                int temp = arr[j];
                while(j >= gap && temp < arr[j-gap]){
                    arr[j] = arr[j-gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }
}
