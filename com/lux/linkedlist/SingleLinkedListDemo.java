package com.lux.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 创建英雄
        HeroNode hero1 = new HeroNode(1, "SongJiang", "rain");
        HeroNode hero2 = new HeroNode(2, "LuJunyi", "yuqiling");
        HeroNode hero3 = new HeroNode(3, "WuYong", "smart");
        HeroNode hero4 = new HeroNode(4, "LinChong", "puma");
        
        // 创建链表
        SingleLinkedList sLinkedList = new SingleLinkedList();
        // 添加英雄
        sLinkedList.push(hero1);
        sLinkedList.push(hero2);
        sLinkedList.push(hero3);
        sLinkedList.push(hero4);
        // 显示
        sLinkedList.show();
    }
}

// 定义一个单链表 来管理英雄
class SingleLinkedList {
    // 初始化头节点，且不要动
    private HeroNode head = new HeroNode(0, "", "");

    // 添加节点
    public void push(HeroNode newNode){
        // 找到当前链表节点的最后节点，让其next指向新节点
        HeroNode temp = head;
        while(true){
            if(temp.next == null) break;
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // 显示链表
    public void show() {
        // 判断链表是否为空
        if(head.next == null) {
            System.out.println("The list is empty");
            return;
        }
        HeroNode temp = head.next;
        while(true){
            if(temp == null) break;
            System.out.println(temp);
            temp = temp.next;
        }
    }
} 


// 这里用节点来表示英雄人物信息
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; // 指向下一个节点

    // 构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "HeroNode [no=" + no + 
            ", name=" + name + 
            ", nickname=" + nickName + "]";
    }

}
