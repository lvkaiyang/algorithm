package org.algorithm.algorithm.utils;

import org.algorithm.algorithm.structures.basic.ListNode;

/**
 * @author KaiyangLyu
 * @version 1.0
 * @name ListNodeUtil
 * @date 2020/6/3 14:17
 **/
public class ListNodeUtil {

    public static String serialize(ListNode head) {
        // write your code here
        if (head == null) return null;

        StringBuilder sb = new StringBuilder();

        while (head != null) {
            sb.append("->".concat(String.valueOf(head.val)));
            head = head.next;
        }

        sb.append("->null");

        return sb.substring(2);
    }

    public static ListNode deserialize(String data) {
        // write your code here
        if (data == null || "".equals(data)) return null;

        String[] splits = data.split("->");
        ListNode dummy = new ListNode(-1);
        ListNode head = new ListNode(Integer.parseInt(splits[0]));

        dummy.next = head;

        for (int i = 1; i < splits.length; i++) {
            String split = splits[i];
            if (split.equals("null")) {
                head.next = null;
                break;
            } else {
                head.next = new ListNode(Integer.parseInt(split));
                head = head.next;
            }
        }

        return dummy.next;
    }
}
