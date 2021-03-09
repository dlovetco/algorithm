package structure.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@Data
public class BinaryTree {

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

    /**
     * 非递归方式前序遍历
     */
    public void preOrderTraversalWithoutRecursive(Node root) {
        if (root == null) {
            return;
        }
        //借助栈 通过节点的反复出栈入栈来实现遍历
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            Node tempNode = nodeStack.pop();
            System.out.print(tempNode.getValue() + " ");
            //先压右子树 这样左子树就会在上面
            if (tempNode.getRight() != null) {
                nodeStack.push(tempNode.getRight());
            }
            if (tempNode.getLeft() != null) {
                nodeStack.push(tempNode.getLeft());
            }
        }
    }

    /**
     * 中序遍历 左->中->右
     */
    public void midOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        if (root.getLeft() != null) {
            midOrderTraversal(root.getLeft());
        }
        System.out.print(root.getValue() + " ");
        if (root.getRight() != null) {
            midOrderTraversal(root.getRight());
        }
    }

    /**
     * 非递归方式中序遍历
     */
    public void midOrderTraversalWithoutRecursive(Node root) {
        if (root == null) {
            return;
        }

        //借助栈 通过节点的反复出栈入栈来实现遍历
        Stack<Node> nodeStack = new Stack<>();
        //用来判断栈里面某个节点有没有分解过
        Set<Integer> visitedSet = new HashSet<>();

        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            Node tempNode = nodeStack.peek();
            //表示当前节点是否需要分解
            boolean ifVisited = false;

            //如果有右节点 则自己需要出栈
            if (tempNode.getRight() != null && !visitedSet.contains(tempNode.getValue())) {
                nodeStack.pop();
                nodeStack.push(tempNode.getRight());
                nodeStack.push(tempNode);
                ifVisited = true;
            }

            //如果有左节点 则把左节点入栈
            if (tempNode.getLeft() != null && !visitedSet.contains(tempNode.getValue())) {
                nodeStack.push(tempNode.getLeft());
                ifVisited = true;
            }

            //如果分解了 则进入下一个循环 不输出
            if (ifVisited) {
                visitedSet.add(tempNode.getValue());
                continue;
            }

            System.out.print(tempNode.getValue() + " ");
            nodeStack.pop();
        }
    }

    /**
     * 后序遍历  左->右->中
     */
    public void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        if (root.getLeft() != null) {
            postOrderTraversal(root.getLeft());
        }
        if (root.getRight() != null) {
            postOrderTraversal(root.getRight());
        }
        System.out.print(root.getValue() + " ");
    }

    /**
     * 非递归方式后序遍历
     */
    public void postOrderTraversalWithoutRecursive(Node root) {
        if (root == null) {
            return;
        }

        //借助栈 通过节点的反复出栈入栈来实现遍历
        Stack<Node> nodeStack = new Stack<>();
        //用来判断栈里面某个节点有没有分解过
        Set<Integer> visitedSet = new HashSet<>();

        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            Node tempNode = nodeStack.peek();
            //表示当前节点是否需要分解
            boolean ifVisited = false;

            //如果有右节点 则把右节点入栈  必须先判断右节点
            if (tempNode.getRight() != null && !visitedSet.contains(tempNode.getValue())) {
                nodeStack.push(tempNode.getRight());
                ifVisited = true;
            }

            //如果有左节点 则把左节点入栈
            if (tempNode.getLeft() != null && !visitedSet.contains(tempNode.getValue())) {
                nodeStack.push(tempNode.getLeft());
                ifVisited = true;
            }

            //如果分解了 则进入下一个循环 不输出
            if (ifVisited) {
                visitedSet.add(tempNode.getValue());
                continue;
            }

            System.out.print(tempNode.getValue() + " ");
            nodeStack.pop();
        }

    }

    /**
     * 前序查找
     */
    public boolean preSearch(Node root, int value, String path, List<Integer> compareList) {
        if (root == null) {
            return false;
        }
        compareList.add(root.getValue());
        if (root.getValue() == value) {
            System.out.println(path.substring(0, path.length() - 2));
            return true;
        }
        if (root.getLeft() != null) {
            if (preSearch(root.getLeft(), value, path + "左->", compareList)) {
                return true;
            }
        }

        if (root.getRight() != null) {
            if (preSearch(root.getRight(), value, path + "右->", compareList)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 中序查找
     */
    public boolean midSearch(Node root, int value, String path, List<Integer> compareList) {
        if (root == null) {
            return false;
        }
        if (root.getLeft() != null) {
            if (midSearch(root.getLeft(), value, path + "左->", compareList)) {
                return true;
            }
        }
        compareList.add(root.getValue());
        if (root.getValue() == value) {
            System.out.println(path.substring(0, path.length() - 2));
            return true;
        }
        if (root.getRight() != null) {
            if (midSearch(root.getRight(), value, path + "右->", compareList)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 后序查找
     */
    public boolean postSearch(Node root, int value, String path, List<Integer> compareList) {
        if (root == null) {
            return false;
        }
        if (root.getLeft() != null) {
            if (postSearch(root.getLeft(), value, path + "左->", compareList)) {
                return true;
            }
        }
        if (root.getRight() != null) {
            if (postSearch(root.getRight(), value, path + "右->", compareList)) {
                return true;
            }
        }
        compareList.add(root.getValue());
        if (root.getValue() == value) {
            System.out.println(path.substring(0, path.length() - 2));
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        root.setLeft(node2);
        node2.setLeft(node1);
        node2.setRight(node3);
        root.setRight(node6);
        node6.setLeft(node5);
        node6.setRight(node7);

        BinaryTree binaryTree = new BinaryTree();
        System.out.println("前序遍历--------------------");
        binaryTree.preOrderTraversal(root);
        System.out.println();
        binaryTree.preOrderTraversalWithoutRecursive(root);
        System.out.println();
        System.out.println("中序遍历--------------------");
        binaryTree.midOrderTraversal(root);
        System.out.println();
        binaryTree.midOrderTraversalWithoutRecursive(root);
        System.out.println();
        System.out.println("后序遍历--------------------");
        binaryTree.postOrderTraversal(root);
        System.out.println();
        binaryTree.postOrderTraversalWithoutRecursive(root);
        System.out.println();

        System.out.println("前序搜索--------------------");
        List<Integer> compareList = new ArrayList<>();
        binaryTree.preSearch(root, 3, "", compareList);
        System.out.println("比较次数:" + compareList.size());

        System.out.println("中序搜索--------------------");
        compareList = new ArrayList<>();
        binaryTree.midSearch(root, 3, "", compareList);
        System.out.println("比较次数:" + compareList.size());

        System.out.println("后序搜索--------------------");
        compareList = new ArrayList<>();
        binaryTree.postSearch(root, 3, "", compareList);
        System.out.println("比较次数:" + compareList.size());

    }
}

