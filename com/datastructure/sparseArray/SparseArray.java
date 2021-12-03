package com.datastructure.sparseArray;

public class SparseArray {
    public static void main(String[] args) {
        // 创建一个11x11的矩阵
        // 0 表示没有棋子， 1 表示黑棋子， 2 表示白棋子
        int chessArray[][] = new int[11][11];
        // 在矩阵[2,3]和[3,4]处分别有一个黑棋子和一个白棋子
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][5] = 2;
        // 打印数组
        System.out.println("original chess table:");
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d ", data);
            }
            System.out.println();
        }

        // 将二维数组转化为稀疏数组
        // 遍历矩阵，找到棋子个数
        int count = 0;
        for (int[] row : chessArray) {
            for (int data : row) {
                if (data != 0) {
                    ++count;
                }
            }
        }
        // System.out.println("count = " + count);
        // 创建稀疏数组
        int sparseArray[][] = new int[count + 1][3];
        // 给稀疏数组赋值
        sparseArray[0][0] = chessArray.length;
        sparseArray[0][1] = chessArray[0].length;
        sparseArray[0][2] = count;

        // 再次遍历 chessArray，将棋子位置拿到
        int k = 0;
        for (int i = 0; i < chessArray.length; ++i) {
            for (int j = 0; j < chessArray[0].length; ++j) {
                if (chessArray[i][j] != 0) {
                    ++k;
                    sparseArray[k][0] = i;
                    sparseArray[k][1] = j;
                    sparseArray[k][2] = chessArray[i][j];
                }
            }
        }
        // 打印稀疏矩阵
        System.out.println("sparseArray:");
        System.out.println("row\tcol\titem");
        for (int row[] : sparseArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 稀疏数组恢复为矩阵
        // 创建矩阵
        int recoverArray[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
        // 将稀疏矩阵后几行的数据放到恢复数组中
        for (int i = 1; i < sparseArray.length; ++i) {
            recoverArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        // 打印恢复后的二维数组
        System.out.println("recoverArray:");
        for (int[] row : recoverArray) {
            for (int data : row) {
                System.out.printf("%d ", data);
            }
            System.out.println();
        }
    }
}