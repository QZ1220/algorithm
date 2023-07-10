package com.audi.other;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 求一个字符串的最长无重复字符的子串长度
 *
 * @author : wangquanzhou
 * @date : 2023/7/6 07:55
 */
public class MaxDisctinctSubStr {
    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() < 1) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int i = 0, j = 1;
        int maxlen = 1;
        Set<Character> charSet = new HashSet<>();
        charSet.add(s.charAt(i));
        while (i < j && i < s.length() && j < s.length()) {
            if (charSet.contains(s.charAt(j))) {
                while (i < j && charSet.contains(s.charAt(j))) {
                    charSet.remove(s.charAt(i));
                    i++;
                }
                charSet.add(s.charAt(j));
                j++;
            } else {
                charSet.add(s.charAt(j));
                j++;
            }
            maxlen = charSet.size() > maxlen ? charSet.size() : maxlen;
        }
        return maxlen;
    }

    public static void main(String[] args) {

    }
}
