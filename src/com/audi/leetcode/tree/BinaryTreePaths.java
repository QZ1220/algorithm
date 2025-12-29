package com.audi.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-paths/
 * <p>
 * 求二叉树的所有到叶子节点的路径
 *
 * @author : wangquanzhou
 * @date : 2023/3/17 11:36
 */
public class BinaryTreePaths {

    private static final String SEPARATOR = "->";

    /**
     * 使用递归求解
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<>();
        travel(root, "", paths);
        return paths;
    }

    private void travel(TreeNode root, String path, List<String> paths) {
        if (root == null) {
            return;
        }
        // 收集结果，收集后记得return，否则会NPE
        if (root.left == null && root.right == null) {
            path = path + root.val;
            paths.add(path);
            return;
        }
        path = path + root.val + SEPARATOR;
        travel(root.left, path, paths);
        travel(root.right, path, paths);
    }


    public static void main(String[] args) {

    }


}
