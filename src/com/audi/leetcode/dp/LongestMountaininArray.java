package com.audi.leetcode.dp;


import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-mountain-in-array/
 * <p>
 * 题目给了一个整型数组，需要求一个满足条件的子数组的最大长度
 * 1、数组的长度至少为3
 * 2、数组的元素组成一个山峰状，也就是存在某个位置，左边和右边的元素都比这个位置的元素小，左边是递增，右边是递减
 * <p>
 * 听上去貌似类似wiggle-subsequence，可以借助状态机来求解
 * <p>
 * 注意这里求的子数组，区别于子序列，数组元素位置是连续的
 * <p>
 * Input: arr = [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 *
 * @author: WangQuanzhou
 * @date: 2021-10-29 10:59 AM
 */
public class LongestMountaininArray {

    /**
     * 下面的解法遇到 {2, 3, 1, 2, 3, 4, 5, 6} 这种测试用例，结果会错
     *
     * @param arr
     * @return
     */
    public int longestMountain2(int[] arr) {
        int length = arr.length;
        if (length < 3) {
            return 0;
        }

        final int BEGIN = 0;
        final int UP = 1;
        final int DOWN = 2;

        int STATE = BEGIN;

        int res = 0;
        // 初始为1
        int maxLen = 0;
        Set<Integer> set = new HashSet<>(4);

        for (int i = 1; i < length; i++) {
            switch (STATE) {
                case BEGIN:
                    if (arr[i] > arr[i - 1]) {
                        maxLen = 1;
                        STATE = UP;
                        set.add(UP);
                        maxLen++;
                    }
                    break;
                case UP:
                    if (arr[i] < arr[i - 1]) {
                        STATE = DOWN;
                        set.add(DOWN);
                    }
                    maxLen++;
                    if (arr[i] == arr[i - 1]) {
                        STATE = BEGIN;
                    }
                    break;
                case DOWN:
                    if (arr[i] == arr[i - 1]) {
                        STATE = BEGIN;
                        res = Math.max(maxLen, res);
                    } else if (arr[i] > arr[i - 1]) {
                        STATE = UP;
                        res = Math.max(maxLen, res);
                        maxLen = 1;
                    } else {
                        maxLen++;
                    }
            }
        }
        return set.size() == 2 ? Math.max(res, maxLen) : 0;
    }

    /**
     * 官方solution使用的是双指针求解
     *
     * @param A
     * @return
     */
    public int longestMountain(int[] A) {
        int N = A.length;
        int ans = 0, base = 0;
        while (base < N) {
            int end = base;
            // if base is a left-boundary
            if (end + 1 < N && A[end] < A[end + 1]) {
                // set end to the peak of this potential mountain
                while (end + 1 < N && A[end] < A[end + 1]) end++;

                // if end is really a peak..
                if (end + 1 < N && A[end] > A[end + 1]) {
                    // set end to the right-boundary of mountain
                    while (end + 1 < N && A[end] > A[end + 1]) end++;
                    // record candidate answer
                    ans = Math.max(ans, end - base + 1);
                }
            }

            base = Math.max(end, base + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
//        int[] arr = {2, 1, 4, 7, 3, 2, 5};
//        int[] arr = {2, 2, 2};
//        int[] arr = {0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0, 0};
//        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
//        int[] arr = {3, 2, 1, 0, 5, 6, 2};
        int[] arr = {2, 3, 1, 2, 3, 4, 5, 6};
        LongestMountaininArray mountaininArray = new LongestMountaininArray();
        System.out.println(mountaininArray.longestMountain(arr));
    }

}
