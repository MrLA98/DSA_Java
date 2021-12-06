package com.algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Knight {
    public static void main(String[] args) {
        Point begPoint = new Point(1, 1);
        Knight knight = new Knight(8);
        knight.travel(begPoint);
    }

    int N; // 棋盘大小
    boolean[][] visited; // 某个位置是否访问
    int[][] chessBodard; // 棋盘
    boolean finished; // 完成

    // 构造器
    public Knight(int chess) {
        N = chess;
    }

    // 周游 -- 接口函数
    public void travel(Point beginPos) {
        visited = new boolean[N][N];
        chessBodard = new int[N][N];
        travel(beginPos.y, beginPos.x, 1);
        show();
    }

    // 周游 -- 具体实现
    private void travel(int row, int col, int step) {
        chessBodard[row][col] = step; // 标记是第几步
        visited[row][col] = true; // 标记走过了
        // 这一步都下一步
        ArrayList<Point> ps = getNext(new Point(col, row));
        // 按照下一步能走几步来进行排序
        sortPs(ps);
        // 逐个尝试下一步
        while (!ps.isEmpty()) {
            Point p = ps.remove(0); // 弹出最多的
            if (!visited[p.y][p.x]) { // 没走过
                travel(p.y, p.x, step + 1); // 深度递归
            }
        }
        // 没完成
        if (step < N * N && !finished) {
            chessBodard[row][col] = 0; // 标成0
            visited[row][col] = false; // 这个位置没访问过
        } else { // 完成了
            finished = true;
        }
    }

    // 某个位置可以走的下一步
    private ArrayList<Point> getNext(Point cur) {
        ArrayList<Point> ps = new ArrayList<Point>();
        if (cur.x - 2 >= 0 && cur.y + 1 < N) {
            ps.add(new Point(cur.x - 2, cur.y + 1));
        }
        if (cur.x - 2 >= 0 && cur.y - 1 >= 0) {
            ps.add(new Point(cur.x - 2, cur.y - 1));
        }
        if (cur.x - 1 >= 0 && cur.y + 2 < N) {
            ps.add(new Point(cur.x - 1, cur.y + 2));
        }
        if (cur.x - 1 >= 0 && cur.y - 2 >= 0) {
            ps.add(new Point(cur.x - 1, cur.y - 2));
        }
        if (cur.x + 2 < N && cur.y + 1 < N) {
            ps.add(new Point(cur.x + 2, cur.y + 1));
        }
        if (cur.x + 2 < N && cur.y - 1 >= 0) {
            ps.add(new Point(cur.x + 2, cur.y - 1));
        }
        if (cur.x + 1 < N && cur.y + 2 < N) {
            ps.add(new Point(cur.x + 1, cur.y + 2));
        }
        if (cur.x + 1 < N && cur.y - 2 >= 0) {
            ps.add(new Point(cur.x + 1, cur.y - 2));
        }
        return ps;
    }

    // 用贪心的方法来优化
    private void sortPs(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            public int compare(Point o1, Point o2){
                return getNext(o1).size() - getNext(o2).size();
            }
        });
    }

    // 展示棋盘
    private void show() {
        for (int[] row : chessBodard) {
            System.out.println(Arrays.toString(row));
        }
    }
}

// 点
class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
}