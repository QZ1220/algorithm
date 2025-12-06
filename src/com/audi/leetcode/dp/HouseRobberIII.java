package com.audi.leetcode.dp;

import com.audi.leetcode.tree.TreeNode;

/**
 * https://leetcode.com/problems/house-robber-iii/
 * <p>
 * 题目给定一个二叉树，每个节点有一个值，现在希望遍历这个二叉树，偷节点的值，使得最终结果最大。相邻的节点不能偷。
 * <p>
 * 采用二叉树的后续遍历
 * <p>
 * https://blog.csdn.net/qq_35328850/article/details/99692311
 * <p>
 * https://www.jianshu.com/p/934c3ff52f3d
 *
 * @author WangQuanzhou
 * @date 2020-05-12
 */
public class HouseRobberIII {


    public int rob(TreeNode root) {
        int[] values = getValues(root);
        return Math.max(values[0], values[1]);
    }

    /**
     * 自底向上寻找的思路
     * <p>
     * 参考视频：
     * https://www.bilibili.com/video/BV15T411h7Dn/?spm_id_from=333.337.search-card.all.click&vd_source=d1530fb814268f770330143e24aaf1e6
     * <p>
     * 采用后续遍历的方式
     *
     * @param root
     * @return
     */
    private int[] getValues(TreeNode root) {
        if (root == null) {
            return new int[2];
        }

        int[] leftValues = getValues(root.left);
        int[] rightValues = getValues(root.right);
        // values[0] 表示当前root节点要偷时，可以获得的最大值
        // values[1] 表示当前root节点不偷时，可以获得的最大值
        int[] values = new int[2];
        values[0] = root.val + leftValues[1] + rightValues[1];
        // root不偷时，可以选择从左右节点中选择一个最大的偷，所以下面是求max
        values[1] = Math.max(leftValues[0], leftValues[1]) + Math.max(rightValues[0], rightValues[1]);
        return values;
    }

    public static void main(String[] args) {
        Integer[] nums = {3, 2, 3, null, 3, null, 1};


    }
}
