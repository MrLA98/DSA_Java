package com.algorithm.recursion;

public class NQueens {

    // 数组大小
    int Queens = 4;
    int[] arr = new int[Queens];
    static int count = 0;

    public static void main(String[] args) {
        NQueens qSolution = new NQueens();
        qSolution.check(0);
        System.out.println(count + "solutions total.");
    }

    // 放置方法
    private void check(int n){
        if(n == Queens){ // 放好了
            ++count;
            System.out.println("No." + count + "solution:");
            showln();
            if(Queens < 7){
                show();
            }
            return;
        }
        // 放皇后，判断冲突与否
        for(int i = 0; i < Queens; ++i){
            arr[n] = i; // 把当前皇后放到n行i列
            if(judge(n)){ // 不冲突
                check(n + 1);
            } // 冲突则下一个位置
        }
    }

    // 检测方法：放置第n个皇后，检测该皇后是否和前面的冲突了
    private boolean judge(int n){
        for(int i = 0; i < n; ++i){
            if(arr[i] == arr[n] // 同列
            || Math.abs(n-i) == Math.abs(arr[n]-arr[i]) // 同斜线
                ){
                return false;
            }
        }
        return true;
    }

    // 以棋盘形式打印某个结果
    private void show(){
        for(int item : arr){
            System.out.print("| ");
            for(int i = 0; i < Queens; ++i){
                String s = i == item ? "Q" : "_";
                System.out.printf("%s ",s);
            }
            System.out.println("|");
        }
    }

    // 以一维数组形式打印某个结果
    private void showln(){
        System.out.print("[ ");
        for(int item : arr){
            System.out.print(item + " ");
        }
        System.out.println("]");
    }
}