package com.datastructure.sort;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Checker {
	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue, int minValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = minValue + (int) (Math.random() * (maxValue - minValue + 1));
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 100;
		int maxSize = 500000;
		int maxValue = 5000;
		int minValue = -5000; // 基数排序时，最小值必须是0
		boolean succeed = true;
		// Bubble sortFunc = new Bubble(); // 冒泡
		// Selection sortFunc = new Selection(); // 选择
		// Insertion sortFunc = new Insertion(); // 插入
		// Shell sortFunc = new Shell(); // 希尔
		// Quick sortFunc = new Quick(); // 快排
		// Merge sortFunc = new Merge(); // 归并
		// Radix sortFunc = new Radix(); // 基数
		Heap sortFunc = new Heap(); // 堆排序

		Date date1 = new Date();
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = sDateFormat.format(date1);
		System.out.println("测试前时间：" + date1Str);

		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue, minValue);
			int[] arr2 = copyArray(arr1);
			sortFunc.Sort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		Date date2 = new Date();
		String date2Str = sDateFormat.format(date2);
		System.out.println("测试后时间：" + date2Str);
		long t = date2.getTime() - date1.getTime();
		System.out.println("用时 = " + t / 1000.0 + "s");
	}
}
