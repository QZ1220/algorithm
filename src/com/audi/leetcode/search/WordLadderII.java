package com.audi.leetcode.search;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder-ii/
 * <p>
 * 这道题是 https://leetcode.com/problems/word-ladder/ 的升级版
 * <p>
 * 需要求出所有的最短路径（不能重复）
 * <p>
 * 本题需要注意beginWord初始时在不在wordList中的情况，否则求出的结果会有重复的情况出现
 * <p>
 * 还是使用广度优先搜索的思想来解决这个问题
 *
 * @author WangQuanzhou
 * @date 2020-08-15
 */
public class WordLadderII {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() < 1) {
            return new LinkedList<>();
        }

        Boolean containsBeginWord = Boolean.FALSE;
        Boolean containsEndWord = Boolean.FALSE;

        for (String word : wordList) {
            if (word.equals(endWord)) {
                containsEndWord = Boolean.TRUE;
            }
            if (word.equals(beginWord)) {
                containsBeginWord = Boolean.TRUE;
            }
        }

        // 如果wordList不包含endWord，直接return
        if (!containsEndWord) {
            return new LinkedList<>();
        }
        // 如果wordList不包含beginWord，直接将其加入到wordList方便后续构建graph
        if (!containsBeginWord) {
            wordList.add(beginWord);
        }

        // 构建图
        Map<String, List<String>> graph = constructGraph(wordList);

        return null;
    }

    /**
     * 对构建的图进行广度优先搜索
     *
     * @param beginWord  搜索起始单词
     * @param endWord    搜索结束单词
     * @param graph      图
     * @param queue      队列
     * @param endPosList 记录终点位置元素的下标（可能有多条路径搜索到endWord，因此这里是个List）
     */
    private void BFS_graph(String beginWord, String endWord, Map<String, List<String>> graph, List<Item> queue,
                           List<Integer> endPosList) {
        // 构造一个map  记录哪些单词搜索过，并且记录搜索到该单词花费的步数
        Map<String, Integer> visitMap = new LinkedHashMap<>();
        // 搜索结束需要的最小步数
        int minStep = 0;
        // beginWord入队
        queue.add(new Item(beginWord, -1, 1));
        // 标记beginWord已经访问过
        visitMap.put(beginWord, 1);
        // 标记前驱节点的位置
        int front = 0;
        // 这里的循环  queue中的元素不会进行出队操作
        // 如果front等于queue.size 说明元素都已经被搜索过
        while (front != queue.size()) {
            // 获取队列指定位置元素
            Item item = queue.get(front);
            String word = item.word;
            Integer step = item.step;

            // 此时表示所有到终点的路径都已完成搜索
            if (minStep != 0 && step > minStep) {
                break;
            }

            if (word.equals(endWord)) {
                minStep = step;
                endPosList.add(front);
            }

            // 遍历当前单词的邻接节点
            List<String> neighbors = graph.get(word);
            for (int i = 0; i < neighbors.size(); i++) {
                // 节点没被访问或者另外一条最短路径
//                if (visitMap.get(neighbors.get(i)) == step + 1 ||(visitMap.get(neighbors.get(i))==visitMap)) {

            }
        }
//        }

    }


    /**
     * 判断两个单词是否直接相连
     *
     * @param word1
     * @param word2
     * @return
     */
    private Boolean connect(String word1, String word2) {
        // 题目给出了 预设  两个单词的长度是一样的，且没有重复的单词
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count += 1;
                if (count > 1) {
                    return Boolean.FALSE;
                }
            }
        }
        return count == 1;
    }

    /**
     * 构造图
     *
     * @param wordList
     * @return
     */
    private Map<String, List<String>> constructGraph(List<String> wordList) {
        Map<String, List<String>> graph = new HashMap<>();

        wordList.forEach(word -> graph.put(word, new ArrayList<>()));

        for (int i = 0; i < wordList.size(); i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (connect(wordList.get(i), wordList.get(j))) {
                    graph.get(wordList.get(i)).add(wordList.get(j));
                    graph.get(wordList.get(j)).add(wordList.get(i));
                }
            }
        }
        return graph;
    }

    // 保存当前单词的信息，包括前继节点 以及走到该节点耗费的步数
    class Item {
        // 当前单词
        String word;

        // 通过哪个单词 访问到的当前单词
        Integer front;

        // 走到当前节点花费的步数
        Integer step;

        /**
         * 构造函数
         *
         * @param word
         * @param front
         * @param step
         */
        Item(String word, Integer front, Integer step) {
            this.word = word;
            this.front = front;
            this.step = step;
        }
    }


    public static void main(String[] args) {

    }
}
