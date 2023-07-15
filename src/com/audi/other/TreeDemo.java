package com.audi.other;

public class TreeDemo {

    /**
     * 将一个递增的数组转换为BST（二叉搜索平衡树）
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (null == nums || nums.length < 1) {
            return null;
        }
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);
        return root;
    }

    /**
     * 给定一个树的根节点，求与给定的target最接近的节点值，节点的值可以就等于target
     *
     * @param root
     * @param target
     * @return
     */
    下面的代码可以解决上面的问题吗
    public static int closeVal = 0;
    public static double diff = Double.MAX_VALUE;

    public int closestValue(TreeNode root, double target) {
        closeVal = root.val;
        closestValueInTree(root, target);
        return closeVal;
    }

    private void closestValueInTree(TreeNode root, double target) {
        if (root == null) {
            return;
        }
        if (Math.abs(target - root.val) < diff) {
            closeVal = root.val;
            diff = Math.abs(target - root.val);
        }
        if (target < root.val) {
            closestValueInTree(root.left, target);
        } else {
            closestValueInTree(root.right, target);
        }
    }

    public static void main(String[] args) {

    }
}
