package com.audi.leetcode.sort;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * <p>
 * 给定一个二维矩阵，每一行都是递增的，每一列都是递增的，编写一个尽可能高效的算法来求解target是否存在于矩阵之中
 * <p>
 * Input: matrix =
 * [[1,4,7,11,15],
 * [2,5,8,12,19],
 * [3,6,9,16,22],
 * [10,13,14,17,24],
 * [18,21,23,26,30]],
 * target = 5
 * Output: true
 *
 * @author : wangquanzhou
 * @date : 2023/4/10 22:36
 */
public class Search2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
//        int height = matrix.length;
//        int width = matrix[0].length;
//
//        return findT(matrix, target, 0, 0, height, width);
        // 对于二维矩阵的每一行进行二分搜索，可以全部AC
        for (int[] arr : matrix) {
            if (binarySearch(arr,target)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 以下DFS没有全部AC，
     * 124 / 130 testcases passed
     *
     * @param matrix
     * @param target
     * @param x
     * @param y
     * @param height
     * @param width
     * @return
     */
    private boolean findT(int[][] matrix, int target, int x, int y, int height, int width) {
        if (matrix[x][y] == target) {
            return Boolean.TRUE;
        }
        if ((x + 1) < height) {
            if (findT(matrix, target, x + 1, y, height, width)) {
                return Boolean.TRUE;
            }
        }
        if ((y + 1) < width) {
            if (findT(matrix, target, x, y + 1, height, width)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 针对数组的二分排序
     *
     * @param arr
     * @param target
     * @return
     */
    private boolean binarySearch(int[] arr, int target) {
        int len = arr.length;
        if (target < arr[0] || target > arr[len - 1]) {
            return Boolean.FALSE;
        }

        int left = 0;
        int right = len - 1;
        while (left <= right && left < len && right < len && left >= 0 && right >= 0) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return Boolean.TRUE;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return Boolean.FALSE;
    }

    public static void main(String[] args) {
        Search2DMatrixII matrixII = new Search2DMatrixII();
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(matrixII.binarySearch(arr, 1));
        System.out.println(matrixII.binarySearch(arr, 2));
        System.out.println(matrixII.binarySearch(arr, 3));
        System.out.println(matrixII.binarySearch(arr, 4));
        System.out.println(matrixII.binarySearch(arr, 5));
    }

}
