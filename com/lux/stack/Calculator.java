package com.lux.stack;

// 存在问题：不能解决小括号的问题
public class Calculator {
    public static void main(String[] args) {
        String expression = "30+60/3+112*6-255";
        CalStack numStack = new CalStack(10);
        CalStack operStack = new CalStack(10);
        int num1 = 0, num2 = 0, oper = 0, res = 0;
        String keepNum = "";
        for (int i = 0; i < expression.length(); ++i) {
            // 取得一个字符
            char ch = expression.charAt(i);
            // 判断是数字还是符号
            if (isOper(ch)) { // 是符号
                if (!operStack.isEmpty() // 符号栈不为空
                        && priority(ch) <= priority(operStack.top())) { // 且该符号优先级比栈顶符号优先级小或相等
                                                                        // 两栈取顶计算
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    oper = operStack.pop();
                    res = cal(num1, num2, oper);
                    // 计算结果放入数栈
                    numStack.push(res);
                    // 该符号放入符号栈
                    operStack.push(ch);
                } else { // 符号栈为空，或不为空且优先级比栈顶高，直接入栈
                    operStack.push(ch);
                }
            } else { // 是数字
                keepNum += ch; // 将连续的数字符号拼接起来
                if (i == expression.length() - 1 // 最后一个是数字，直接入栈
                        || isOper(expression.charAt(i + 1)) // 下一个是符号，直接入栈
                ) {
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }

            }
        }
        // 数栈和符号栈弹出元素依次运算
        while (!operStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = cal(num1, num2, oper);
            // 计算结果放入数栈
            numStack.push(res);
        }
        System.out.println("表达式 " + expression + " 的结果 = " + numStack.pop());
    }

    // 工具函数
    // 返回运算符的优先级
    // 优先级用数字表示，数字越大优先级越大
    public static int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else { // 假定只有+-*/
            return -1;
        }
    }

    // 判断是不是一个运算符
    public static boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public static int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
        case '+':
            res = num1 + num2;
            break;
        case '-':
            res = num2 - num1; // 注意顺序
            break;
        case '*':
            res = num1 * num2;
            break;
        case '/':
            res = num2 / num1; // 注意顺序
            break;
        }
        return res;
    }
}

// 先创建一个栈
class CalStack {
    private int maxSize;
    private int[] stack;
    private int top;

    // 构造器
    public CalStack(int maxSize) {
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
