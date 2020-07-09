package com.audi.leetcode.recursion;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 *
 * @author: WangQuanzhou
 * @date: 2020/6/27 22:24
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        ArrayList<String> ret = new ArrayList<String>();
        if (n <= 0) {
            return ret;
        }
        // 递归调用
        dfs(n, n, "", ret);
        return ret;
    }

    private void dfs(int left, int right, String s, ArrayList<String> ret) {
        // 左右括号数量相等  且左右括号都已经用完
        if (left == 0 && right == 0) {
            ret.add(s);
            return;
        }
        // 先加左括号
        if (left > 0) {
            dfs(left - 1, right, s + "(", ret);
        }
        // 再加右括号
        if (left < right) {
            dfs(left, right - 1, s + ")", ret);
        }

    }

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        System.out.println(generateParentheses.generateParenthesis(3));
    }
}
