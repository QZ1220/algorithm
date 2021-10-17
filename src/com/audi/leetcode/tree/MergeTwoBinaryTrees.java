package com.audi.leetcode.tree;


/**
 * https://leetcode.com/problems/merge-two-binary-trees/
 * <p>
 * 题目要求合并两棵树，如果某些节点位置，两棵树都有元素，就把二者加起来，否则保留非空的那棵树的节点元素
 *
 * @author: WangQuanzhou
 * @date: 2021-10-17 6:29 PM
 */
public class MergeTwoBinaryTrees {


    /**
     * 将root2中的元素全部合并到root1上面去
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (null == root1) {
            return root2;
        }
        if (null == root2) {
            return root1;
        }

        root1.val = root1.val + root2.val;

        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
    }


    public static void main(String[] args) {

        TreeNode node15 = new TreeNode(5);
        TreeNode node12 = new TreeNode(2);
        TreeNode node13 = new TreeNode(3, node15, null);
        TreeNode root1 = new TreeNode(1, node13, node12);

        TreeNode node24 = new TreeNode(4);
        TreeNode node27 = new TreeNode(7);
        TreeNode node21 = new TreeNode(1, null, node24);
        TreeNode node23 = new TreeNode(3, null, node27);
        TreeNode root2 = new TreeNode(2, node21, node23);

        MergeTwoBinaryTrees mergeTwoBinaryTrees = new MergeTwoBinaryTrees();
        mergeTwoBinaryTrees.mergeTrees(root1, root2);
    }
}
