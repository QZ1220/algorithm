package com.audi.leetcode.search;

import com.audi.leetcode.tree.TreeNode;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * <p>
 * 求一个BST的第k小的元素
 * <p>
 * 直接按照中序遍历二叉树，当遍历到第k个节点时，就是本题的解
 *
 * @author : wangquanzhou
 * @date : 2023/7/29 19:37
 */
public class KthSmallestElementinaBST {

    int kth = Integer.MIN_VALUE;
    int cnt = 0;

    public int kthSmallest(TreeNode root, int k) {
        if (null == root) {
            return -1;
        }
        inOrder(root, k);
        return kth;
    }

    private void inOrder(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        if (kth == Integer.MIN_VALUE) {
            inOrder(root.left, k);
        }

        cnt = cnt + 1;
        if (cnt == k) {
            kth = root.val;
        }

        if (kth == Integer.MIN_VALUE) {
            inOrder(root.right, k);
        }
    }


    public static void main(String[] args) {

    }
}
