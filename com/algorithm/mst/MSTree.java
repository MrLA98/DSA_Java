package com.algorithm.mst;

import java.util.Arrays;

public class MSTree {
    public static void main(String[] args) {
        char[] data = { 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weights = {
                { INF, 12 , INF, INF, INF, 16 , 14  },
                { 12 , INF, 10 , INF, INF, 7  , INF },
                { INF, 10 , INF, 3  , 5  , 6  , INF },
                { INF, INF, 3  , INF, 4  , INF, INF },
                { INF, INF, 5  , 4  , INF, 2  , 8   },
                { 16 , 7  , 6  , INF, 2  , INF, 9   },
                { 14 , INF, INF, INF, 8  , 9  , INF }
        };
        graph G = new graph(weights,data);
        System.out.println("建立图:"+G.vers+"个顶点,"+G.edges+"条边");
        Prim(G, 0);
    }
    static int INF = 100000;

    // 普里姆算法
    public static void Prim(graph G, int v) {
        int count = 0;
        // 访问
        boolean[] visited = new boolean[G.vers];
        // 节点v标记访问过
        visited[v] = true;
        // 外循环，vers-1条边
        for (int k = 1; k < G.vers; ++k) {
            // 局部变量
            int h1 = -1, h2 = -1;
            int minWeight = INF;
            // 中循环，所有边里访问过的
            for (int i = 0; i < G.vers; ++i) {
                // 内循环，所有边里没有访问过的
                for (int j = 0; j < G.vers; ++j) {
                    if (visited[i] && !visited[j] && G.weights[i][j] < minWeight) {
                        h1 = i; // 老节点
                        h2 = j; // 新节点
                        minWeight = G.weights[i][j]; // 更新最小权重
                    }
                }
            }
            // 输出
            System.out.println("<" + G.data[h1] + "," + G.data[h2] + ">" + " weights = " + minWeight);
            // 标记h2访问过
            visited[h2] = true;
            count += minWeight;
        }
        System.out.println("总权值之和 = " + count);
    }

}

class graph {
    char[] data;
    int vers;
    int[][] weights;
    int edges;
    static int INF = 100000;

    public graph(int[][] weights, char[] data) {
        this.data = data;
        vers = data.length;
        this.weights = weights;
        edges = getEdges();
    }

    public void showWeights() {
        for (int[] row : weights) {
            System.out.println(Arrays.toString(row));
        }
    }

    private int getEdges(){
        int count = 0;
        for(int i = 0; i < vers; ++i){
            for(int j = i; j < vers; ++j){
                if(weights[i][j] != INF){
                    ++count;
                }
            }
        }
        return count;
    }
}
