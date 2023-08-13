package com.audi.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * <p>
 * 给定一个电话的键盘，按下对应的数字，求可能的字符串组合情况
 * <p>
 * Input: digits = "23"    2-对应abc   3-对应def
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * @author : wangquanzhou
 * @date : 2023/8/14 06:48
 */
public class LetterCombinationsofaPhoneNumber {

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (null == digits || digits.length() < 1) {
            return res;
        }
        String[] digitDict = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTrack2(digits, digitDict, res, new StringBuilder(), 0);
        return res;
    }

    public void backTrack(String digits, String[] digitDict, List<String> res, StringBuilder item, int startIndex) {
        if (item.length() == digits.length()) {
            res.add(item.toString());
            return;
        }

        for (int i = startIndex; i < digits.length(); i++) {
            String digit = digitDict[digits.charAt(startIndex) - '0'];
            for (int j = 0; j < digit.length(); j++) {
                item.append(digit.charAt(j));
                backTrack(digits, digitDict, res, item, i + 1);
                item.deleteCharAt(item.length() - 1);
            }
        }
    }


    public void backTrack2(String digits, String[] digitDict, List<String> res, StringBuilder item, int startIndex) {
        if (item.length() == digits.length()) {
            res.add(item.toString());
            return;
        }

        String digit = digitDict[digits.charAt(startIndex) - '0'];
        for (int j = 0; j < digit.length(); j++) {
            item.append(digit.charAt(j));
            backTrack2(digits, digitDict, res, item, startIndex + 1);
            item.deleteCharAt(item.length() - 1);
        }
    }

    public static void main(String[] args) {
        String s = "23";
        LetterCombinationsofaPhoneNumber letterCombinationsofaPhoneNumber = new LetterCombinationsofaPhoneNumber();
        System.out.println(letterCombinationsofaPhoneNumber.letterCombinations(s));

    }
}
