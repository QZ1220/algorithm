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

        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        // 递归到底了
        if (left == null && right == null) {
            return Boolean.TRUE;
        }
        // 如果左右的值不相等或者不对称 那么就不需要再继续进行递归  直接返回false
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
