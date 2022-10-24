package 算法.剑指offer;


import java.util.*;

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


    /**
     * 前中后序遍历
     * https://leetcode.cn/problems/binary-tree-preorder-traversal/
     *
     * 中左右 左中右  左右中
     * 输入：root = [1,null,2,3]
     * 输出：[1,2,3]
     *
     */
    public int[][] threeOrders (TreeNode root) {
        // write code here
        List<Integer> res1 = preorderTraversal(root);
        List<Integer> res2 = inorderTraversal(root);
        List<Integer> res3 = postorderTraversal(root);
        int[][] res = {res1.stream().mapToInt(Integer::valueOf).toArray(), res2.stream().mapToInt(Integer::valueOf).toArray() ,res3.stream().mapToInt(Integer::valueOf).toArray()};
        return res;
    }

    private List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    private List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    private List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    private void postorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }


    /**
     * NC119 最小的K个数
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        quickSort(input,  0, input.length - 1);
        int[] arr = Arrays.copyOf(input, k);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i : arr) {
            res.add(i);
        }
        return res;
    }


    /**
     * NC15 求二叉树的层序遍历
     * <p>
     * 示例1:
     * 输入：
     * {1,2,3,4,#,#,5}
     * 返回值：
     * [[1],[2,3],[4,5]]
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        while (!deque.isEmpty()) {
            int size = deque.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                list.add(poll.val);
                if (poll.left != null) {
                    deque.add(poll.left);
                }
                if (poll.right != null) {
                    deque.add(poll.right);
                }
            }
            res.add(list);
        }
        return res;
    }


    /**
     * NC88 寻找第K大
     *
     * 有一个整数数组，请你根据快速排序的思路，找出数组中第 k 大的数。
     *
     * 给定一个整数数组 a ,同时给定它的大小n和要找的 k ，请返回第 k 大的数(包括重复的元素，不用去重)，保证答案存在。
     * 要求：时间复杂度 O(nlogn)，空间复杂度 O(1)
     */
    public int findKth(int[] a, int n, int K) {
        // write code here
        quickSort(a, 0, n - 1);
        return a[n - K];
    }

    /**
     * NC61 两数之和
     *
     * 给出一个整型数组 numbers 和一个目标值 target，请在数组中找出两个加起来等于目标值的数的下标，返回的下标按升序排列。
     * （注：返回的数组下标从1开始算起，保证target一定可以由数组里面2个数字相加得到）
     */
    public int[] twoSum (int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>(numbers.length);
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                if (i > map.get(target - numbers[i])) {
                    return new int[]{map.get(target - numbers[i]) + 1, i + 1};
                } else {
                    return new int[]{i + 1, map.get(target - numbers[i]) + 1};
                }
            } else {
                map.put(numbers[i], i);
            }
        }
        return null;
    }


    /**
     * NC33 合并两个排序的链表
     * 输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
     *
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }


}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

}

class TreeNode {
    int val = 0;
    TreeNode left;
    TreeNode right;
}
