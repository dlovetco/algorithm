package structure.tree;

import lombok.Data;

@Data
public class HuffmanNode implements Comparable<HuffmanNode> {

    private int weight;

    private String value;

    private HuffmanNode left;

    private HuffmanNode right;

    HuffmanNode(String value) {
        this.value = value;
    }

    HuffmanNode(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.weight > o.getWeight() ? 1 : -1;
    }
}
