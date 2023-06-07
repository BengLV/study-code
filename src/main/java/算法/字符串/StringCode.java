package 算法.字符串;

/**
 * @author: LuPeng
 * @create: 2023-06-07
 **/
public class StringCode {


    /**
     * 415. 字符串相加
     * https://leetcode.cn/problems/add-strings/
     * 
     * 给定两个字符串形式的非负整数num1 和num2，计算它们的和并同样以字符串形式返回。
     * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。
     *
     * 示例 1：
     * 输入：num1 = "11", num2 = "123"
     * 输出："134"
     *
     */
    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int l1 = num1.length() - 1, l2 = num2.length() - 1, temp = 0;
        while (l1 >= 0 || l2 >= 0) {
            int n1 = l1 >= 0 ? num1.charAt(l1) - '0' : 0;
            int n2 = l2 >= 0 ? num2.charAt(l2) - '0' : 0;
            int res = n1 + n2 + temp;
            temp = res / 10;
            builder.append(res % 10);
            l1--;
            l2--;
        }
        if (temp == 1) {
            builder.append(temp);
        }
        return builder.reverse().toString();
    }


}
