package com.audi.leetcode.tree;


/**
 * https://leetcode.com/problems/symmetric-tree/
 * <p>
 * 对称二叉树
 * <p>
 * 递归判断
 *
 * @author: WangQuanzhou
 * @date: 2021-08-04 10:18 PM
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return Boolean.TRUE;
        }

        // 递归判断左右字数是否是对称的
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return Boolean.TRUE;
        }
        if (left == null || right == null || left.val != right.val) {
            return Boolean.FALSE;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public static void main(String[] args) {
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
