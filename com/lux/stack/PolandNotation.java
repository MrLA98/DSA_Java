package com.lux.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        // 给定逆波兰表达式
        // (3+4)*5-6 => 3 4 + 5 * 6 -
        String expression = "4 5 * 8 - 60 + 8 2 / +";
        // 将expression放到ArrayList中
        List<String> rpnList = String2List(expression);
        System.out.println("rpnList = " + rpnList);
        // 将ArrayList传给一个方法
        System.out.println("res = " + calculate(rpnList));
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
}
