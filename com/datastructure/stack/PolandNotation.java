package com.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        // 给中缀表达式表达式
        String expression = "10 + ( 3 + 48 / 12 ) * 55 - 60 ";
        // 将expression放到ArrayList中
        List<String> orgList = String2List(expression);
        System.out.println("orgList = " + orgList);
        // 将中缀转成后缀 ：
        // [1, +, (, 3, +, 4, /, 2, ), *, 5, -, 6]
        // => [1, 3, 4, 2, /, +, 5, *, +, 6, -]
        List<String> rpnList = toRpnExpList(orgList);
        System.out.println("rpnList = " + rpnList);
        // 将ArrayList传给一个方法得到计算结果
        System.out.println("res = " + calculate(rpnList));
    }

    // 中缀转后缀
    public static List<String> toRpnExpList(List<String> orgList) {
        List<String> rpnList = new ArrayList<String>();
        Stack<String> operStack = new Stack<String>();
        for (String item : orgList) {
            // 数字直接输出
            if (item.matches("\\d+")) {
                rpnList.add(item);
            } else if (item.equals(")")) { // 遇见右括号
                // 不断出栈，直到遇见左括号
                while (!operStack.peek().equals("(")) {
                    rpnList.add(operStack.pop());
                }
                operStack.pop(); // 两括号抵消
            } else if (operStack.empty() // 栈空
                    || item.equals("(") // 左括号
                    || operStack.peek() == "(" // 栈顶是左括号
                    || priority(item) > priority(operStack.peek()) // 新符号更优先
            ) {
                operStack.push(item);
            } else { // 新来的符号不够优先
                while (!operStack.empty() // 栈没空 且 不够优先
                        && priority(item) <= priority(operStack.peek())) {
                    rpnList.add(operStack.pop()); // 栈不断弹出
                }
                operStack.push(item);
            }
        }
        while (!operStack.empty()) {
            rpnList.add(operStack.pop());
        }
        return rpnList;
    }

    // 将expression放到ArrayList中
    public static List<String> String2List(String exp) {
        // 用空格分隔原表达式
        String[] split = exp.split(" ");
        // 新建一个数组
        List<String> list = new ArrayList<String>();
        // 将分割后的表达式数组，逐个元素的放入list中
        for (String ch : split) {
            list.add(ch);
        }
        return list;
    }

    // 将ArrayList传给一个方法得到计算结果
    public static int calculate(List<String> rpnList) {
        // 创建栈
        Stack<String> rpnStack = new Stack<String>();
        // 遍历List
        for (String item : rpnList) {
            // 正则表达式来取出数
            if (item.matches("\\d+")) { // 匹配多位数
                // 入栈
                rpnStack.push(item);
            } else {
                int num2 = Integer.parseInt(rpnStack.pop());
                int num1 = Integer.parseInt(rpnStack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("wrong chracter");
                }
                rpnStack.push("" + res);
            }
        }
        return Integer.parseInt(rpnStack.peek());
    }

    // 返回运算符的优先级
    // 优先级用数字表示，数字越大优先级越大
    public static int priority(String oper) {
        if (oper.equals("*") || oper.equals("/")) {
            return 2;
        } else if (oper.equals("+") || oper.equals("-")) {
            return 1;
        } else { // 假定只有+-*/
            // System.out.println("不存在该操作符");
            return 0;
        }
    }
}
