package com.audi.other;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    /**
     * return true if any paths match sum up all node val from root to leaf equals to targetSum
     *
     * @param root
     * @param targetSum
     * @return
     */
    boolean hasPath = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        pathSum(root, targetSum);
        return hasPath;
    }

    private void pathSum(TreeNode root, int tmp) {
        if (root == null) {
            return;
        }
        if (tmp - root.val == 0 && root.left == null && root.right == null) {
            hasPath = true;
            return;
        }
        pathSum(root.left, tmp - root.val);
        pathSum(root.right, tmp - root.val);

        tmp += root.val;
    }

    /**
     * return all the root-to-leaf paths, which sum up equals to targetSum
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSumII(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>();
        pathSum(root, targetSum, res, new LinkedList<>());
        return res;
    }

    private void pathSum(TreeNode root, int tmp, List<List<Integer>> res, List<Integer> item) {
        if (root == null) {
            return;
        }
        item.add(root.val);
        if (tmp - root.val == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<>(item));
        }
        pathSum(root.left, tmp - root.val, res, item);
        pathSum(root.right, tmp - root.val, res, item);

        // 注意这里需要考虑将减去的值加回来
        // 这种情况，可以考虑假设root是叶子节点的情况，就比较好思考
        tmp = tmp + root.val;
        item.remove(item.size() - 1);
    }

    /**
     * 求给定两个节点的最近公共祖先
     * <p>
     * 算法思路：
     * 先遍历二叉树，搜索某个指定的节点p，并且需要记录遍历搜索的路径，记为listP
     * 对于节点q，一样的有listQ
     * 最近公共祖先问题就转换为，求listP、listQ的最后一个相等节点的val
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    boolean find = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> listP = new LinkedList<>();
        preOrderSearch(root, listP, p);

        find = false;
        List<TreeNode> listQ = new LinkedList<>();
        preOrderSearch(root, listQ, q);

        TreeNode LCA = listP.get(0);
        for (int i = 0; i < listP.size() && i < listQ.size(); i++) {
            if (listP.get(i) != listQ.get(i)) {
                break;
            }
            LCA = listP.get(i);
        }
        return LCA;
    }

    private void preOrderSearch(TreeNode root, List<TreeNode> path, TreeNode node) {
        if (null == root || find) {
            return;
        }
        path.add(root);
        if (node == root) {
            find = true;
            return;
        }

        preOrderSearch(root.left, path, node);
        preOrderSearch(root.right, path, node);
        if (find) {
            return;
        }

        path.remove(path.size() - 1);
    }

    /**
     * 将一个二叉树拉直
     * <p>
     * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        TreeNode last = null;
        preOrder(root, last);
    }

    private void preOrder(TreeNode node, TreeNode last) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            last = node;
            return;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;
        TreeNode leftLast = null;
        TreeNode rightLast = null;
        if (null != left) {
            preOrder(left, leftLast);
            node.left = null;
            node.right = left;
            last = leftLast;
        }
        if (null != right) {
            preOrder(right, rightLast);
            if (null != leftLast) {
                leftLast = right;
            }
            last = rightLast;
        }
    }

    /**
     * https://leetcode.com/problems/binary-tree-right-side-view/
     * <p>
     * 使用层序遍历的思想，求解每一层最右边的那个节点，组成的集合记为解
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (null == root) {
            return new LinkedList<>();
        }
        Queue<Pair<Integer, TreeNode>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, root));

        List<Integer> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            Pair<Integer, TreeNode> pair = queue.poll();
            // 说明马上就会遍历到下一层，集当前pair就是当前层的最后一个节点
            if (queue.isEmpty() || queue.peek().getKey() > pair.getKey()) {
                res.add(pair.getValue().val);
            }

            if (null != pair.getValue().left) {
                queue.add(new Pair<>(pair.getKey() + 1, pair.getValue().left));
            }
            if (null != pair.getValue().right) {
                queue.add(new Pair<>(pair.getKey() + 1, pair.getValue().right));
            }
        }
        return res;
    }

    /**
     * 判断一颗二叉树是否是对称的，镜像对称
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == root.right) {
            return true;
        }

        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (null == left && null == right) {
            return true;
        }
        if (null == left || null == right) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public int rob(TreeNode root) {
        int[] values = getValues(root);
        return Math.max(values[0], values[1]);
    }

    /**
     * 自底向上寻找的思路
     * <p>
     * 参考视频：
     * https://www.bilibili.com/video/BV15T411h7Dn/?spm_id_from=333.337.search-card.all.click&vd_source=d1530fb814268f770330143e24aaf1e6
     *
     * @param root
     * @return
     */
    private int[] getValues(TreeNode root) {
        if (root == null) {
            return new int[2];
        }

        int[] leftValues = getValues(root.left);
        int[] rightValues = getValues(root.right);
        // values[0] 表示当前root节点要偷时，可以获得的最大值
        // values[1] 表示当前root节点不偷时，可以获得的最大值
        int[] values = new int[2];
        values[0] = root.val + leftValues[1] + rightValues[1];
        values[1] = Math.max(leftValues[0], leftValues[1]) + Math.max(rightValues[0], rightValues[1]);
        return values;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node3;
        node1.right = node4;

        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node2.left = node5;
        node2.right = node6;

        TreeNode node7 = new TreeNode(7);
        node3.left = node7;

        TreeDemo treeDemo = new TreeDemo();
//        treeDemo.preOrder(root, 0);

        TreeNode node = treeDemo.lowestCommonAncestor(root, node5, node6);
        System.out.println(node.val);

    }
}
