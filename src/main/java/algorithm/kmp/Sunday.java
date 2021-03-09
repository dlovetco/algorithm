package algorithm.kmp;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * 只不过Sunday算法是从前往后匹配，在匹配失败时关注的是文本串中参加匹配的最末位字符的下一位字符。
 * 如果该字符没有在模式串中出现则直接跳过，即移动位数 = 匹配串长度 + 1；
 * 否则，其移动位数 = 模式串中最右端的该字符到末尾的距离+1。
 */
@Data
public class Sunday {

    private String string1;

    private List<String> string1List;

    private String string2;

    private List<String> string2List;

    public Sunday(String string1, String string2) {
        this.string1 = string1;
        this.string1List = Arrays.asList(string1.split(""));
        this.string2 = string2;
        this.string2List = Arrays.asList(string2.split(""));
    }

    public int solve() {
        int i = 0;
        int j = 0;
        while (i <= string1.length() - string2.length()) {
            boolean match = true;
            for (; j < string2.length(); j++) {
                if (!string1List.get(i + j).equals(string2List.get(j))) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
            //不匹配则关注string2后面的字符 看看string2里面有没有 没有的话就直接跳过一大截
            if (i + string2.length() >= string1.length()) {
                //说明已经到最后了 还是不匹配直接返回-1
                return -1;
            } else {
                String nextString1Char = string1List.get(i + string2.length());
                int k = string2.length() - 1;
                boolean existInString2 = false;
                for (; k >= 0; k--) {
                    if (nextString1Char.equals(string2List.get(k))) {
                        existInString2 = true;
                        break;
                    }
                }
                if (!existInString2) {
                    k = -1;
                }
                i = i + string2.length() - k;
                j = 0;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Sunday sunday = new Sunday("BBC ABCDAB ABCDABCDABDE", "ABCDABD");
        System.out.println(sunday.solve());
        sunday = new Sunday("HERE IS A SIMPLE EXAMPLE", "EXAMPLE");
        System.out.println(sunday.solve());
        sunday = new Sunday("abcde","cd");
        System.out.println(sunday.solve());
        sunday = new Sunday("aaaaa","bba");
        System.out.println(sunday.solve());
        sunday = new Sunday("mississippi", "issipi");
        System.out.println(sunday.solve());
    }
}
