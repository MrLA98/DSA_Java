package com.lux.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建一棵树
        BinaryTree tree = new BinaryTree();
        // 节点
        HeroNode root = new HeroNode(1, "songjiang");
        HeroNode node2 = new HeroNode(2, "wuyong");
        HeroNode node3 = new HeroNode(3, "lujunyi");
        HeroNode node4 = new HeroNode(4, "linchong");
        HeroNode node5 = new HeroNode(5, "guansheng");
        // 手动创建二叉树
        root.left = node2;
        root.right = node3;
        node3.right = node4;
        node3.left = node5;
        tree.setRoot(root);
        //tree.preOrder();
        //tree.postOrder();
        tree.infixOrder();
    }
}

// 二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    // 前序遍历
    public void preOrder(){
        if(this.root != null) this.root.preOrder();
        else System.out.println("empty tree");
    }
    // 前序遍历
    public void infixOrder(){
        if(this.root != null) this.root.infixOrder();
        else System.out.println("empty tree");
    }
    // 前序遍历
    public void postOrder(){
        if(this.root != null) this.root.postOrder();
        else System.out.println("empty tree");
    }
}


// hero node 节点
class HeroNode{
    public int no;
    public String name;
    public HeroNode left;
    public HeroNode right;
    public HeroNode(int no, String name){
        this.no = no;
        this.name = name;
    }
    @Override
    public String toString() {
        return "Node [no=" + no + ", name=" + name + "]";
    }
    // 前序
    public void preOrder(){
        System.out.println(this);
        if(this.left != null) this.left.preOrder();
        if(this.right != null) this.right.preOrder();
    }
    // 中序
    public void infixOrder(){
        if(this.left != null) this.left.infixOrder();
        System.out.println(this);
        if(this.right != null) this.right.infixOrder();
    }
    // 后续
    public void postOrder(){
        if(this.left != null) this.left.postOrder();
        if(this.right != null) this.right.postOrder();
        System.out.println(this);
    }
}
