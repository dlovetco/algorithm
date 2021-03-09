package structure.tree;

import lombok.Data;

/**
 * 线索化二叉树——一棵二叉树中，所有的叶子节点的left和right指针都是空的
 * 如果把这些没用的指针指到二叉树中的某几个节点 这些指针则称为线索
 * 指针的指法与遍历方法有关
 * 例如按照中序遍历 可以得到一个链式数据结构 1 2 3 4 5 6
 * 其中 2的前驱节点是1 2的后继节点是3
 * 一个节点的left与right指针 并不一定是前序与后继节点 有时候的left指向的是一棵子树  有时候的left指向的是前驱节点
 */
@Data
public class ThreadedBinaryTree {

    /**
     * 当前节点的上一个节点  用于线索化中连接用（跟单链表逻辑差不多）
     */
    private ThreadedNode pre;

    /**
     * 中序线索化
     * 在中序遍历的过程中 把left指针和right指针为null的 都分别指向前后
     */
    public void midThreaded(ThreadedNode root) {
        if (root == null) {
            return;
        }
        //线索化左节点
        midThreaded(root.getLeft());

        //线索化逻辑
        if (root.getLeft() == null) {
            //第一次进来的时候pre是null 这也就让第一个数的前驱节点成为null
            root.setLeft(pre);
            root.setLeftType(1);
        }

        //这边的逻辑是当pre指向叶子节点时 把叶子节点的right指向父节点（此时root节点是pre的父节点）
        //第一次执行到这里的时候pre还没有赋值
        if (pre != null && pre.getRight() == null) {
            pre.setRight(root);
            pre.setRightType(1);
        }

        //这句话写在这个位置（当前节点线索化之后） 保证在处理的时候 pre是root前一个节点
        pre = root;

        //线索化右节点
        midThreaded(root.getRight());
    }

    /**
     * 按照中序线索遍历
     * （因为我们修改了叶子节点的left和right指针 所以一棵线索化的树 是不能被原来的遍历方式遍历的）
     */
    public void midThreadedTraversal(ThreadedNode root) {
        ThreadedNode temp = root;
        //线索化树的遍历是线性的
        while (temp != null) {
            //找到第一个要输出的节点
            while (temp.getLeftType() == 0) {
                temp = temp.getLeft();
            }
            System.out.print(temp.getValue() + " ");
            //如果右指针指的是后继节点 则一直输出
            while (temp.getRightType() == 1) {
                temp = temp.getRight();
                System.out.print(temp.getValue() + " ");
            }

            //跳出循环说明右指针指的是null或者指的是右子树
            //此时直接转移到右子树
            temp = temp.getRight();
        }
    }

    /**
     * 前序线索化
     */
    public void preThreaded(ThreadedNode root) {
        if (root == null) {
            return;
        }

        if (root.getLeft() == null) {
            root.setLeft(pre);
            root.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(root);
            pre.setRightType(1);
        }

        pre = root;

        //线索化左节点
        if (root.getLeftType() != 1) {
            preThreaded(root.getLeft());
        }
        //线索化右节点
        preThreaded(root.getRight());
    }

    /**
     * 前序线索化遍历
     */
    public void preThreadedTraversal(ThreadedNode root) {
        ThreadedNode temp = root;
        while (temp != null) {
            //找到第一个要输出的节点
            while (temp.getLeftType() == 0) {
                System.out.print(temp.getValue() + " ");
                temp = temp.getLeft();
            }

            //如果右指针指的是后继节点 则一直输出
            //或者右指针指的是null（说明是最后一个节点 也让他往下指 打印出最后一个节点方便循环退出）
            while (temp != null && (temp.getRightType() == 1 || temp.getRight() == null)) {
                System.out.print(temp.getValue() + " ");
                temp = temp.getRight();
            }
        }
    }

    /**
     * 后序线索化
     */
    public void postThreaded(ThreadedNode root) {
        if (root == null) {
            return;
        }

        //线索化左节点
        postThreaded(root.getLeft());

        //线索化右节点
        postThreaded(root.getRight());

        if (root.getLeft() == null) {
            root.setLeft(pre);
            root.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(root);
            pre.setRightType(1);
        }
        pre = root;
    }

    /**
     * 后序线索化遍历
     * 单纯使用左右指针无法实现 需要添加父指针辅助
     */
    public void postThreadedTraversal(ThreadedNode root) {
        ThreadedNode temp = root;
        //找到第一个要输出的节点
        while (temp.getLeftType() == 0) {
            temp = temp.getLeft();
        }

        ThreadedNode pre = null;
        while (temp != null) {

            //如果右节点是线索化节点则输出当前节点并指向下一个节点
            if (temp.getRightType() == 1) {
                System.out.print(temp.getValue() + " ");
                pre = temp;
                temp = temp.getRight();
            } else {
                //如果当前节点的右节点与pre一样则输出当前节点并进入父节点
                if (temp.getRight() == pre) {
                    System.out.print(temp.getValue() + " ");
                    pre = temp;
                    temp = temp.getParent();
                } else {
                    //如果是当前节点的左节点与pre一样 则找到当前节点的右子树中最左的节点
                    //相当于在右子树中重新开始一轮遍历
                    temp = temp.getRight();
                    while (temp != null && temp.getLeftType() == 0) {
                        temp = temp.getLeft();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        ThreadedNode root = new ThreadedNode(4);
        ThreadedNode node1 = new ThreadedNode(1);
        ThreadedNode node2 = new ThreadedNode(2);
        ThreadedNode node3 = new ThreadedNode(3);
        ThreadedNode node5 = new ThreadedNode(5);
        ThreadedNode node6 = new ThreadedNode(6);
        ThreadedNode node7 = new ThreadedNode(7);
        root.setLeft(node2);
        node2.setParent(root);
        node2.setLeft(node1);
        node1.setParent(node2);
        node2.setRight(node3);
        node3.setParent(node2);
        root.setRight(node6);
        node6.setParent(root);
        node6.setLeft(node5);
        node5.setParent(node6);
        node6.setRight(node7);
        node7.setParent(node6);
//        tree.midThreaded(root);
//        tree.midThreadedTraversal(root);

//        tree.preThreaded(root);
//        tree.preThreadedTraversal(root);

        tree.postThreaded(root);
        tree.postThreadedTraversal(root);

    }
}
