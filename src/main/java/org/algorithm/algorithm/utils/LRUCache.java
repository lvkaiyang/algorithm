package org.algorithm.algorithm.utils;

import java.util.HashMap;

/**
 * @author KaiyangLyu
 * @version 1.0
 * @name LRUCache
 * @date 2020/11/17 15:35
 **/
public class LRUCache {

    HashMap<Integer, ListNode> map;
    ListNode head, tail;
    int capacity;

    /*
     * @param capacity: An integer
     */
    public LRUCache(int capacity) {
        // do intialization if necessary
        this.map = new HashMap<>((int) (Math.ceil(capacity / 0.75) + 1));
        this.capacity = capacity;
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
        if (!this.map.containsKey(key)) return -1;

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
            move_to_tail(this.map.get(key));
        } else {
            if (this.map.size() == capacity) {
                ListNode last = this.head.next;
                this.head.next = last.next;
                last.next.prev = this.head;
                this.map.remove(last.key);
            }
            ListNode curr = new ListNode(key, value);
            curr.prev = this.head;
            this.map.put(key, curr);
            move_to_tail(curr);
        }
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
        ListNode prev, next;
        int key, value;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
