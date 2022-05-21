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

}
