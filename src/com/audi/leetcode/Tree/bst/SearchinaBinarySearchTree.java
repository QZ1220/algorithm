package com.audi.leetcode.Tree.bst;

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
