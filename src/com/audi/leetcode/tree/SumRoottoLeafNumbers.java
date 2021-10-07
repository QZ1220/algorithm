package com.audi.leetcode.tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * <p>
 * 树的根节点到叶节点的路径组成一个数字，求所有数字的和
 * <p>
 * 典型的递归回溯来求解
 *
 * @author: WangQuanzhou
 * @date: 2021-10-07 4:34 PM
 */
public class SumRoottoLeafNumbers {

    public int sumNumbers(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        traverse(root, list, new LinkedList<>());
        int sum = 0;
        for (List<Integer> subList : list) {
            int tempSum = 0;
            for (Integer i : subList) {
                tempSum = tempSum * 10 + i;
            }
            sum = sum + tempSum;
        }
        return sum;
    }

    public void traverse(TreeNode node, List<List<Integer>> list, List<Integer> subList) {
        if (null == node) {
            return;
        }
        subList.add(node.val);
        if (node.left == null && node.right == null) {
            list.add(new LinkedList<>(subList));
            subList.remove(subList.size() - 1);
            return;
        }

        traverse(node.left, list, subList);
        traverse(node.right, list, subList);
        subList.remove(subList.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode left5 = new TreeNode(5);
        TreeNode left7 = new TreeNode(7);
        TreeNode right1 = new TreeNode(1);
        TreeNode left9 = new TreeNode(9, left5, right1);
        TreeNode right0 = new TreeNode(0, left7, null);
        TreeNode root4 = new TreeNode(4, left9, right0);
        SumRoottoLeafNumbers numbers = new SumRoottoLeafNumbers();
        System.out.println(numbers.sumNumbers(root4));
    }
}
