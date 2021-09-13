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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
