package com.audi.leetcode.graph;


import com.audi.leetcode.graph.bo.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/clone-graph/
 * <p>
 * 返回一个无向图的深拷贝
 * <p>
 * DFS遍历图
 * https://leetcode.com/problems/clone-graph/discuss/1543461/Easy-Java-DFS-Solution
 *
 * 时间复杂度  O(N)??
 *
 * @author: WangQuanzhou
 * @date: 2021/11/7 14:53
 */
public class CloneGraph {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        // 存储访问过程中的节点
        Stack<Node> visitStack = new Stack<>();
        // 使用map存储深拷贝后的图
        Map<Node, Node> map = new HashMap<>();
        // 添加初始节点
        map.put(node, new Node(node.val));
        visitStack.push(node);
        while (!visitStack.isEmpty()) {
            Node tempNode = visitStack.pop();
            for (Node neighbor : tempNode.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val));
                    visitStack.push(neighbor);
                }
                map.get(tempNode).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }

    public static void main(String[] args) {

    }
}
