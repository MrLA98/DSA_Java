package com.datastructure.tree;

import java.util.Scanner;

public class HeapDemo {
    public static void main(String[] args) {
        Heap heap = new Heap(6);
        Scanner scan = new Scanner(System.in);
        String key = "";
        while (true) {
            System.out.println("push -- add item");
            System.out.println("pop -- pop item");
            System.out.println("top -- top item");
            System.out.println("show -- show heap");
            System.out.println("exit -- exit program");
            key = scan.next();
            switch (key) {
                case "push":
                    System.out.println("input a number:");
                    heap.Insert(scan.nextInt());
                    break;
                case "pop":
                    try {
                        System.out.println("pop a number:" + heap.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "top":
                    try {
                        System.out.println("the top number:" + heap.top());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "show":
                    heap.show();
                    break;
                case "exit":
                    scan.close();
                    return;
                default:
                    break;
            }
        }
    }
}

// 写一个大顶堆
// 最重要的知识点就是heapify和heapInsert
class Heap {
    private int maxSize; // 最大长度
    private int[] arr; // 维护的数组
    private int len; // 数组中堆的范围，指向最后一个元素的后一个位置

    // 构造器
    public Heap(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    // 添加一个数字到大顶堆中
    public void Insert(int a) {
        if (isFull()) {
            System.out.println("Full Heap");
            return;
        }
        int i = len++; // 长度加一
        arr[i] = a;
        // 只要比父结点要大，就和父节点交换，继续往上
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    // 弹出堆顶的数字
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("empty heap");
        }
        swap(0, --len); // 和最后一个交换位置
        heapify(0); // 此时除了头，子树都已经完成“堆化”，因此直接“堆化”0位置即可
        return arr[len]; // 最后返回堆有效范围后一个数即可
    }

    // 前提，i的子树都已经完成“堆化”
    // 将i及其子树都进行“堆化”
    private void heapify(int i) {
        int temp = arr[i];
        int leftChild = 2 * i + 1;
        while (leftChild < len) {
            int large = leftChild + 1 < len && arr[leftChild + 1] > arr[leftChild] ? 
                        leftChild + 1 : leftChild;
            large = arr[large] > temp ? large : i;
            if (large == i)
                break;
            arr[i] = arr[large];
            i = large;
            leftChild = 2 * i + 1;
        }
        arr[i] = temp;
    }

    // 看一眼堆顶数字
    public int top() {
        if (isEmpty()) {
            throw new RuntimeException("empty heap");
        }
        return arr[0];
    }

    // 是不是空的
    private boolean isEmpty() {
        return len == 0;
    }

    // 是不是满的
    private boolean isFull() {
        return len == maxSize;
    }

    // 交换位置
    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 展示整个堆的有效区域
    public void show() {
        System.out.print("[");
        for (int i = 0; i < len - 1; ++i) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[len - 1] + "]");
    }
}
