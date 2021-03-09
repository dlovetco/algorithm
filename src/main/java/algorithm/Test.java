package algorithm;

import java.util.LinkedList;
import java.util.List;

public class Test {


    public static void main(String[] args) {
    }

    LinkedList<List<Integer>> path = new LinkedList<>();
    LinkedList<Integer> tempPath = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        getPath(root, sum, 0);
        return path;
    }

    public void getPath(TreeNode root, int sum, int tempSum) {
        tempPath.add(root.val);
        tempSum += root.val;
        if (root.right == null && root.left == null) {
            if (tempSum == sum) {
                path.add(new LinkedList<>(tempPath));
            }
            tempPath.pollLast();
            return;
        }
        if (root.right != null) {
            getPath(root.right, sum, tempSum);
        }
        if (root.left != null) {
            getPath(root.left, sum, tempSum);
        }
        tempPath.pollLast();
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}




