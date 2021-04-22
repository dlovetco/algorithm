package algorithm;

import algorithm.sort.BubbleSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    public List<BubbleSort[]> bubbleSortList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        Test test = new Test();
        while (true) {
            test.setALogFGCObjects();
            Thread.sleep(200);
            test.setALotYGCObjects();
        }

    }

    public void setALotYGCObjects() {
        int size = (int) (Math.random() * 1000);
        BubbleSort[] strings = new BubbleSort[size];
        for (int i = 0; i < size; i++) {
            strings[i] = new BubbleSort(new int[0]);
        }
        List<BubbleSort[]> bubbleList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            bubbleList.add(strings);
        }
    }

    public void setALogFGCObjects() {
        int size = (int) (Math.random() * 1000);
        BubbleSort[] strings = new BubbleSort[size];
        for (int i = 0; i < size; i++) {
            strings[i] = new BubbleSort(new int[0]);
        }
        for (int i = 0; i < 100000; i++) {
            bubbleSortList.add(strings);
        }
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




