package com.algorithm.graphAlgorithm.shortestroute;

import java.util.Arrays;

public class ShortestRoute {
    static int INF = 65535;

    public static void main(String[] args) {
        char[] nodes = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] weights = {
                { INF, 12, INF, INF, INF, 16, 14 },
                { 12, INF, 10, INF, INF, 7, INF },
                { INF, 10, INF, 3, 5, 6, INF },
                { INF, INF, 3, INF, 4, INF, INF },
                { INF, INF, 5, 4, INF, 2, 8 },
                { 16, 7, 6, INF, 2, INF, 9 },
                { 14, INF, INF, INF, 8, 9, INF }
        };
        graph G = new graph(nodes, weights);
    
        Dijkstra dSolution = new Dijkstra();
        dSolution.shortest(G, 6);

        System.out.println();

        Floyd fSolution = new Floyd();
        fSolution.shortest(G);
    }
}

// 图
class graph {
    static int INF = 65535;
    char[] nodes;
    int[][] weights;
    int edges;

    // 构造器
    public graph(char[] nodes, int[][] weights) {
        this.nodes = nodes;
        this.weights = weights;
        updateEdges();
    }

    // 计算边的个数
    private void updateEdges() {
        edges = 0;
        for (int i = 0; i < nodes.length; ++i) {
            for (int j = i + 1; j < nodes.length; ++j) {
                if (weights[i][j] != INF)
                    ++edges;
            }
        }
    }

    // 展示邻接数组
    public void showWeights() {
        for (int[] row : weights) {
            System.out.println(Arrays.toString(row));
        }
    }
}
