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

}
