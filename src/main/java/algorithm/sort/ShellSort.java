package algorithm.sort;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 希尔排序
 * 设数组长度为m 间隔系数为n  则数组会被分为m/n 组   m，m+m/n，m+2m/n...为一组进行直接插入排序  m = m+1 。。。。。
 * 排完序之后 n按一定规律增长重复上述排序  到最后n==m时 就已经排好序了
 */
@Data
public class ShellSort {

    private int[] data;

    ShellSort(int[] data) {
        this.data = data;
    }

    public void sort(int n) {
        while (n <= data.length) {
            int gap = data.length / n;
//            System.out.println("===>start" + LocalDateTime.now());
            for (int i = gap; i < data.length; i++) {
                int temp = data[i];
                int j = i - gap;
                //这个判断用于减少移位的次数
                if (temp < data[j]) {
                    //从后往前扫描 方便移位
                    for (; j >= 0; j = j - gap) {
                        data[j + gap] = data[j];
                        if (data[j] < temp) {
                            //找到了要插入的点 就退出循环
                            break;
                        }
                    }
                }
                data[j + gap] = temp;
            }
            n *= 2;
//            System.out.println("===>end" + LocalDateTime.now());
        }

    }

    public static void main(String[] args) {
        int[] data = new int[]{5,3,4,2,1};
//        for (int i = 0; i < 1000000; i++) {
//            data[i] = (int) (Math.random() * 1000000);
//        }
        ShellSort shellSort = new ShellSort(data);
        System.out.println(LocalDateTime.now());
        shellSort.sort(2);
        System.out.println(LocalDateTime.now());//30毫秒
        for (int i : shellSort.getData()) {
            System.out.println(i);
        }
    }
}
