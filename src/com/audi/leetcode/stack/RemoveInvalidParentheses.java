package com.audi.leetcode.stack;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * <p>
 * 题目给了一个字符串，可能包含小写字母，左右括号，求最少去掉多少括号可以使得字符串成为一个合法的括号表达式
 * <p>
 * 返回所有的合法的表达式，顺序不限
 * <p>
 * 初步思路：
 * 要想成为合法的表达式，左右括号的数量一定要相等，如果左or右括号多了，可以尝试在不同位置进行移除，已得到合法的解
 * <p>
 * 假如左括号多，可以删除除开第一个左括号外的相应数量的左括号
 * <p>
 * 假如右括号多，可以删除除开最后一个右括号外的相应数量的右括号
 *
 * @author: WangQuanzhou
 * @date: 2021-08-17 1:50 PM
 */
public class RemoveInvalidParentheses {

    private static final Character LEFT = '(';
    private static final Character RIGHT = ')';
    private static final String SPACE = " ";
    private static final String EMPTY = "";

    public List<String> removeInvalidParentheses(String s) {
        if (valid(s)) {
            return Arrays.asList(s);
        }
        if (null == s) {
            return Arrays.asList("");
        }

        int leftCount = 0;
        int rightCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == LEFT) {
                leftCount++;
            }
            if (c == RIGHT) {
                rightCount++;
            }
        }

        if (leftCount == 0 && rightCount == 0) {
            return Arrays.asList(s);
        }
        if (leftCount == 0) {
            return Arrays.asList(s.replaceAll("\\)", ""));
        }
        if (rightCount == 0) {
            return Arrays.asList(s.replaceAll("\\(", ""));
        }

        return leftCount > rightCount ? removeLeft(s, leftCount - rightCount) : removeRight(s, rightCount - leftCount);
    }

    /**
     * 去除左括号
     * <p>
     * 第一个左括号不能删除
     *
     * @param s
     * @return
     */
    private List<String> removeLeft(String s, int diff) {
        List<String> ret = new ArrayList<>();
        boolean firstLeft = true;
        int tempDiff = diff;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != LEFT) {
                continue;
            }
            if (firstLeft) {
                firstLeft = false;
                continue;
            } else {
                StringBuilder sb = new StringBuilder(s);
                diff = tempDiff;
                for (int j = i; j < s.length() && diff > 0; j++) {
                    c = s.charAt(j);
                    if (c == LEFT) {
                        sb.replace(j, j + 1, SPACE);
                        diff--;
                    }
                }
                String s1 = sb.toString().replaceAll(SPACE, EMPTY);
                if (valid(s1)) {
                    ret.add(s1);
                }
            }
        }
        ret = ret.stream().distinct().collect(Collectors.toList());
        if (ret.size() < 1) {
            return Arrays.asList("");
        } else {
            return ret;
        }
    }

    /**
     * 去除右括号
     * <p>
     * 最后一个右括号不能删除
     *
     * @param s
     * @return
     */
    private List<String> removeRight(String s, int diff) {
        List<String> ret = new ArrayList<>();
        boolean firstRight = true;
        int tempDiff = diff;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c != RIGHT) {
                continue;
            }
            if (firstRight) {
                firstRight = false;
                continue;
            } else {
                StringBuilder sb = new StringBuilder(s);
                diff = tempDiff;
                for (int j = i; j >= 0 && diff > 0; j--) {
                    c = s.charAt(j);
                    if (c == RIGHT) {
                        sb.replace(j, j + 1, SPACE);
                        diff--;
                    }
                }
                String s1 = sb.toString().replaceAll(SPACE, EMPTY);
                if (valid(s1)) {
                    ret.add(s1);
                }
            }
        }
        ret = ret.stream().distinct().collect(Collectors.toList());
        if (ret.size() < 1) {
            return Arrays.asList("");
        } else {
            return ret;
        }
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
            if (c != '(' && c != ')') {
                continue;
            }
            if (c == '(') {
                stack.add(c);
                continue;
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
        String s = ")(n";
        RemoveInvalidParentheses parentheses = new RemoveInvalidParentheses();
        System.out.println(parentheses.removeInvalidParentheses(s));
    }
}
