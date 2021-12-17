package 算法.链表;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyLinkedList {

    /**
     * 707.设计链表
     * https://leetcode-cn.com/problems/design-linked-list/
     * <p>
     * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val和next。
     * val是当前节点的值，next是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性prev以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
     * <p>
     * 在链表类中实现这些功能：
     * <p>
     * get(index)：获取链表中第index个节点的值。如果索引无效，则返回-1。
     * addAtHead(val)：在链表的第一个元素之前添加一个值为val的节点。插入后，新节点将成为链表的第一个节点。
     * addAtTail(val)：将值为val 的节点追加到链表的最后一个元素。
     * addAtIndex(index,val)：在链表中的第index个节点之前添加值为val 的节点。如果index等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
     * deleteAtIndex(index)：如果索引index 有效，则删除链表中的第index 个节点。
     * <p>
     * 示例：
     * MyLinkedList linkedList = new MyLinkedList();
     * linkedList.addAtHead(1);
     * linkedList.addAtTail(3);
     * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
     * linkedList.get(1);            //返回2
     * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
     * linkedList.get(1);            //返回3
     *
     */

    int size;
    ListNode head;

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            this.val = x;
        }
    }

    public MyLinkedList() {
        this.size = 0;
        head = new ListNode(0);
    }

    /**
     * addAtIndex(index,val)：在链表中的第index个节点之前添加值为val 的节点。
     * 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
     * 如果 index 大于链表长度，则不会插入节点。
     * 如果 index 小于0，则在头部插入节点。
     */
    public void addAtIndex(int index, int val) {
        if (index > size) return;

        if (index < 0) index = 0;

        ++size;
        // find predecessor of the node to be added
        ListNode pred = head;
        for (int i = 0; i < index; ++i) {
            pred = pred.next;
        }

        ListNode toAdd = new ListNode(val);
        toAdd.next = pred.next;
        pred.next = toAdd;
    }

    /**
     * addAtHead(val)：在链表的第一个元素之前添加一个值为val的节点。插入后，新节点将成为链表的第一个节点。
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * addAtTail(val)：将值为val 的节点追加到链表的最后一个元素。
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * get(index)：获取链表中第index个节点的值。如果索引无效，则返回-1。
     */
    public int get(int index) {
        if (index >= size || index < 0) {
            return -1;
        }
        ListNode pred = head;
        for (int i = 0; i < size; i++) {
            pred = pred.next;
            if (i == index) {
                return pred.val;
            }
        }
        return pred.val;
    }

    /**
     * deleteAtIndex(index)：如果索引index 有效，则删除链表中的第index 个节点。
     */
    public void deleteAtIndex(int index) {
        if (index >= size || index < 0) {
            return;
        }
        ListNode pred = head;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        pred.next = pred.next.next;
        size--;
    }



    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        List list  = Arrays.asList(1,2,3,4);
        list.get(1);
        /*["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
[[],[1],[3],[1,2],[1],[1],[1]]*/
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);
        linkedList.get(1);
        linkedList.deleteAtIndex(0);
        linkedList.get(0);
        System.out.println(linkedList);
    }

}
