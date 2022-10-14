package 算法.剑指offer;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: 牛客网高频面试题
 * https://www.nowcoder.com/exam/oj?page=1&tab=%E7%AE%97%E6%B3%95%E7%AF%87&topicId=117
 * @author: LuPeng
 * @create: 2022-10-12
 **/
public class CommonInterviewTopic {

    /**
     * 206. 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * https://leetcode.cn/problems/reverse-linked-list/
     *
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        ListNode pre = null;
        while (temp != null) {
            ListNode node = temp.next;
            temp.next = pre;
            pre = temp;
            temp = node;
        }
        return pre;
    }


    /**
     * 912. 排序数组
     * 给你一个整数数组 nums，请你将该数组升序排列。
     *
     * 示例 1：
     * 输入：nums = [5,2,3,1]
     * 输出：[1,2,3,5]
     */
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] arr, int l, int r) {
        if (r <= l) return;
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, l, i);
        quickSort(arr, l, i - 1);
        quickSort(arr, i + 1, r);
    }

    private void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    /**
     * 146. LRU 缓存
     * https://leetcode.cn/problems/lru-cache/
     *
     * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
     * 实现 LRUCache 类：
     * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。
     * 如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     *
     */
    class LRUCache extends LinkedHashMap<Integer, Integer> {

        private Integer capacity;

        public LRUCache(int capacity) {
            //容量, 负载银子, true:按照读取顺序排序, false:按照插入顺序排序
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }


   /* public class Solution {

        LinkedHashMap<Integer, Integer> map = null;
        int capacity = 0;

        public Solution(int capacity) {
            // write code here
            map = new LinkedHashMap<>(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            // write code here
            return map.getOrDefault(key, -1);
        }

        public void set(int key, int value) {
            // write code here
            if (map.size() == capacity) {
                map.remove(map.keySet().iterator().next());
            }
            map.put(key, value);
        }
    }*/


}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

}
