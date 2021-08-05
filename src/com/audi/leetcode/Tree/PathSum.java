package com.audi.leetcode.Tree;


/**
 * https://leetcode.com/problems/path-sum/
 * <p>
 * 求从根节点到叶子节点，加起来的和等于target的路径
 *
 * @author: WangQuanzhou
 * @date: 2021-08-05 9:25 PM
 */
public class PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (null == root) {
            return false;
        }
        return hasPathSum(root, 0, targetSum);
    }

    private boolean hasPathSum(TreeNode root, int sum, int targetSum) {
        if (root == null) {
            return false;
        }
        sum = sum + root.val;
        if (sum == targetSum && root.left == null && root.right == null) {
            return true;
        }

        return hasPathSum(root.left, sum, targetSum) || hasPathSum(root.right, sum, targetSum);
    }

    public static void main(String[] args) {
    }
}
