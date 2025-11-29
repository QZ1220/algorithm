package com.audi.leetcode.tree.bst;


import com.audi.leetcode.tree.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/?envType=study-plan&id=level-1
 * <p>
 * 参考https://blog.csdn.net/ln_ydc/article/details/127160749思路
 *
 * @author : wangquanzhou
 * @date : 2023/3/27 20:09
 */
public class LowestCommonAncestorofaBinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        if (root.val > Math.max(p.val, q.val)) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < Math.min(p.val, q.val)) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    public static void main(String[] args) {

    }
}
