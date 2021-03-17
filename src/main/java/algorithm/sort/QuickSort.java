package algorithm.sort;

import lombok.Data;

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

    public static void main(String[] args) {
        int[] data = new int[]{9, 1, 7, 9};
//        for (int i = 0; i < 10; i++) {
//            data[i] = (int) (Math.random() * 100000);
//        }
        QuickSort quickSort = new QuickSort(data);
//        System.out.println(LocalDateTime.now());
        quickSort.sort(0, 3);
//        System.out.println(LocalDateTime.now());//1毫秒
        for (int i : quickSort.getData()) {
            System.out.println(i);
        }
    }

    /**
     * 递归调用自己
     *
     * @param start 数组开始坐标
     * @param end   数组结束坐标
     */
    public void sort(int start, int end) {
        //从第二位开始 第一位用作左右指针相撞之后交换
        int left = start;
        int right = end;
        //基准数 取第一位
        int standard = data[start];
        while (true) {
            //右指针 一直自减 直到找到一个比standard小或者相等的数
            while (data[right] > standard) {
                right--;
                if (right <= left) {
                    break;
                }
            }
            //如果发现right==left 说明已经指针相撞了  和头结点交换位置退出循环
            if (right <= left) {
                int temp = data[right];
                data[right] = data[start];
                data[start] = temp;
                break;
            }

            left++;
            //如果上面没有相撞 那说明right发现了一个比standard小的数 左指针向前走 尝试找有没有比standard大的数 或者相等的数
            //相等的数可以 只要保证 小的数在相等的数左边就好了
            while (data[left] < standard) {
                left++;
                if (right <= left) {
                    break;
                }
            }

            //这里再判断一次right==left
            //如果=  那么说明没有比standard大的数了
            if (right <= left) {
                int temp = data[right];
                data[right] = data[start];
                data[start] = temp;
                break;
            }

            //两个指针不相撞 则执行交换
            int temp = data[left];
            data[left] = data[right];
            data[right] = temp;
        }

        //递归调用自己 当子列长度>2的时候才需要往下进行递归 因为如果长度是2 上面必然已经调换过位置了
        if (start < left - 1) {
            sort(start, left - 1);
        }
        if (right < end - 1) {
            sort(right + 1, end);
        }
    }
}
