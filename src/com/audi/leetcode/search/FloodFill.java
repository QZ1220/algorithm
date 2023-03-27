package com.audi.leetcode.search;

/**
 * https://leetcode.com/problems/flood-fill/description/?envType=study-plan&id=level-1
 * <p>
 * 给定一个初始矩阵，每个矩阵点都有各自的数值
 * 给定一个起始位置，将与该起始位置数值相等的点「染色」成新的值「color」
 *
 * @author : wangquanzhou
 * @date : 2023/3/27 19:18
 */
public class FloodFill {

    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};

    /**
     * 使用广搜
     *
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originColor = image[sr][sc];
        int row = image.length;
        int column = image[0].length;
        int[][] visit = new int[row][column];
        BFS(image, visit, sr, sc, color, originColor);
        return image;
    }

    private void BFS(int[][] image, int[][] visit, int sr, int sc, int color, int originColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length) {
            return;
        }
        if (visit[sr][sc] != 0) {
            return;
        }
        if (image[sr][sc] == originColor) {
            image[sr][sc] = color;
            visit[sr][sc] = 1;
            for (int i = 0; i < 4; i++) {
                BFS(image, visit, sr + dx[i], sc + dy[i], color, originColor);
            }
        }
    }

    public static void main(String[] args) {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        FloodFill fill = new FloodFill();
        image = fill.floodFill(image, 1, 1, 2);
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                System.out.print(image[i][j] + "  ");
            }
            System.out.println("\n");
        }
    }
}
