package com.audi.leetcode.tree;


/**
 * https://leetcode.com/problems/path-sum/
 * <p>
 * 求从根节点到叶子节点，加起来的和等于target的路径
 * <p>
 * 深度优先遍历
 * 递归，如果递归到根节点还没有满足条件，就返回false
 *
 * @author: WangQuanzhou
 * @date: 2021-08-05 9:25 PM
 */
public class PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (null == root) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }


    public static void main(String[] args) {
    }
}
