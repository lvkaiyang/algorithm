package org.algorithm.algorithm.structures.basic;

/**
 * @author KaiyangLyu
 * @version 1.0
 * @name TreeNode
 * @date 2020/6/3 13:59
 **/
public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
