package algorithm.sort;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 快速排序
 * 把数组分割开 左边的都比右边的小
 * 再把数组以更小的粒度拆分
 */
@Data
public class QuickSort {

    private int[] data;

    QuickSort(int[] data) {
        this.data = data;
    }

    /**
     * 递归调用自己
     *
     * @param start 数组开始坐标
     * @param end   数组结束坐标
     */
    public void sort(int start, int end) {
        int left = start;
        int right = end;
        int middle = (end + start) / 2;
        int standard = data[middle];
        while (true) {
            //左指针 一直自增 直到找到一个比standard大或者相同的数
            while (data[left] < standard) {
                left++;
            }

            //右指针 一直自减 直到找到一个比standard小或者相同的数
            while (data[right] > standard) {
                right--;
            }

            int temp = data[left];
            data[left] = data[right];
            data[right] = temp;
            //如果两个指针重合了 则说明已经排序好了
            if (left == right) {
                break;
            }
        }

        //递归调用自己 当子列长度>2的时候才需要往下进行递归 因为如果长度是2 上面必然已经调换过位置了
        if (start < left - 1) {
            sort(start, left);
        }
        if (right < end - 1) {
            sort(right + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] data = new int[10];
        for (int i = 0; i < 10; i++) {
            data[i] = (int) (Math.random() * 100000);
        }
        QuickSort quickSort = new QuickSort(data);
        System.out.println(LocalDateTime.now());
        quickSort.sort(0, 9);
        System.out.println(LocalDateTime.now());//1毫秒
        for (int i : quickSort.getData()) {
            System.out.println(i);
        }
    }
}
