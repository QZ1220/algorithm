package com.audi.leetcode.slide.window;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * <p>
 * 使用滑窗+双指针的思想来解决
 *
 * @author : wangquanzhou
 * @date : 2023/4/8 21:51
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (null == s) {
            return 0;
        }

        int left = 0;
        Map<Character, Integer> map = new HashMap<>(128);
        for (int right = 0; right < s.length(); right++) {
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);
            if (isDuplicate(map)) {
                remove(map, s.charAt(left));
                left++;
            }
        }
        return s.length() - left;

    }

    /**
     * 盘点map的value是否存在大于2的情况
     *
     * @param map
     * @return
     */
    private boolean isDuplicate(Map<Character, Integer> map) {
        return map.values().stream().anyMatch(v -> v > 1);
    }

    /**
     * 移除map的元素，如果只出现一次那么直接移除，如果出现多次，那么将出现次数-1即可
     *
     * @param map
     * @param c
     */
    private void remove(Map<Character, Integer> map, Character c) {
        Integer i = map.get(c);
        if (i.equals(1)) {
            map.remove(c);
            return;
        }
        map.put(c, i - 1);
    }
}
