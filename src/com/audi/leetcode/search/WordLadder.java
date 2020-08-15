package com.audi.leetcode.search;

import javafx.util.Pair;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/
 * <p>
 * 题目希望借助一些中间单词，将一个单词转换为另外一个单词，两两转换之间只能变动一个字符
 * <p>
 * 如果借助中间单词转换不了，就返回0
 * <p>
 * 这道题可以使用图的广度优先搜索  自己构造一张图（无向图）  使用map构造临接表表示图
 * <p>
 * map的key为word，value为与该word直接相连（字母相差一个）的所有word
 *
 * @author WangQuanzhou
 * @date 2020-08-09
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return 0;
        }
        // 将beginWord加入到wordList中 以便构建图
        wordList.add(beginWord);

        // 构造图
        Map<String, List<String>> graph = constructGraph(wordList);

        // 建立广搜的队列
        Queue<Pair<String, Integer>> queue = new LinkedList<>();

        // 将起始节点入队
        queue.offer(new Pair<>(beginWord, 1));

        // 记录走过的节点  避免重复搜索
        Set<String> visitSet = new HashSet<>();

        while (!queue.isEmpty()) {

            Pair<String, Integer> pair = queue.poll();

            if (pair.getKey().equals(endWord)) {
                // 找到了可以转换的路径
                return pair.getValue();
            }
            // 已经遍历过该节点 直接略过
            if (visitSet.contains(pair.getKey())) {
                continue;
            }

            visitSet.add(pair.getKey());
            List<String> tmpList = graph.get(pair.getKey());
            tmpList.forEach(word -> {
                int step = pair.getValue() + 1;
                queue.offer(new Pair<>(word, step));
            });
        }

        return 0;
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

    public static void main(String[] args) {
        System.out.println(1);
    }
}
