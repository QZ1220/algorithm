package com.audi.leetcode.array;


import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/set-matrix-zeroes/
 * <p>
 * 题目给定一个整型矩阵，如果矩阵的某个位置元素等于0，那么就将当前行、当前列所有元素都设置为0
 * <p>
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 *
 * @author: WangQuanzhou
 * @date: 2021/11/7 16:44
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        // 首先循环遍历二维数组，记录0元素出现的位置
        // 题设限定了输入不可能为空
        int m = matrix.length;
        int n = matrix[0].length;
        Set<Integer> zeroRow = new HashSet<>();
        Set<Integer> zeroColumn = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // 如果某个位置=0，使用set记录0元素出现的行列信息
                    zeroRow.add(i);
                    zeroColumn.add(j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (zeroRow.contains(i) || zeroColumn.contains(j)) {
                    // 设置整行、整列为0
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        SetMatrixZeroes setMatrixZeroes = new SetMatrixZeroes();
        setMatrixZeroes.setZeroes(matrix);
    }
}
