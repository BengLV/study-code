package 算法.数组;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组--双指针
 */
public class DoublePointer {

    /**
     * 977.有序数组的平方
     * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
     * <p>
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * <p>
     * 示例 1：
     * 输入：nums = [-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 解释：平方后，数组变为 [16,1,0,9,100]，排序后，数组变为 [0,1,9,16,100]
     */
    public static int[] sort(int[] nums) {
        nums = new int[]{-5, -3, -2, 1, 6, 8};
        int k = nums.length - 1;
        int[] result = new int[nums.length];
        for (int left = 0, right = nums.length - 1; left <= right; ) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                result[k] = nums[left] * nums[left];
                left++;
                k--;
            } else {
                result[k] = nums[right] * nums[right];
                k--;
                right--;
            }
        }
        System.out.println(Arrays.toString(result));
        return nums;
    }


    /**
     * 26. 删除有序数组中的重复项
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
     * <p>
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 示例 1：
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
     */
    public static int removeDuplicates(int[] nums) {
        nums = new int[]{1, 2, 2, 2, 3, 4, 4, 5, 6, 6};
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[left] != nums[right]) {
                nums[left + 1] = nums[right];
                left++;
            }
            right++;
        }
        System.out.println(Arrays.toString(nums));
        return left + 1;
    }

    /**
     * 283.移动零
     * https://leetcode-cn.com/problems/move-zeroes/
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 示例:
     * <p>
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     * <p>
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     */
    public static int[] moveZero(int[] nums) {
        nums = new int[]{0, 1, 1, 3, 0, 0, 12, 0, 0, 0, 0};
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
        System.out.println(Arrays.toString(nums));
        return null;
    }

    /**
     * 15. 三数之和
     * https://leetcode-cn.com/problems/3sum/
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * 示例 1：
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                }
            }
        }
        return list;
    }

    /**
     * 344.反转字符串
     * https://leetcode-cn.com/problems/reverse-string/
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 541. 反转字符串 II
     * https://leetcode-cn.com/problems/reverse-string-ii/
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
     * <p>
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     * <p>
     * 示例 1：
     * 输入：s = "abcdefg", k = 2
     * 输出："bacdfeg"
     * <p>
     * 示例 2：
     * 输入：s = "abcd", k = 2
     * 输出："bacd"
     */
    public static String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        for (int i = 0; i < len; i += 2 * k) {
            int left = i;
            int right = i + k - 1;
            if (right > len - 1) {
                right = len - 1;
            }
            while (left < right) {
                char temp = ch[left];
                ch[left] = ch[right];
                ch[right] = temp;
                left++;
                right--;
            }
        }
        return new String(ch);
    }

    /**
     * 151. 翻转字符串里的单词
     * https://leetcode-cn.com/problems/reverse-words-in-a-string/
     * 给你一个字符串 s ，逐个翻转字符串中的所有单词 。
     * <p>
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * <p>
     * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
     * <p>
     * 说明：
     * <p>
     * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
     * 翻转后单词间应当仅用一个空格分隔。
     * 翻转后的字符串中不应包含额外的空格。
     * <p>
     * 示例 1：
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     * <p>
     * 示例 2：
     * 输入：s = " hello world "
     * 输出："world hello"
     * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
     */
    public String reverseWords(String s) {
        return null;
    }


    /**
     * 56. 合并区间
     * BM89 合并区间
     * https://leetcode.cn/problems/merge-intervals/
     * <p>
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * <p>
     * 示例 1：
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     */
    public int[][] merge(int[][] intervals) {
        int length = intervals.length;
        if (length == 1) {
            return intervals;
        }
        //先将所有的左边界排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < length; i++) {
            //如果前一个区间的右边界大于等于当前区间的左边界，则合并
            if (intervals[i][0] <= intervals[i - 1][1]) {
                //左边界则为最小值
                intervals[i][0] = Math.min(intervals[i][0], intervals[i - 1][0]);
                //右边界则为最大值
                intervals[i][1] = Math.max(intervals[i][1], intervals[i - 1][1]);
            } else {
                //如果不需要合并，则说明当前区间是独立的，则记录索引。
                list.add(i - 1);
            }
        }
        //记录最后一个区间
        list.add(length - 1);
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = intervals[list.get(i)];
        }
        return res;
    }


    /**
     * 31. 下一个排列
     * https://leetcode.cn/problems/next-permutation/
     * <p>
     * 简单说: 从排列后的数数组中, 找出一个最小的大能够大于当前数的值.
     * <p>
     * 整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
     * <p>
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
     * 如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
     * <p>
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     * 给你一个整数数组 nums ，找出 nums 的下一个排列。
     * <p>
     * 必须 原地 修改，只允许使用额外常数空间。
     */
    public void nextPermutation(int[] nums) {
        //从右往左遍历,如果找到 i < i + 1 位置的元素, 记录 i 的位置,然后从 i + 1到 len - 1 中找到大于 i 位置的最小值
        //交换两个值, 然后对 i + 1 到 len -1 区间的数进行升序排序, 由于此时后面的数是倒序,转换成升序即可.
        if (nums == null) {
            return;
        }

        int len = nums.length;
        int idx = -1;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i + 1] > nums[i]) {
                idx = i;
                break;
            }
        }
        //如果不存在
        if (idx == -1) {
            Arrays.sort(nums);
            return;
        }
        //由于 i + 1 到 len - 1的位置是倒序,往后找到大于 i 的值,交互位置即可.
        for (int i = len - 1; i > idx; i--) {
            if (nums[i] > nums[idx]) {
                swap(idx, i, nums);
                break;
            }
        }
        //将 idx + 1 到 len - 1 的位置升序排列.
        int left = idx + 1, right = len - 1;
        while (left < right) {
            swap(left, right, nums);
            left++;
            right--;
        }
    }

    private void swap(int left, int right, int[] nums) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


    public static void main(String[] args) {
        //sort(null);
        //moveZero(null);

        int[] te = new int[]{1, 0, 3, 2, 5, 4};
        sort(te);
        removeDuplicates(null);
        //threeSum(te);
        //System.out.println(reverseStr("abcdefg", 2));
    }
}
