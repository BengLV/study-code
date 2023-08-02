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


    /**
     * 8. 字符串转换整数 (atoi)
     * https://leetcode.cn/problems/string-to-integer-atoi/description/
     *
     * 解题思路:
     * 1. 首先判断前面是否有空格
     * 2. 判断正负标识
     * 3. 数字计算方法, 字符转换成数字的方法,以及判断是否是否为数字
     * 4. 判断数字是否超出范围的技巧,以及需要注意 214748364 + n
     *    如果 n 大于 7 时, 会自动将数组转换成负数.
     */
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int idx = 0, len = s.length();
        //前导空格
        while (idx < len && s.charAt(idx) == ' ') {
            idx++;
        }
        //判断符合
        if (idx == len) {
            return 0;
        }

        int flag = 1;
        if (s.charAt(idx) == '-' || s.charAt(idx) == '+') {
            flag = s.charAt(idx) == '-' ? -1 : 1;
            idx++;
        }

        if (idx == len) {
            return 0;
        }
        int res = 0;
        while (idx < len && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
            int num = s.charAt(idx) - '0';
            //处理超出范围
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num > 7)) {
                return flag == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = res * 10 + num;
            idx++;
        }
        return res * flag;


    }


}
