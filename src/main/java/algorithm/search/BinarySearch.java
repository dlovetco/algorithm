package algorithm.search;

import lombok.Data;

/**
 * 二分查找算法 要求被查找的数据必须本身是有序的
 * 递归二分之一 二分之一
 */
@Data
public class BinarySearch {

    private int[] data;

    BinarySearch(int[] data) {
        this.data = data;
    }

    /**
     * @param aimValue 目标值
     * @param left     左边界下标
     * @param right    右边界下标
     */
    public int search(int aimValue, int left, int right) {
        if (left == right && data[left] != aimValue) {
            return -1;
        }
        int middle = (left + right) / 2;
        if (aimValue == data[middle]) {
            return middle;
        } else if (aimValue < data[middle]) {
            return search(aimValue, left, middle);
        } else if (aimValue > data[middle]) {
            return search(aimValue, middle + 1, right);
        }
        return -1;
    }

    /**
     * 非递归方式实现二分查找
     *
     * @param aimValue 目标值
     */
    public int searchWithoutRecursion(int aimValue) {
        int left = 0;
        int right = data.length - 1;
        for (int i = left; i <= right; i++) {
            int middle = (left + right) / 2;
            if (data[middle] == aimValue) {
                return middle;
            }
            if (aimValue < data[middle]) {
                right = middle;
            } else if (aimValue > data[middle]) {
                left = middle;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = new int[100000];
        for (int i = 0; i < 100000; i++) {
            data[i] = i;
        }
        BinarySearch binarySearch = new BinarySearch(data);
        System.out.println(binarySearch.search(-2, 0, 99999));
        System.out.println(binarySearch.searchWithoutRecursion(497));
    }

}
