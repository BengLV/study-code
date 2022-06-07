package 算法.avarice;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.regex.Matcher;

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


    /**
     * 376. 摆动序列
     * https://leetcode.cn/problems/wiggle-subsequence/
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
     *
     * 例如，[1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3)是正负交替出现的。
     * 相反，[1, 4, 7, 2, 5]和[1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
     * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
     *
     * 示例 1：
     * 输入：nums = [1,7,4,9,2,5]
     * 输出：6
     * 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums.length;
        }
        //当前差值
        int curDiff = 0;
        //上一个差值
        int preDiff = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            //得到当前差值
            curDiff = nums[i] - nums[i - 1];
            //如果当前差值和上一个差值为一正一负
            //等于0的情况表示初始时的preDiff
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                count++;
                preDiff = curDiff;
            }
        }
        return count;
        /**
         * int n = nums.length;
         *         if (n < 2) {
         *             return n;
         *         }
         *         int up = 1;
         *         int down = 1;
         *         for (int i = 1; i < n; i++) {
         *             if (nums[i] > nums[i - 1]) {
         *                 up = down + 1;
         *             }
         *             if (nums[i] < nums[i - 1]) {
         *                 down = up + 1;
         *             }
         *         }
         *         return Math.max(up, down);
         */
    }

    /**
     * 53. 最大子数组和
     * https://leetcode.cn/problems/maximum-subarray/
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     *
     * 示例 1：
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     */
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int res = nums[0];
        for (int num : nums) {
            if (sum > 0) {
                //如果总和大于0时, 则加上当前数字
                sum += num;
            } else {
                //小于0时, 则从当前数字重新开始计算.
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给你一个整数数组 prices ，其中prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润。
     * 
     * 示例 1：
     * 输入：prices = [7,1,5,3,6,4]
     * 输出：7
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     *     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     *      总利润为 4 + 3 = 7 。
     *
     *
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            //只要今天的价格比昨天高,就卖出股票,得出利润
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i-1];
            }
        }
        return res;
    }


    /**
     * 55. 跳跃游戏
     * https://leetcode.cn/problems/jump-game/
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标
     * 
     * 示例1：
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        //当前能走的最大范围
        int range = nums[0];
        for (int i = 0; i <= range; i++) {
            range = Math.max(range, i + nums[i]);
            if (range >= nums.length - 1) return true;
        }
        return false;
    }


    /**
     * 45. 跳跃游戏 II
     * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * 假设你总是可以到达数组的最后一个位置。
     *
     * 示例 1:
     *
     * 输入: nums = [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *     从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
     *
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        int ans = 0;//跳跃次数
        int curRange = 0;//当前下标值能覆盖的最大范围
        int maxRange = 0;//覆盖的最大范围
        for (int i = 0; i <= nums.length; i++) {
            //获取当前下表最大覆盖范围
            maxRange = Math.max(maxRange, i + nums[i]);
            //超出数组长度,则返回
            if (maxRange >= nums.length - 1) {
                ans++;
                break;
            }
            //如果当前下标值等于当前能覆盖的最大范围,则更新最大范围,以及次数加一
            if (i == curRange) {
                curRange = maxRange;
                ans++;
            }
        }
        return ans;
    }


    /**
     * 1005. K 次取反后最大化的数组和
     * https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/
     * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
     * 选择某个下标 i并将 nums[i] 替换为 -nums[i] 。
     * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
     * 以这种方式修改数组后，返回数组 可能的最大和 。
     *
     * 示例 1：
     * 输入：nums = [4,2,3], k = 1
     * 输出：5
     * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
     */
    public static int largestSumAfterKNegations(int[] nums, int k) {
        // 排序，把可能有的负数排到前面
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 贪心：如果是负数，而k还有盈余，就把负数反过来
            if (nums[i] < 0 && k > 0) {
                nums[i] = -1 * nums[i];
                k--;
            }
            sum += nums[i];
        }
        Arrays.sort(nums);
        // 如果k没剩，那说明能转的负数都转正了，已经是最大和，返回sum
        // 如果k有剩，说明负数已经全部转正，所以如果k还剩偶数个就自己抵消掉，不用删减，如果k还剩奇数个就减掉2倍最小正数。
        return sum - (k % 2 == 0 ? 0 : 2 * nums[0]);
    }

    public static void main(String[] args) {
        int[] nums = {8,-7,-3,-9,1,9,-6,-9,3};
        largestSumAfterKNegations(nums, 8);
    }


}
