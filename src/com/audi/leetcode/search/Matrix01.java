package com.audi.leetcode.search;


import com.audi.heap.MaxHeap;

/**
 * https://leetcode.com/problems/01-matrix/
 * <p>
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two adjacent cells is 1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [[0,0,0],
 * [0,1,0],
 * [0,0,0]]
 * <p>
 * Output:
 * [[0,0,0],
 * [0,1,0],
 * [0,0,0]]
 * Example 2:
 * <p>
 * Input:
 * [[0,0,0],
 * [0,1,0],
 * [1,1,1]]
 * <p>
 * Output:
 * [[0,0,0],
 * [0,1,0],
 * [1,2,1]]
 *
 * @author: WangQuanzhou
 * @date: 2020/10/19 21:57
 */
public class Matrix01 {

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length < 2) {
            return matrix;
        }
        // 矩阵大小
        int N = matrix.length;
        // int初始化默认为0
        int[][] distance = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }

                int left = j - 1;
                int right = j + 1;
                int up = i - 1;
                int down = i + 1;
                int zeroDis = N - 1;

                if (left >= 0) {
                    int leftZeroDis = 1;
                    while (left >= 0) {
                        if (matrix[i][left] == 0) {
                            break;
                        }
                        leftZeroDis++;
                        left--;
                    }
                    zeroDis = zeroDis > leftZeroDis ? leftZeroDis : zeroDis;
                }

                if (right < N) {
                    int rightZeroDis = 1;
                    while (right < N) {
                        if (matrix[i][right] == 0) {
                            break;
                        }
                        rightZeroDis++;
                        right++;
                    }
                    zeroDis = zeroDis > rightZeroDis ? rightZeroDis : zeroDis;
                }

                if (up >= 0) {
                    int upZeroDis = 1;
                    while (up >= 0) {
                        if (matrix[up][j] == 0) {
                            break;
                        }
                        upZeroDis++;
                        up--;
                    }
                    zeroDis = zeroDis > upZeroDis ? upZeroDis : zeroDis;
                }


                if (down < N) {
                    int downZeroDis = 1;
                    while (down < N) {
                        if (matrix[down][j] == 0) {
                            break;
                        }
                        downZeroDis++;
                        down++;
                    }
                    zeroDis = zeroDis > downZeroDis ? downZeroDis : zeroDis;
                }

                distance[i][j] = zeroDis;
            }
        }
        return distance;

    }

    public static void main(String[] args) {
        int[][] matrix = {
                {
                        1, 1, 0
                },
                {
                        0, 1, 0
                },
                {
                        1, 1, 1
                }
        };

        Matrix01 matrix01 = new Matrix01();
        int[][] dis = matrix01.updateMatrix(matrix);
        int length = dis.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(dis[i][j] + " ");
            }
            System.out.println();
        }
    }
}
