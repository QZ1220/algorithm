package com.audi.leetcode.tree;


/**
 * https://leetcode.com/problems/same-tree/
 * <p>
 * 判断两颗二叉树是否相等
 *
 * @author: WangQuanzhou
 * @date: 2021-08-04 10:18 PM
 */
public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    public static void main(String[] args) {
    }
}
