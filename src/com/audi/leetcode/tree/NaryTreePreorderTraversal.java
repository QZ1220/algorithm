package com.audi.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个N叉树，求这个树的前序遍历
 * <p>
 * 递归解决
 *
 * @author : wangquanzhou
 * @date : 2023/3/25 21:05
 */
public class NaryTreePreorderTraversal {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new LinkedList<>();
        dfs(root, list);
        return list;
    }

    private void dfs(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        for (Node node : root.children) {
            dfs(node, list);
        }
    }

    public static void main(String[] args) {

    }
}


// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};