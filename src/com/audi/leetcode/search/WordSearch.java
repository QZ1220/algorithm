package com.audi.leetcode.search;

/**
 * https://leetcode.com/problems/word-search/
 * <p>
 * 给一个字母组成的二维矩阵，判断指定的字符串是否存在于矩阵中，矩阵中的字母不能重复使用
 * <p>
 * Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'ABCCED'
 * Output: true
 * <p>
 * Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'SEE'
 * Output: true
 * <p>
 * Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'ABCB'
 * Output: false
 *
 * @author : wangquanzhou
 * @date : 2023/4/12 08:03
 */
public class WordSearch {

    public boolean exist(char[][] matrix, String word) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        if (rows * columns < word.length()) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boolean[][] visited = new boolean[rows][columns];
                if (dfs(matrix, visited, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] matrix, boolean[][] visited, int x, int y, int pos, String word) {
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || pos >= word.length() ||
                matrix[x][y] != word.charAt(pos) || visited[x][y]) {
            return false;
        }

        if (pos == word.length() - 1) {
            return true;
        }
        visited[x][y] = true;
        if (dfs(matrix, visited, x - 1, y, pos + 1, word) || dfs(matrix, visited, x + 1, y, pos + 1, word) ||
                dfs(matrix, visited, x, y + 1, pos + 1, word) || dfs(matrix, visited, x, y - 1, pos + 1, word)) {
            return true;
        }
        // 回溯
        visited[x][y] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] chars = {{'A', 'B', 'F'}, {'P', 'C', 'D'}};
        char[][] chars1 = {
                {'A', 'A', 'B', 'A', 'A', 'B'},
                {'A', 'A', 'B', 'B', 'B', 'A'},
                {'A', 'A', 'A', 'A', 'B', 'A'},
                {'B', 'A', 'B', 'B', 'A', 'B'},
                {'A', 'B', 'B', 'A', 'B', 'A'},
                {'B', 'A', 'A', 'A', 'A', 'B'}};
        WordSearch search = new WordSearch();
//        System.out.println(search.exist(chars, "ABCD"));
        System.out.println(search.exist(chars1, "BBBAABBBBBAB"));
    }
}
