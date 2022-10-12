package 算法.剑指offer;

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


    public int[] sortArray(int[] nums) {
        return null;
    }

}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

}
