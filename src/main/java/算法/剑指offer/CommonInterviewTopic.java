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


    /**
     * NC3 链表中环的入口结点
     * 给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) return null;
        ListNode fast = pHead;
        ListNode slow = pHead;
        while (fast != null) {
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            slow = slow.next;
            if (slow == fast) {
                ListNode res = pHead;
                while (res != slow) {
                    res = res.next;
                    slow = slow.next;
                }
                return res;
            }
        }
        return null;
    }

    /**
     * NC52 有效括号序列
     * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
     * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || c != stack.pop()) return false;
        }
        return stack.isEmpty();
    }


    /**
     * NC53 删除链表的倒数第n个节点
     * 给定一个链表，删除链表的倒数第 n 个节点并返回链表的头指针
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /*//空间复杂度O(1), 时间复杂度O(n)
        int nodeCount = 0;
        ListNode temp = head;
        while (temp != null) {
            nodeCount++;
            temp = temp.next;
        }
        temp = head;
        if (nodeCount - n == 0) return head.next;
        for (int i = 1; i < nodeCount - n; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return head;*/
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode fast = head;
        ListNode slow = temp;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return temp.next;
    }

    /**
     * NC1 大数加法 <br>
     * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
     *
     */
    public String solve(String s, String t) {
        // write code here
        StringBuilder builder = new StringBuilder();
        int i = s.length() - 1, j = t.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? s.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? t.charAt(j) - '0' : 0;
            int temp = n1 + n2 + carry;
            carry = temp / 10;
            builder.append(temp % 10);
            i--;
            j--;
        }
        if (carry == 1) {
            builder.append(1);
        }
        return builder.reverse().toString();
    }


    /**
     * NC14 按之字形顺序打印二叉树
     * 例如：
     * 给定的二叉树是{1,2,3,#,#,4,5}
     *
     * 该二叉树之字形层序遍历的结果是
     * [
     * [1],
     * [3,2],
     * [4,5]
     * ]
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(pRoot);
        int level = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (level % 2 == 0) {
                    temp.add(poll.val);
                } else {
                    temp.add(0, poll.val);
                }
                if (poll.left != null) {
                    deque.add(poll.left);
                }
                if (poll.right != null) {
                    deque.add(poll.right);
                }
            }
            level++;
            res.add(temp);
        }
        return res;
    }


    /**
     * NC127 最长公共子串
     *
     * 给定两个字符串str1和str2,输出两个字符串的最长公共子串
     * 题目保证str1和str2的最长公共子串存在且唯一。
     */
    public String LCS(String str1, String str2) {
        // write code here
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        //记录相同字串最后一位的下标
        int lastIdx = 0;
        //相同字串的长度
        int maxLength = 0;
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                //如果相等时
                if (str1.charAt(i) == str2.charAt(j)) {
                    //dp公式
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    if (dp[i + 1][j + 1] > maxLength) {
                        maxLength = dp[i + 1][j + 1];
                        lastIdx = i;
                    }
                } else {
                    dp[i + 1][j + 1] = 0;
                }
            }
        }
        return str1.substring(lastIdx - maxLength + 1, lastIdx + 1);
    }


    /**
     * NC66 两个链表的第一个公共结点
     * 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        ListNode node1 = pHead1;
        ListNode node2 = pHead2;
        while (node1 != node2) {
            node1 = node1 == null ? pHead2 : node1.next;
            node2 = node2 == null ? pHead1 : node2.next;
        }
        return  node1;
    }


    /**
     * NC40 链表相加(二)
     * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
     * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
     */
    public ListNode addInList (ListNode head1, ListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (head1 != null) {
            stack1.add(head1.val);
            head1 = head1.next;
        }
        while (head2 != null) {
            stack2.add(head2.val);
            head2 = head2.next;
        }
        int i = stack1.size(), j = stack2.size(), carry = 0;
        ListNode head = new ListNode(0);
        ListNode temp = head.next;
        while (i > 0 || j > 0) {
            int n1 = i > 0 ? stack1.pop() : 0;
            int n2 = j > 0 ? stack2.pop() : 0;
            int sum = n1 + n2 + carry;
            ListNode node = new ListNode(sum % 10);
            //头插法
            node.next = temp;
            temp = node;
            carry = sum / 10;
            i--;
            j--;
        }
        if (carry == 1) {
            ListNode node = new ListNode(1);
            node.next = temp;
            temp = node;
        }
        return temp;
    }


    /**
     * NC102 在二叉树中找到两个节点的最近公共祖先
     * 给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
     */
    public int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        // write code here
        TreeNode res = dfs(root, o1, o2);
        return res.val;
    }
    public TreeNode dfs(TreeNode root, int o1, int o2) {
        if(root == null || root.val == o1 || root.val == o2) {
            return root;
        }
        TreeNode left = dfs(root.left, o1, o2);
        TreeNode right = dfs(root.right, o1, o2);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }

    /**
     * NC103 反转字符串
     * 写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串
     */
    public String solve (String str) {
        if (str == "" || str == null) return "";
        //直接 return new StringBuilder(str).reverse().toString();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            builder.append(str.charAt(i));
        }
        return builder.reverse().toString();
    }


    /**
     * NC38 螺旋矩阵
     *
     * 给定一个m x n大小的矩阵（m行，n列），按螺旋的顺序返回矩阵中的所有元素。
     */
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        if (matrix == null) return null;
        int width = matrix.length;
        if (width == 0) return res;
        int length = matrix[0].length;
        int left = 0, top = 0, right = length - 1, down = width - 1;
        while (true) {
            //从左往右 left right
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            if (top > down) break;
            //上往下
            for (int i = top; i <= down; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if (right < left) break;
            //从右往左
            for (int i = right; i >= left; i--) {
                res.add(matrix[down][i]);
            }
            down--;
            if (down < top) break;
            //从下往上
            for (int i = down; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            if (left > right) break;

        }
        return res;
    }

    /**
     * NC65 斐波那契数列
     *
     */
    public int Fibonacci(int n) {
        int res = 0;
        if (n == 1 || n == 2) return 1;
        int a = 1, b = 1;
        for (int i = 3; i <= n; i++) {
            res = a + b;
            b = a;
            a = res;
        }
        return res;
    }


    /**
     * NC17 最长回文子串
     *
     * 对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。
     */
    int ans = 0;
    public int getLongestPalindrome (String A) {
        // write code here
        if (A == null) return 0;
        char[] ch = A.toCharArray();
        for (int i = 0; i < A.length(); i++) {
            i = getPalindromeLength(ch, i);
        }
        return ans;
    }

    private int getPalindromeLength(char[] ch, int left) {
        int right = left;
        //如果是连续的字符，则跳过
        while (right < ch.length - 1 && ch[left] == ch[right + 1]) {
            right++;
        }
        int res = right;
        //从中间往两边扩散
        while (left > 0 && right < ch.length - 1 && ch[left - 1] == ch[right + 1]) {
            left--;
            right++;
        }
        ans = Math.max(right - left + 1, ans);
        return res;
    }

    /**
     * NC54 三数之和
     * 给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组。
     */
    public List<List<Integer>> threeSum(int[] num) {
        if (num.length < 3) return new ArrayList<>();
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            if (num[i] > 0) {
                break;
            }
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }
            int left = i + 1, right = num.length - 1;
            while (left < right) {
                int sum = num[i] + num[left] + num[right];
                if (sum == 0) {
                    res.add(Arrays.asList(num[i], num[left], num[right]));
                    while (left < right && num[right] == num[right- 1]) {
                        right--;
                    }
                    while (left < right && num[left] == num[left + 1]) {
                        left++;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    /**
     * NC12 重建二叉树
     * 给定节点数为 n 的二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点
     */
    //前序遍历  中左右 pre  中序遍历 左中右  vin
    private HashMap<Integer, Integer> map = new HashMap<>();
    private int[] temp;
    public TreeNode reConstructBinaryTree(int [] pre,int [] vin) {
        //将中序遍历结果存起来,后续用来分割数组
        for (int i = 0; i < vin.length; i++) {
            map.put(vin[i], i);
        }
        temp = pre;
        return helper(0, 0, vin.length - 1);
    }

    /**
     * @param root 根节点在前序遍历的索引root
     * @param left 子树在中序遍历的左边界
     * @param right 子树在中序遍历的右边界
     */
    private TreeNode helper(int root, int left, int right) {
        if (left > right) return null;
        //获取中节点
        int val = temp[root];
        TreeNode node = new TreeNode(val);
        //根节点在中序遍历的索引
        int idx = map.get(val);
        node.left = helper(root + 1, left, idx - 1);
        //root + idx - left + 1:根节点索引 + 左子树长度 + 1
        node.right = helper(root + idx - left + 1  ,idx + 1, right);
        return node;
    }

    /**
     * NC32 求平方根
     */
    public int mySqrt(int x) {
        if (x == 1) return 1;
        int min = 0, max = x;
        //等于1会陷入死循环
        while (max - min > 1) {
            //二分法
            int m = (max + min) / 2;
            //用x/m<m而不是m*m>x防止溢出
            if (x / m < m) {
                max = m;
            } else {
                min = m;
            }
        }
        return min;
    }


    /**
     * NC48 在旋转过的有序数组中寻找目标值
     * 33. 搜索旋转排序数组
     * https://leetcode.cn/problems/search-in-rotated-sorted-array/
     *
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     *
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。(二分法)
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[right]) {
                //如果中间的值小于最右边的值，说明右半段是有序的。
                if(nums[mid] < target && target <= nums[right]) {
                    //如果中间的值小于目标值并且目标值小于最右边的值(防止目标值过大不存在)
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                //说明左半段是有序的。
                if(target < nums[mid] && nums[left] <= target) {
                    //如果最左边的值小于目标值
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }


    /**
     * NC90 包含min函数的栈
     * 剑指 Offer 30. 包含min函数的栈
     * https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/
     */
    //先进后出
    class MinStack {

        private Node node;

        public MinStack() {

        }

        //添加
        public void push(int x) {
            if (node == null) {
                node = new Node(x, x, null);
            } else {
                node = new Node(x, Math.min(node.min, x), node);
            }
        }

        //删除最后添加的那个元素
        public void pop() {
            node = node.next;
        }

        public int top() {
            return node.val;
        }

        public int min() {
            return node.min;
        }

        class Node {

            private int val;

            private int min;

            private Node next;

            public Node(int val, int min, Node next) {
                this.min = min;
                this.val = val;
                this.next = next;
            }
        }

    }


    /**
     * NC7 买卖股票的最好时机(一)
     * 只能买卖一次
     */
    public int maxProfit (int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int min = prices[0], res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= min) {
                min = prices[i];
            } else {
                res = Math.max(res, prices[i] - min);
            }
        }
        return res;
    }

    /**
     * NC51 合并k个已排序的链表
     * 合并 k 个升序的链表并将结果作为一个升序的链表返回其头节点。
     */
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists == null || lists.size() == 0) return null;
        //使用有线队列
        PriorityQueue<ListNode> deque = new PriorityQueue<>(lists.size(), (o1, o2) -> {
            if (o1.val < o2.val) {
                return -1;
            } else if (o1.val == o2.val) {
                return 0;
            } else {
                return 1;
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        //先添加头部节点即可.
        for (ListNode node : lists) {
            if (node != null) {
                deque.add(node);
            }
        }
        while (!deque.isEmpty()) {
            tail.next = deque.poll();
            tail = tail.next;
            //如果临时节点的next节点不为空,则添加next节点
            if (tail.next != null) {
                deque.add(tail.next);
            }
        }
        return dummy.next;
    }

    /**
     * NC121 字符串的排列
     * 输入一个长度为 n 字符串，打印出该字符串中字符的所有排列，你可以以任意顺序返回这个字符串数组。
     * 例如输入字符串ABC,则输出由字符A,B,C所能排列出来的所有字符串ABC,ACB,BAC,BCA,CBA和CAB。
     *
     * 递归回溯
     */
    private Set<String> set = new HashSet<>();
    public ArrayList<String> Permutation(String str) {
        boolean[] visited = new boolean[str.length()];
        PermutationDFS(str.toCharArray(), "", visited);
        return new ArrayList<>(set);
    }


    private void PermutationDFS(char[] arr, String s, boolean[] visited) {
        if (s.length() == arr.length) {
            set.add(s);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            PermutationDFS(arr, s + arr[i], visited);
            visited[i] = false;
        }
    }


    /**
     * NC128 接雨水问题
     * 42. 接雨水
     * 给定一个整形数组arr，已知其中所有的值都是非负的，将这个数组看作一个柱子高度图，计算按此排列的柱子，下雨之后能接多少雨水。(数组以外的区域高度视为0)
     */
    public int trap(int[] height) {
        int sum = 0;
        //选中一根柱子,第一根跟最后一根不用接
        for (int i = 1; i < height.length - 1; i++) {
            //得出当前柱子左右的最大值
            int maxLeft = 0, maxRight = 0;
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            //得出左右中的最小值
            int min = Math.min(maxLeft, maxRight);
            //只有当前柱子比旁边的柱子都要矮时,才能接住雨水.
            if (height[i] < min) {
                sum += min - height[i];
            }
        }
        return sum;
    }

    /**
     * 动态规划
     */
    public int trap1(int[] height) {
        int sum = 0;
        //用两个数组记录第 i 列左边最高的墙的高度
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        //它前边的墙的左边的最高高度和它前边的墙的高度选一个较大的，就是当前列左边最高的墙了。
        for (int i = 1; i < height.length - 1; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }
        //它后边的墙的右边的最高高度和它后边的墙的高度选一个较大的，就是当前列右边最高的墙了
        for (int j = height.length - 2; j >= 0; j--) {
            maxRight[j] = Math.max(maxRight[j + 1], height[j + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(maxLeft[i], maxRight[i]);
            if (min > height[i]) {
                sum += min - height[i];
            }
        }
        return sum;
        /**
        int sum = 0;
        int max_left = 0;
        int[] max_right = new int[height.length];
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            max_left = Math.max(max_left, height[i - 1]);
            int min = Math.min(max_left, max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
        */
    }


    /**
     * NC136 输出二叉树的右视图
     * 请根据二叉树的前序遍历，中序遍历恢复二叉树，并打印出二叉树的右视图
     */
    private static HashMap<Integer, Integer> solveMap = new HashMap<>();
    private static int[] solveTemp;
    public static int[] solve (int[] xianxu, int[] zhongxu) {
        // write code here
        for (int i = 0; i < zhongxu.length; i++) {
            solveMap.put(zhongxu[i], i);
        }
        solveTemp = xianxu;
        TreeNode node = solveHelper(0, 0, zhongxu.length - 1);
        Queue<TreeNode> deque = new LinkedList<>();
        deque.add(node);
        ArrayList<Integer> list = new ArrayList<>();
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (poll.left != null) {
                    deque.add(poll.left);
                }
                if (poll.right != null) {
                    deque.add(poll.right);
                }
                if (i == size - 1) {
                    list.add(poll.val);
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private static TreeNode solveHelper(int root, int left, int right) {
        if (left > right) return null;
        int val = solveTemp[root];
        TreeNode node = new TreeNode(val);
        int idx = solveMap.get(val);
        node.left = solveHelper(root + 1, left, idx - 1);
        node.right = solveHelper(root + idx + 1 - left, idx + 1, right);
        return node;
    }


    /**
     * NC109 岛屿数量
     * 200. 岛屿数量
     * https://leetcode.cn/problems/number-of-islands/
     *
     * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     */
    public int solve (char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    solveDFS(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 感染函数, 从当前位置上下左右进行标记
     */
    private void solveDFS(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        //防止重复遍历
        grid[i][j] = '2';
        solveDFS(grid, i + 1, j);
        solveDFS(grid, i - 1, j);
        solveDFS(grid, i, j + 1);
        solveDFS(grid, i, j - 1);
    }


    /**
     * NC13 二叉树的最大深度
     * 求给定二叉树的最大深度，
     * 深度是指树的根节点到任一叶子节点路径上节点的数量。
     * 最大深度是所有叶子节点的深度的最大值。
     */
    public int maxDepthBFS (TreeNode root) {
        int res = 0;
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (poll.left != null) {
                    deque.add(poll.left);
                }
                if (poll.right != null) {
                    deque.add(poll.right);
                }
            }
            res++;
        }
        return res;
    }

    public int maxDepthDFS (TreeNode root) {
        if (root == null) return 0;
        int left =maxDepthDFS(root.left);
        int right = maxDepthDFS(root.right);
        return Math.max(left, right) + 1;
    }


    /**
     * NC141 判断是否为回文字符串
     */
    public boolean judge(String str) {
        char[] strChar = str.toCharArray();
        int length = strChar.length;
        for (int i = 0; i < length; i++) {
            if (strChar[i] != strChar[length - 1]) {
                return false;
            }
            length--;
        }
        return true;
    }


    /**
     * NC70 单链表的排序
     * 剑指 Offer II 077. 链表排序
     * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     */
    public ListNode sortInList(ListNode head) {
        if (head == null) return null;
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        quickSort(arr, 0, list.size() - 1);
        ListNode res = new ListNode(0);
        ListNode cur = res;
        for (int i = 0; i < list.size(); i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return res.next;
    }

    /**
     * NC62 判断是不是平衡二叉树
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) return true;
        return IsBalanced_Solution_Dfs(root) != -1;
    }

    public int IsBalanced_Solution_Dfs(TreeNode root) {
        if (root == null) return 0;
        int left = IsBalanced_Solution_Dfs(root.left);
        if (left == - 1) {
            return -1;
        }
        int right = IsBalanced_Solution_Dfs(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }


    /**
     * NC73 数组中出现次数超过一半的数字
     * 给一个长度为 n 的数组，数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组[1,2,3,2,2,2,5,4,2]。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
     * */
    public int MoreThanHalfNum(int[] array) {
        int res = 0, count = 0;
        for (int i = 0; i < array.length; i++) {
            if (count == 0) {
                count++;
                res = array[i];
            } else {
                //由于题目说明出现的次数超出数组长度的一半, count最终一定会大于1.
                count = res == array[i] ? count + 1 : count - 1;
            }
        }
        return res;
    }

    /**
     * NC59 矩阵的最小路径和
     * 剑指 Offer 47. 礼物的最大价值
     * 给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，
     * 路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。
     */
    public int minPathSum(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }
        for (int i =1 ; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * NC97 字符串出现次数的TopK问题
     * 给定一个字符串数组，再给定整数 k ，请返回出现次数前k名的字符串和对应的次数。
     * 返回的答案应该按字符串出现频率由高到低排序。如果不同的字符串有相同出现频率，按字典序排序。
     * 对于两个字符串，大小关系取决于两个字符串从左到右第一个不同字符的 ASCII 值的大小关系。
     * 比如"ah1x"小于"ahb"，"231"<”32“
     * 字符仅包含数字和字母
     *
     */
    public String[][] topKstrings(String[] strings, int k) {
        // write code here
        if (k == 0) {
            return new String[][]{};
        }
        String[][] res = new String[k][2];
        //TreeMap中的元素默认按照keys的自然排序排列。
        //（对Integer来说，其自然排序就是数字的升序；对String来说，其自然排序就是按照字母表排序）
        TreeMap<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < strings.length; i++) {
            String s = strings[i];
            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s) + 1);
            }
        }
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        //先是按出现次数降序比较，相同则再按照字符ASCII码降序比较
        Collections.sort(list, (o1, o2) -> o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey()) : o2.getValue().compareTo(o1.getValue()));
        for (int i = 0; i < k; i++) {
            res[i][0] = list.get(i).getKey();
            res[i][1] = list.get(i).getValue().toString();
        }
        return res;
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
       // merge(new int[]{0}, 0, new int[]{1}, 1);

        //[1,2,4,5,3],[4,2,5,1,3]
        solve(new int[]{1,2,4,5,3}, new int[]{4,2,5,1,3});
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

    public TreeNode(int val) {
        this.val = val;
    }
}
