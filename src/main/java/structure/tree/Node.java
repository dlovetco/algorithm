package structure.tree;

import lombok.Data;

@Data
class Node implements Comparable<Node> {
    private int value;

    private Node left;

    private Node right;

    Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value > o.getValue() ? 1 : -1;
    }
}
