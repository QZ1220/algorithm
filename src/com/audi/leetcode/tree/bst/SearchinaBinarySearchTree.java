package com.audi.leetcode.tree.bst;


/**
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 * <p>
 * 求BST中等于指定值的节点的树
 * <p>
 * 直接递归
 *
 * @author: WangQuanzhou
 * @date: 2021-08-03 7:33 AM
 */
public class SearchinaBinarySearchTree {

    public TreeNode searchBST(TreeNode root, int val) {

        if (null == root) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        TreeNode temp = searchBST(root.left, val);
        if (temp != null) {
            return temp;
        }

        temp = searchBST(root.right, val);
        if (temp != null) {
            return temp;
        }

        return null;
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
