package com.lux.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    public static void main(String[] args) {
        int n = 5;
        String vertexs[] = { "A", "B", "C", "D", "E" };
        Graph graph = new Graph(n);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        // ab,ac,bc,bd,be,de
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 4, 1);

        graph.showMarix();

        graph.dfs();
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
    private int getNextNeighbor(int v1, int v2){
        for(int i = v2 + 1; i < vertexList.size(); ++i){
            if(edges[v1][i] != 0) 
                return i;
        }
        return -1;
    }

    // dfs -- 接口方法
    public void dfs(){
        boolean[] bucket = new boolean[vertexList.size()];
        for(int i = 0; i < vertexList.size(); ++i){
            if(!bucket[i])
                dfs(bucket, i);
        }
    }

    // dfs -- 实际实现
    private void dfs(boolean[] bucket, int index){
        // 到达了就打印
        System.out.print(getItem(index) + "->");
        // 设为访问过
        bucket[index] = true;
        // 然后继续深度优先
        // 找nex的下一条边
        int nex = getNextNeighbor(index, -1);
        while(nex != -1){
            if(!bucket[nex]){ // 没访问过
                dfs(bucket, nex);
            }
            // 没访问过
            nex = getNextNeighbor(index, nex);
        }
    }
}
