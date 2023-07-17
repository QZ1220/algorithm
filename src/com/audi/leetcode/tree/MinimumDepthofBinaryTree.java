package com.audi.leetcode.tree;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 * <p>
 * 求一颗二叉树的最小深度
 *
 * @author : wangquanzhou
 * @date : 2023/7/18 07:28
 */
public class MinimumDepthofBinaryTree {

    public int minDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        if (root.left==null){
            return minDepth(root.right)+1;
        }
        if (root.right==null){
            return minDepth(root.left)+1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public static void main(String[] args) {

    }
}
