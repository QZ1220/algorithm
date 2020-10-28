package com.audi.leetcode.search;


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

    /**
     * 这种解法，仅限于寻找同行、列最近的0
     *
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length < 2) {
            return matrix;
        }
        // 矩阵大小
        int height = matrix.length;
        int width = matrix[0].length;
        // int初始化默认为0
        int[][] distance = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }

                int left = j - 1;
                int right = j + 1;
                int up = i - 1;
                int down = i + 1;
                int zeroDis = height > width ? height - 1 : width - 1;

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

                if (right < width) {
                    int rightZeroDis = 1;
                    while (right < width) {
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


                if (down < height) {
                    int downZeroDis = 1;
                    while (down < height) {
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

    /**
     * 看来只能进行BFS了
     * <p>
     * 虽然题目要求只能上下左右走，但是结合实际，最近的0有可能是在对角
     *
     * @param matrix
     * @return
     */
    public int[][] updateMatrix2(int[][] matrix) {
        return null;
    }

    public static void main(String[] args) {
//        int[][] matrix = {
//                {
//                        1, 1, 0
//                },
//                {
//                        0, 1, 0
//                },
//                {
//                        1, 1, 1
//                }
//        };

        // 直到遇到这个测试用例之前，我都认为我的思路没错。
        // 我原本以为最近的0只能在自己这一行或者这一列找，是我太年轻，最近的0是环形辐射最近的0.。。
        // 看来这题标成「中等」难度，确实还是有点道理
        int[][] matrix = {
                {0, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 0, 0, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {0, 0, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 1, 0, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 0}};

        Matrix01 matrix01 = new Matrix01();
        int[][] dis = matrix01.updateMatrix(matrix);
        int length = dis.length;
        int width = dis[0].length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(dis[i][j] + " ");
            }
            System.out.println();
        }
    }
}
