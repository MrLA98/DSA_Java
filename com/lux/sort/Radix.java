package com.lux.sort;

import java.util.Arrays;

public class Radix {
    public static void main(String[] args) {
        int[] arr = { 4, 2, 5, 8, 1, 53, 1, 0, 7, 2 };
        System.out.println(Arrays.toString(arr));
        Radix Func = new Radix();
        Func.Sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    
    private int findRadix(int[] arr){
        int max = arr[0];
        for(int item : arr){
            max = item > max ? item : max;
        }
        int times = 0;
        while(max != 0){
            max /= 10;
            ++times;
        }
        return times;
    }

    private int getDigit(int x, int d){
        return ((x / ((int) Math.pow(10, d))) % 10);
    }

    public void Sort(int arr[]){
        int times = findRadix(arr);
        int[] helper = new int[arr.length];
        for(int d = 0; d < times; ++d){
            int[] count = new int[10];
            for(int item : arr){
                ++count[getDigit(item, d)];
            }
            for(int i = 1; i < 10; ++i){
                count[i] += count[i-1];
            }
            for(int i = arr.length-1; i >= 0; --i){
                int j = getDigit(arr[i], d);
                helper[--count[j]] = arr[i]; 
            }
            for(int i = 0; i < helper.length; ++i){
                arr[i] = helper[i];
            }
        }
    }
}
