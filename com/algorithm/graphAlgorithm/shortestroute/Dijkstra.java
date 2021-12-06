package com.algorithm.graphAlgorithm.shortestroute;

import java.util.Arrays;

/**  迪杰斯特拉算法 -- 广度优先思路
 * 思路：两个步骤往复交替
 * 1.在dis数组中找到离出发点最近且没做过中转节点的节点a
 * 2.以点a为中转节点，计算到各点的距离，如果更小就更新dis数组
 * 往复进行，直到所有节点都作过中转节点了
 * 注：可以用优先级队列来做dis数组，从而优化代码 */
public class Dijkstra {
    graph G;
    disMemory vv;

    // 初始化图和距离数组
    private void setGandVV(graph g, int index) {
        G = g;
        vv = new disMemory(G.nodes.length, index);
    }

    // 算法接口
    public void shortest(graph G, int index) {
        setGandVV(G, index); // 初始化图和距离数组
        update(index); // 以头节点作为中转节点，更新数组
        for (int i = 1; i < G.nodes.length; ++i) {
            // 寻找下一个中转节点
            int next = vv.updateArr();
            // 用新的中转节点来更新数组
            update(next);
        }
        show(index); // 打印数组
    }

    // 从index进入，更新距离数组
    private void update(int index) {
        int len = 0;
        // 以index为中转，重新计算从起点到达各点的距离
        for (int j = 0; j < G.nodes.length; ++j) {
            // 到中转节点的距离 + 中转节点到j的距离
            len = vv.dis[index] + G.weights[index][j];
            // 如果j之前没有当过中转节点，且新距离比之前距离数组中到j的距离要短
            if (!vv.visited[j] && len < vv.dis[j]) {
                vv.dis[j] = len; // 更新到j的距离
            }
        }
    }

    // 显示结果
    private void show(int index) {
        System.out.println(G.nodes[index] + "点到各点到距离：");
        System.out.println(Arrays.toString(G.nodes));
        System.out.println(Arrays.toString(vv.dis));
    }
}

// 距离数组
class disMemory {
    public boolean[] visited; // 有没有做过中转节点
    public int[] dis; // 距离
    static int INF = 65535;

    // 构造器
    public disMemory(int len, int index) {
        visited = new boolean[len];
        visited[index] = true; // 第一个节点访问过了
        dis = new int[len];
        Arrays.fill(dis, INF);
        dis[index] = 0; // 到自己的距离是0
    }

    // 返回一个新的访问节点
    public int updateArr() {
        int min = INF, index = 0;
        // 遍历所有节点 -- 找到一个距离起点最近的点 -- 作为下一个中转节点
        for (int i = 0; i < visited.length; ++i) {
            // 没访问过 且 到它的距离比min小
            if (!visited[i] && dis[i] < min) {
                min = dis[i]; // 更新min
                index = i; // 记录该节点
            }
        }
        visited[index] = true; // 标记其访问过了
        return index; // 返回找到的这个节点
    }
}

