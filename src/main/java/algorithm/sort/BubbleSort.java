package algorithm.sort;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 冒泡排序
 * 两次循环把小的/大的值往上冒泡
 */
@Data
public class BubbleSort {

    private int[] data;

    BubbleSort(int[] data) {
        this.data = data;
    }

    public void sort() {
        for (int i = 0; i < data.length - 1; i++) {
            //如果在某一次的排序中 发现没有交换 则可认为现状已经是排好序的了 可以提前结束排序 （优化点）
            boolean sorted = true;
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] < data[j + 1]) {
                    sorted = false;
                    int temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
            if (sorted) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[100000];
        for (int i = 0; i < 100000; i++) {
            data[i] = (int) (Math.random() * 100000);
        }
        BubbleSort bubbleSort = new BubbleSort(data);
        System.out.println(LocalDateTime.now());
        bubbleSort.sort();
        System.out.println(LocalDateTime.now());//13秒
//        for (int i : bubbleSort.getData()) {
//            System.out.println(i);
//        }
    }

}
