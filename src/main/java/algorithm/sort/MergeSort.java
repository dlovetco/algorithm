package algorithm.sort;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 归并排序 将一个无序数组 拆分成多个有序数组 然后合并成一个有序数组
 * 总的思路就是 分-合 分而治之
 */
@Data
public class MergeSort {

    private int[] data;

    private int[] temp;

    MergeSort(int[] data) {
        this.data = data;
        this.temp = new int[data.length];
    }

    /**
     * 首先递归调用自己 完成   分而治之的分操作
     */
    public void sort(int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            sort(left, middle);
            sort(middle + 1, right);
            //只有满足left<right 才会进入merge方法  如果left==right都进不来 所以能进来的最小粒度就是相差 right-left=1
            merge(left, right);
        }
    }

    /**
     * 合并 分而治之的 治操作
     */
    public void merge(int left, int right) {
        int middle = (left + right) / 2;

        //看成两个有序队列 [left,middle] [middle+1,right]

        //如果data[middle]<data[middle+1] 则可以直接返回 因为已经排序好了
        if (data[middle] < data[middle + 1]) {
            return;
        }
        int i = left;
        int j = middle + 1;
        int n = 0; //temp数组放置的下标
        while (i <= middle && j <= right) {
            if (data[i] <= data[j]) {
                temp[n] = data[i];
                i++;
                n++;
            } else if (data[j] < data[i]) {
                temp[n] = data[j];
                j++;
                n++;
            }
        }

        //跳出循环后 检查是否有游标没有指到最后  把剩下的数据都塞进temp里面
        if (i <= middle) {
            for (; i <= middle; i++) {
                temp[n] = data[i];
                n++;
            }
        }

        if (j <= right) {
            for (; j <= right; j++) {
                temp[n] = data[j];
                n++;
            }
        }

        //再把temp中的数组放回data
        System.arraycopy(temp, 0, data, left, right - left + 1);
    }

    public static void main(String[] args) {
        int[] data = new int[]{5,4,3,2,1};
//        for (int i = 0; i < 100000; i++) {
//            data[i] = (int) (Math.random() * 100000);
//        }
        MergeSort mergeSort = new MergeSort(data);
        System.out.println(LocalDateTime.now());
        mergeSort.sort(0, 4);
        System.out.println(LocalDateTime.now());//10毫秒
        for (int i : mergeSort.getData()) {
            System.out.println(i);
        }
    }
}
