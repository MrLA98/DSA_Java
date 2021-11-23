package com.lux.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        testCircleArray();
    }

    public static void testCircleArray(){
        // 测试
        // 初始化一个队列
        CircleArray circleArray = new CircleArray(5);
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
            System.out.println("l(length): length of the queue");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's': // 展示队列
                    circleArray.show();
                    break;
                case 'e': // 退出程序
                    scanner.close();
                    loop = false;
                    break;
                case 'a': // 添加数据
                    System.out.println("please input a number");
                    int val = scanner.nextInt();
                    circleArray.push(val);
                    break;
                case 'g': // 取出数据
                    try {
                        int get = circleArray.pop();
                        System.out.println("get a number = " + get);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 队列头部的数据
                    try {
                        int head = circleArray.top();
                        System.out.println("head = " + head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'l': // 队列有效元素个数
                    System.out.println("length = " + circleArray.size());
                    break;
                default:
                    break;
            }
        }
        
        System.out.println("exit the programe");
    }

}

// 环形数组模拟队列
class CircleArray {
    private int maxSize; // 数组尺寸
    private int rear; // 队列尾部的下一个
    private int front; // 队列头部
    private int[] arr; // 数组
    
    // 构造方法
    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        // rear = front = 0;
        arr = new int[maxSize];
    }

    // 数组满了
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 数组空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加元素
    public void push(int item){
        // 判断是不是满了
        if(isFull()){
            System.out.println("The queue is full");
            return;
        }
        // 因为rear为队列尾部最后一个元素的下一位，所以直接添加
        arr[rear] = item;
        // rear后移
        rear = (rear + 1) % maxSize;
    }

    // 取数据
    public int pop() {
        // 判断是不是为空
        if(isEmpty()){
            throw new RuntimeException("The queue is empty");
        }
        // 保存队首变量
        int popVal = arr[front];
        // front后移
        front = (front + 1) % maxSize;
        return popVal;
    }

    // 当前队列有效数据个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列数据
    public void show() {
        // 从front开始，遍历有效个数
        for(int i = front; i < front + size(); ++i){
            System.out.println("arr[" + i%maxSize + "] = " + arr[i%maxSize]);
        }
    }

    // 显示队首
    public int top(){
        if(isEmpty()){
            throw new RuntimeException("The queue is empty");
        }
        return arr[front];
    }
}