package com.audi.leetcode.recursion;


/**
 * https://leetcode.com/problems/symmetric-tree/
 * <p>
 * 判断一颗二叉树是否是对称的
 *
 * @author: WangQuanzhou
 * @date: 2020/10/19 21:20
 */
public class SymmetricTree {
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

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return Boolean.TRUE;
        }

        return isSymmetric(root.left) && isSymmetric(root.right);
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
        SymmetricTree symmetricTree = new SymmetricTree();
//        symmetricTree.isSymmetric()
    }
}
