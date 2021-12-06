package com.algorithm.mst;

// KrusKal
public class krusKal {
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

