package 算法.剑指offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。
 * 如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lru-cache
 * 
 * @author: LuPeng
 * @create: 2023-04-04
 **/
class LRUCache {

    private int capacity;
    private int size;
    private Node head;
    private Map<Integer, Node> map = new HashMap<>();
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        head.prev = head;
        head.next = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        node = node.remove();
        head.insert(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, value);
            map.put(key, node);
            size++;
        } else {
            node = node.remove();
            node.val = value;
        }
        head.insert(node);
        if (size > capacity) {
            Node removed = head.prev.remove();
            map.remove(removed.key);
            size--;
        }
    }

     class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node() {
        }

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        Node remove() {
            prev.next = next;
            next.prev = prev;
            next = null;
            prev = null;
            return this;
        }

        void insert(Node node) {
            node.next = next;
            next.prev = node;
            node.prev = this;
            next = node;
        }
    }
}




