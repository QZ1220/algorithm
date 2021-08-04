package com.audi.leetcode.Tree;


/**
 * https://leetcode.com/problems/invert-binary-tree/
 * <p>
 * 翻转一颗二叉树
 *
 * @author: WangQuanzhou
 * @date: 2021-08-04 10:27 PM
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return null;
        }

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public static void main(String[] args) {
    }


}
