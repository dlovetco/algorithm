package structure.tree;

import lombok.Data;

/**
 * 平衡二叉树
 * 左右子树的高度差不能超过1
 */
@Data
public class BalancedBinaryTree {

    private Node root;

    public BalancedBinaryTree(Node root) {
        this.root = root;
    }


    public int getMaxHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(root.getLeft() == null ? 0 : getMaxHeight(root.getLeft()), root.getRight() == null ? 0 : getMaxHeight(root.getRight())) + 1;
    }

    public boolean ifBalance(Node root) {
        int left = getMaxHeight(root.getLeft());
        int right = getMaxHeight(root.getRight());
        return Math.abs(left - right) <= 1;
    }

    /**
     * 前序遍历 中->左->右
     */
    public void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getValue() + " ");
        if (root.getLeft() != null) {
            preOrderTraversal(root.getLeft());
        }
        if (root.getRight() != null) {
            preOrderTraversal(root.getRight());
        }
    }

    public Node keepBalance(Node root) {
        int left = getMaxHeight(root.getLeft());
        int right = getMaxHeight(root.getRight());

        //左右子树高度差大于1则说明需要调整
        //根据高度差判断要旋转几次
        while (!ifBalance(root)) {
            if (left > right) {
                root = rightHanded(root);
            } else {
                root = leftHanded(root);
            }
        }
        return root;
    }

    /**
     * 左旋 逆时针旋转 让左子树增加高度 右子树减少高度
     * 需要注意 左旋中要避免 root.getRight的左子树高度大于右子树高度 否则有可能会出现旋转之后出现还是不平衡的情况 需要右转
     * 如果出现root.getRight的左子树高度大于右子树高度 则需要对root.getRight进行右转
     * 1、newRoot = root.right
     * 2、newRoot.left = root
     * 3、原newRoot的左节点如果存在则成为oldRoot.right
     */
    public Node leftHanded(Node root) {
        while (getMaxHeight(root.getRight().getLeft()) > getMaxHeight(root.getRight().getRight())) {
            root.setRight(rightHanded(root.getRight()));
        }
        Node newRoot = root.getRight();
        Node tempLeft = newRoot.getLeft();
        newRoot.setLeft(root);

        root.setRight(tempLeft);
        return newRoot;
    }

    /**
     * 右旋 顺时针旋转 让右子树增加高度 左子树减少高度
     * 1、newRoot = root.left
     * 2、newRoot.right = root
     * 3、原newRoot的右节点如果存在则成为oldRoot.left
     */
    public Node rightHanded(Node root) {
        while (getMaxHeight(root.getLeft().getRight()) > getMaxHeight(root.getLeft().getLeft())) {
            root.setLeft(leftHanded(root.getLeft()));
        }
        Node newRoot = root.getLeft();
        Node tempLeft = newRoot.getRight();
        newRoot.setRight(root);

        root.setLeft(tempLeft);
        return newRoot;
    }

    public static void main(String[] args) {
        Node root = new Node(5);

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node7 = new Node(7);
        Node node6 = new Node(6);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);

        root.setLeft(node3);
        root.setRight(node9);
        node3.setLeft(node1);
        node3.setRight(node2);
        node9.setLeft(node6);
        node6.setRight(node7);
        node7.setRight(node8);
        node9.setRight(node10);

        BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree(root);
        balancedBinaryTree.preOrderTraversal(balancedBinaryTree.getRoot());

        System.out.println();
        System.out.println(balancedBinaryTree.getMaxHeight(balancedBinaryTree.getRoot()));
        System.out.println(balancedBinaryTree.getMaxHeight(balancedBinaryTree.getRoot().getRight()));
        System.out.println(balancedBinaryTree.getMaxHeight(balancedBinaryTree.getRoot().getLeft()));

        Node newRoot = balancedBinaryTree.keepBalance(balancedBinaryTree.getRoot());
        balancedBinaryTree.setRoot(newRoot);
        balancedBinaryTree.preOrderTraversal(balancedBinaryTree.getRoot());
        System.out.println();
        System.out.println(balancedBinaryTree.getMaxHeight(balancedBinaryTree.getRoot()));
        System.out.println(balancedBinaryTree.getMaxHeight(balancedBinaryTree.getRoot().getRight()));
        System.out.println(balancedBinaryTree.getMaxHeight(balancedBinaryTree.getRoot().getLeft()));
    }
}
