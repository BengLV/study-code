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
     *
     * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，
     * 写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     * 示例 1:
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     *
     */
    public int search(int[] nums, int target) {
        if  (nums.length == 0) {
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

}
