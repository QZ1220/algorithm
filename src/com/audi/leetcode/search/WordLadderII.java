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

        // 通过那个单词 访问到的当前单词
        Integer prefix;

        //
        Integer step;
    }


    public static void main(String[] args) {

    }
}
