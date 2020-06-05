package com.audi.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给你字符串 s 和整数 k 。
 * <p>
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * <p>
 * 英文中的 元音字母 为（a, e, i, o, u）。
 *
 * @author WangQuanzhou
 * @date 2020-06-05
 */
public class MaximumNumberofVowelsinaSubstringofGivenLength {

    public int maxVowels(String s, int k) {
        if (null == s || s.length() == 0) {
            return 0;
        }

        int length = s.length();
        ArrayList<Boolean> arrayList = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(isVowel(s.charAt(i)));
        }

        // 各个滑动窗口中的最大值
        int max = 0;

        // 初始化max
        for (int i = 0; i < k && i < length; i++) {
            if (arrayList.get(i)) {
                max++;
            }
        }

        // 各个滑动过程中，最大值
        int max_max = max;

        for (int i = k; i < length; i++) {
            if (arrayList.get(i - k)) {
                max--;
            }
            if (arrayList.get(i)) {
                max++;
            }

            if (max > max_max) {
                max_max = max;
            }
            // 因为子串的长度限制了最大为k  因此当元音字母个数为k时，就不可能再更大了
            if (max_max >= k) {
                return k;
            }
        }

        return max_max;

    }

    /**
     * 判断当前字符是否是元音字符
     *
     * @param c
     * @return
     */
    public Boolean isVowel(Character c) {
        List<Character> characterList = Arrays.asList('a', 'e', 'i', 'o', 'u');
        return characterList.contains(c);
    }

    public static void main(String[] args) {
        MaximumNumberofVowelsinaSubstringofGivenLength substringofGivenLength = new MaximumNumberofVowelsinaSubstringofGivenLength();
//        String s = "rhythms";
        String s = "zpuerktejfp";
        System.out.println(substringofGivenLength.maxVowels(s, 3));
    }
}
