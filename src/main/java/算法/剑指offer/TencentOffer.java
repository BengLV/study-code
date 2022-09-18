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

}
