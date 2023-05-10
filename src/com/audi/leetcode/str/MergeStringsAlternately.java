package com.audi.leetcode.str;

/**
 * https://leetcode.com/problems/merge-strings-alternately/?envType=study-plan-v2&id=leetcode-75
 * <p>
 * 交替合并两个字符串
 *
 * @author : wangquanzhou
 * @date : 2023/5/10 19:21
 */
public class MergeStringsAlternately {

    public String mergeAlternately(String word1, String word2) {
        StringBuilder builder = new StringBuilder();
        int i=0;
        while (i < word1.length() || i < word2.length()) {
            if (i<word1.length()){
                builder.append(word1.charAt(i));
            }
            if (i<word2.length()){
                builder.append(word2.charAt(i));
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {

    }
}
