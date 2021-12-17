package 算法.KMP;

public class Kmp {

    /**
     * 28. 实现 strStr()
     * https://leetcode-cn.com/problems/implement-strstr/
     * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
     * 说明：
     * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
     * <p>
     * 示例 1：
     * 输入：haystack = "hello", needle = "ll"
     * 输出：2
     * <p>
     * 示例 2：
     * 输入：haystack = "aaaaa", needle = "bba"
     * 输出：-1
     * <p>
     * 示例 3：
     * 输入：haystack = "", needle = ""
     * 输出：0
     */
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int[] next = new int[needle.length()];
        int j = -1;
        getNext(next, needle);
        for (int i = 0; i < haystack.length(); i++) {
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            if (j == needle.length() - 1) {
                return i - needle.length() + 1;
            }

        }
        return -1;
    }


    public static void getNext(int[] next, String s) {
        //j指向前缀起始位置
        int j = -1;
        next[0] = j;
        //从1开始,i指向后缀起始位置。
        for (int i = 1; i < s.length(); i++) {
            //前后缀不相等
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
                //往前回退
                j = next[j];
            }
            if (s.charAt(i) == s.charAt(j +1)) {
                //前后缀相等
                j++;
            }
            // 将j（前缀的长度）赋给next[i]
            next[i] = j;
        }
    }

}
