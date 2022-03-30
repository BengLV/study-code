package 算法.数组;

import com.sun.scenario.effect.Brightpass;

import java.util.HashMap;

/**
 * 数组--滑动窗口
 */
public class SlidingWindow {

    /**
     * 209.长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，
     * 找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，
     * 并返回其长度。如果不存在符合条件的子数组，返回 0
     * 示例：
     * 输入：s = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     */
    public static int minSubArrayLen(int s, int[] nums) {
        nums = new int[]{2, 3, 1, 2, 4, 3};
        //左指针,控制起始位置
        int left = 0;
        //记录大于s的和值
        int sum = 0;
        //返回值,记录数组最小长度
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                int subLength = right - left + 1;
                result = subLength > result ? result : subLength;
                sum -= nums[left++];
            }
        }
        System.out.println(result);
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 904. 水果成篮
     * 链接：https://leetcode-cn.com/problems/fruit-into-baskets
     * <p>
     * 在一排树中，第 i 棵树产生 tree[i] 型的水果。
     * 你可以从你选择的任何树开始，然后重复执行以下步骤：
     * <p>
     * 把这棵树上的水果放进你的篮子里。如果你做不到，就停下来。
     * 移动到当前树右侧的下一棵树。如果右边没有树，就停下来。
     * 请注意，在选择一颗树后，你没有任何选择：你必须执行步骤 1，然后执行步骤 2，然后返回步骤 1，然后执行步骤 2，依此类推，直至停止。
     * <p>
     * 你有两个篮子，每个篮子可以携带任何数量的水果，但你希望每个篮子只携带一种类型的水果。
     * <p>
     * 用这个程序你能收集的水果树的最大总量是多少？
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,1]
     * 输出：3
     * 解释：我们可以收集 [1,2,1]。
     * 示例 2：
     * <p>
     * 输入：[0,1,2,2]
     * 输出：3
     * 解释：我们可以收集 [1,2,2]
     * 如果我们从第一棵树开始，我们将只能收集到 [0, 1]。
     * 示例 3：
     * <p>
     * 输入：[1,2,3,2,2]
     * 输出：4
     * 解释：我们可以收集 [2,3,2,2]
     * 如果我们从第一棵树开始，我们将只能收集到 [1, 2]。
     * 示例 4：
     * <p>
     * 输入：[3,3,3,1,2,1,1,2,3,3,4]
     * 输出：5
     * 解释：我们可以收集 [1,2,1,1,2]
     * 如果我们从第一棵树或第八棵树开始，我们将只能收集到 4 棵水果树。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/fruit-into-baskets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int totalFruit(int[] tree) {
            int ans = 0, i = 0;
            tree = new int[] {3,3,3,1,2,1,1,2,3,3,4};
            Counter count = new Counter();
            for (int j = 0; j < tree.length; ++j) {
                count.add(tree[j], 1);
                while (count.size() >= 3) {
                    count.add(tree[i], -1);
                    if (count.get(tree[i]) == 0) {
                        count.remove(tree[i]);
                    }
                    i++;
                }
                ans = Math.max(ans, j - i + 1);
            }
            return ans;
    }

    static class Counter extends HashMap<Integer, Integer> {
        public int get(int k) {
            return containsKey(k) ? super.get(k) : 0;
        }

        public void add(int k, int v) {
            put(k, get(k) + v);
        }

    }

    public static void main(String[] args) {
        totalFruit(null);
    }

}
