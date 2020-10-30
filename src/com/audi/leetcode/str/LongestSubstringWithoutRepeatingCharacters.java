package com.audi.leetcode.str;

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

    public int lengthOfLongestSubstring(String s) {

    }


    public static void main(String[] args) {

    }
}
