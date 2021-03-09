package algorithm.sort;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 桶排序（按照个十百千。。依次排序）
 * 初始化10个temp数组
 * 把待排序的数组按照个位数不同 依次放入10个数组中 然后按照顺序取出
 * 把待排序的数组按照百位数不同 依次放入10个数组中 然后按照顺序取出
 * 。。。
 * 直到最高位
 * <p>
 * 最后一次取完就得到了排序之后的数组
 */
@Data
public class RadixSort {

    private int[] data;

    private int[][] temps;

    /**
     * 记录每个桶里面放了多少个数据
     */
    int[] tempSize = new int[10];

    RadixSort(int[] data) {
        this.data = data;
        temps = new int[10][data.length];
    }

    public void sort() {
        //用于计算的数字 每次*10
        int calNum = 1;

        while (true) {
            calNum *= 10;
            boolean end = true;
            for (int num : data) {
                int result = (num % calNum) / (calNum / 10);
                tempSize[result]++;
                temps[result][tempSize[result] - 1] = num;
                if (num / calNum != 0) {
                    end = false;
                }
            }
            //数据写回data数组的下标
            int n = 0;
            for (int i = 0; i < tempSize.length; i++) {
                for (int j = 0; j < tempSize[i]; j++) {
                    data[n] = temps[i][j];
                    n++;
                }
            }
            if (end) {
                break;
            }
            //每次结束tempSize归零
            tempSize = new int[10];
        }
    }

    public static void main(String[] args) {
        int[] data = new int[100000];
        for (int i = 0; i < 100000; i++) {
            data[i] = (int) (Math.random() * 100000);
        }
        RadixSort radixSort = new RadixSort(data);
        System.out.println(LocalDateTime.now());
        radixSort.sort();
        System.out.println(LocalDateTime.now());//30ms
//        for (int i : radixSort.getData()) {
//            System.out.println(i);
//        }
    }
}
