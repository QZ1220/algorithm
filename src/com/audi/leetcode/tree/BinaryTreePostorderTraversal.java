package com.audi.leetcode.tree;


import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 * <p>
 * 后续打印二叉树
 *
 * @author: WangQuanzhou
 * @date: 2021-10-02 5:29 PM
 */
public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (null == root) {
            return list;
        }

        postOrder(list, root);
        return list;
    }

    private void postOrder(List<Integer> list, TreeNode root) {
        if (null == root) {
            return;
        }
        postOrder(list, root.left);
        postOrder(list, root.right);
        list.add(root.val);
    }

    public static void main(String[] args) {
    }
}
