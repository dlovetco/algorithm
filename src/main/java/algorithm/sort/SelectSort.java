package algorithm.sort;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 选择排序
 * 第一次循环把最小的那个值与第一个交换 第二次循环把最小那个值与第二个交换、、、、
 */
@Data
public class SelectSort {

    private int[] data;

    public SelectSort(int[] data) {
        this.data = data;
    }

    public void sort() {
        for (int i = 0; i < data.length; i++) {
            int temp = data[i];
            for (int j = i; j < data.length; j++) {
                if (data[j] < temp) {
                    temp = data[j];
                }
            }
            data[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[100000];
        for (int i = 0; i < 100000; i++) {
            data[i] = (int) (Math.random() * 100000);
        }
        SelectSort selectSort = new SelectSort(data);
        System.out.println(LocalDateTime.now());
        selectSort.sort();
        System.out.println(LocalDateTime.now());//2.5秒
//        for (int i : selectSort.getData()) {
//            System.out.println(i);
//        }
    }
}
