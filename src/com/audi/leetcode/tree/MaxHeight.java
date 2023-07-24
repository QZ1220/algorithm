package com.audi.leetcode.tree;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 * <p>
 * 求二叉树的最大深度，要有递归的思想
 *
 * @author : wangquanzhou
 * @date : 2023/7/4 20:55
 */
public class MaxHeight {
    public int maxDepth(TreeNode root) {
        // 在这里实现算法
        if (root == null) {
            return 0;
        }
        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);
        return Math.max(maxLeft, maxRight) + 1;
    }


    public static void main(String[] args) {

    }

}
