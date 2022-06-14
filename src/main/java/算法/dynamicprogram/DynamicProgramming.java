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

}
