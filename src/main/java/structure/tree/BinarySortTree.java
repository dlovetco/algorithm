package structure.tree;

import lombok.Data;

/**
 * 二叉排序树（BST）
 * 对于任何的非叶子节点 要求右子节点大于左子节点
 * 右节点大于根节点 左节点小于根节点  这样使用中序遍历出来的就是排好序的
 */
@Data
public class BinarySortTree {

    private int[] data;

    private Node root;

    BinarySortTree(int[] data) {
        this.data = data;
    }

    /**
     * 创建排序二叉树
     */
    public Node createBST() {
        if (data == null || data.length == 0) {
            return null;
        }
        root = new Node(data[0]);
        for (int i = 1; i < data.length; i++) {
            addNode(data[i], root);
        }
        return root;
    }

    public void addNode(int value, Node root) {
        //如果传入的值比根节点要小
        if (value <= root.getValue()) {
            //如果没有左子节点则直接放入左子节点
            if (root.getLeft() == null) {
                root.setLeft(new Node(value));
            } else {
                addNode(value, root.getLeft());
            }
        }

        //如果传入的值比根节点要大
        if (value > root.getValue()) {
            //如果没有右子节点则直接放入右子节点
            if (root.getRight() == null) {
                root.setRight(new Node(value));
            } else {
                addNode(value, root.getRight());
            }
        }
    }

    /**
     * bst 删除节点 需要分情况考虑
     */
    public void deleteNode(int value) {
        Node temp = root;
        //根据value大小寻找目标节点
        Node targetNode = null;
        Node targetParent = root;
        while (temp != null) {
            if (value > temp.getValue()) {
                targetParent = temp;
                temp = temp.getRight();
            } else if (value < temp.getValue()) {
                targetParent = temp;
                temp = temp.getLeft();
            } else if (value == temp.getValue()) {
                targetNode = temp;
                break;
            }
        }
        //如果没有找到
        if (targetNode == null) {
            return;
        }

        //如果要删除的节点没有子节点则直接通过父节点干掉
        if (targetNode.getRight() == null && targetNode.getLeft() == null) {

            if (targetNode == root) {
                //如果删除的是根节点
                root = null;
            }
            if (targetParent.getLeft() == targetNode) {
                targetParent.setLeft(null);
            } else if (targetParent.getRight() == targetNode) {
                targetParent.setRight(null);
            }
        } else if (targetNode.getRight() != null && targetNode.getLeft() == null) {
            if (targetNode == root) {
                //如果删除的是根节点
                root = root.getRight();
            }
            //如果要删除的节点有一个右节点 则直接使用右节点代替自己
            if (targetParent.getLeft() == targetNode) {
                targetParent.setLeft(targetNode.getRight());
            } else if (targetParent.getRight() == targetNode) {
                targetParent.setRight(targetNode.getRight());
            }

        } else if (targetNode.getRight() == null && targetNode.getLeft() != null) {
            if (targetNode == root) {
                //如果删除的是根节点
                root = root.getLeft();
            }
            //如果要删除的节点有一个左节点 则直接使用左节点代替自己
            if (targetParent.getLeft() == targetNode) {
                targetParent.setLeft(targetNode.getLeft());
            } else if (targetParent.getRight() == targetNode) {
                targetParent.setRight(targetNode.getLeft());
            }
        } else if (targetNode.getRight() != null && targetNode.getLeft() != null) {
            //如果要删除的节点左右子节点都有 则有两种策略
            //1、从右子树中选一个最小的代替自己 下面代码找右子树的最小值
            //2、从左子树中选一个最大的代替自己
            Node tempNode = targetNode;
            //先往右 然后一直往左 就能找到最小的节点
            tempNode = tempNode.getRight();
            while (tempNode.getLeft() != null) {
                tempNode = tempNode.getLeft();
            }
            //接下去的递归调用需要删除的的节点必然只有两种情况
            //1、本身是叶子节点
            //2、只有一个子节点
            deleteNode(tempNode.getValue());

            //删除完后 用删除完的值覆盖原本要删除的节点targetNode
            targetNode.setValue(tempNode.getValue());
        }
    }


    public void midOrderTraversal(Node rootNode) {
        if (rootNode == null) {
            System.out.println("二叉树为空");
            return;
        }
        if (rootNode.getLeft() != null) {
            midOrderTraversal(rootNode.getLeft());
        }
        System.out.print(rootNode.getValue() + " ");
        if (rootNode.getRight() != null) {
            midOrderTraversal(rootNode.getRight());
        }
    }

    public static void main(String[] args) {
        int[] data = {4, 2, 1, 3, 6, 7, 5};
        BinarySortTree binarySortTree = new BinarySortTree(data);
        binarySortTree.createBST();
        binarySortTree.midOrderTraversal(binarySortTree.getRoot());
        System.out.println();
        binarySortTree.deleteNode(1);
        binarySortTree.midOrderTraversal(binarySortTree.getRoot());
        System.out.println();
        binarySortTree.deleteNode(2);
        binarySortTree.midOrderTraversal(binarySortTree.getRoot());
        System.out.println();
        binarySortTree.deleteNode(3);
        binarySortTree.midOrderTraversal(binarySortTree.getRoot());
        System.out.println();
        binarySortTree.deleteNode(4);
        binarySortTree.midOrderTraversal(binarySortTree.getRoot());
        System.out.println();
        binarySortTree.deleteNode(5);
        binarySortTree.midOrderTraversal(binarySortTree.getRoot());
        System.out.println();
        binarySortTree.deleteNode(6);
        binarySortTree.midOrderTraversal(binarySortTree.getRoot());
        System.out.println();
        binarySortTree.deleteNode(7);
        binarySortTree.midOrderTraversal(binarySortTree.getRoot());
        System.out.println();
    }
}
