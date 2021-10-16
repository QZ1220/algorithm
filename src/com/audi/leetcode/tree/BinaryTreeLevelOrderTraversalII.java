package com.audi.leetcode.tree;


import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * <p>
 * 这题和  https://leetcode.com/problems/binary-tree-level-order-traversal/
 * 很像，无非这里是要求结果从最下层往最上层遍历
 * <p>
 * 直接先按照上层往下层遍历的结构构建list，然后逆序即可。
 *
 * @author: WangQuanzhou
 * @date: 2021-10-16 3:01 PM
 */
public class BinaryTreeLevelOrderTraversalII {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = levelOrder(root);
        if (null == list || list.size() < 1) {
            return list;
        }
        List<List<Integer>> res = new LinkedList<>();
        for (List<Integer> item : list) {
            ((LinkedList<List<Integer>>) res).addFirst(item);
        }
        return res;
    }

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
