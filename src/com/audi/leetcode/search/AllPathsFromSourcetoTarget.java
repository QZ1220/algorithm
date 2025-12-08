package com.audi.leetcode.search;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/all-paths-from-source-to-target/description/
 * <p>
 * 题目按照邻接矩阵的方式，给了一些节点（数组下标）以及节点之间的链接方式，现在希望求从初始节点到目标节点总共有多少种路径
 * <p>
 * 输入图是有向的
 * Input: graph = [[1,2],[3],[3],[]]
 * 节点0余节点1，2相连
 * 节点1与节点3相连
 * 节点2与节点3相连
 * <p>
 * Output: [[0,1,3],[0,2,3]]
 * <p>
 * <p>
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 * <p>
 * 采用深搜的思路求解，代码模板和回溯的模板基本是一样的
 *
 * @author : wangquanzhou
 * @date : 2025/12/8 18:05
 */
public class AllPathsFromSourcetoTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == graph || graph.length < 1) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        // 需要提前加入起点
        path.add(0);
        dfs(graph, res, path, graph.length - 1, 0);
        return res;
    }


    // 该函数完成功能语义：
//    dfs(..., x) 表示：
//
//            “当前正在访问节点 x，且 x 已经在路径中，现在要从 x 出发继续探索。”
//
//    那么：
//
//    初始调用时，我们“正在访问节点 0”，所以 0 必须已经在 path 中。
//    如果 path 初始为空，就违反了这个语义。
    private void dfs(int[][] graph, List<List<Integer>> res, List<Integer> path, int target, int x) {
        if (x == target) {
            res.add(new ArrayList<>(path));
        }

        for (int neighbor : graph[x]) {
            path.add(neighbor);
            dfs(graph, res, path, target, neighbor);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
//        int[][] graph = {{1, 2}, {3}, {3}, {}};
        int[][] graph = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        System.out.println(graph.length);
        System.out.println(graph[0].length);
        System.out.println(graph[1].length);
        System.out.println(graph[2].length);
        System.out.println(graph[3].length);
        AllPathsFromSourcetoTarget paths = new AllPathsFromSourcetoTarget();
        System.out.println(paths.allPathsSourceTarget(graph));
    }
}
