package com.audi.leetcode.str;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * <p>
 * 给定两个字符串s、t，在s中寻找一个子串，使得该子串包含t的全部字符。
 * <p>
 * 要求寻找满足条件的子串的长度最短，如果不存在上述子串就返回空字符串
 * <p>
 * 借助hash加双层循环s实现
 *
 * @author : wangquanzhou
 * @date : 2023/9/3 10:47
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        Set<Character> tSet = new HashSet<>();
        for (int i = 0; i < tArr.length; i++) {
            tSet.add(tArr[i]);
        }

        int res = Integer.MAX_VALUE;
        int count = 0;
        String subStr = "";
        for (int i = 0; i < sArr.length; i++) {
            if (!tSet.contains(sArr[i])) {
                continue;
            }
            count = 0;
            for (int j = i; j < sArr.length; j++) {
                if (tSet.contains(sArr[j])) {
                    count++;
                    if (count == tArr.length) {
                        if (res > j - i + 1) {
                            subStr = s.substring(i, j + 1);
                            res = j - i + 1;
                        }
                        break;
                    }
                }
            }
        }
        return subStr;
    }

    public static void main(String[] args) {
        // 下面的测试用例会失败，如果要修正，可以使用hashmap精确记录t的各个字母出现的次数
        String s = "bba";
        String t = "ab";
        MinimumWindowSubstring substring = new MinimumWindowSubstring();

        System.out.println(substring.minWindow(s, t));
    }
}
