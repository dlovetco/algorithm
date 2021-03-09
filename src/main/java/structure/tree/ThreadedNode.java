package structure.tree;

import lombok.Data;

/**
 * 线索化节点 在原节点的基础上增加了指针类型
 */
@Data
public class ThreadedNode {

    ThreadedNode(int value) {
        this.value = value;
    }

    private int value;

    private ThreadedNode left;

    private ThreadedNode right;

    /**
     * 父指针
     * 用于后序线索化遍历
     */
    private ThreadedNode parent;

    /**
     * 如果是0表示指向的是左子树 如果是1表示指向的是前驱节点
     */
    private int leftType;

    /**
     * 如果是0表示指向的是右子树 如果是1表示指向的是后继节点
     */
    private int rightType;
}
