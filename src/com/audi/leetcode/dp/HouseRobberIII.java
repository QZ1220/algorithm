package com.audi.leetcode.dp;

/**
 * https://leetcode.com/problems/house-robber-iii/
 * <p>
 * https://blog.csdn.net/qq_35328850/article/details/99692311
 * <p>
 * https://www.jianshu.com/p/934c3ff52f3d
 *
 * @author WangQuanzhou
 * @date 2020-05-12
 */
public class HouseRobberIII {


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

    public int rob(TreeNode root) {
        return dfs(root);
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int result = root.val;
        if (root.left != null) result += dfs(root.left.left) + dfs(root.left.right);    // 该节点左间接子树和最大值
        if (root.right != null) result += dfs(root.right.left) + dfs(root.right.right); // 该节点右间接子树和最大值
        result = Math.max(result, dfs(root.left) + dfs(root.right));    // 比较选择包含该节点还是不包含该节点的和最大值
        return result;
    }

    public static void main(String[] args) {
        Integer[] nums = {3, 2, 3, null, 3, null, 1};


    }
}
