package com.audi.leetcode.tree;

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 * <p>
 * 判断一颗二叉树是否是平衡二叉树
 * <p>
 * 对于二叉树的任意节点，左右子树的高度差不能超过1
 *
 * @author : wangquanzhou
 * @date : 2023/7/18 07:42
 */
public class BalancedBinaryTree {


    // 解法一  时间复杂度nLogn
//    public boolean isBalanced(TreeNode root) {
//        if (null==root){
//            return true;
//        }
//        if (root.left==null&&root.right==null){
//            return true;
//        }
//        int leftHeight = getHeight(root.left);
//        int rightHeight = getHeight(root.right);
//        if (Math.abs(leftHeight-rightHeight)>1){
//            return false;
//        }
//        return isBalanced(root.left)&&isBalanced(root.right);
//
//    }
//
//    private int getHeight(TreeNode root){
//        if (root==null){
//            return 0;
//        }
//        return Math.max(getHeight(root.left),getHeight(root.right))+1;
//    }


    // 解法二  时间复杂度n
    public boolean isBalanced(TreeNode root) {
        if (null == root) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (getHeight(root) == -1) {
            return false;
        }
        return true;

    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (leftHeight==-1||rightHeight==-1){
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
