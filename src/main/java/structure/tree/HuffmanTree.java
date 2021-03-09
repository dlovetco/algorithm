package structure.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 哈夫曼树——最优二叉树
 * 设每个叶子节点都带权值 整个二叉树的带权路径长度为 w1*l1+w2*l2+...（w为叶子的权值，l为叶子到根节点的路径长度）
 * 则最优的二叉树的样子应该是，大权值的叶子节点离根节点尽量近
 * 对于一个唯一数组 哈夫曼树的形状是有可能不一样的 关键点在于对于两个相同的权值 每一次的排序会有区别
 * 例如：两个权值为10的根（子树的形状不同） 哪个是左 哪个是右并不能确保
 * 但是wpl是一样的（这个是由算法确保的）
 */
@Data
public class HuffmanTree {

    private HuffmanNode root;

    HuffmanTree(int[] data) {
        List<HuffmanNode> nodeList = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            nodeList.add(new HuffmanNode(data[i]));
        }
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            HuffmanNode left = nodeList.remove(0);
            HuffmanNode right = nodeList.remove(0);
            HuffmanNode temp = new HuffmanNode(left.getWeight() + right.getWeight());
            temp.setLeft(left);
            temp.setRight(right);
            //把temp放回列表
            nodeList.add(temp);
        }
        root = nodeList.get(0);
    }

    HuffmanTree(List<HuffmanNode> nodeList) {
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            HuffmanNode left = nodeList.remove(0);
            HuffmanNode right = nodeList.remove(0);
            HuffmanNode temp = new HuffmanNode(left.getWeight() + right.getWeight());
            temp.setLeft(left);
            temp.setRight(right);
            //把temp放回列表
            nodeList.add(temp);
        }
        root = nodeList.get(0);
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6};
        HuffmanTree huffmanTree = new HuffmanTree(data);
        System.out.println(huffmanTree.getRoot());
    }
}
