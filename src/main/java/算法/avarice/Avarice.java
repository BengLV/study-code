package 算法.avarice;

import java.util.Arrays;

/**
 * @description: 贪心算法
 * @author: LuPeng
 * @create: 2022-05-30
 **/
public class Avarice {


    /**
     * 455. 分发饼干
     * https://leetcode.cn/problems/assign-cookies/
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * 对每个孩子 i，都有一个胃口值g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j]。如果 s[j]>= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * 示例1:
     * 输入: g = [1,2,3], s = [1,1]
     * 输出: 1
     * 解释: 
     * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
     * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
     * 所以你应该输出1。
     *
     */
    public int findContentChildren(int[] g, int[] s) {
        //先排序
        Arrays.sort(s);
        Arrays.sort(g);
        //返回结果
        int child = 0;
        if (g.length == 0 || s.length == 0) return child;
        if (g[0] > s[s.length - 1]) return child;
        int gIdx = 0;
        int sIdx = 0;
        //遍历条件
        while (gIdx < g.length && sIdx < s.length) {
            //如果胃口值 小于等于 饼干的尺寸, 则孩子数加一
            if (g[gIdx] <= s[sIdx]) {
                child ++;
                gIdx ++;
            }
            //一个饼干只用一次
            sIdx ++;
        }
        return child;
    }


}
