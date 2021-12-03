package com.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    public static void main(String[] args) {
        String vertexs[] = { "1", "2", "3", "4", "5", "6", "7", "8" };
        Graph graph = new Graph(vertexs.length);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        // 12,13,24,25,36,37,67,48,58
        graph.insertEdge(0, 1, 1); // 12
        graph.insertEdge(0, 2, 1); // 13
        graph.insertEdge(1, 3, 1); // 24
        graph.insertEdge(1, 4, 1); // 25
        graph.insertEdge(2, 5, 1); // 36
        graph.insertEdge(2, 6, 1); // 37
        graph.insertEdge(5, 6, 1); // 67
        graph.insertEdge(3, 7, 1); // 48
        graph.insertEdge(4, 7, 1); // 58

        graph.showMarix();

        graph.dfs();
        System.out.println();
        graph.bfs();
    }

    private ArrayList<String> vertexList; // 顶点
    private int[][] edges; // 边
    private int NumOfEdges; // 边的个数

    // 构造器
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
    }

    // 插入顶点
    public void insertVertex(String v) {
        vertexList.add(v);
    }

    // 添加边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        NumOfEdges++;
    }

    // 其他常用方法
    // 返回节点个数
    public int getNumofVertex() {
        return vertexList.size();
    }

    // 边的数目
    public int getNumofEdges() {
        return NumOfEdges;
    }

    // 节点内容
    public String getItem(int index) {
        return vertexList.get(index);
    }

    // 返回v1 v2 的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示邻接矩阵
    public void showMarix() {
        for (int[] row : edges) {
            System.out.println(Arrays.toString(row));
        }
    }

    // dfs
    // v1行，v2开始下一个不为0的边
    private int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); ++i) {
            if (edges[v1][i] != 0)
                return i;
        }
        return -1;
    }

    // dfs -- 接口方法
    public void dfs() {
        boolean[] bucket = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); ++i) {
            if (!bucket[i])
                dfs(bucket, i);
        }
    }

    // dfs -- 实际实现
    private void dfs(boolean[] bucket, int index) {
        // 到达了就打印
        System.out.print(getItem(index) + "->");
        // 设为访问过
        bucket[index] = true;
        // 然后继续深度优先
        // 找nex的下一条边
        int nex = getNextNeighbor(index, -1);
        while (nex != -1) {
            if (!bucket[nex]) { // 没访问过
                dfs(bucket, nex); // 一条路走到底
            }
            // 下一个
            nex = getNextNeighbor(index, nex);
        }
    }

    // bfs
    public void bfs() {
        boolean[] bucket = new boolean[vertexList.size()];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < vertexList.size(); ++i) {
            if (!bucket[i]) {
                bfs(bucket, queue, i);
            }
        }
    }

    // bfs -- 实际实现
    private void bfs(boolean[] bucket, LinkedList<Integer> queue, int index) {
        // 直接打印
        System.out.print(getItem(index) + "=>");
        // 标记为已经访问
        bucket[index] = true;
        // 加入队列
        queue.addLast(index);
        // 广度遍历
        while (!queue.isEmpty()) {
            // 队列弹出一个作为头节点
            int u = queue.removeFirst();
            // 以u为头节点找其邻接节点
            int w = getNextNeighbor(u, -1);
            while (w != -1) { // 找到一个有效的
                if (!bucket[w]) { // 没遍历过
                    System.out.print(getItem(w) + "=>"); // 打印
                    bucket[w] = true; // 标记为已访问
                    queue.addLast(w); // 放到队列尾部
                }
                // 向后寻找u的其他邻接顶点
                w = getNextNeighbor(u, w);
            }
        }
    }
}
