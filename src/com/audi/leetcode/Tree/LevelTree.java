package com.audi.leetcode.Tree;


import java.util.LinkedList;
import java.util.List;

/**
 * 层序遍历一个二叉树
 *
 * @author: WangQuanzhou
 * @date: 2021-08-04 7:01 AM
 */
public class LevelTree {

    public void levelTraverse(TreeNode root) {
        if (null == root) {
            return;
        }
        List<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode node = ((LinkedList<TreeNode>) list).poll();
            System.out.println(node.val);
            if (null != node.left) {
                list.add(node.left);
            }
            if (null != node.right) {
                list.add(node.right);
            }
        }
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
