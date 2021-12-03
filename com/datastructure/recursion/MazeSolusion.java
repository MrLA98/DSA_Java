package com.datastructure.recursion;

public class MazeSolusion {
    public static void main(String[] args) {
        // 创建二维数组，模拟迷宫
        // 小球要从(1,1)到(6,6)
        int[][] map = new int[8][8];
        // 墙
        createMap(map);
        // 打印地图
        showMap(map);
        // 找路
        // setWay(map, 1, 1);
        setWay2(map, 1, 1);
        // 打印新地图
        showMap(map);
    }

    // 给地图加墙
    public static void createMap(int[][] map) {
        for (int i = 0; i < 8; ++i) {
            map[0][i] = map[i][0] = 1;
            map[7][i] = map[i][7] = 1;
        }
        map[2][3] = map[2][4] = map[2][6] = 1;
        map[3][1] = map[3][2] = map[3][3] = 1;
        map[5][5] = map[5][6] = map[4][5] = 1;
        map[5][2] = map[5][3] = map[5][4] = 1;
    }

    // 打印地图
    public static void showMap(int[][] map) {
        System.out.println("this is the maze:");
        for (int[] row : map) {
            for (int point : row) {
                System.out.printf("%d ", point);
            }
            System.out.println();
        }
        System.out.println();
    }

    // 找路
    // (i,j) 起点
    // map[i][j] == 0 没走过
    // map[i][j] == 1 墙
    // map[i][j] == 2 走过了，可以走通
    // map[i][j] == 3 走过了，但走不通
    // 先后顺序：下、右、上、左
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[i][j] == 2 || map[i][j] == 0) {
            System.out.println("reach(" + i + "," + j + ")");
        }
        if (map[6][6] == 2) { // 找到了
            return true;
        }
        if (map[i][j] == 0) { // 没走过
            // 下、右、上、左
            map[i][j] = 2; // 先假定可以走通
            if (setWay(map, i + 1, j))
                return true; // 下
            else if (setWay(map, i, j + 1))
                return true; // 右
            else if (setWay(map, i - 1, j))
                return true; // 上
            else if (setWay(map, i, j - 1))
                return true; // 左
            else { // 走不通
                map[i][j] = 3;
                return false;
            }
        } else { // 不是0，有可能是1、2、3
            return false;
        }
    }

    // 找路：上右下左
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[i][j] == 2 || map[i][j] == 0) {
            System.out.println("reach(" + i + "," + j + ")");
        }
        if (map[6][6] == 2) { // 找到了
            return true;
        }
        if (map[i][j] == 0) { // 没走过
            // 下、右、上、左
            map[i][j] = 2; // 先假定可以走通
            if (setWay2(map, i - 1, j))
                return true; // 上
            else if (setWay2(map, i, j + 1))
                return true; // 右
            else if (setWay2(map, i + 1, j))
                return true; // 下
            else if (setWay2(map, i, j - 1))
                return true; // 左
            else { // 走不通
                map[i][j] = 3;
                return false;
            }
        } else { // 不是0，有可能是1、2、3
            return false;
        }
    }
}
