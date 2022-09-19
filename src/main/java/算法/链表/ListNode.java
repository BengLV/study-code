package 算法.链表;

public class ListNode {
    public Integer data;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int data) {
        this.data = data;
    }

    public ListNode(int a, ListNode listNode) {
        this.data = a;
        this.next = listNode;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public void add(int newdata) {
        ListNode newNode = new ListNode(newdata);
        if (this.next == null) {
            this.next = newNode;
        } else {
            this.next.add(newdata);
        }
    }
}
