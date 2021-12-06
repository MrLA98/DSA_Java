package com.algorithm.mst;

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

// KrusKal
class krusKal {
    public void MST(graph G) {
        int count = 0;
        // 终点数组
        int[] ends = new int[G.vers];
        // 得到边的集合
        EData[] Edges = G.gEDatas();
        // 排序
        sortEdgs(Edges);
        // 从小到达遍历所有的边
        for(EData edg : Edges){
            // 这条边的起点和终点
            int p1 = G.getIndex(edg.start);
            int p2 = G.getIndex(edg.end);
            // 判断是不是回路 -- 这两个点的终点是不是一样
            int e1 = getEnd(ends, p1);
            int e2 = getEnd(ends, p2);
            if(e1 != e2){ // 不是回路
                ends[e1] = e2; // 终点数组添加
                System.out.println("<" + edg.start + "," + edg.end + ">" + " = " + edg.weight);
                count += edg.weight;
            }
        }
        System.out.println("总权值之和 = " + count);
    }

    // 获得终点
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    // 对边排序
    private void sortEdgs(EData[] Edges) {
        for (int i = 1; i < Edges.length; ++i) {
            for (int j = i; j > 0 && Edges[j].weight < Edges[j - 1].weight; --j) {
                swapArr(j, j - 1, Edges);
            }
        }
    }

    private void swapArr(int i, int j, EData[] arr) {
        EData temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

// prim
class prim {
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
