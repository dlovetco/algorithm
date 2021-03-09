package algorithm.search;

import lombok.Data;

/**
 * 插值查找 是基于二分查找的优化  在数据分布比较平均下 插值查找会比二分查找快
 * 把每次区间的分割 根据目标值大小 进行合理化分割
 * 算出aimValue与left的差值 right与left差值的百分之几 再用这个百分比*(right-left) 就是middle的值
 */
@Data
public class InterpolationSearch {

    private int[] data;

    InterpolationSearch(int[] data) {
        this.data = data;
    }

    public int search(int aimValue, int left, int right) {
//        int middle = (left + right) / 2;

        //防止目标数过大 导致下面计算出现问题
        if (aimValue > data[data.length - 1] || aimValue < data[0]) {
            return -1;
        }
        //要先* 再/  这里也需要注意如果数据过大 运算中会超过int类型的长度
        int middle = (aimValue - data[left]) * (right - left) / (data[right] - data[left]) + left;
        if (aimValue < data[middle]) {
            return search(aimValue, left, middle);
        } else if (aimValue > data[middle]) {
            return search(aimValue, middle + 1, right);
        } else {
            return middle;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[10000];
        for (int i = 0; i < 10000; i++) {
            data[i] = i;
        }
        InterpolationSearch interpolationSearch = new InterpolationSearch(data);
        System.out.println(interpolationSearch.search(5611, 0, 9999));
    }
}
