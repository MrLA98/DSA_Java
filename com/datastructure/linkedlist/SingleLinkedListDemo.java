package com.datastructure.linkedlist;

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
        sLinkedList.addByOrder(hero2);
        sLinkedList.addByOrder(hero4);
        sLinkedList.addByOrder(hero3);
        sLinkedList.addByOrder(hero1);
        // 显示
        sLinkedList.show();
        // 修改
        HeroNode hero = new HeroNode(2, "luhao", "Kyrin");
        sLinkedList.update(hero);
        sLinkedList.show();
        // 删除
        sLinkedList.del(2);
        sLinkedList.show();
        sLinkedList.del(1);
        sLinkedList.show();
        sLinkedList.del(4);
        sLinkedList.show();
        sLinkedList.del(3);
        sLinkedList.show();
    }
}

// 定义一个单链表 来管理英雄
class SingleLinkedList {
    // 初始化头节点，且不要动
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    // 添加节点-- 按照添加的先后顺序
    public void push(HeroNode newNode) {
        // 找到当前链表节点的最后节点，让其next指向新节点
        HeroNode temp = head;
        while (true) {
            if (temp.next == null)
                break;
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // 按照英雄序号来添加
    public void addByOrder(HeroNode newHero) {
        HeroNode temp = head;
        boolean flag = false; // 默认newhero编号不存在
        // temp的目标位置是newHero位置的前一个位置
        while (true) {
            // 已经到达结尾
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > newHero.no) {
                break;
            } else if (temp.next.no == newHero.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断flag
        if (flag) { // 已经有这个编号了
            System.out.println("This hero has existed");
        } else {
            newHero.next = temp.next;
            temp.next = newHero;
        }
    }

    // 显示链表
    public void show() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("The list is empty");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null)
                break;
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 修改节点信息（除了no）
    public void update(HeroNode changedHero) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("This list is empty");
            return;
        }
        // 寻找该节点
        HeroNode temp = head.next;
        boolean flag = false; // 默认没有这个节点
        while (true) {
            if (temp == null)
                break; // 到达结尾了
            if (temp.no == changedHero.no) {
                flag = true; // 找到了
                break;
            }
            temp = temp.next;
        }
        if (flag) { // 找到了
            temp.name = changedHero.name;
            temp.nickName = changedHero.nickName;
        } else { // 没找到
            System.out.println("Can't find target hero");
        }
    }

    // 删除节点
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; // 是否找到
        while (true) {
            if (temp.next == null)
                break; // 到达末尾
            if (temp.next.no == no) {
                flag = true; // 找到了
                break;
            }
            temp = temp.next;
        }
        if (flag) { // 找到了
            temp.next = temp.next.next;
        } else { // 没找到
            System.out.println("Can't find target Hero");
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
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickName + "]";
    }

}
