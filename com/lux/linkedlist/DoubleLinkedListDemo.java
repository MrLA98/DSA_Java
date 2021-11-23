package com.lux.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "SongJiang", "rain");
        HeroNode2 hero2 = new HeroNode2(2, "LuJunyi", "yuqiling");
        HeroNode2 hero3 = new HeroNode2(3, "WuYong", "smart");
        HeroNode2 hero4 = new HeroNode2(4, "LinChong", "puma");
        
        // 创建链表
        DoubleLinkedList dLinkedList = new DoubleLinkedList();
        // 添加英雄
        dLinkedList.addByOrder(hero2);
        dLinkedList.addByOrder(hero4);
        dLinkedList.addByOrder(hero3);
        dLinkedList.addByOrder(hero1); 
        //打印
        dLinkedList.show();

        // 修改
        HeroNode2 hero = new HeroNode2(2, "luhao", "Kyrin");
        dLinkedList.update(hero);
        dLinkedList.show();

        // 删除
        dLinkedList.del(3);
        dLinkedList.show();
    }
}

class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0, "", "");
    
    public HeroNode2 getHead() {
        return head;
    }

    // 打印链表
    public void show() {
        // 判断链表是否为空
        if(head.next == null) {
            System.out.println("The list is empty");
            return;
        }
        HeroNode2 temp = head.next;
        while(true){
            if(temp == null) break;
            System.out.println(temp);
            temp = temp.next;
        }
    }
    
    // 添加节点
    public void push(HeroNode2 newNode){
        // 找到当前链表节点的最后节点，让其next指向新节点
        HeroNode2 temp = head;
        while(true){
            if(temp.next == null) break;
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.pre = temp;
    }

    // 按照编号添加
    public void addByOrder(HeroNode2 newHero) { 
        HeroNode2 temp = head;
        boolean flag = false; // 默认newhero编号不存在
        // temp的目标位置是newHero位置的前一个位置
        while(true){
            // 已经到达结尾
            if(temp.next == null){
                break;
            }
            if(temp.next.no > newHero.no){
                break;
            }else if(temp.next.no == newHero.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断flag
        if(flag){ // 已经有这个编号了
            System.out.println("This hero has existed");
        }else{
            newHero.next = temp.next;
            temp.next = newHero;
            newHero.pre = temp;
            if(newHero.next != null) newHero.next.pre = newHero;
        }
    }

    // 修改内容
    public void update(HeroNode2 changedHero){
        // 判断链表是否为空
        if(head.next == null){
            System.out.println("This list is empty");
            return;
        }
        // 寻找该节点
        HeroNode2 temp = head.next;
        boolean flag = false; // 默认没有这个节点
        while(true){
            if(temp == null) break; // 到达结尾了
            if(temp.no == changedHero.no){
                flag = true; // 找到了
                break;
            }
            temp = temp.next;
        }
        if(flag){ // 找到了
            temp.name = changedHero.name;
            temp.nickName = changedHero.nickName;
        }else{ // 没找到
            System.out.println("Can't find target hero");
        }
    }

    // 删除节点
    public void del(int no) {
        if(head.next == null){
            System.out.println("The list is empty");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false; // 是否找到
        while(true){
            if(temp == null) break; // 到达末尾
            if(temp.no == no){
                flag = true; // 找到了
                break;
            }
            temp = temp.next;
        }
        if(flag){ // 找到了
            temp.pre.next = temp.next;
            if(temp.next != null) temp.next.pre = temp.pre;
        }else{ // 没找到
            System.out.println("Can't find target Hero");
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;
    // 构造器
    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
    // 打印
    @Override
    public String toString() {
        return "HeroNode2 [no=" + no 
            + ", name =" + name 
            + ", nickname = " + nickName + "]";
    }
}