package com.audi.other;

import java.util.*;

public class HashDemo {

    /**
     * 求字符串s是否是pattern的格式
     * <p>
     * Input: pattern = "abba", s = "dog cat cat dog"
     * Output: true
     * <p>
     * Input: pattern = "abba", s = "dog cat cat fish"
     * Output: false
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] sArr = s.trim().split(" ");
        if (sArr.length != pattern.length()) {
            return false;
        }
        Map<String, Character> mapA = new HashMap<>();
        for (int i = 0; i < sArr.length; i++) {
            String tmpS = sArr[i];
            char c = pattern.charAt(i);

            if (mapA.containsKey(tmpS)) {
                if (mapA.get(tmpS) != c) {
                    return false;
                }
            } else {
                if (mapA.containsValue(c)) {
                    return false;
                }
                mapA.put(tmpS, c);
            }
        }
        return true;
    }

    /**
     * 字符串分组
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new LinkedList<>();
        if (null == strs || strs.length < 1) {
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String s = sortWord(str);
            if (map.containsKey(s)) {
                map.get(s).add(str);
            } else {
                List<String> list = new LinkedList();
                list.add(str);
                map.put(s, list);
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    public String sortWord(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat do";
        HashDemo hashDemo = new HashDemo();
        boolean match = hashDemo.wordPattern(pattern, s);
        System.out.println(match);
    }
}
