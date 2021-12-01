package com.lux.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {
    // for test
    // 一整个生成哈夫曼编码表的过程
    public static Map<Byte, String> generateHuffMap(String str) {
        // 将字符串变成字符数组
        byte[] strBytes = str.getBytes();
        // 新建赫夫曼编码表对象
        HuffmanCode hCode = new HuffmanCode();
        // 用字符数组生成节点[字符，次数]
        List<HNode> nodes = hCode.getNodes(strBytes);
        // 按照次数生成赫夫曼树
        HNode root = hCode.createHuffmanTree(nodes);
        // 根据赫夫曼树生成赫夫曼编码表
        return hCode.getCodes(root);
    }

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        System.out.println(generateHuffMap(str));
    }

    // 哈夫曼编码表
    private Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();

    // 重载的对外方法，将赫夫曼树根节点传入，返回赫夫曼编码表
    public Map<Byte, String> getCodes(HNode root) {
        StringBuilder sBuilder = new StringBuilder();
        getCodes(root, "", sBuilder);
        return huffmanCodes;
    }

    // 根据赫夫曼树 计算生成赫夫曼编码表
    private void getCodes(HNode root, String code, StringBuilder sBuilder) {
        // 保留之前的字符串生成器
        StringBuilder sBuilder2 = new StringBuilder(sBuilder);
        // 将新的字符拼接
        sBuilder2.append(code);
        // 空节点直接返回
        if (root == null)
            return;
        // 不是空节点
        if (root.data == null) { // 不是叶子节点
            getCodes(root.left, "0", sBuilder2); // 左递归
            getCodes(root.right, "1", sBuilder2); // 右递归
        } else { // 是叶子节点 -- 递归完成，将字符和对应的字符串放入赫夫曼表中
            huffmanCodes.put(root.data, sBuilder2.toString());
        }
    }

    // 将字符数组转化为节点数组
    private List<HNode> getNodes(byte[] bytes) {
        // 新建节点数组
        ArrayList<HNode> nodes = new ArrayList<HNode>();
        // 词频统计
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        // 根据字符、词频创建数组
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new HNode(entry.getKey(), entry.getValue()));
        }
        // 返回节点数组
        return nodes;
    }

    // 根据节点数组 生成赫夫曼树
    private HNode createHuffmanTree(List<HNode> nodes) {
        // 最后节点数组一定只剩下头节点，尺寸大于1就一直继续
        while (nodes.size() > 1) {
            // 排序
            Collections.sort(nodes); 
            // 把最小的两个拿出来，作为新节点的左右孩子
            HNode l = nodes.get(0);
            HNode r = nodes.get(1);
            HNode parent = new HNode(null, l.weight + r.weight);
            parent.left = l;
            parent.right = r;
            // 数组中移除这两个，添加新的
            nodes.remove(l);
            nodes.remove(r);
            nodes.add(parent);
        }
        // 最后节点数组一定只剩下头节点，直接返回
        return nodes.get(0);
    }
}

// 赫夫曼树节点
class HNode implements Comparable<HNode> {
    Byte data;
    int weight;
    HNode left;
    HNode right;

    public HNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    @Override
    public int compareTo(HNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "[data = " + data + ", weight = " + weight + "]";
    }
}
