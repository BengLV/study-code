package 算法.剑指offer;

import 算法.链表.ListNode;

/**
 * @description: 腾讯精选练习50题
 * @author: LuPeng
 * @create: 2022-09-18
 **/
public class TencentOffer {


    /**
     * 2. 两数相加
     * https://leetcode.cn/problems/add-two-numbers/?favorite=ex0k24j
     *
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
     * 
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbersDFS(l1, l2, 0);
    }

    private ListNode addTwoNumbersDFS(ListNode l1, ListNode l2, int a) {
        if (l1 == null && l2 == null) {
            return  a == 0 ? null : new ListNode(a);
        }
        if (l1 != null) {
            a += l1.data;
            l1 = l1.next;
        }
        if (l2 != null) {
            a += l2.data;
            l2 = l2.next;
        }
        return new ListNode(a % 10, addTwoNumbersDFS(l1, l2, a / 10));
    }

    public ListNode addTwoNumbersBFS(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        //临时节点
        ListNode tempNode = res;
        int a = 0;
        //a不等于是为了防止节点数一致时,最后一位相加漏掉一位
        while (l1 != null || l2 != null || a != 0) {
            if (l1 != null) {
                a += l1.data;
                l1 = l1.next;
            }
            if (l2 != null) {
                a += l2.data;
                l2 = l2.next;
            }
            tempNode.next = new ListNode(a % 10);
            tempNode = tempNode.next;
            a = a / 10;
        }
        return res.next;
    }


    /**
     * 4. 寻找两个正序数组的中位数
     * https://leetcode.cn/problems/median-of-two-sorted-arrays/?favorite=ex0k24j
     *
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     *
     * 示例 1：
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * 
     * 示例 2：
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * 
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if (length % 2 == 1) {
            return findMedianSortedArraysHelper(nums1, nums2, length / 2 + 1);
        } else {
            return (findMedianSortedArraysHelper(nums1, nums2, length / 2)
                    + findMedianSortedArraysHelper(nums1, nums2, length / 2 + 1)) / 2;
        }
    }

    private double findMedianSortedArraysHelper(int[] nums1, int[] nums2, int k) {
        int l1 = nums1.length, l2 = nums2.length;
        int idx1 = 0, idx2 = 0;
        while (true) {
            //说明此时nums1数组已经需要全部剔除, 取nums2中第k小的元素
            if (idx1 == l1) {
                return nums2[idx2 + k - 1];
            }
            if (idx2 == l2) {
                return nums1[idx1 + k - 1];
            }

            if (k == 1) {
                return Math.min(nums1[idx1], nums2[idx2]);
            }
            //二分法
            int half = k / 2;
            //获取一个新的下标,取两者的最小值,防止越界,同时为了后续判断是否存在于该数组中
            int newIdx1 = Math.min(idx1 + half, l1) - 1;
            int newIdx2 = Math.min(idx2 + half, l2) - 1;
            //如果一个数组的经过二分后的值大于另一个数组, 则剔除最小的那一部分,将小的数组下标更新为新的
            if (nums1[newIdx1] <= nums2[newIdx2]) {
                k -= newIdx1 - idx1 + 1;
                idx1 = newIdx1 + 1;
            } else {
                k -= newIdx2 - idx2 + 1;
                idx2 = newIdx2 + 1;
            }
        }
    }

}
