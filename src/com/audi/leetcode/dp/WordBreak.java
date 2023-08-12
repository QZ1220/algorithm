package com.audi.leetcode.dp;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/word-break/
 * <p>
 * 给定一个字符串s和一个字符串字段dict，求是否可以使用空格将s进行分割，使得分割后的子串都在dict里
 * <p>
 * 可以返回true，否则返回false
 *
 * @author : wangquanzhou
 * @date : 2023/8/12 10:16
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dictSet = wordDict.stream().collect(Collectors.toSet());
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] == true && dictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    // 如果i位置之前的元素可以被拆分，那么直接可以终止内层循环，继续移动i的位置
                    break;
                }
            }
        }
        return dp[len];
    }
}
