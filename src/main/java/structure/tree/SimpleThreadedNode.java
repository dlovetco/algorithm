package structure.tree;

import lombok.Data;

/**
 * 线索化节点（自己理解的简易方式）
 */
@Data
public class SimpleThreadedNode {

    SimpleThreadedNode(int value) {
        this.value = value;
    }

    private int value;

    private SimpleThreadedNode left;

    private SimpleThreadedNode right;

    private SimpleThreadedNode threadedLeft;

    private SimpleThreadedNode threadedRight;

}
