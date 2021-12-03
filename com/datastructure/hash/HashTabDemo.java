package com.datastructure.hash;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTab hash = new HashTab(7);
        Scanner scan = new Scanner(System.in);
        String key = "";
        while(true){
            System.out.println("add -- add item");
            System.out.println("show -- show hash");
            System.out.println("find -- find id");
            System.out.println("exit -- exit program");
            key = scan.next();
            switch(key){
            case "add":
                System.out.println("input id-num:");
                int id = scan.nextInt();
                System.out.println("input name:");
                String name = scan.next();
                hash.add(new Emp(id,name));
                break;
            case "show":
                hash.list();
                break;
            case "find":
                System.out.println("input id-num:");
                hash.findId(scan.nextInt());
                break;
            case "exit":
                scan.close();
                break;
            }
        }
    }
}

// 哈希表
class HashTab{
    private EmpList[] empListArr;
    private int size;

    public HashTab(int size){
        this.size = size;
        // 初始化链表数组
        empListArr = new EmpList[size]; 
        // 初始化每一条链表
        for(int i = 0; i < size; ++i){
            empListArr[i] = new EmpList();
        }
    }

    // 添加员工
    public void add(Emp emp){
        int listNo = hashFun(emp.id);
        empListArr[listNo].add(emp);
    }

    // 遍历所有的链表
    public void list(){
        for(int i = 0; i < size; ++i){
            System.out.print("list["+i+"]");
            empListArr[i].list();
        }
    }

    // 根据id查找
    public boolean findId(int id){
        int index = hashFun(id);
        Emp emp = empListArr[index].findId(id);
        if(emp != null){
            System.out.println("find [id = "+id+", name = "+ emp.name+"] in list["+index+"]");
            return true;
        }else{
            System.out.println("no item with id = "+ id);
            return false;
        }
    }

    // 散列函数
    private int hashFun(int id){
        return id % size;
    }
}

// 链表的节点
class Emp{
    public int id;
    public String name;
    public Emp next;
    public Emp(int id, String name){
        this.id = id;
        this.name = name;
    }
}

// 链表
class EmpList{
    private Emp head; // 头指针

    // 添加雇员到链表最后
    public void add(Emp emp){
        if(head == null) {
            head = emp;
            return;
        }
        Emp cur = head;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = emp;
    }

    // 遍历链表
    public void list(){
        if(head == null){
            System.out.println("=>[empty list]");
            return;
        }
        Emp cur = head;
        while(cur != null){
            System.out.print("=>[id = " + cur.id + ", name = " + cur.name+"]");
            cur = cur.next;
        }
        System.out.println();
    }

    // 根据id查找
    public Emp findId(int id){
        if(head == null){
            System.out.println("empty list");
            return null;
        }
        Emp cur = head;
        while(cur != null && cur.id != id){
            cur = cur.next;
        }
        return cur;
    }
}
