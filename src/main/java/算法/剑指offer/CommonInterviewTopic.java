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
    public ListNode Merge(ListNode list1, ListNode list2) {
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

    /**
     * NC76 用两个栈实现队列
     *
     * 用两个栈来实现一个队列，使用n个元素来完成 n 次在队列尾部插入整数(push)和n次在队列头部删除整数(pop)的功能。 队列中的元素为int类型。保证操作合法，即保证pop操作时队列内已有元素。
     *
     * 要求：存储n个元素的空间复杂度为 O(n)，插入与删除的时间复杂度都是 O(1)
     */
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                return -1;
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
        return stack2.pop();
    }


    /**
     * NC68 跳台阶
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * 要求：时间复杂度：O(n) ，空间复杂度： O(1)
     */
    public int jumpFloor(int target) {
        if (target < 3) return target;
        int a = 1, b = 2, res = 0;
        for (int i = 3; i <= target; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }


    /**
     * NC50 链表中的节点每k个一组翻转
     * 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
     * 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
     * 你不能更改节点中的值，只能更改节点本身。
     */
    public static ListNode reverseKGroup (ListNode head, int k) {
        //记录当前列表的长度
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            length++;
        }
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        pre.next = head;
        ListNode curr = head;
        //12345 k=3
        for (int i = 0; i < length / k; i++) {
            for (int j = 0; j < k - 1; j++) {
                //当前遍历的节点
                ListNode node = curr.next;      //node:2345  >> 345
                //这一轮中,最开始的那个节点. 指向第三个节点
                curr.next = node.next;         //curr:1345   >> 145
                //交换两个节点
                node.next = pre.next;         //node:21345   >> 32145
                //将交换后的节点指向虚拟节点
                pre.next = node;             //021345        >> 032145
            }
            //挪动区间
            pre = curr;                     //145
            curr = curr.next;               //45
        }
        return dummy.next;
    }


    /**
     * NC19 连续子数组的最大和
     * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。
     * 求所有子数组的和的最大值。
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int res = array[0];
        int sum = 0;
        for (int num : array) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
        /**
         * int[] dp = new int[array.length];
         *         dp[0] = array[0];
         *         int res = array[0];
         *         for (int i = 1; i < array.length; i++) {
         *             dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
         *             res = Math.max(res, dp[i]);
         *
         *         }
         *         return res;
         *
         *  //空间优化后的动态规划
         *  int sum = 0;
         *         int max = array[0];
         *         for(int i=0;i<array.length;i++){
         *             // 优化动态规划，确定sum的最大值
         *             sum = Math.max(sum + array[i], array[i]);
         *             // 每次比较，保存出现的最大值
         *             max = Math.max(max,sum);
         *         }
         *         return max;
         */
    }

    /**
     * NC41 最长无重复子数组
     * 给定一个长度为n的数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
     * 子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
     */
    public static int maxLength(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < arr.length) {
            while (set.contains(arr[right])) {
                set.remove(arr[left]);
                left++;
            }
            res = Math.max(res, right - left + 1);
            set.add(arr[right]);
            right++;
        }
        return res;
    }


    /**
     * NC4 判断链表中是否有环
     * 判断给定的链表中是否有环。如果有环则返回true，否则返回false。
     */
    public boolean hasCycle(ListNode head) {
        /* 空间复杂度:O(n) 时间复杂度:O(n)
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;*/

        //空间复杂度:O(1)
        if (head == null || head.next == null) return false;
        ListNode fast = head.next;
        ListNode slow = head;
        while (slow != fast) {
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }


    /**
     * NC22 合并两个有序的数组
     * 给出一个有序的整数数组 A 和有序的整数数组 B ，请将数组 B 合并到数组 A 中，变成一个有序的升序数组
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            if (i < 0 || nums2[j] > nums1[i]) {
                nums1[k--] = nums2[j--];
            } else {
                nums1[k--] = nums1[i--];
            }

        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        //reverseKGroup(node1, 3);
        //maxLength(new int[]{1, 2, 3,3,4});
        merge(new int[]{0}, 0, new int[]{1}, 1);
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
