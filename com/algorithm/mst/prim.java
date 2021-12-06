package com.algorithm.mst;

// prim
public class prim {
    static int INF = 100000;

    public void MST(graph G) {
        MST(G, 0);
    }

    private void MST(graph G, int v) {
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
            System.out.println("<" + G.data[h1] + "," + G.data[h2] + ">" + " = " + minWeight);
            // 标记h2访问过
            visited[h2] = true;
            count += minWeight;
        }
        System.out.println("总权值之和 = " + count);
    }
}
