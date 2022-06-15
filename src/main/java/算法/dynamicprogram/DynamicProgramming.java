package 算法.dynamicprogram;

/**
 * @description: 动态规划
 * @author: LuPeng
 * @create: 2022-06-10
 **/
public class DynamicProgramming {

    /**
     * 509. 斐波那契数
     * https://leetcode.cn/problems/fibonacci-number/
     * 斐波那契数（通常用F(n) 表示）形成的序列称为 斐波那契数列 。该数列由0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1)= 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     *
     * 示例 1：
     * 输入：n = 2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
     */
    public int fib(int n) {
        if (n <2) return n;
        int res = 0;
        int a = 0;
        int b = 1;
        for (int i = 1; i <n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }


    /**
     * 70. 爬楼梯
     * https://leetcode.cn/problems/climbing-stairs/
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 示例 1：
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     *
     * 示例 2：
     * 输入：n = 3
     * 输出：3
     * 解释：有三种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶 + 1 阶
     * 2. 1 阶 + 2 阶
     * 3. 2 阶 + 1 阶
     *
     * 解析:第n个台阶只能从第n-1或者n-2个上来。到第n-1个台阶的走法 + 第n-2个台阶的走法 = 到第n个台阶的走法，
     * 已经知道了第1个和第2个台阶的走法，一路加上去。
     */
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2, res = 0;
        for (int i =3; i <= n; i ++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }


    /**
     * 746. 使用最小花费爬楼梯
     * https://leetcode.cn/problems/min-cost-climbing-stairs/
     * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     * 请你计算并返回达到楼梯顶部的最低花费。
     *
     * 示例 1：
     * 输入：cost = [10,15,20]
     * 输出：15
     * 解释：你将从下标为 1 的台阶开始。
     * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
     * 总花费为 15 。
     *
     * 示例2:
     * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
     * 输出：6
     * 解释：你将从下标为 0 的台阶开始。
     * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
     * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
     * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
     * 总花费为 6 。
     *
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[1] = cost[0];
        dp[2] = cost[1];
        for (int i = 3; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i - 1];
        }
        return Math.min(dp[cost.length], dp[cost.length - 1]);
    }

}
