package com.audi.leetcode.tree.bst;


/**
 * https://leetcode.com/problems/range-sum-of-bst/
 * <p>
 * 求一颗二叉树中落在指定区间的节点的集合
 * <p>
 * 思路：直接遍历树，然后累加，递归实现
 *
 * @author: WangQuanzhou
 * @date: 2021-08-02 9:18 PM
 */
public class RangeSumofBST {

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (null == root) {
            return 0;
        }
        return rangeSum(root, low, high, 0);
    }

    private int rangeSum(TreeNode root, int low, int high, int sum) {
        if (null == root) {
            return sum;
        }
        if (root.val >= low && root.val <= high) {
            sum = sum + root.val;
        }

        sum = rangeSum(root.left, low, high, sum);
        return rangeSum(root.right, low, high, sum);
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
