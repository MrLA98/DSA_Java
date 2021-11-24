package com.lux.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayStack aStack = new ArrayStack(4);
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        String key = "";
        while (loop) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("push -- add number");
            System.out.println("pop -- pop number");
            System.out.println("top -- top number");
            System.out.println("show -- show stack");
            System.out.println("exit -- exit program");
            key = scanner.next();
            switch (key) {
            case "push":
                System.out.println("input a number:");
                aStack.push(scanner.nextInt());
                break;
            case "pop":
                try {
                    System.out.println("pop out: " + aStack.pop());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "top":
                try {
                    System.out.println("top: " + aStack.top());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "show":
                System.out.println("print the stack");
                aStack.show();
                break;
            case "exit":
                loop = false;
                scanner.close();
                return;
            default:
                break;
            }
            System.out.println("press 'c' to continue");
            scanner.next();
        }
    }
}

class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top;

    // 构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top = -1;
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int val) {
        if (isFull()) {
            System.out.println("the stack is full");
            return;
        }
        stack[++top] = val;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("the stack is empty");
        }
        return stack[top--];
    }

    // 查看栈顶元素
    public int top() {
        if (isEmpty()) {
            throw new RuntimeException("the stack is empty");
        }
        return stack[top];
    }

    // 显示栈
    public void show() {
        if (isEmpty()) {
            System.out.println("the stack is empty");
            return;
        }
        for (int i = top; i >= 0; --i) {
            System.out.println("元素[" + i + "]=" + stack[i]);
        }
    }
}
