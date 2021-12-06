package com.algorithm.graphAlgorithm.minimumspanningtree;

import java.util.Arrays;

public class MSTree {
    static int INF = 100000;

    public static void main(String[] args) {
        char[] data = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] weights = {
                { INF, 12, INF, INF, INF, 16, 14 },
                { 12, INF, 10, INF, INF, 7, INF },
                { INF, 10, INF, 3, 5, 6, INF },
                { INF, INF, 3, INF, 4, INF, INF },
                { INF, INF, 5, 4, INF, 2, 8 },
                { 16, 7, 6, INF, 2, INF, 9 },
                { 14, INF, INF, INF, 8, 9, INF }
        };
        graph G = new graph(weights, data);
        System.out.println("建立图:" + G.vers + "个顶点," + G.edges + "条边");

        // prim
        prim pSolution = new prim();
        pSolution.MST(G);

        System.out.println();

        // krusKal
        krusKal kSolution = new krusKal();
        kSolution.MST(G);
    }
}

// 图
class graph {
    char[] data;
    int vers;
    int[][] weights;
    int edges;
    static int INF = 100000;

    public graph(int[][] weights, char[] data) {
        this.data = data.clone();
        vers = data.length;
        this.weights = weights.clone();
        edges = getEdges();
    }

    public void showWeights() {
        for (int[] row : weights) {
            System.out.println(Arrays.toString(row));
        }
    }

    // 计算边的数目
    private int getEdges() {
        int count = 0;
        for (int i = 0; i < vers; ++i) {
            for (int j = i + 1; j < vers; ++j) {
                if (weights[i][j] != INF) {
                    ++count;
                }
            }
        }
        return count;
    }

    // 得到一个边数组
    public EData[] gEDatas() {
        EData[] Edges = new EData[edges];
        int index = 0;
        for (int i = 0; i < vers; ++i) {
            for (int j = i + 1; j < vers; ++j) {
                if (weights[i][j] != INF) {
                    Edges[index++] = new EData(data[i], data[j], weights[i][j]);
                }
            }
        }
        return Edges;
    }

    // 获得下标
    public int getIndex(char ch){
        for(int i = 0; i < data.length; ++i){
            if(data[i] == ch) return i;
        }
        return -1;
    }
}

// 边
class EData {
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "<" + start + "," + end + "> = " + weight;
    }
}
