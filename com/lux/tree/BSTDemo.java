package com.lux.tree;

public class BSTDemo {
    public static void main(String[] args) {
        int[] arr = { 4, 2, 6, 9, 1, 5, 7, 3, 0 };
        BST bst = new BST();
        for (int item : arr) {
            bst.add(new BNode(item));
        }
        bst.infixOrder();
        //System.out.println(bst.find(-1));
    }
}

// BST
class BST {
    private BNode root;

    // 添加节点
    public void add(BNode node) {
        if (root == null)
            root = node;
        else
            root.add(node);
    }

    // 中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }
        root.infixOrder();
    }

    // 查找
    public BNode find(int index) {
        if (root == null)
            return null;
        return root.find(index);
    }

    // 查找父亲
    public BNode findParent(int index) {
        if (root == null)
            return null;
        return root.findParent(index);
    }

    // 删除节点
    public void del(int index) {
        BNode target = find(index);
        // 没找到或者是空树
        if (target == null)
            return;
        // 找到了，是根节点，且根节点没有孩子
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        BNode parent = findParent(index);
        // 叶子节点
        if (target.left == null && target.right == null) {
            if (parent.left != null && parent.left.value == target.value)
                parent.left = null;
            else
                parent.right = null;
            return;
        // 左右子树都不为空
        } else if(target.left != null && target.right != null){

        // 有一个子树是空
        } else{

        }
    }
}

// 节点
class BNode {
    public int value;
    public BNode left;
    public BNode right;

    // 构造器
    public BNode(int value) {
        this.value = value;
    }

    // 在当前节点下添加节点
    public void add(BNode node) {
        // 空节点直接返回
        if (node == null)
            return;
        // 比当前节点值小，往左左
        if (node.value < this.value) {
            // 当前节点左为空，直接挂上
            if (this.left == null) {
                this.left = node;
            } else { // 左不为空，递归
                this.left.add(node);
            }
        } else if (node.value > this.value) { // 大于当前节点，往右走
            // 当前节点右为空，直接挂上
            if (this.right == null) {
                this.right = node;
            } else { // 右不为空，递归
                this.right.add(node);
            }
        } else
            return; // 等于直接return
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.infixOrder();
    }

    @Override
    public String toString() {
        return "node[value = " + value + "]";
    }

    // 查找
    public BNode find(int index) {
        if (this.value == index)
            return this;
        else if (this.value > index) {
            return this.left == null ? null : this.left.find(index);
        } else
            return this.right == null ? null : this.right.find(index);
    }

    // 查找目标节点的父亲
    public BNode findParent(int index) {
        if ((this.left != null && this.left.value == index)
                || (this.right != null && this.right.value == index)) {
            return this;
        } else {
            if (this.left != null && this.value > index) {
                return this.left.findParent(index);
            } else if (this.right != null && this.value < index) {
                return this.right.findParent(index);
            } else
                return null;
        }
    }
}