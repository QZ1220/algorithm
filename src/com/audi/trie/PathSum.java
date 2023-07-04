package com.audi.trie;

/**
 * 给定一个二叉树，确定是否存在一条从根节点到叶子节点的路径，使得该路径的节点值之和等于给定的目标值。
 *
 * @author : wangquanzhou
 * @date : 2023/7/4 21:02
 */
public class PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 在这里实现算法
        if (root == null) {
            return false;
        }
        targetSum -= root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            return true;
        }
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }

    public static void main(String[] args) {

    }
}
