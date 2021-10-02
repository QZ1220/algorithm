package com.audi.leetcode.tree;


import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 * <p>
 * 完成二叉树的前序遍历
 *
 * @author: WangQuanzhou
 * @date: 2021-10-02 5:17 PM
 */
public class BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (null == root) {
            return list;
        }

        preOrder(list, root);
        return list;
    }

    private void preOrder(List<Integer> list, TreeNode root) {
        if (null == root) {
            return;
        }
        list.add(root.val);
        preOrder(list, root.left);
        preOrder(list, root.right);
    }

    public static void main(String[] args) {
    }
}
