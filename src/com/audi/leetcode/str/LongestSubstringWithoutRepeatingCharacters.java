package com.audi.leetcode.str;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * <p>
 * 思路：
 * 使用LinkedHashMap<Char,List<Integer>>的结构  key为字符串中的字符  Value为字符串出现的位置
 * <p>
 * 如果所有的Value的长度都为1 那么整个字符串都没有重复字符
 * <p>
 * 否则从前往后，去除Value的值，从两个位置中间获取字符串元素
 *
 * @author WangQuanzhou
 * @date 2020-10-30
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * 这种揭发忽略了 重复字符之间的内容还可以和前后的未重复的字符串形成最长字符串  例如 abcb 的形式  最长的子串是abc
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (null == s) {
            return 0;
        }
        int length = s.length();
        if (length < 2) {
            return length;
        }

        Boolean unique = Boolean.TRUE;

        // 定义有序hashMap  默认按照插入顺序排序
        Map<Character, List<Integer>> map = new LinkedHashMap<>();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.get(c).add(i);
                unique = Boolean.FALSE;
                continue;
            }
            List<Integer> list = new ArrayList<>();
            list.add(i);
            map.put(c, list);
        }

        if (unique) {
            return length;
        }

        int maxlength = 0;
        for (Character character : map.keySet()) {
            List<Integer> list = map.get(character);
            if (list.size() < 2) {
                continue;
            }
            maxlength = max(list, length);
        }
        return maxlength;
    }

    private int max(List<Integer> list, int length) {
        int size = list.size();
        int leftMax = list.get(0) + 1;
        int rightMax = length - list.get(size - 1);
        int midMax = 0;

        for (int i = size - 1; i > 0; i--) {
            int distance = list.get(i) - list.get(i - 1);
            if (midMax < distance) {
                midMax = distance;
            }
        }
        return leftMax > midMax ? (leftMax > rightMax ? leftMax : rightMax) : (midMax > rightMax ? midMax : rightMax);
    }

    /**
     * https://segmentfault.com/a/1190000016216003?utm_source=tag-newest
     * <p>
     * 采用滑动窗口解决
     * <p>
     * 自己还是走了弯路
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (null == s || s.length() < 1) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>(s.length());
        int max = 0;
        // 这里pre只能初始化为-1
        int pre = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                Integer index = map.get(c);
                pre = Math.max(pre, index);
            }
            max = Math.max(max, i - pre);
            map.put(c, i);
        }
        return max;
    }


    public static void main(String[] args) {

//        String s = "pwwkew";
//        String s = "";
//        String s = "cdd";
        String s = "abcb";
        LongestSubstringWithoutRepeatingCharacters characters = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(characters.lengthOfLongestSubstring2(s));

    }
}
