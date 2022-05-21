package 算法.backtracking;

import java.util.ArrayList;
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

}
