package com.audi.leetcode.recursion;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * <p>
 * 给定数字n，代表n组括号，求使用n组括号可以组成多少种合法的括号子集
 * <p>
 * n=3
 * <p>
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

    private void dfs2(int left, int right, StringBuilder sb, ArrayList<String> ret) {
        // 左右括号数量相等  且左右括号都已经用完
        if (left == 0 && right == 0) {
            ret.add(sb.toString());
            return;
        }
        // 先加左括号
        if (left > 0) {
            dfs2(left - 1, right, sb.append("("), ret);
        }
        // 再加右括号
        if (left < right) {
            dfs2(left, right - 1, sb.append(")"), ret);
        }
    }

    // 这个题也可以使用这种解法：
    // 先求出所有可能的括号组合，再依次判断各个组合是否是合法的
    // 这种解法性能上，要比上面的dfs差一些
    public List<String> generateParenthesis2(int n) {
        List<String> ret = new LinkedList<>();
        if (n <= 0) {
            return ret;
        }

        subset(2 * n, "", ret);

        return ret;
    }

    /**
     * 递归产生子串
     *
     * @param m
     * @param s
     * @param ret
     */
    private void subset(int m, String s, List<String> ret) {
        if (s.length() == m) {
            if (valid(s)) {
                ret.add(s);
            }
            return;
        }
        subset(m, s + "(", ret);
        subset(m, s + ")", ret);
    }

    /**
     * 判断一个字符串是否是合法字符串
     *
     * @param s
     * @return
     */
    private boolean valid(String s) {
        if (null == s || s.length() < 2) {
            return false;
        }
        if (s.startsWith(")") || s.endsWith("(")) {
            return false;
        }
        // 借助栈  来判断括号字符串是否合法
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.add(c);
            }
            if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        System.out.println(generateParentheses.generateParenthesis(2));
        System.out.println(generateParentheses.generateParenthesis(2).size());
        System.out.println(generateParentheses.generateParenthesis2(2));
        System.out.println(generateParentheses.generateParenthesis2(2).size());
    }
}
