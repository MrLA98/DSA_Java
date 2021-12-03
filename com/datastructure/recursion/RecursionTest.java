package com.datastructure.recursion;

import java.util.Scanner;

public class RecursionTest {
    public static void main(String[] args) {
        System.out.println("input a number : ");
        Scanner scan = new Scanner(System.in);
        int val = scan.nextInt();
        test(val);
        System.out.println(val + "! = " + fact(val));
        scan.close();
    }

    // 顺序打印和逆序打印
    public static void test(int n) {
        // 顺序打印
        System.out.println("n = " + n);
        if (n > 1) {
            test(n - 1);
        }
        // 逆序打印
        // System.out.println("n = " + n);
    }

    // 阶乘
    public static int fact(int n) {
        if (n <= 1)
            return 1;
        return n * fact(n - 1);
    }
}
