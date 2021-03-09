package algorithm.kmp;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * BM算法定义了两个规则：
 * <p>
 * 坏字符规则：当文本串中的某个字符跟模式串的某个字符不匹配时，我们称文本串中的这个失配字符为坏字符，此时模式串需要向右移动，
 * 移动的位数 = 坏字符在模式串中的位置 - 坏字符在模式串中最右出现的位置。
 * 此外，如果"坏字符"不包含在模式串之中，则最右出现位置为-1。
 * <p>
 * 好后缀规则：当字符失配时，后移位数 = 好后缀在模式串中的位置 - 好后缀在模式串上一次出现的位置，
 * 且如果好后缀在模式串中没有再次出现，则为-1。
 * 好后缀的意思是：字符串失配的位置到string2的结束位置，所有的后缀。
 *
 * <p>
 * 每次模式串移动的位置 是上面两个规则取最大值
 * <p>
 * 此外BM算法是从模式串末尾开始比较的
 * 最坏情况下复杂度是O(n)
 */
@Data
public class BM {

    private String string1;

    private List<String> string1List;

    private String string2;

    private List<String> string2List;

    public BM(String string1, String string2) {
        this.string1 = string1;
        this.string1List = Arrays.asList(string1.split(""));
        this.string2 = string2;
        this.string2List = Arrays.asList(string2.split(""));
    }

    public int solve() {

        //string1的下标
        int i = 0;
        //string2的下标
        int j = string2.length() - 1;
        while (i <= string1.length() - string2.length()) {
            boolean match = true;
            for (; j >= 0; j--) {
                if (!string1List.get(i + j).equals(string2List.get(j))) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
            //如果失配了 则根据坏字符规则算出一个偏移量
            int rule1 = 1;
            if (!match) {
                //查看string1中失配的字符是否存在于string2
                String noMatchedChar = string1List.get(i + j);
                boolean existedInString2 = false;

                //从右往左数 因为比较也是从右往左比较的
                //如果从左往右数，则容易跳过太多
                int k = string2.length() - 1;
                for (; k >= 0; k--) {
                    if (string2List.get(k).equals(noMatchedChar)) {
                        existedInString2 = true;
                        break;
                    }
                }
                if (!existedInString2) {
                    k = -1;
                }
                rule1 = j - k;
            }
            //最小步数是1
            rule1 = rule1 < 1 ? 1 : rule1;

            //再根据好后缀规则计算出一个偏移量
            //好后缀成立的前提是至少当前比较的最后一位 string1和string2一样
            //好后缀规则应该只要看最后一个值是否在之前的子串里面 因为如果最后一个值在前面没有，则好后缀规则不成立
            int rule2 = 1;
            if (!match && string2List.get(string2List.size() - 1).equals(string1List.get(i + string2List.size() - 1))) {
                String lastChar = string2List.get(string2List.size() - 1);
                int k = string2.length() - 2;
                boolean existedInString2 = false;
                for (; k >= 0; k--) {
                    if (lastChar.equals(string2List.get(k))) {
                        existedInString2 = true;
                        break;
                    }
                }
                if (!existedInString2) {
                    k = -1;
                }
                rule2 = j - k;
            }
            //最小步数是1
            rule2 = rule2 < 1 ? 1 : rule2;
            i = i + Math.max(rule1, rule2);
            j = string2.length() - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        BM bm = new BM("BBC ABCDAB ABCDABCDABDE", "ABCDABD");
        System.out.println(bm.solve());
        bm = new BM("HERE IS A SIMPLE EXAMPLE", "EXAMPLE");
        System.out.println(bm.solve());
        bm = new BM("abcde", "cd");
        System.out.println(bm.solve());
        bm = new BM("aaaaa", "bba");
        System.out.println(bm.solve());
        bm = new BM("mississippi", "issipi");
        System.out.println(bm.solve());
    }

}
