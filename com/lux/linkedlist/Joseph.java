package com.lux.linkedlist;

/* 
- 约瑟夫问题
  - 编号为1、2、3、.. 、n的人围坐成一圈
  - 从编号k的人从1开始报数
  - 报到m的那个人出列
  - 下一个人又从1开始报数，数到m的那个人又出列
  - 依次类推，直到所有人出列为止。
  - 产生一个出队编号序列
*/
public class Joseph {
    public static void main(String[] args) {
        CircleSingleLinkedList cirBoys = new CircleSingleLinkedList(12);
        cirBoys.show();
        cirBoys.joseph(2, 3);
    }
}

// 单向环形链表
class CircleSingleLinkedList {
    // first节点
    private Boy first;
    private int childNums;

    // 添加小孩节点
    public CircleSingleLinkedList(int nums) {
        first = new Boy(-1);
        childNums = nums;
        if (nums < 1) {
            System.out.println("wrong number");
            return;
        }
        // 用编号创建环形链表
        Boy cur = null;
        for (int i = 1; i <= childNums; ++i) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.next = first;
            } else {
                cur.next = boy;
                boy.next = first;
            }
            cur = boy;
        }
    }

    // 遍历所有小孩
    public void show() {
        if (first == null) {
            System.out.println("empty list");
            return;
        }
        Boy cur = first;
        while (true) {
            System.out.println("小孩的编号" + cur.no);
            if (cur.next == first)
                break;
            cur = cur.next;
        }
    }

    // 计算约瑟夫问题：开始位置startNo，计数countNum
    public void joseph(int startNo, int countNum) {
        if (startNo < 1 || startNo > childNums) {
            System.out.println("wrong parameters");
            return;
        }
        Boy helper = first;
        // 让helper到达最后一个节点
        while (helper.next != first) {
            helper = helper.next;
        }
        // 让first移动到start位置
        for (int j = 0; j < startNo - 1; ++j) {
            first = first.next;
            helper = helper.next;
        }
        while (true) {
            // 圈里只剩一个人
            if (helper == first)
                break;
            for (int j = 0; j < countNum - 1; ++j) {
                first = first.next;
                helper = helper.next;
            }
            // 此时有一个小孩出圈
            System.out.println("小孩[" + first.no + "]出圈~");
            // 删掉这个小孩
            first = first.next;
            helper.next = first;
        }
        System.out.println("留在圈里的小孩是[" + first.no + "]");
    }
}

// 节点
class Boy {
    public int no;
    public Boy next;

    // 构造类
    public Boy(int no) {
        this.no = no;
    }
}