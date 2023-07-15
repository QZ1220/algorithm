package com.audi.other;

public class TreeDemo {

    /**
     * 将一个递增的数组转换为BST（二叉搜索平衡树）
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (null==nums||nums.length<1){
            return null;
        }
        return buildBST(nums,0,nums.length-1);
    }

    private TreeNode buildBST(int[] nums,int left,int right){
        if (left>right){
            return null;
        }
        int mid = (left+right)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums,left,mid-1);
        root.right =buildBST(nums,mid+1,right);
        return root;
    }

    public static void main(String[] args) {

    }
}
