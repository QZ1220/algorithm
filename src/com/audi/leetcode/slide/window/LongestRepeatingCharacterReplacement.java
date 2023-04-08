package com.audi.leetcode.slide.window;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 * <p>
 * 题目给定一个只包含大写字母的字符串S，求字符串S中的字母，最多经过K次的字母替换，可以组成的相同字母子串的最长长度
 * <p>
 * 使用滑窗+双指针的思维来解决
 * <p>
 * 时间复杂度O(N)
 * <p>
 * 参考：https://www.youtube.com/watch?v=gqXU1UyA8pk&t=1083s
 * https://www.cnblogs.com/migoo/p/12227706.html
 *
 * @author : wangquanzhou
 * @date : 2023/4/5 09:24
 */
public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        // s题设要求不为空，因此最小的窗口大小就是1
        if (s.length() < 2) {
            return 1;
        }
        int maxF = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>(26);
        for (int right = 0; right < s.length(); right++) {
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);
            maxF = maxF < map.get(s.charAt(right)) ? map.get(s.charAt(right)) : maxF;
            if ((right - left + 1 - maxF) > k) {
                left++;
                if (map.get(s.charAt(left - 1)).equals(1)) {
                    map.remove(s.charAt(left - 1));
                } else {
                    map.put(s.charAt(left - 1), map.get(s.charAt(left - 1)) - 1);
                }
            }
        }
        return s.length() - left;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement replacement = new LongestRepeatingCharacterReplacement();
        System.out.println(replacement.characterReplacement("AABABBA", 0));
    }
}
