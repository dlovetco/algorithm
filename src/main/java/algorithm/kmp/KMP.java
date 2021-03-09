package algorithm.kmp;

import lombok.Data;

import java.util.Arrays;

/**
 * kmp算法 解决子串匹配问题
 * 有String1 和 String2
 * 如果String1里面包含String2 则返回匹配的下标 没有则返回-1
 * <p>
 * kmp算法的核心在于构建部分匹配表 来避免暴力匹配 提升效率
 * <p>
 * 有一个核心思想在于如果在匹配过程中 有部分长度是相同的 则下次匹配可以跳过一部分子串
 * 具体可以跳过多少 则需要依赖部分匹配表
 * 比如：
 * BBC ABCDAB ABCDABCDABDE   String1
 * ----ABCDABD           String2
 * 当前在String2的最后一个D处失配  下一次比较应该直接比较C和空格。这样同时优化了i（String1的指针）和j（String2的指针）
 * BBC ABCDAB ABCDABCDABDE
 * --------ABCDABD
 *
 * 最坏情况为 一直匹配不成功 知道i-j的地方才找到  所以复杂度为O(m)+O(n)
 */
@Data
public class KMP {
    public KMP(String string1, String string2) {
        this.string1 = string1;
        this.string1Array = string1.toCharArray();
        this.string2 = string2;
        this.string2Array = string2.toCharArray();
        partialMatchMap = new int[string2.length()];
        next = new int[string2.length()];
    }

    /**
     * 部分匹配表
     * 数组下标-> String2中的下标
     * v-> String2从0开始到当前下标的子串 的前缀后缀中公共子串的最大长度
     */
    private int[] partialMatchMap;

    /**
     * 当String2的某一个字符失配后 String2的指针应该指到多少
     * next数组的值 其实就是partialMatchMap整体向右移动一位并且在最前面补-1
     * next数组的实际含义是 String2从0开始到当前下标-1的子串的 公共子串最大长度
     * 比如ABCDABD
     * 如果是最后一个D失配了，则它的next值是ABCDAB的 公共子串最大长度=2  下一次就直接用C去比
     * 同时string1的下标也移动i = j - next[j]
     */
    private int[] next;

    private String string1;

    private char[] string1Array;

    private String string2;

    private char[] string2Array;

    public void generatePartialMatchMap() {
        int length = string2Array.length;
        for (int i = 0; i < length; i++) {
            //如果长度是1 则都赋值为初始值
            if (i == 0) {
                partialMatchMap[i] = 0;
                next[i] = -1;
                continue;
            }
            String tempString = String.valueOf(Arrays.copyOfRange(string2Array, 0, i + 1));
            //求前缀和后缀最大长度
            int maxSize = 0;
            int tempLength = tempString.length();
            for (int j = 1; j < tempLength; j++) {
                String pre = tempString.substring(0, j);
                String suffix = tempString.substring(tempLength - j);
                if (pre.equals(suffix)) {
                    //因为j一直在增大 所以如果相等的话 可以直接赋值
                    maxSize = j;
                }
            }
            partialMatchMap[i] = maxSize;

            //next数组相当于partialMatchMap整体向右移 在最开始补-1
            next[i] = partialMatchMap[i - 1];
            //如果string2[i]==string2[next] 那么下次比较则失去意义
            //这里递归的意义 相当于把多步移动合并成一步 3-2+2--1 = 3- -1
            while (next[i] != -1 && string2Array[i] == string2Array[next[i]]) {
                next[i] = next[next[i]];
            }
        }
    }

    public int solve() {
        //在比较过程中string1的下标
        int i = 0;
        //在比较过程中string2的下标
        int j = 0;
        char[] string1Array = string1.toCharArray();
        char[] string2Array = string2.toCharArray();
        while (i <= string1.length() - string2.length()) {
            //如果对应起来则比较下一个字符
            if (string1Array[i + j] == string2Array[j]) {
                //如果比较到string2的最后一位了 则说明已经匹配成功
                if (j == string2.length() - 1) {
                    return i;
                }
                j++;
                continue;
            }
            //如果失配则 i向右移动 j - partialMatchMap[j-1]+1 这里的加一是因为数组的下标是从0开始的
            //partialMatchMap[j-1] = next[j]
            // j = next[j]
            i = i + j - next[j];
            j = next[j];
            //如果j==-1 说明要重新从头开始比较了
            if (j == -1) {
                j = 0;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP("BBC ABCDAB ABCDABCDABDE", "ABCDABD");
        kmp.generatePartialMatchMap();
        System.out.println(kmp.solve());
        kmp = new KMP("HERE IS A SIMPLE EXAMPLE", "EXAMPLE");
        kmp.generatePartialMatchMap();
        System.out.println(kmp.solve());
        kmp = new KMP("abcde","cd");
        kmp.generatePartialMatchMap();
        System.out.println(kmp.solve());
        kmp = new KMP("aaaaa","bba");
        kmp.generatePartialMatchMap();
        System.out.println(kmp.solve());
        kmp = new KMP("mississippi", "issipi");
        kmp.generatePartialMatchMap();
        System.out.println(kmp.solve());
    }

}
