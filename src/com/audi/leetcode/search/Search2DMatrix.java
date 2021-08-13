package com.audi.leetcode.search;


/**
 * https://leetcode.com/problems/search-a-2d-matrix/
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
