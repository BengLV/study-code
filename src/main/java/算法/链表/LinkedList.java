package 算法.链表;


import java.util.Objects;

public class LinkedList {

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
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
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
        removeLinkedList(node, 8);
        //removeNthFromEnd(node, 2);
        getIntersectionNode(node1, node2);
        System.out.println(node);
    }
}