package com.audi.leetcode.tree.bst;


import com.audi.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 * <p>
 * A valid BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * 要求实现一个函数 判断给定的树是不是二叉排序树
 * <p>
 * 一开始的想法是在遍历的过程中，判断当前节点与其父节点的值的大小从而判断是不是BST，
 * 但是忽略了一点当前节点的值还要与路径上的所有父亲节点进行比较，这样子就有点麻烦
 * <p>
 * 换一种思路，先对BST进行中序遍历，一个合格的BST，中序遍历完，是一个排序的升序数组，时间复杂度O(N)+O(logN)，也就是O(N)
 *
 * @author: WangQuanzhou
 * @date: 2021-10-03 8:14 AM
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if ((root.left != null && root.left.val >= root.val) ||
                (root.right != null && root.right.val <= root.val)) {
            return false;
        }
        return isValidBST2(root.left) && isValidBST2(root.right) ? true : false;
//        return preOrder(root);
    }

    public boolean isValidBST(TreeNode root) {
        // 注意：这里使用ArrayList，而不是LinkedList，否则后续的遍历判断时，时间复杂度达不到O(N)
        List<Integer> list = new ArrayList<>();
        midOrder(root, list);

        if (list.size() < 2) {
            return true;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private void midOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        midOrder(root.left, list);
        list.add(root.val);
        midOrder(root.right, list);
    }

    private boolean preOrder(TreeNode root) {
        if (!valid(root) || !valid(root.left) || !valid(root.right)) {
            return false;
        }
        return true;
    }

    private boolean valid(TreeNode root) {
        if (root == null) {
            return true;
        }
        if ((root.left != null && root.left.val >= root.val) ||
                (root.right != null && root.right.val <= root.val)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(2, left, right);
        ValidateBinarySearchTree searchTree = new ValidateBinarySearchTree();
        boolean validBST = searchTree.isValidBST2(root);
        System.out.println("validBST = " + validBST);

    }
}
