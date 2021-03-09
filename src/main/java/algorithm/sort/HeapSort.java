package algorithm.sort;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 堆排序
 * 大顶堆：根节点比子节点都要大的二叉树
 * 小顶堆：根节点比子节点都要小的二叉`树
 * 大顶堆和小顶堆的选择取决于要按照什么方式排序
 * <p>
 * 堆排序中的二叉树使用顺序存储二叉树，所以是以数组形式存储的二叉树
 * 第一次排序，把输入的数组构建成大顶堆，这样就得到了一个最大的数（二叉树的根节点） 最大的数跟数组最后一个值交换 因为后面会缩短数组 去掉最后一个值比较方便
 * 第二次排序，去掉第一次排序得出的最大值，再构建大顶堆
 * 。。。
 * 直到数组中未排序的数只有一个
 */
@Data
public class HeapSort {

    private int[] data;

    public HeapSort(int[] data) {
        this.data = data;
    }

    public void sort() {
        //第一次先构建出一个大顶堆
        generateHeap();

        //在构建完成后 每次都把堆顶的数与数组最后的数交换 然后再调整大顶堆（从上往下调整）
        for (int length = data.length - 1; length > 0; length--) {
            //第一个与最后一个交换
            int temp = data[0];
            data[0] = data[length];
            data[length] = temp;
            adjustHeap(length - 1);
        }
    }

    /**
     * 构建大顶堆
     * 顺序存储二叉树性质：
     * 1、设i是数组的下标（同时也是顺序存储二叉树节点的顺序编号），则i的左子节点为 2n+1 右子节点为2n+2
     * 2、i的父亲节点为(i-1)/2
     * <p>
     * 对于一棵二叉树 从最后一个左叶子节点开始调整起（即数组的最后一位）
     */
    public void generateHeap() {
        for (int i = (data.length - 1 - 1) / 2; i >= 0; i--) {
            // 要与根节点交换的下标 初始默认为左节点
            int swap = 2 * i + 1;
            //因为传进来的i是子树的根 说明至少有左子节点 但是不一定有右子节点
            if (2 * i + 2 <= data.length - 1) {
                //左右节点先比较 如果右节点比较大则swap指向右节点
                if (data[swap] < data[swap + 1]) {
                    swap++;
                }
            }
            //左右节点中比较大的值与根节点比较 并交换
            if (data[swap] > data[i]) {
                int tempRoot = data[i];
                data[i] = data[swap];
                data[swap] = tempRoot;
            }
        }
    }

    /**
     * 调整大顶堆（在一棵已经建立好的大顶堆的基础上微调）
     */
    public void adjustHeap(int length) {
        //从0开始往下找有没有比自己大的
        int temp = data[0];
        int aim = 0;
        for (int i = 0 * 2 + 1; i <= length; i = i * 2 + 1) {
            //i始终指向的是aim的左子节点
            if (i + 1 <= length) {
                //如果有右节点 则比较大小 i指向大的那个
                if (data[i] < data[i + 1]) {
                    i++;
                }
            }

            //比较当前节点与temp的大小
            if (data[i] > temp) {
                //如果当前节点比temp大 则他必要往上升一级（有可能他父亲已经升级了，则他去取代他父亲的位置）
                data[aim] = data[i];
                aim = i;
                //这句话也可以现在写在这里 但是会调用几次 因为考虑到一直往下找的场景，儿子总是会取代父亲的位置
                // 只要找到那个最小的儿子 在儿子取代父亲之后 用temp占据儿子的位置 就可以了
               // data[aim] = temp;

            } else {
                //如果当前节点比temp小 说明它的所有子节点都比temp小 则就把temp赋值给当前这个节点
                break;
            }
        }
        data[aim] = temp;
    }

    public static void main(String[] args) {
//        int[] data = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] data = new int[100000];
        for (int i = 0; i < 100000; i++) {
            data[i] = (int) (Math.random() * 100000);
        }
        HeapSort heapSort = new HeapSort(data);
        System.out.println(LocalDateTime.now());
        heapSort.sort();
        System.out.println(LocalDateTime.now());
//        for (int i : heapSort.getData()) {
//            System.out.print(i + " ");
//        }
    }

}
