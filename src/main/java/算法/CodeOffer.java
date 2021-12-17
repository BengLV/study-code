package 算法;

import org.springframework.util.CollectionUtils;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class CodeOffer {

    /**
     * 剑指 Offer 05. 替换空格
     * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     *
     * 示例 1：
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     *
     */
    public static String replaceSpace(String s) {
        StringBuffer sb = new StringBuffer();
        char[] ch = s.toCharArray();
        for (char c : ch) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
        //return s.replaceAll(" ", "%20");
    }

    public static class User {
        public int id;
        public String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<Integer> aa = list.subList(0,1);
        System.out.println(aa);
    }
}
