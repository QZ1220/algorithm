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
 *
 * @author : wangquanzhou
 * @date : 2023/4/5 09:24
 */
public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        // 如果字符串只有一个字母，那么不需要替换
        if (s.length() == 1) {
            return 1;
        }

        // 定义滑窗的左右端点指针
        int left = 0;
        int right = 1;
        // 定义hash表存储滑窗内的字母出现的频率
        Map<Character, Integer> charMap = new HashMap<>(26);
        charMap.put(s.charAt(left), charMap.getOrDefault(s.charAt(left), 0) + 1);
        charMap.put(s.charAt(right), charMap.getOrDefault(s.charAt(right), 0) + 1);
        // 记录滑窗内字母出现的最大次数，如果初始两个字母一样，那么maxCount=2，否则等于1
        int maxCount = charMap.size() == 2 ? 1 : 2;
        // 记录滑窗的最大宽度
        int maxWindowSize = k > 0 ? 2 : 1;
        if (charMap.size() == 1 && k == 0) {
            maxWindowSize = 2;
        }

        // 开始扫描字符串
        while (left <= right && left < s.length() && right < s.length()) {
            if (((++right - left + 1 - maxCount) <= k) && right < s.length()) {
                charMap.put(s.charAt(right), charMap.getOrDefault(s.charAt(right), 0) + 1);
                maxCount = charMap.get(s.charAt(right)) > maxCount ? charMap.get(s.charAt(right)) : maxCount;
                maxWindowSize = (right - left + 1) > maxWindowSize ? (right - left + 1) : maxWindowSize;
                continue;
            } else if (++left < s.length())
                if (charMap.get(s.charAt(left - 1)).equals(1)) {
                    charMap.remove(s.charAt(left - 1));
                } else {
                    charMap.put(s.charAt(left - 1), charMap.get(s.charAt(left - 1)) - 1);
                }
        }
        return maxWindowSize;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement replacement = new LongestRepeatingCharacterReplacement();
        System.out.println(replacement.characterReplacement("AABABBA", 0));
    }
}
