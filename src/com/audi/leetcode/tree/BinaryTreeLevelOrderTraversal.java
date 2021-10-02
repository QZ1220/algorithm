package com.audi.leetcode.tree;


import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * <p>
 * 层序遍历二叉树，每一层放在一个数组内
 * <p>
 * 这里相比于普通的二叉树层序遍历，有一点不同，他需要将每一层都单独放在一个list中，因此遍历的过程中需要单独创建新的list
 *
 * @author: WangQuanzhou
 * @date: 2021-10-02 5:43 PM
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if (null == root) {
            return list;
        }

        // 存储遍历过程中的节点
        List<TreeNode> tempList = new LinkedList<>();
        tempList.add(root);

        while (!tempList.isEmpty()) {
            // 存储每一层的遍历结果
            List<Integer> subList = new LinkedList<>();
            for (TreeNode treeNode : tempList) {
                subList.add(treeNode.val);
            }
            // 将每一层的结果存储到最终的返回数据中
            list.add(subList);

            // 深拷贝一份 tempList
            LinkedList<TreeNode> levelNodeList = new LinkedList<>(tempList);
            tempList.clear();
            while (!levelNodeList.isEmpty()) {
                TreeNode node = levelNodeList.poll();
                if (node.left != null) {
                    tempList.add(node.left);
                }
                if (node.right != null) {
                    tempList.add(node.right);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
    }
}
