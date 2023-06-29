package com.audi.leetcode.str;

/**
 * 求一个字符串中最后一个单词的长度
 *
 * @author : wangquanzhou
 * @date : 2023/6/29 19:20
 */
public class LengthofLastWord {

    public int lengthOfLastWord(String s) {
        int length = s.length();
        int len = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (' ' == s.charAt(i)) {
                if (len > 0) {
                    return len;
                }
                continue;
            }
            len++;
        }
        return len;
    }

    public static void main(String[] args) {

    }
}
