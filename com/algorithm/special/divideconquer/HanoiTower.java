package com.algorithm.special.divideconquer;

public class HanoiTower {
    public static void main(String[] args) {
        hanoiMove(5, "A", "C", "B");
    }

    public static void hanoiMove(int n, String start, String end, String help){
        // 只有一个盘，直接移动到end
        if(n == 1){
            System.out.println("disk[1]: " + start + "->" + end);
            return;
        }
        // 上面n-1个盘移动到help
        hanoiMove(n-1, start, help, end);
        // 最下面一个移动到end
        System.out.println("disk["+n+"]: " + start + "->" + end);
        // 刚刚到help上的n-1个移动到end上
        hanoiMove(n-1, help, end, start);
    }
}

