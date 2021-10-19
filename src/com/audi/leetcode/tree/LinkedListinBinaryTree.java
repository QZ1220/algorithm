package com.audi.leetcode.tree;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/linked-list-in-binary-tree/
 * <p>
 * 题目给出了一颗二叉树，以及一个链表，求这个链表是不是存在于这棵树中
 * 树可以按照任意的顺序进行遍历
 * <p>
 * 思路：
 * 按照三种遍历方式，依次在遍历的过程中判断，list是否在tree中存在，存在就立即返回true
 * 如果三种遍历方式都不存在就返回false，提交时提示会TLE
 *
 * @author: WangQuanzhou
 * @date: 2021-10-19 8:21 AM
 */
public class LinkedListinBinaryTree {

    /**
     * 这样做  会超时
     *
     * @param head
     * @param root
     * @return
     */
    public boolean isSubPath2(ListNode head, TreeNode root) {

        List<Integer> srcList = new ArrayList<>();
        while (head != null) {
            srcList.add(head.val);
        }
        List<Integer> treeList = new ArrayList<>();

        preOrder(treeList, root);
        if (contains(treeList, srcList)) {
            return true;
        }

        treeList = new ArrayList<>();
        midOrder(treeList, root);
        if (contains(treeList, srcList)) {
            return true;
        }

        treeList = new ArrayList<>();
        postOrder(treeList, root);
        if (contains(treeList, srcList)) {
            return true;
        }

        return false;
    }

    /**
     * 参考 https://blog.csdn.net/edisonzhi/article/details/120188058
     *
     * @param head
     * @param root
     * @return
     */
    public boolean isSubPath(ListNode head, TreeNode root) {

        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        // 从当前节点开始，判断后续位置是否满足题意
        if (head.val == root.val && judge(root, head)) {
            return true;
        }
        // 从当前节点的左右节点尝试，是否满足题意
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    /**
     * 判断从当前节点开始，后续树的节点是否包含list的节点
     *
     * @param root
     * @param head
     * @return
     */
    public boolean judge(TreeNode root, ListNode head) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (head.val != root.val) {
            return false;
        }
        return judge(root.left, head.next) || judge(root.right, head.next);
    }


    private void preOrder(List<Integer> treeList, TreeNode node) {
        if (null == node) {
            return;
        }
        treeList.add(node.val);
        preOrder(treeList, node.left);
        preOrder(treeList, node.right);
    }

    private void midOrder(List<Integer> treeList, TreeNode node) {
        if (null == node) {
            return;
        }
        preOrder(treeList, node.left);
        treeList.add(node.val);
        preOrder(treeList, node.right);
    }

    private void postOrder(List<Integer> treeList, TreeNode node) {
        if (null == node) {
            return;
        }
        preOrder(treeList, node.left);
        preOrder(treeList, node.right);
        treeList.add(node.val);
    }


    /**
     * 判断list1中是否包含list2，List自带的containsAll判断包含的元素可以是不连续的
     * 但是这里要求必须是连续的
     *
     * @param list1
     * @param list2
     * @return
     */
    private boolean contains(List<Integer> list1, List<Integer> list2) {
        if (null == list1 || list1.size() < 1) {
            return false;
        }
        if (list1.size() < list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(0)) {
                continue;
            }
            int tempPos = 1;
            for (int j = i + 1; j < list1.size() && tempPos < list2.size(); j++, tempPos++) {
                if (list1.get(j) != list2.get(tempPos)) {
                    break;
                }
            }
            if (tempPos == list2.size()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4);
        LinkedListinBinaryTree tree = new LinkedListinBinaryTree();
        System.out.println(tree.contains(list1, list2));
        System.out.println();
    }
}
