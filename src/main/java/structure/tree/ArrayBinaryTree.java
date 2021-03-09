package structure.tree;

import lombok.Data;

/**
 * 顺序存储二叉树——使用数组来存放二叉树 数组的下标能够反映出二叉树的节点关系  二叉树是完全二叉树
 * 二叉树的层序遍历
 * 这种二叉树的特点：
 * 第n个元素的左子节点数组下标为2n+1
 * 第n个元素的右子节点数组下标为2n+2
 * 第n个元素的父节点数组下标为(n-1)/2
 * n表示二叉树的第几个元素（从0开始数） 一层一层从左到右数过去
 */
@Data
public class ArrayBinaryTree {

    //既是数组 也是一棵顺序存储二叉树 数组中存放的顺序称之为层序
    int[] data;

    ArrayBinaryTree(int[] data) {
        this.data = data;
    }

    /**
     * 前序遍历 此时的2n+1 变成了left 2n+2变成了right
     */
    public void preOrderTraversal(int index) {
        if (data == null || data.length == 0) {
            return;
        }
        //首先打印自身
        System.out.print(data[index] + " ");

        if (2 * index + 1 < data.length) {
            //左递归
            preOrderTraversal(2 * index + 1);
        }

        if (2 * index + 2 < data.length) {
            //右递归
            preOrderTraversal(2 * index + 2);
        }
    }

    public static void main(String[] args) {

        int[] data = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree tree = new ArrayBinaryTree(data);
        tree.preOrderTraversal(0);
        System.out.println();
    }
}
