package algorithm.search;

import lombok.Data;

import java.util.Arrays;

/**
 * 斐波那契查找
 * 把数组按照斐波那契数列分割
 * f(n) = f(n-1) + f(n-2)
 * =>d.length = [0,f(n-1)-1]+[f(n-1)-1,f(n)-1]
 * 我们要找寻的mid = f(n-1)-1
 * f(n)-1 = f(n-1)-1+f(n-2)-1 +1
 * mid = f(n) - f(n-2) - 1
 */
@Data
public class FibonacciSearch {

    private int[] data;

    /**
     * 斐波那契数列
     */
    private int[] fib = new int[100000];

    FibonacciSearch(int[] data) {
        this.data = data;
        buildFib();
    }

    public int search(int aimValue) {
        //斐波那契数列的下标
        int n = 1;
        //首先根据数组长度找到大于数组长度的斐波那契数列的值
        while (data.length - 1 >= fib[n]) {
            n++;
        }

        //如果选中的斐波那契数大于数组的长度则使用数组的最大值来填充（保证数组是有序的）
        if (fib[n] > data.length) {
            int[] newData = Arrays.copyOf(data, fib[n]);
            for (int i = data.length; i < fib[n]; i++) {
                newData[i] = newData[data.length - 1];
            }
            data = newData;
        }

        int left = 0;
        //data[mid] = data[left + mid]
        while (n > 2) {
            //一个数组的左边一段是fib[n-1] 右边一段是fib[n-2] 要根据aimValue与data[mid]比较后 判断使用哪一段
            int mid = fib[n] - fib[n - 2] - 1;
            if (aimValue == data[left + mid]) {
                return left + mid;
            }
            if (aimValue > data[left + mid]) {
                //取右边这一段
                left = left + mid;
                n = n - 2;
            } else if (aimValue < data[left + mid]) {
                //取左边这一段
                n = n - 1;
            }
        }
        return -1;
    }

    /**
     * 构建斐波那契数列
     */
    public void buildFib() {
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
    }

    public static void main(String[] args) {
        int[] data = new int[100];
        for (int i = 0; i < 100; i++) {
            data[i] = i;
        }
        FibonacciSearch fibonacciSearch = new FibonacciSearch(data);
        System.out.println(fibonacciSearch.search(25));
    }
}
