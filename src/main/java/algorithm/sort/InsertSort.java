package algorithm.sort;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 插入排序
 * 把数组看做两节  前面的是有序的 后面的是无序的  随着遍历下来 整个无序表渐渐变成有序表
 */
@Data
public class InsertSort {

    private int[] data;

    InsertSort(int[] data) {
        this.data = data;
    }

    /**
     * 从小到大排序
     */
    public void sort() {
        for (int i = 1; i < data.length; i++) {
            int temp = data[i];
            int j = i - 1;
            //这个判断用于减少移位的次数
            if (temp < data[j]) {
                //从后往前扫描 方便移位
                for (; j >= 0; j--) {
                    data[j + 1] = data[j];
                    if (temp > data[j]) {
//                        location = j;
                        break;
                    }
                }
            }
            data[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[100000];
        for (int i = 0; i < 100000; i++) {
            data[i] = (int) (Math.random() * 100000);
        }
        InsertSort insertSort = new InsertSort(data);
        System.out.println(LocalDateTime.now());
        insertSort.sort();
        System.out.println(LocalDateTime.now());//1秒
//        for (int i : insertSort.getData()) {
//            System.out.println(i);
//        }
    }

}
