package com.audi.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/ransom-note/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 求一个字符串的字母是否都包含在另外一个字符串中，每个字符只能使用一次
 *
 * @author : wangquanzhou
 * @date : 2023/7/5 23:02
 */
public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            if (map.containsKey(magazine.charAt(i))) {
                map.put(magazine.charAt(i), map.get(magazine.charAt(i)) + 1);
            } else {
                map.put(magazine.charAt(i), 1);
            }
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            Integer cnt = map.getOrDefault(ransomNote.charAt(i), 0);
            if (cnt == 0) {
                return false;
            }
            map.put(ransomNote.charAt(i), cnt - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        String a = "aa";
        String b = "aab";
        RansomNote ransomNote = new RansomNote();
        System.out.println(ransomNote.canConstruct(a, b));
    }
}
