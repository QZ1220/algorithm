package com.audi.leetcode.tree;


import java.util.ArrayList;

/**
 * https://leetcode.com/problems/path-sum-iii/
 * <p>
 * 求二叉树上，满足连续节点和等于target的所有可能路径数量
 * <p>
 * 和path sum ii类似的思想
 * 遍历路径，从路径尾部计算sum，看看是否满足条件
 *
 * @author: WangQuanzhou
 * @date: 2021-08-07 2:44 PM
 */
public class PathSumIII {

    // 注意这里count不能使用参数的方式进行传递（值传递）
    public static int count = 0;

    public int pathSum(TreeNode root, int targetSum) {

        if (null == root) {
            return 0;
        }
        pathSum(root, targetSum, new ArrayList<Integer>());
        return count;
    }

    private void pathSum(TreeNode root, int targetSum, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        match(targetSum, list);

        pathSum(root.left, targetSum, list);
        pathSum(root.right, targetSum, list);


        list.remove(list.size() - 1);
    }

    /**
     * 求指定的list中是否从末尾开始往前遍历，遍历的连续节点的和为targetSum
     * <p>
     * 因为新添加的元素都在list的尾部，尾插法，因此是从尾部开始计算和
     *
     * @param targetSum
     * @param list
     * @return
     */
    private void match(int targetSum, ArrayList<Integer> list) {
        if (null == list || list.size() == 0) {
            return;
        }
        int size = list.size();
        int sum = 0;
        for (int i = size - 1; i >= 0; i--) {
            if ((sum = sum + list.get(i)) == targetSum) {
                count++;
            }
        }
    }

    public static void main(String[] args) {

        // 从叶子节点向上构建二叉树
//        TreeNode node1 = new TreeNode(3);
//        TreeNode node2 = new TreeNode(-2);
//        TreeNode node3 = new TreeNode(3, node1, node2);
//
//        TreeNode node4 = new TreeNode(1);
//        TreeNode node5 = new TreeNode(2, null, node4);
//        TreeNode node6 = new TreeNode(5, node3, node5);
//
//        TreeNode node7 = new TreeNode(11);
//        TreeNode node8 = new TreeNode(-3, null, node7);
//        TreeNode node9 = new TreeNode(10, node6, node8);

        // [5,4,8,11,null,13,4,7,2,null,null,5,1]
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(2);

        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(4, node1, node2);
        TreeNode node6 = new TreeNode(13);

        TreeNode node7 = new TreeNode(11, node4, node3);
        TreeNode node8 = new TreeNode(8, node6, node5);
        TreeNode node9 = new TreeNode(4, node7, null);

        TreeNode node10 = new TreeNode(5, node9, node8);


        PathSumIII pathSumIII = new PathSumIII();
        System.out.println(pathSumIII.pathSum(node10, 22));

    }
}
