package com.algorithm.dynamiprogramming;

import java.util.Arrays;

public class BackPack {
    public static void main(String[] args) {
        // 新建物品
        item g = new item("Guitar", 1500, 1);
        item s = new item("Audio", 3000, 4);
        item l = new item("Laptop", 2000, 3);
        // 物品列表
        item it[] = new item[3];
        it[0] = g;
        it[1] = s;
        it[2] = l;
        // 动态规划解决问题
        System.out.println(DPsolution(it, 4));
        System.out.println(DPsolutionln(it, 4));
        System.out.println(DPmemory(it, 4));
    }

    // 动态规划二维表
    public static int DPsolution(item[] it, int backPack) {
        // 新建动态规划表
        int dp[][] = new int[it.length + 1][backPack + 1];
        // 最初第0行和第0列都是0
        for (int i = 0; i < dp.length; ++i) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < dp[0].length; ++j) {
            dp[0][j] = 0;
        }
        // 从第一行第一列开始计算动态规划表
        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[0].length; ++j) {
                // 如果当前行对应的物品比j大，说明j装不下这个物品
                if (it[i - 1].weight > j) {
                    dp[i][j] = dp[i - 1][j];
                } else { // 用j可以装下，那就看装这个物品和不装哪个价值更大
                    dp[i][j] = Math.max(dp[i - 1][j], it[i - 1].value + dp[i - 1][j - it[i - 1].weight]);
                }
            }
        }
        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return dp[it.length][backPack];
    }

    // 简化的动态规划表
    public static int DPsolutionln(item[] it, int backPack) {
        int dp[][] = new int[2][backPack + 1];
        for (int i = 0; i < it.length; ++i) {
            for (int j = 1; j < dp[0].length; ++j) {
                if (it[i].weight <= j) {
                    dp[1][j] = Math.max(dp[0][j], dp[0][j - it[i].weight] + it[i].value);
                } else {
                    dp[1][j] = dp[0][j];
                }
            }
            dp[0] = dp[1];
            System.out.println(Arrays.toString(dp[1]));
        }
        return dp[1][backPack];
    }

    // 记录选择方法的动态规划
    public static int DPmemory(item[] it, int backPack){
        int last[] = new int[backPack+1];
        int dp[] = new int[backPack+1];
        String mLast[] = new String[backPack+1];
        String mNew[] = new String[backPack+1];
        for(int i = 0; i < it.length; ++i){
            for(int j = 1; j < dp.length; ++j){
                if(it[i].weight <= j && last[j] < last[j-it[i].weight] + it[i].value){
                    dp[j] = last[j-it[i].weight] + it[i].value;
                    mNew[j] = mLast[j-it[i].weight] + it[i].name;
                }else{
                    dp[j] = last[j];
                    mNew[j] = mLast[j];
                }
            }
            last = dp;
            mLast = mNew;
            System.out.println(Arrays.toString(dp));
            System.out.println(Arrays.toString(mNew));
        }
        return dp[backPack];
    }
}

// 物品
class item {
    String name;
    int value;
    int weight;

    public item(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }
}
