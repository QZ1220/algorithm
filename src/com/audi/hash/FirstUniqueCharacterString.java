package com.audi.hash;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * <p>
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 *
 * @author: WangQuanzhou
 * @date: 2021-07-24 7:22 PM
 */
public class FirstUniqueCharacterString {

    /**
     * 使用hashmap实现
     *
     * @param s
     * @return
     */
    public int firstUniqChar2(String s) {
        if (null == s || "".equals(s)) {
            return -1;
        }
        int length = s.length();
        if (length == 1) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, -1);
                continue;
            }
            map.put(c, i);
        }

        int minPos = -1;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() < 0) {
                continue;
            }
            if (-1 == minPos) {
                minPos = entry.getValue();
                continue;
            }
            if (minPos > entry.getValue()) {
                minPos = entry.getValue();
            }
        }

        return minPos;
    }


    /**
     * 使用linkedhashmap实现，主要是可以保证有序
     *
     * @param s
     * @return
     */
    public int firstUniqChar3(String s) {
        if (null == s || "".equals(s)) {
            return -1;
        }
        int length = s.length();
        if (length == 1) {
            return 0;
        }
        // 默认是插入有序的逻辑
        Map<Character, Integer> map = new LinkedHashMap<>(length);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, -1);
                continue;
            }
            map.put(c, i);
        }

        int minPos = -1;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() < 0) {
                continue;
            }
            return entry.getValue();
        }

        return minPos;
    }


    /**
     * 使用数组实现
     * 因为题目给出了限制条件，所有部分判空的语句可以不写了
     * <p>
     * 1 <= s.length <= 10^5
     * s consists of only lowercase English letters.
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {

        int[] count = new int[26];

        int len = s.length();
        for (int i = 0; i < len; i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < len; i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        FirstUniqueCharacterString uniqueCharacterString = new FirstUniqueCharacterString();
        System.out.println(uniqueCharacterString.firstUniqChar("loveleetcode"));
    }
}
