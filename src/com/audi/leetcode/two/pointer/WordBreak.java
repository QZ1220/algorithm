package com.audi.leetcode.two.pointer;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/word-break/
 * <p>
 * 给定一个字符串s和一个字符串字段dict，求是否可以使用空格将s进行分割，使得分割后的子串都在dict里
 * <p>
 * 可以返回true，否则返回false
 *
 * @author : wangquanzhou
 * @date : 2023/8/12 10:16
 */
public class WordBreak {

    /**
     * 如下的算法在面对
     * String s = "aaaaaaa";
     * List<String> wordDict = Arrays.asList("aaaa", "aaa");
     * 测试用例时 会失败
     * 因为，下面的算法会优先按照最短单词进行匹配，这回导致较长的单词被拆分，从而导致结果出错
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dictSet = wordDict.stream().collect(Collectors.toSet());
        if (s.length() == 1) {
            return dictSet.contains(s);
        }

        int left = 0, right = 0;
        while (left <= right && right < s.length()) {
            if (dictSet.contains(s.substring(left, right + 1))) {
                left = right + 1;
                right = right + 1;
            } else {
                right++;
            }
        }
        return left == right;
    }

    public static void main(String[] args) {
        String s = "aaaaaaa";
        List<String> wordDict = Arrays.asList("aaaa", "aaa");
        WordBreak wordBreak = new WordBreak();
        System.out.println(wordBreak.wordBreak(s, wordDict));
    }
}
