package com.audi.leetcode.tree;


import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/even-odd-tree/
 * <p>
 * 判断一棵树是否是满足下列条件：
 * 1、树的偶数层为奇数
 * 2、树的奇数层为偶数
 * 3、偶数层单调递增
 * 4、奇数层单调递减
 * <p>
 * 思路：
 * 层序遍历，依次判断
 *
 * @author: WangQuanzhou
 * @date: 2021-08-03 7:12 PM
 */
public class EvenOddTree {

    public boolean isEvenOddTree(TreeNode root) {

        if ((root.val & 1) == 0) {
            return false;
        }

        List<Node> list = new LinkedList<>();
        Node preNode = new Node(root.val, root.left, root.right, 0);
        list.add(preNode);
        while (!list.isEmpty()) {
            Node node = ((LinkedList<Node>) list).poll();
            // 奇数层
            if ((((node.level & 1) == 1) && (node.val & 1) == 1)) {
                return false;
            }
            if ((node.level & 1) == 1) {
                if (node.level == preNode.level && node.val >= preNode.val) {
                    return false;
                }
            }
            // 偶数层
            if ((((node.level & 1) == 0) && (node.val & 1) == 0)) {
                return false;
            }
            if ((node.level & 1) == 0) {
                if (node.level == preNode.level && node != preNode && node.val <= preNode.val) {
                    return false;
                }
            }

            // 添加左右节点
            if (null != node.left) {
                list.add(new Node(node.left.val, node.left.left, node.left.right, node.level + 1));
            }
            if (null != node.right) {
                list.add(new Node(node.right.val, node.right.left, node.right.right, node.level + 1));
            }
            preNode = node;
        }
        return true;
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

    public class Node extends TreeNode {
        int level;

        Node() {
        }

        Node(int val, TreeNode left, TreeNode right, int level) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.level = level;
        }
    }
}
