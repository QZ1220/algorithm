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
    public int longestMountain22(int[] A) {
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


    /**
     * 我们可以使用动态规划的方法来解决这个问题。定义两个动态规划数组：up[i]表示以第i个元素结尾的上升段（即左侧递增序列）的长度，
     * down[i]表示以第i个元素开始的下降段（即右侧递减序列）的长度。
     * <p>
     * 遍历数组，从左到右更新up[i]，从右到左更新down[i]。具体的动态规划状态转移方程如下：
     * <p>
     * 对于i从1到n-1（n为数组长度）：
     * <p>
     * 如果arr[i] > arr[i-1]，则up[i] = up[i-1] + 1；
     * 否则，up[i] = 0（重新开始计算上升段）。
     * 对于i从n-2到0：
     * <p>
     * 如果arr[i] > arr[i+1]，则down[i] = down[i+1] + 1；
     * 否则，down[i] = 0（重新开始计算下降段）。
     * 遍历完数组后，再遍历一次，计算最长的山脉长度。对于每个位置i，如果up[i]和down[i]都大于0，
     * 则以i为山顶的山脉长度为up[i] + down[i] + 1，更新最长山脉的长度。
     *
     * @param arr
     * @return
     */
    public int longestMountain(int[] arr) {
        if (null == arr || arr.length < 3) {
            return 0;
        }
        int[] dpUp = new int[arr.length];
        int[] dpDown = new int[arr.length];

        for (int i = 1; i < arr.length; i++) {
            dpUp[i] = arr[i] > arr[i - 1] ? dpUp[i - 1] + 1 : 0;
        }

        for (int i = arr.length - 2; i >= 0; i--) {
            dpDown[i] = arr[i] > arr[i + 1] ? dpDown[i + 1] + 1 : 0;
        }

        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            if (dpUp[i] > 0 && dpDown[i] > 0) {
                // i表示山顶的位置 dp[i]表示左边山坡的高度   dpDown[i]表示右边山坡的高度
                // 因此总长度 = dpUp[i] + dpDown[i] + 1（1表示山顶的元素）
                maxLen = Math.max(maxLen, dpUp[i] + dpDown[i] + 1);
            }
        }

        return maxLen;
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
