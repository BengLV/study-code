package 算法.二分查找;

/**
 * @description: 二分查找
 * @author: LuPeng
 * @create: 2023-02-07
 **/
public class Dichotomy {

    /**
     * 704. 二分查找
     * BM17 二分查找-I
     * <p>
     * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，
     * 写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
     * <p>
     * 示例 1:
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            //防止超出整数范围
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 74. 搜索二维矩阵
     * BM18 二维数组中的查找
     * <p>
     * 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。/
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int x = 0;
        int y = m - 1;
        while (y >= 0 && x < n) {
            if (matrix[x][y] > target) {
                y--;
            } else if (matrix[x][y] < target) {
                x++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 162. 寻找峰值
     * BM19 寻找峰值
     * https://leetcode.cn/problems/find-peak-element/
     * <p>
     * 峰值元素是指其值严格大于左右相邻值的元素。
     * 给你一个整数数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     * 你可以假设nums[-1] = nums[n] = -∞ 。
     * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。 (时间复杂度限制只能用二分查找)
     * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
     * <p>
     * 爬坡法(想象出两个单调递增或递减的连续函数)
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            //如果右边的元素大于左边的,则在右边一定有峰值
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;//返回right也行
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * https://leetcode.cn/problems/median-of-two-sorted-arrays/
     * <p>
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * <p>
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        //为了简化代码,奇数位置的中间值
        int left = (l1 + l2 + 1) / 2;
        //偶数位置的中间值
        int right = (l1 + l2 + 2) / 2;
        return (findK(nums1, nums2, 0, 0, left) + findK(nums1, nums2, 0, 0, right)) / 2.0;
    }

    private int findK(int[] nums1, int[] nums2, int i, int j, int k) {
        //i 表示num1的起始位置, j 表示num2的起始位置.
        //如果i 大于等于数组的长度,说明其所有的数字已经被淘汰了, 则在另外一个数组中找数字.
        if (i >= nums1.length) return nums2[j + k - 1];
        if (j >= nums2.length) return nums1[i + k - 1];
        //k==1 表示在有序数组中找到起始位置的最小值.
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        //赋予最大值的意思只是说如果第一个数组的K/2不存在，则说明这个数组的长度小于K/2，那么另外一个数组的前K/2个我们是肯定不要的。
        // 给你举个例子，加入第一个数组长度是2，第二个数组长度是12，则K为7，K/2为3，因为第一个数组长度小于3，则无法判断中位数是否在其中，
        // 而第二个数组的前3个肯定不是中位数！故当K/2不存在时，将其置为整数型最大值，这样就可以继续下一次循环。
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findK(nums1, nums2, i + k / 2, j, k - k / 2);
        } else {
            return findK(nums1, nums2, i, j + k / 2, k - k / 2);
        }
    }

}
