package algorithm.search;

import lombok.Data;

/**
 * 线性查找算法
 * 就是一直往下找啊找
 */
@Data
public class SeqSearch {

    private int[] data;

    SeqSearch(int[] data) {
        this.data = data;
    }

    public int search(int aimValue) {
        for (int i = 0; i < data.length; i++) {
            if (aimValue == data[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = new int[100000];
        for (int i = 0; i < 100000; i++) {
//            data[i] = (int) (Math.random() * 100000);
            data[i] = i;
        }
        SeqSearch seqSearch = new SeqSearch(data);
        System.out.println(seqSearch.search(100));
    }
}
