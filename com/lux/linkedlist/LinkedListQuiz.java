package com.lux.linkedlist;

public class LinkedListQuiz {
    public static void main(String[] args) {
        // 创建英雄
        HeroNode hero1 = new HeroNode(1, "SongJiang", "rain");
        HeroNode hero2 = new HeroNode(2, "LuJunyi", "yuqiling");
        HeroNode hero3 = new HeroNode(3, "WuYong", "smart");
        HeroNode hero4 = new HeroNode(4, "LinChong", "puma");
        
        // 创建链表
        SingleLinkedList sLinkedList = new SingleLinkedList();
        // 添加英雄
        sLinkedList.push(hero2);
        sLinkedList.push(hero4);
        sLinkedList.push(hero3);
        sLinkedList.push(hero1);

        // quiz1
        System.out.println(quiz1(sLinkedList.getHead()));

        // quiz2
        System.out.println(quiz2(sLinkedList.getHead(), 3));

        // quiz3
        quiz3(sLinkedList.getHead());
        sLinkedList.show();

        // quiz4
        quiz4(sLinkedList.getHead().next);
    }

    // 获取单链表的节点个数，有头节点，不统计头节点
    public static int quiz1(HeroNode head){
        if(head.next == null) return 0;
        int count = 0;
        HeroNode cur = head.next;
        while(cur != null){
            ++count;
            cur = cur.next;
        }
        return count;
    }

    // 获取单链表的倒数第k个节点
    public static HeroNode quiz2(HeroNode head, int index){
        if(head.next == null) return null;
        int size = quiz1(head);
        if(index <= 0 || index > size) return null;
        HeroNode cur = head.next;
        for(int i = 0; i < size-index; ++i){
            cur = cur.next;
        }
        return cur;
    }

    // 单链表的反转
    public static void quiz3(HeroNode head){
        if(head.next == null || head.next.next == null) return;
        HeroNode last = null, cur = null, next = head.next;
        while(next != null){
            cur = next;
            next = next.next;
            cur.next = last;
            last = cur;
        }
        head.next = cur;
    }
    public static void reverse(HeroNode head){ // 头插法
        if(head.next == null || head.next.next == null) return;
        HeroNode rvsHead = new HeroNode(0, "", ""), cur = head.next;
        while(cur != null){
            HeroNode temp = cur;
            cur = cur.next;
            temp.next = rvsHead.next;
            rvsHead.next = temp;
        }
        head.next = rvsHead.next;
    }

    // 从尾到头打印单链表
    public static void quiz4(HeroNode head){
        if(head == null) return;
        quiz4(head.next);
        System.out.println(head);
    }
}
