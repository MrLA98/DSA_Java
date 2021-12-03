package com.datastructure.tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1, "tom");
        Node node2 = new Node(2, "jack");
        Node node3 = new Node(3, "luh");
        Node node4 = new Node(4, "max");
        Node node5 = new Node(5, "dark");
        Node node6 = new Node(6, "white");

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

        ThreadBinaryTree tBTree = new ThreadBinaryTree();
        tBTree.setRoot(node1);
        tBTree.threadedNodes();
        // System.out.println("4的左="+node4.left);
        // System.out.println("4的右="+node4.right);
        // System.out.println("5的左="+node5.left);
        // System.out.println("5的右="+node5.right);
        // System.out.println("3的右="+node3.right);
        // System.out.println("6的左="+node6.left);
        // System.out.println("6的右="+node6.right);
        tBTree.threadedList();
        
    }
}

// 二叉树
class ThreadBinaryTree{
    private Node root;
    private Node pre = null;
    public void setRoot(Node root){
        this.root = root;
    }

    // 遍历
    public void threadedList(){
        // 存储当前节点
        Node node = root;
        while(node != null){
            while(!node.leftType){
                node = node.left;
            }
            System.out.println(node);
            while(node.rightType){
                node = node.right;
                System.out.println(node);
            }
            node = node.right;
        }
    }
    
    // 中序线索化
    public void threadedNodes(){
        threadedNodes(root);
    }
    private void threadedNodes(Node node){
        if(node == null) return;
        // 线索化左子树
        threadedNodes(node.left);
        // 线索化当前
        if(node.left == null){ 
            node.left = pre;
            node.leftType = true;
        }
        if(pre != null && pre.right == null){  
            pre.right = node;
            pre.rightType = true;
        }
        pre = node;
        // 线索化右子树
        threadedNodes(node.right);
    }
}

// 节点类
class Node{
    public int no;
    public String name;
    public Node left;
    public Node right;
    public boolean leftType; // 0表示是左子树 1表示是中序前驱 
    public boolean rightType; // 0表示是右子树 1表示是中序后继

    public Node(int no, String name){
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node [no=" + no + ", name="+name+"]";
    }

    
}
