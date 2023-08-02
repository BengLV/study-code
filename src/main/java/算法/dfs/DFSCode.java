package 算法.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LuPeng
 * @create: 2023-08-02
 **/
public class DFSCode {


    /**
     * 22. 括号生成
     * https://leetcode.cn/problems/generate-parentheses/description/
     *
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     * 示例 1：
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     */
    List<String> res1 = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return  res1;
        }
        generateParenthesisDfs("", n, n);
        return res1;

    }

    private void generateParenthesisDfs(String str, int left, int right) {
        if (left == 0 && right == 0) {
            res1.add(str);
            return;
        }
        if (left == right) {
            //如果左右括号数相等，那么下一个必须用左括号
            generateParenthesisDfs(str + "(", left - 1, right);
        } else if (left < right) {
            //如果剩余左括号数小于剩余右括号数，下一个括号用左、右括号都行。
            if (left > 0) {
                generateParenthesisDfs(str + "(", left - 1, right);
            }
            generateParenthesisDfs(str + ")", left, right - 1);
        }
    }


}
