package com.algorithm.graphAlgorithm.shortestroute;

import java.util.Arrays;

/**
 * Floyd算法 -- 三层循环 -- 得到所有点到任意点的最小距离
 */

public class Floyd {
    public void shortest(graph G) {
        int[][] dis = G.weights;
        // 首先将到自己变成0
        for (int i = 0; i < dis.length; ++i) {
            dis[i][i] = 0;
        }
        // k是中间节点
        for (int k = 0; k < dis.length; ++k) {
            // i是起点
            for (int i = 0; i < dis.length; ++i) {
                // j是终点，从i的下一节点开始 -- i->j 等价于 j->i
                for (int j = i + 1; j < dis.length; ++j) {
                    int len = dis[i][k] + dis[k][j];
                    dis[j][i] = dis[i][j] = len < dis[i][j] ? len : dis[i][j];
                }
            }
        }
        // 显示结果
        System.out.println("各点间最小距离矩阵：");
        for (int[] row : dis) {
            System.out.println(Arrays.toString(row));
        }
    }
}
