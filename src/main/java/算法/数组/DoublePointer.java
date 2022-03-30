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
     *
     * 注意：答案中不可以包含重复的三元组。
     * 示例 1：
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i ++) {
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
                    while (left < right && nums[right] == nums[right -1]) {
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
     *
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *
     * 示例 1：
     * 输入：s = "abcdefg", k = 2
     * 输出："bacdfeg"
     *
     * 示例 2：
     * 输入：s = "abcd", k = 2
     * 输出："bacd"
     *
     */
    public static String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        for (int i = 0; i < len; i += 2*k) {
            int left = i;
            int right = i + k -1;
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
     *
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     *
     * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
     *
     * 说明：
     *
     * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
     * 翻转后单词间应当仅用一个空格分隔。
     * 翻转后的字符串中不应包含额外的空格。
     * 
     * 示例 1：
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     *
     * 示例 2：
     * 输入：s = " hello world "
     * 输出："world hello"
     * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
     */
    public String reverseWords(String s) {
        return null;
    }



    public static void main(String[] args) {
        //sort(null);
        //moveZero(null);

        int[] te = new int[]{1,0,3,2,5,4};
        sort(te);
        removeDuplicates(null);
        //threeSum(te);
        //System.out.println(reverseStr("abcdefg", 2));
    }
}
