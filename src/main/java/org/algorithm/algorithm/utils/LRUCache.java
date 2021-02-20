package org.algorithm.algorithm.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KaiyangLyu
 * @version 1.0
 * @name LRUCache
 * @date 2020/11/17 15:35
 **/
public class LRUCache {

    private ListNode head, tail;
    private int capacity;
    private Map<Integer, ListNode> map;

    /*
     * @param capacity: An integer
     */public LRUCache(int capacity) {
        // do intialization if necessary
        this.capacity = capacity;
        this.map = new HashMap<>((int) Math.ceil(capacity / 0.75f) + 1);
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        if (!this.map.containsKey(key))
            return -1;
        ListNode curr = this.map.get(key);
        move_to_tail(curr);
        return curr.value;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // write your code here
        if (this.map.containsKey(key)) {
            this.map.get(key).value = value;
        } else {
            if (this.map.size() == this.capacity) {
                this.map.remove(this.head.next.key);
                ListNode deleteNode = this.head.next;
                this.head.next = deleteNode.next;
                deleteNode.next.prev = this.head;
            }
            ListNode curr = new ListNode(key, value);
            this.map.put(key, curr);
        }
        move_to_tail(this.map.get(key));
    }

    private void move_to_tail(ListNode curr) {
        if (curr.next != null)
            curr.next.prev = curr.prev;
        if (curr.prev != null)
            curr.prev.next = curr.next;

        curr.next = this.tail;
        curr.prev = this.tail.prev;
        this.tail.prev.next = curr;
        this.tail.prev = curr;
    }

    static class ListNode {
        int key, value;
        ListNode prev, next;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
