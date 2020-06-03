package org.algorithm.algorithm.utils;

import org.algorithm.algorithm.structures.basic.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author KaiyangLyu
 * @version 1.0
 * @name TreeNodeUtil
 * @date 2020/6/3 14:05
 **/
public class TreeNodeUtil {
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public static String serialize(TreeNode root) {
        // write your code here
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr != null) {
                    sb.append(",".concat(String.valueOf(curr.val)));
                } else {
                    sb.append(",".concat("#"));
                }

                if (curr != null) {
                    if (curr.left != null) {
                        queue.offer(curr.left);
                    } else {
                        queue.offer(null);
                    }

                    if (curr.right != null) {
                        queue.offer(curr.right);
                    } else {
                        queue.offer(null);
                    }
                }
            }
        }

        while (sb.charAt(sb.length() - 1) == '#' || sb.charAt(sb.length() - 1) == ',')
            sb.deleteCharAt(sb.length() - 1);

        sb.setCharAt(0, '{');
        sb.append("}");

        return sb.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public static TreeNode deserialize(String data) {
        // write your code here
        if (data == null || "".equals(data)) return null;

        data = data.substring(1, data.length() - 1);
        String[] split = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        int idx = 1;

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (idx < split.length) {
                if (!split[idx].equals("#")) {
                    TreeNode left = new TreeNode(Integer.parseInt(split[idx]));
                    curr.left = left;
                    queue.offer(left);
                }
                idx++;
            }
            if (idx < split.length) {
                if (!split[idx].equals("#")) {
                    TreeNode right = new TreeNode(Integer.parseInt(split[idx]));
                    curr.right = right;
                    queue.offer(right);
                }
                idx++;
            }
        }

        return root;
    }
}
