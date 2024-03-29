package 算法.链表;


import 算法.二叉树.TreeNode;

import java.util.*;

public class LinkedListCode {

    /**
     * 203. 移除链表元素
     * https://leetcode-cn.com/problems/remove-linked-list-elements/
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     * 输入：head = [1,2,6,3,4,5,6], val = 6
     * 输出：[1,2,3,4,5]
     * <p>
     * 输入：head = [], val = 1
     * 输出：[]
     * <p>
     * 输入：head = [7,7,7,7], val = 7
     * 输出：[]
     */
    public static ListNode removeLinkedList(ListNode node, int target) {
        //递归
        /*if (node == null) {
            return node;
        }
        node.next = removeLinkedList1(node.next, target);
        return Objects.equals(node.getData(), target) ? node.next : node;*/

        //迭代
        ListNode listNode = new ListNode(0);
        listNode.next = node;
        //temp 跟listnode 为同一个对象
        ListNode temp = listNode;
        while (temp.next != null) {
            if (temp.next.data == target) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return listNode.next;
    }

    public static ListNode removeLinkedList1(ListNode node, int target) {
        //递归
        if (node == null) {
            return node;
        }
        node.next = removeLinkedList2(node.next, target);
        return Objects.equals(node.getData(), target) ? node.next : node;
    }

    public static ListNode removeLinkedList2(ListNode node, int target) {
        //递归
        if (node == null) {
            return node;
        }
        node.next = removeLinkedList3(node.next, target);
        return Objects.equals(node.getData(), target) ? node.next : node;
    }

    public static ListNode removeLinkedList3(ListNode node, int target) {
        //递归
        if (node == null) {
            return node;
        }
        node.next = removeLinkedList2(node.next, target);
        return Objects.equals(node.getData(), target) ? node.next : node;
    }

    /**
     * 206.反转链表
     * https://leetcode-cn.com/problems/reverse-linked-list/
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * 示例: 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    public static ListNode reverseList(ListNode head) {
        //双指针大法
        ListNode prev = null;
        ListNode temp = head;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        return prev;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * <p>
     * 进阶：你能尝试使用一趟扫描实现吗？
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     * 示例 2：
     * 输入：head = [1], n = 1
     * 输出：[]
     * 示例 3：
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = new ListNode(0);
        temp.next = head;
        //快指针
        ListNode first = head;
        //慢指针
        ListNode second = temp;
        //快指针往前走n步
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return temp.next;
    }


    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) return null;
        //临时节点
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        //快慢指针
        ListNode slowNode = dummyNode;
        ListNode fastNode = dummyNode;
        //快指针不为空时
        while (fastNode != null) {
            ListNode temp = fastNode.next;
            fastNode = temp;
            if (n < 0) {
                //快指针走完n步走,慢指针再走.
                ListNode temp2 = slowNode.next;
                slowNode = temp2;
            }
            n--;
        }
        slowNode.next = slowNode.next.next;
        return dummyNode.next;
    }


    /**
     * 160. 相交链表
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null
     *
     * 两个链表同时遍历,如果A链表走完了,就去走B链表,B链表走完后,就去走A链表,最后走过的相同部分,即为相交的部分
     *
     * 注意 相同部分指地址相同,而不是数值相同
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    /**
     * 142. 环形链表 II
     * https://leetcode-cn.com/problems/linked-list-cycle-ii/
     *
     * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
     *
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     * 说明：不允许修改给定的链表。
     *
     * 进阶：你是否可以使用 O(1) 空间解决此题？
     *
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            slow = slow.next;
            if (fast == slow) {
                ListNode res = head;
                //它们会在入口处相遇
                while (res != slow) {
                    res = res.next;
                    slow = slow.next;
                }
                return res;
            }
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        if (head == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        //标识是否存在环形链表
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasCycle = true;
                break;
            }
        }
        if (hasCycle) {
            //如果存在环,会在环形入口相遇
            while (head != slow) {
                head = head.next;
                slow = slow.next;
            }
            return head;
        }
        return null;
    }


    /**
     * 92. 反转链表 II
     * https://leetcode.cn/problems/reverse-linked-list-ii/
     * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     *
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        //本质上就是把left到right之间的结点依次插入到m节点的前面，这样就形成了逆序
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //永远指向待反转区域的第一个节点left的前一个节点.循环过程中不变.
        ListNode pre = dummy;
        //left 前面的节点顺序保持不变
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        //指向待反转区域的第一个节点left
        ListNode cur = pre.next;
        //永远指向curr节点的下一个节点
        ListNode next;
        for (int i = left; i < right; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }

    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        //left前面的保持不变
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }

        ListNode cur = pre.next;
        ListNode temp = null;
        //翻转中间的链表
        for (int i = left; i <= right; i++) {
            ListNode next = cur.next;
            cur.next = temp;
            temp = cur;
            cur = next;
        }

        //转换n, m 位置的节点指向
        pre.next.next = cur;
        pre.next = temp;
        return dummy.next;
    }


    /**
     * 234. 回文链表
     * BM13 判断一个链表是否为回文结构
     * https://leetcode.cn/problems/palindrome-linked-list/
     *
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return false;
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.data);
            head = head.next;
        }
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /**
     * 328. 奇偶链表
     * BM14 链表的奇偶重排
     * https://leetcode.cn/problems/odd-even-linked-list/
     * 
     * 给定单链表的头节点head，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
     * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为偶数 ，以此类推。
     *
     * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
     * 你必须在O(1)的额外空间复杂度和O(n)的时间复杂度下解决这个问题。
     *
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        //假设首节点为奇数
        ListNode o = head;
        //偶数的头节点
        ListNode p = head.next;
        //偶数的临时节点
        ListNode e = p;
        while (o.next != null && e.next != null) {
            o.next = e.next;
            o = o.next;
            e.next = o.next;
            e = e.next;
        }
        o.next = p;
        return head;
    }


    /**
     * 剑指 Offer 28. 对称的二叉树
     * BM31 对称的二叉树
     * https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/
     * <p>
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * 1
     * / \
     * 2  2
     * / \ / \
     * 3 4 4 3
     * <p>
     * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
     * <p>
     * 1
     * / \
     * 2  2
     * \  \
     * 3  3
     * <p>
     * <p>
     * 示例 1：
     * 输入：root = [1,2,2,3,4,4,3]
     * 输出：true
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        TreeNode node1 = root;
        TreeNode node2 = root;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(node1.left);
        deque.add(node2.right);
        while (!deque.isEmpty()) {
            node1 = deque.poll();
            node2 = deque.poll();
            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            deque.add(node1.left);
            deque.add(node2.right);
            deque.add(node1.right);
            deque.add(node2.left);
        }
        return true;

        /*if (pRoot == null) {
            return true;
        }
        Deque<TreeNode> d1 = new LinkedList<>();
        Deque<TreeNode> d2 = new LinkedList<>();
        d1.add(pRoot);
        d2.add(pRoot);
        while (!d1.isEmpty()) {
            if (d1.size() != d2.size()) {
                return false;
            }
            for (int i = 0; i < d1.size(); i++) {
                TreeNode node1 = d1.poll();
                TreeNode node2 = d2.poll();
                if (node1 == null && node2 == null) {
                    continue;
                }
                if (node1 == null || node2 == null || node1.val != node2.val) {
                    return false;
                }
                d1.add(node1.left);
                d1.add(node1.right);
                d2.add(node2.right);
                d2.add(node2.left);
            }
        }
        return true;

        boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        return dfs(pRoot.left, pRoot.right);
        }

    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && dfs(left.left, right.right) && dfs(left.right, right.left);
    }

        */
    }


    /**
     * 23. 合并 K 个升序链表
     * https://leetcode.cn/problems/merge-k-sorted-lists/
     *
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * 解题思路: 1. 分治法:可参考归并排序 2. 利用优先队列
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        if (lists.length == 2) return mergeTwoListNode(lists[0], lists[1]);
        int mid = lists.length / 2;
        ListNode[] l1 = new ListNode[mid];
        for (int i = 0; i < l1.length; i++) {
            l1[i] = lists[i];
        }

        ListNode[] l2 = new ListNode[lists.length - mid];
        for (int j = 0; j < l2.length; j++) {
            l2[j] = lists[mid + j];
        }
        return mergeTwoListNode(mergeKLists(l1), mergeKLists(l2));
    }

    private ListNode mergeTwoListNode(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.data <= l2.data) {
            l1.next = mergeTwoListNode(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListNode(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> dq = new PriorityQueue<>((n1, n2) -> n1.data - n2.data);

        for (ListNode listNode : lists) {
            if (listNode == null) continue;
            dq.add(listNode);
        }

        ListNode dummyHead = new ListNode(0);
        ListNode currNode = dummyHead;
        while (!dq.isEmpty()) {
            ListNode nextNode = dq.poll();
            currNode.next = nextNode;
            currNode = currNode.next;
            if (currNode.next != null) {
                dq.add(nextNode.next);
            }
        }
        return dummyHead.next;
    }


    /**
     * 143. 重排链表
     * https://leetcode.cn/problems/reorder-list/
     *
     * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
     * L0 → L1 → … → Ln - 1 → Ln
     *
     * 请将其重新排列后变为：
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     *
     * 链表大杂烩,比较有代表性的链表题目.
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        //快慢指针得到中间节点
        ListNode slow = head;
        ListNode fast = slow.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //将链表分成两段
        ListNode secondHalf = slow.next;
        slow.next = null;

        //翻转第二个链表
        secondHalf = reverseListNode(secondHalf);

        //交替插入节点
        ListNode temp = head;
        while (secondHalf != null) {
            ListNode next = temp.next;
            temp.next = secondHalf;
            secondHalf = secondHalf.next;
            temp.next.next = next;
            temp = next;
        }

    }

    private ListNode reverseListNode(ListNode node) {
        ListNode dummy = node;
        ListNode prev = null;
        while (dummy != null) {
            ListNode next = dummy.next;
            dummy.next = prev;
            prev = dummy;
            dummy = next;
        }
        return prev;
    }


    /**
     * 82. 删除排序链表中的重复元素 II
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
     *
     * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
     *
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(101);
        dummy.next = head;
        //前驱结点
        ListNode prev = dummy;
        //当前节点
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.data == cur.next.data) {
                int val = cur.data;
                //判断是否相等,往后推
                while (cur != null && cur.data == val) {
                    cur = cur.next;
                }
                //前驱节点转化
                prev.next = cur;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }


    /**
     * 83. 删除排序链表中的重复元素
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
     *
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次(重复元素值保留一个)。返回 已排序的链表 。
     *
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode temp = head;
        while(temp!= null) {
            if (temp.next != null && temp.data == temp.next.data) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }


    /**
     * 148. 排序链表
     * https://leetcode.cn/problems/sort-list/
     *
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next  == null) {
            return head;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        return quickSort(dummyNode, null);
    }

    private ListNode quickSort(ListNode head, ListNode end) {
        if (head == end || head.next == end || head.next.next == end) {
            return head;
        }
        //临时列表，存放小于划分节点的值。
        ListNode tempNode = new ListNode(-1);
        ListNode tempNodeDummy = tempNode;
        //分割点
        ListNode midNode = head.next;
        ListNode midNodeDummy = midNode;
        while (midNodeDummy.next != end) {
            if (midNodeDummy.next.data < midNode.data) {
                tempNodeDummy.next = midNodeDummy.next;
                tempNodeDummy = tempNodeDummy.next;
                midNodeDummy.next = midNodeDummy.next.next;
            } else {
                midNodeDummy = midNodeDummy.next;
            }
        }
        //合并临时链表和原链表
        tempNodeDummy.next = head.next;
        //将临时节点插入到head后面，实现在原head上面进行操作
        head.next = tempNode.next;
        quickSort(head, midNode);
        quickSort(midNode, end);
        return head.next;
    }


    public ListNode sortList2(ListNode head) {
        //归并排序
        if (head == null || head.next == null) {
            return head;
        }
        ListNode midNode = getMidNode(head);
        ListNode rightNode = midNode.next;
        midNode.next = null;
        return mergeSortListNode(sortList2(head), sortList2(rightNode));
    }

    //寻找链表的中间位置
    private ListNode getMidNode(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode slow = node;
        ListNode fast = node.next.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    //将两个有序链表进行排序
    private ListNode mergeSortListNode(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (left.data <= right.data) {
            left.next = mergeSortListNode(left.next, right);
            return left;
        } else {
            right.next = mergeSortListNode(left, right.next);
            return right;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(8);
        node.add(4);
        node.add(5);

        ListNode node1 = new ListNode(5);
        node1.add(0);
        node1.add(1);
        node1.next = node;

        ListNode node2 = new ListNode(4);
        node2.add(1);
        node2.next = node;
        //reverseList(node);
        //removeLinkedList(node, 8);
        //removeNthFromEnd(node, 2);
        //getIntersectionNode(node1, node2);
        ListNode node3 = new ListNode(1);
        node3.add(2);
        node3.add(3);
        node3.add(4);
        node3.add(5);
        List<String> s = Arrays.asList("21", "232");
        System.out.println(s);
    }
}