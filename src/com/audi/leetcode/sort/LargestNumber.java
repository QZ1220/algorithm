package com.audi.leetcode.sort;


import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/largest-number/
 * <p>
 * 题目给出了一个数字组成的数组，求这些数字能组成的最大数字，因为数字可能会很大，因此返回结果使用字符串代表数字
 * <p>
 * 直观的解题思路：将nums的每个元素转换成字符串，然后对这个字符串数组进行自然序排序
 * 然后按照顺序，从前往后拼接即可
 * 这种解题思路在{3, 30, 34, 5, 9}测试用例下会失败，因为按照自然序降序，那么得到[9, 5, 34, 30, 3]，前三个数字都没问题，但是如果要拼成最大的数字 ，那么3要在30前才行
 * <p>
 * https://blog.csdn.net/qq_41231926/article/details/86549514
 * 使用贪心的思想，比较两个字符串按照不同的顺序进行拼接后的结果
 *
 * @author: WangQuanzhou
 * @date: 2021-09-21 8:59 AM
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length < 1) {
            return "";
        }
        if (nums.length < 2) {
            return String.valueOf(nums[0]);
        }

        List<String> list = new LinkedList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }

        list.sort(((s1, s2) -> (s2 + s1).compareTo(s1 + s2)));
        StringBuilder builder = new StringBuilder();
        for (String s : list) {
            builder.append(s);
        }

        // 特殊处理 [0,0]这种测试用例
        while ('0' == builder.charAt(0)) {
            builder.deleteCharAt(0);
            if (0 == builder.length()) {
                return "0";
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};
        LargestNumber number = new LargestNumber();
        System.out.println(number.largestNumber(nums));
    }
}
