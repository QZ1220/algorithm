package com.audi.other;

import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat do";
        HashDemo hashDemo = new HashDemo();
        boolean match = hashDemo.wordPattern(pattern, s);
        System.out.println(match);
    }
}
