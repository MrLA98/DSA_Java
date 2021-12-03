package com.datastructure.huffmantree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {
    public static void main(String[] args) {
        // String str = "tian yi ran and lu hao";
        // // 编码
        // byte[] strBytes = str.getBytes();
        // Map<Byte, String> huffmanCodes = generateHuffMap(strBytes);
        // System.out.println(huffmanCodes);
        // byte[] huffmanBytes = HuffmanZip(strBytes);
        // System.out.println(Arrays.toString(huffmanBytes));
        // // 解码
        // byte[] b = decode(huffmanCodes, huffmanBytes);
        // System.out.println(Arrays.toString(b));
        // System.out.println(new String(b));

        // 压缩文件
        //String srcFile = "com/source/cam.jpg";
        String dstFile = "com/source/cam.zip";
        //zipFile(srcFile, dstFile);
        // 解压文件
        String unzipFile = "com/source/unzip.jpg";
        unZipFile(dstFile, unzipFile);
    }

    // ---------------------------文件压缩----------------------------
    public static void zipFile(String srcFile, String dstFile){
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try{
            // 读数据
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);
            // 计算哈夫曼表和赫夫曼编码
            Map<Byte, String> huffmanCodes = generateHuffMap(b); // 编码表
            byte[] HuffmanZip = HuffmanZip(huffmanCodes, b); // 直接压缩
            // 写数据
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(HuffmanZip);
            oos.writeObject(huffmanCodes);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{ // 关闭各个流
                is.close();
                os.close();
                oos.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    // --------------------------文件解压缩---------------------------
    public static void unZipFile(String zipFile, String unzipFile){
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try{
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[])ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>)ois.readObject();
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(unzipFile);
            os.write(bytes);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                os.close();
                ois.close();
                is.close();
            }catch(Exception e2){
                System.out.println(e2.getMessage());
            }
        }

    }
    // -----------------------------解码------------------------------
    // 字节变量转成字符串
    public static String byte2String(boolean flag, byte b) {
        int temp = b;
        // 真，需要补高位
        if (flag)
            temp |= 256;
        String str = Integer.toBinaryString(temp);
        return flag ? str.substring(str.length() - 8) : str;
    }

    // 解码
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder sBuilder = new StringBuilder();
        // 将机器码转成字符串
        for (int i = 0; i < huffmanBytes.length; ++i) {
            boolean flag = !(i == huffmanBytes.length - 1);
            sBuilder.append(byte2String(flag, huffmanBytes[i]));
        }
        // 调转赫夫曼编码表
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> ent : huffmanCodes.entrySet()) {
            map.put(ent.getValue(), ent.getKey());
        }
        // 存放byte
        List<Byte> list = new ArrayList<>();
        // 遍历字符串，用反转的赫夫曼表匹配字符
        for (int i = 0; i < sBuilder.length();) {
            int count = 1;
            while (true) {
                String key = sBuilder.substring(i, i + count);
                Byte b = map.get(key);
                if (b == null)
                    ++count;
                else {
                    list.add(b);
                    break;
                }
            }
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            b[i] = list.get(i);
        }
        return b;
    }

    // -----------------------------编码------------------------------
    // for test
    // 一整个生成哈夫曼编码表的过程
    public static Map<Byte, String> generateHuffMap(byte[] strBytes) {
        // 新建赫夫曼编码表对象
        HuffmanCode hCode = new HuffmanCode();
        // 用字符数组生成节点[字符，次数]
        List<HNode> nodes = hCode.getNodes(strBytes);
        // 按照次数生成赫夫曼树
        HNode root = hCode.createHuffmanTree(nodes);
        // 根据赫夫曼树生成赫夫曼编码表
        return hCode.getCodes(root);
    }

    // 把一个字符串压缩
    public static byte[] HuffmanZip(Map<Byte, String> huffmanCodes, byte[] strBytes) {
        // 新建字符连接器，后续要用
        StringBuilder sBuilder = new StringBuilder();
        // 每个字符，用赫夫曼表找到对应的编码，按照原始顺序拼接起来
        for (byte b : strBytes) {
            sBuilder.append(huffmanCodes.get(b));
        }
        // 编码放入到byte数组中，8个数字是一个byte
        // 计算需要多少个byte，从而新建数组
        int len = (sBuilder.length() + 7) / 8;
        byte[] by = new byte[len];
        for (int i = 0; i < sBuilder.length(); i += 8) {
            // 截取8个，转成byte
            int right = i + 8 > sBuilder.length() ? sBuilder.length() : i + 8;
            String strByte = sBuilder.substring(i, right);
            by[i / 8] = (byte) Integer.parseInt(strByte, 2);
        }
        return by;
    }
    // 重载接口方法
    public static byte[] HuffmanZip(byte[] strBytes) {
        // 先拿到赫夫曼编码表
        Map<Byte, String> huffmanCodes = generateHuffMap(strBytes);
        return HuffmanZip(huffmanCodes, strBytes);
    }

    // 类内方法
    // 哈夫曼编码表
    private Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();

    // 重载的对外方法，将赫夫曼树根节点传入，返回赫夫曼编码表
    private Map<Byte, String> getCodes(HNode root) {
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
