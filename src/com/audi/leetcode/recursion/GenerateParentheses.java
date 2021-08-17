package com.audi.leetcode.recursion;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 *
 * 给定数字n，代表n组括号，求使用n组括号可以组成多少种合法的括号子集
 *
 * n=3
 *
 * ((())), (()()), (())(), ()(()), ()()()
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

    // 这个题也可以使用这种解法：
    // 先求出所有可能的括号组合，再依次判断各个组合是否是合法的

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        System.out.println(generateParentheses.generateParenthesis(3));
    }
}
