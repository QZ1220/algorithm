package com.audi.leetcode.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-ii/
 * <p>
 * 求二叉树中满足根节点到叶子节点和等于target的所有可能路径
 * <p>
 * 是pathSum的升级版
 * <p>
 * 利用一个一维数组记录满足条件的路径
 * <p>
 * 满足条件的路径最终需要添加到二维数组中
 *
 * @author: WangQuanzhou
 * @date: 2021-08-06 9:40 AM
 */
public class PathSumII {

    private static List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return result;
        }

        pathSum(root, 0, targetSum, new ArrayList<Integer>());
        return result;
    }

    private void pathSum(TreeNode root, int sum, int targetSum, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        sum = sum + root.val;
        if (sum == targetSum && root.left == null && root.right == null) {
            result.add(new ArrayList<Integer>(list));
            list.remove(list.size() - 1);
            return;
        }

        pathSum(root.left, sum, targetSum, list);
        pathSum(root.right, sum, targetSum, list);

        sum = sum - root.val;
        // 弹出上次遍历的数据，队列尾部的数据
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
    }
}
