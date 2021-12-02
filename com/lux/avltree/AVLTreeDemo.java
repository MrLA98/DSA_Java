package com.lux.avltree;

public class AVLTreeDemo {
    public static void main(String[] args) {
        // int[] arr = { 4, 3, 6, 5, 7, 8 };
        int[] arr = { 10, 12, 8, 9, 7, 6 };
        AVLTree aTree = new AVLTree();
        for (int item : arr) {
            aTree.add(new ANode(item));
        }
        aTree.infixOrder();
        System.out.println("height = " + aTree.getRoot().getHeight());
        System.out.println("left_h = " + aTree.getRoot().getLeftH());
        System.out.println("right_h = " + aTree.getRoot().getRightH());
        System.out.println(isBalance(aTree.getRoot()));
    }

    public  static boolean isBalance(ANode node){
        boolean a = node.left == null ? true : isBalance(node.left);
        boolean b = node.right == null ? true : isBalance(node.right);
        return a && b && Math.abs(node.getLeftH() - node.getRightH()) <= 1;
    }
}

// BST
class AVLTree {
    private ANode root;

    // 获取根节点
    public ANode getRoot() {
        return root;
    }

    // 添加节点
    public void add(ANode node) {
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
    public ANode find(int index) {
        if (root == null)
            return null;
        return root.find(index);
    }

    // 查找父亲
    public ANode findParent(int index) {
        if (root == null)
            return null;
        return root.findParent(index);
    }

    // 删除节点
    public void del(int index) {
        ANode target = find(index);
        // 没找到或者是空树
        if (target == null)
            return;
        // 找到了，是根节点，且根节点没有孩子
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        ANode parent = findParent(index); // 找父亲节点
        // 叶子节点
        if (target.left == null && target.right == null) {
            // 是其父亲的左孩子，父亲左置空即可
            if (parent.left != null && parent.left.value == target.value) {
                parent.left = null;
            } else { // 是其父亲的右孩子，父亲右置空即可
                parent.right = null;
            }
        }
        // 左右子树都不为空
        else if (target.left != null && target.right != null) {
            // 将右树最小的节点删掉，然后拿出来
            ANode RightMin = delRightMin(target.right);
            // 将其信息复制给target，target的左右孩子不变，父亲不变
            target.value = RightMin.value;
        }
        // 有一个子树是空
        else {
            if (parent == null) { // 只剩下头节点和其一边的子树了
                root = target.left != null ? target.left : target.right;
                return; // 直接修改root，让其为头节点的某个子树即可
            }
            if (target.left != null) { // 左子树不为空
                // 目标是其父亲的左孩子
                if (parent.left != null && parent.left.value == target.value) {
                    parent.left = target.left;
                } else { // 目标是父亲的右孩子
                    parent.right = target.left;
                }
            } else { // 右子树不为空
                // 目标是其父亲的左孩子
                if (parent.left != null && parent.left.value == target.value) {
                    parent.left = target.right;
                } else { // 目标是其父亲的右孩子
                    parent.right = target.right;
                }
            }
        }
    }

    // 传入右子树头节点，删除右子树最小的节点
    private ANode delRightMin(ANode node) {
        ANode cur = node;
        while (cur.left != null) {
            cur = cur.left;
        }
        del(cur.value);
        return cur;
    }
}

// 节点
class ANode {
    public int value;
    public ANode left;
    public ANode right;

    // 构造器
    public ANode(int value) {
        this.value = value;
    }

    // 以当前节点为头左旋
    private void leftRotate() {
        // 核心：在头节点只改变值的情况下，取缔其右节点
        // 创建新的节点
        ANode newNode = new ANode(value);
        newNode.left = left;
        newNode.right = right.left;
        // 头节点修改
        value = right.value;
        left = newNode;
        // C++: *ANode cur = right;
        right = right.right;
        // C++: delete cur;
    }

    // 以当前节点为头右旋
    private void rightRotate() {
        // 创建新的节点
        ANode newNode = new ANode(value);
        newNode.right = right;
        newNode.left = left.right;
        // 头节点修改
        value = left.value;
        right = newNode;
        // C++: *ANode cur = left;
        left = left.left;
        // C++: delete cur;
    }

    // 返回当前 以当前节点为根节点的树 的高度
    public int getHeight() {
        return Math.max(left == null ? 0 : left.getHeight(),
                right == null ? 0 : right.getHeight()) + 1;
    }

    // 返回当前节点左子树高度
    public int getLeftH() {
        return left == null ? 0 : left.getHeight();
    }

    // 返回当前节点右子树高度
    public int getRightH() {
        return right == null ? 0 : right.getHeight();
    }

    // 在当前节点下添加节点
    public void add(ANode node) {
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

        // 调整平衡
        selfBalance();
    }

    // 调整平衡
    private void selfBalance(){
        if (getRightH() - getLeftH() > 1) { // 右树比左树大超过1
            // 右节点的左树高度>右节点的右树高度
            if(right.getLeftH() > right.getRightH()){
                right.rightRotate();
            }
            leftRotate();
            return;
        }
        if (getLeftH() - getRightH() > 1) { // 左树比右树大超过1
            // 左节点的右树高度>左节点的左树高度
            if(left.getRightH() > left.getLeftH()){
                left.leftRotate();
            }
            rightRotate();
        }
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
    public ANode find(int index) {
        if (this.value == index)
            return this;
        else if (this.value > index) {
            return this.left == null ? null : this.left.find(index);
        } else
            return this.right == null ? null : this.right.find(index);
    }

    // 查找目标节点的父亲
    public ANode findParent(int index) {
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
