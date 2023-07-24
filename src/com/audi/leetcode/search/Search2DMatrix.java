package com.audi.leetcode.search;


/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 * <p>
 * 给定一个二维矩阵，每一行从左到右递增，每一列从上到下递增
 *
 * 求在这个矩阵中，给定的target元素是否存在
 *
 * 此题可以从矩阵的左下角开始搜索，因为左下角是当前行最小的，当前列最大的
 *
 * @author: WangQuanzhou
 * @date: 2021-08-13 8:06 AM
 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (null == matrix || matrix.length < 1) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (binarySearch(matrix[i], target)) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[] array, int target) {
        if (null == array || array.length < 1) {
            return false;
        }

        int low = 0;
        int high = array.length - 1;

        // 注意这里应该是<=，因为可以想象当array只有一个元素的时候，如果是<，那么下面的while根本不会走
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] > target) {
                high = mid - 1;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
