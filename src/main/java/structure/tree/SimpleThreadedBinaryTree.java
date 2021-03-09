package structure.tree;

import lombok.Data;

/**
 * 在线索化过程中不破坏原来的指针
 * 使用新的指针线索化
 */
@Data
public class SimpleThreadedBinaryTree {

    private SimpleThreadedNode pre;

    /**
     * 前序线索化
     */
    public void preThreaded(SimpleThreadedNode root) {
        if (root == null) {
            return;
        }

        root.setThreadedLeft(pre);
        if (pre != null) {
            pre.setThreadedRight(root);
        }
        pre = root;

        preThreaded(root.getLeft());
        preThreaded(root.getRight());

    }

    /**
     * 中序线索化
     */
    public void midThreaded(SimpleThreadedNode root) {
        if (root == null) {
            return;
        }
        midThreaded(root.getLeft());
        root.setThreadedLeft(pre);
        if (pre != null) {
            pre.setThreadedRight(root);
        }
        pre = root;
        midThreaded(root.getRight());
    }

    /**
     * 后序线索化
     */
    public void postThreaded(SimpleThreadedNode root) {
        if (root == null) {
            return;
        }
        postThreaded(root.getLeft());
        postThreaded(root.getRight());
        root.setThreadedLeft(pre);
        if (pre != null) {
            pre.setThreadedRight(root);
        }
        pre = root;
    }

    public void preThreadedTraversal(SimpleThreadedNode root) {
        if (root == null) {
            return;
        }
        while (root != null) {
            System.out.print(root.getValue() + " ");
            root = root.getThreadedRight();
        }
    }

    public void midThreadedTraversal(SimpleThreadedNode root) {
        if (root == null) {
            return;
        }
        //找到第一个要输出的节点
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        while (root != null) {
            System.out.print(root.getValue() + " ");
            root = root.getThreadedRight();
        }
    }

    public void postThreadedTraversal(SimpleThreadedNode root) {
        if (root == null) {
            return;
        }
        //找到第一个要输出的节点
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        while (root != null) {
            System.out.print(root.getValue() + " ");
            root = root.getThreadedRight();
        }
    }

    public static void main(String[] args) {
        SimpleThreadedNode root = new SimpleThreadedNode(4);
        SimpleThreadedNode node1 = new SimpleThreadedNode(1);
        SimpleThreadedNode node2 = new SimpleThreadedNode(2);
        SimpleThreadedNode node3 = new SimpleThreadedNode(3);
        SimpleThreadedNode node5 = new SimpleThreadedNode(5);
        SimpleThreadedNode node6 = new SimpleThreadedNode(6);
        SimpleThreadedNode node7 = new SimpleThreadedNode(7);
        root.setLeft(node2);
        node2.setLeft(node1);
        node2.setRight(node3);
        root.setRight(node6);
        node6.setLeft(node5);
        node6.setRight(node7);

        SimpleThreadedBinaryTree tree = new SimpleThreadedBinaryTree();
//        tree.preThreaded(root);
//        tree.preThreadedTraversal(root);
//        tree.midThreaded(root);
//        tree.midThreadedTraversal(root);
        tree.postThreaded(root);
        tree.postThreadedTraversal(root);
    }
}
