package com.datastructure.stack;

import java.util.Scanner;

public class ListStackDemo {
    public static void main(String[] args) {
        ListStack lStack = new ListStack();
        Scanner scanner = new Scanner(System.in);
        String key = "";
        while (true) {
            System.out.println("push -- push a number");
            System.out.println("pop -- pop a number");
            System.out.println("top -- the top number");
            System.out.println("show -- show the stack");
            System.out.println("len -- length of the stack");
            System.out.println("exit -- exit the program");
            key = scanner.next();
            switch (key) {
            case "push":
                System.out.println("input a number:");
                lStack.push(scanner.nextInt());
                break;
            case "pop":
                try {
                    System.out.println("pop out: " + lStack.pop());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "top":
                try {
                    System.out.println("top: " + lStack.top());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "show":
                lStack.show();
                break;
            case "len":
                System.out.println("length of the stack :" + lStack.length());
                break;
            case "exit":
                scanner.close();
                return;
            default:
                break;
            }

        }
    }
}

class ListStack {
    private Node top = new Node(-1);

    // 栈空
    public boolean isEmpty() {
        return top.next == null;
    }

    // 入栈
    public void push(int val) {
        Node newNode = new Node(val);
        newNode.next = top;
        top = newNode;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("the stack is empty");
        }
        int val = top.data;
        top = top.next;
        return val;
    }

    // 栈顶
    public int top() {
        if (isEmpty()) {
            throw new RuntimeException("the stack is empty");
        }
        return top.data;
    }

    // 从顶到底打印栈
    public void show() {
        if (isEmpty()) {
            System.out.println("the stack is empty");
            return;
        }
        Node cur = top;
        while (cur.next != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }
    }

    // 栈内元素个数
    public int length() {
        int count = 0;
        for (Node cur = top; cur.next != null; ++count) {
            cur = cur.next;
        }
        return count;
    }
}

// 节点
class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }
}