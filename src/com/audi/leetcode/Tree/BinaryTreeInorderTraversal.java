package com.audi.leetcode.Tree;


import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * <p>
 * 中序遍历二叉树
 *
 * @author: WangQuanzhou
 * @date: 2021-08-05 6:58 AM
 */
public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> list = new LinkedList<>();
        return inorderTraversal(root, list);
    }

    private List<Integer> inorderTraversal(TreeNode root, List<Integer> list) {
        if (null == root) {
            return list;
        }

        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
        return list;
    }


    public static void main(String[] args) {
    }
}
