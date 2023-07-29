package com.qz;

import com.audi.leetcode.tree.TreeNode;

/**
 * kind of demo code
 *
 * @author : wangquanzhou
 * @date : 2023/7/29 16:29
 */
public class Demo {

    /**
     * 交换二叉树的左右子树
     *
     * @param root
     */
    public void invert(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invert(root.left);
        invert(root.right);
    }
}
