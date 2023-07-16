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

    /**
     * 前序遍历二叉树，并且输出二叉树的节点所在的层数，root节点为第0层
     *
     * @param root
     * @param layer
     */
    public void preOrder(TreeNode root, int layer) {
        if (root == null) {
            return;
        }
        System.out.println("val= " + root.val + "  layer= " + layer);
        preOrder(root.left, layer + 1);
        preOrder(root.right, layer + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        root.left=node1;
        root.right=node2;

        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node3;
        node1.right=node4;

        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node2.left=node5;
        node2.right=node6;

        TreeNode node7 = new TreeNode(7);
        node3.left=node7;

        TreeDemo treeDemo = new TreeDemo();
        treeDemo.preOrder(root,0);
    }
}
