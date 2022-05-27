package 算法.backtracking;

import designpatterns.结构型模式.组合模式.shapes.CompoundShape;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 回溯算法
 * @author: LuPeng
 * @create: 2022-05-17
 **/
public class BackTracking {


    /**
     * 77. 组合
     * https://leetcode.cn/problems/combinations/
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案
     *
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     */
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1);
        return result;
    }

    private void combineHelper(int n, int k, int startIndex) {
        //终止条件
        if (path.size() == k) {
            //存放结果
            result.add(new ArrayList<>(path));
            return;
        }
        //本层集合中元素
        for (int i = startIndex; i <= n; i++) {
            //剪枝处理后
        //for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            //处理节点
            path.add(i);
            //递归
            combineHelper(n, k, i + 1);
            //回溯,撤销处理结果
            path.removeLast();
        }
    }


    /**
     * 216. 组合总和 III
     * https://leetcode.cn/problems/combination-sum-iii/
     * 找出所有相加之和为n 的k个数的组合，且满足下列条件：
     *
     * 只使用数字1到9
     * 每个数字最多使用一次
     * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     *
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 解释:
     * 1 + 2 + 4 = 7
     * 没有其他符合的组合了。
     * 
     */
    List<List<Integer>> result1 = new ArrayList<>();
    LinkedList<Integer> path1 = new LinkedList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        getPath1(k, n, 1, 0);
        return result1;
    }

    private void getPath1(int k, int n, int startIndex, int sum) {
        //剪枝
        if (k >= n) {
            return;
        }
        if (path1.size() == k) {
            //存放结点
            if (sum == n) {
                result1.add(new ArrayList<>(path1));
            }
            return;
        }
        //for (int i = startIndex; i <= 9 - (k - path1.size()) + 1; i++) {  剪枝
        for (int i = startIndex; i <= 9; i++) {
            //处理节点
            sum += i;
            path1.add(i);
            getPath1(k, n, i + 1, sum);
            //回溯
            path1.removeLast();
            sum -= i;
        }
    }


    /**
     * 39. 组合总和
     * https://leetcode.cn/problems/combination-sum/
     * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，
     * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
     *
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
     *
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。
     * 仅有这两种组合。
     *
     */
    List<List<Integer>> result2 = new ArrayList<>();
    LinkedList<Integer> path2 = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        getPath2(candidates, 0, target, 0);
        return result2;
    }

    private void getPath2(int[] candidates, int sum, int target, int startIndex) {
        if (sum > target) return;
        if (sum == target) {
            result2.add(new ArrayList<>(path2));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (sum > target) break;
            path2.add(candidates[i]);
            sum += candidates[i];
            getPath2(candidates, sum, target, i);
            sum -= candidates[i];
            path2.removeLast();
        }
    }


    /**
     * 17. 电话号码的字母组合
     * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
     * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     */
    List<String> result3 = new ArrayList<>();
    StringBuilder builder = new StringBuilder();
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return result3;
        }
        String[] str = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        letterCombinations(digits, str, 0);
        return result3;
    }

    private void letterCombinations(String digits, String[] strs, int num) {
        //终止条件,若符合长度要求,则添加数据并返回
        if (digits.length() == builder.toString().length()) {
            result3.add(builder.toString());
            return;
        }
        //获取当前字母串
        String str = strs[digits.charAt(num) - '0'];
        //从0遍历每个字母串
        for (int i = 0; i < str.length(); i++) {
            builder.append(str.charAt(i));
            letterCombinations(digits, strs, num + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }


    /**
     * 40. 组合总和 II
     * https://leetcode.cn/problems/combination-sum-ii/
     * 给定一个候选人编号的集合candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
     *
     * candidates中的每个数字在每个组合中只能使用一次。
     * 示例1:
     * 输入: candidates =[10,1,2,7,6,1,5], target =8,
     * 输出:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     *
     */
    List<List<Integer>> result4 = new ArrayList<>();
    LinkedList<Integer> path4 = new LinkedList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null) return result4;
        Arrays.sort(candidates);
        combinationSum2(0, target, candidates, 0);
        return result4;
    }

    private void combinationSum2(int startIndex, int target, int[] candidates, int sum) {
        if (sum == target) {
            result4.add(new ArrayList<>(path4));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            //大剪枝
            if (sum > target) break;
            //同一层相同的元素,跳过后面相同元素.
            if (i > startIndex && candidates[i] == candidates [i - 1]) {
                continue;
            }
            path4.add(candidates[i]);
            sum += candidates[i];
            combinationSum2(i + 1, target, candidates, sum);
            sum -= candidates[i];
            path4.removeLast();
        }
    }


    /**
     * 131. 分割回文串
     * https://leetcode.cn/problems/palindrome-partitioning/
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     * 回文串 是正着读和反着读都一样的字符串。
     *
     * 示例 1：
     *
     * 输入：s = "aab"
     * 输出：[["a","a","b"],["aa","b"]]
     *
     */
    static List<List<String>> result5 = new ArrayList<>();
    static LinkedList<String> path5 = new LinkedList<>();

    public static List<List<String>> partition(String s) {
        partition(s, 0);
        return result5;
    }

    public static void partition(String s, int startIndex) {
        if (s.length() == startIndex) {
            result5.add(new ArrayList<>(path5));
            return;
        }
        // 横向遍历
        for (int i = startIndex; i < s.length(); i++) {
            //startIndex 截取同一层的字符串
            String s1 = s.substring(startIndex, i + 1);
            if (!isSymmetrical(s1)) {
                continue;
            }
            path5.add(s1);
            partition(s, i + 1);
            path5.removeLast();
        }
    }

    /**
     * 判断是否为回文数
     */
    public static Boolean isSymmetrical(String s) {
        if (s == null || s.length() <= 1) return true;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    /**
     * 93. 复原 IP 地址
     * https://leetcode.cn/problems/restore-ip-addresses/
     * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     *
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
     * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入'.' 来形成。你 不能重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
     *
     * 示例 1：
     *
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     *
     */
    static List<String> result6 = new ArrayList<>();
    static LinkedList<String> path6 = new LinkedList<>();
    public static List<String> restoreIpAddresses(String s) {
        restoreIpAddresses(0, s);
        return result6;
    }


    private static void restoreIpAddresses(int startIndex, String s) {
        //当path里面有四个字符,并且已经到最后一个字符时,说明符合条件
        if (path6.size() == 4 && startIndex == s.length()) {
            assemblyStr(path6);
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String s1 = s.substring(startIndex, i + 1);
            //如果path6里面的字符数大于4个,则终止当前循环.
            if (path6.size() > 4) break;
            //不符合条件则跳过.
            if ((s1.length() > 1 && s1.startsWith("0")) || Long.parseLong(s1) > 255) {
                continue;
            }
            path6.add(s1);
            restoreIpAddresses(i + 1, s);
            path6.removeLast();
        }

    }

    private static void assemblyStr(LinkedList<String> list) {
        StringBuilder builder = new StringBuilder(list.get(0)).append(".")
                .append(list.get(1)).append(".")
                .append(list.get(2)).append(".")
                .append(list.get(3));
        result6.add(builder.toString());
    }


    /**
     * 78. 子集
     * https://leetcode.cn/problems/subsets/
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     */
    List<List<Integer>> result7 = new ArrayList<>();
    LinkedList<Integer> path7 = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        result7.add(new ArrayList<>(null));
        subsets(nums, 0);
        return result7;
    }

    private void subsets(int[] nums, int startIndex) {
        result7.add(new ArrayList<>(path7));//「遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合」。
        if (startIndex >= nums.length){ //终止条件可不加
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path7.add(nums[i]);
            subsets(nums, i + 1);
            path7.removeLast();
        }
    }

    public static void main(String[] args) {
        restoreIpAddresses("25525511135");
    }

}
