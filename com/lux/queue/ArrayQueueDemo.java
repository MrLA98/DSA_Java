package com.lux.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        testArrayQueue();
    }

    // 数组队列的模拟
    public static void testArrayQueue(){
        // 测试
        // 初始化一个队列
        ArrayQueue testQueue = new ArrayQueue(22);
        char key = ' '; // 用户输入
        Scanner scanner = new Scanner(System.in); // 扫描器
        boolean loop = true;
        // 菜单
        while(loop) {
            System.out.println("s(show): show the queue");
            System.out.println("e(exit): exit the menu");
            System.out.println("a(add): add an item");
            System.out.println("g(get): get an item");
            System.out.println("h(head): show the head item");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's': // 展示队列
                    testQueue.show();
                    break;
                case 'e': // 退出程序
                    scanner.close();
                    loop = false;
                    break;
                case 'a': // 添加数据
                    System.out.println("please input a number");
                    int val = scanner.nextInt();
                    testQueue.push(val);
                    break;
                case 'g': // 取出数据
                    try {
                        int get = testQueue.pop();
                        System.out.println("get a number = " + get);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = testQueue.top();
                        System.out.println("head = " + head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        
        System.out.println("exit the programe");
    }

}



// 使用数组模拟一个队列 -- 编写一个类 - ArrayQueue
class ArrayQueue {
    // 成员变量
    private int maxSize; // 数组的最大容量
    private int rear; // 队列尾部 -- 包含尾部
    private int front; // 队列头部 -- 不包含头部
    private int[] arr; // 数组

    // 方法
    // 构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        rear = front = -1;
        arr = new int[maxSize];
    }
    // 判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }
    // 判断队列是否空
    public boolean isEmpty() {
        return rear == front;
    }
    // 添加数据到队列
    public void push(int item){
        // 判断是否满
        if(isFull()){
            System.out.println("The queue is full");
            return;
        }
        // 队尾后移并添加数据
        arr[++rear] = item;
    }
    // 从队首弹出数据
    public int pop() {
        // 判断是否空
        if(isEmpty()){
            // 抛出异常
            throw new RuntimeException("The queue is empty");
        }
        // 队首弹出元素
        return arr[++front];
    }
    // 展示队列所有数据
    public void show(){
        if(isEmpty()){
            System.out.println("The queue is empty");
            return;
        }
        System.out.println("Print the queue:");
        for(int i = front+1; i <= rear; ++i){
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println();
    }
    // 显示队列的头部
    public int top(){
        // 判断是否空
        if(isEmpty()){
            // 抛出异常
            throw new RuntimeException("The queue is empty");
        }
        return arr[front+1];
    }
}
