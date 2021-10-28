package com.audi.leetcode.dp;


import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-harmonious-subsequence/
 * <p>
 * 题目给出一个整型数组，求数组的子序列，子序列应该满足如下条件：
 * 子序列的元素之间的差值，最大最小都是1
 * 求满足条件的子序列的最长的长度
 * <p>
 * 这居然是一个Easy难度的题目，有点。。怀疑自己的智商了。。
 * <p>
 * Input: nums = [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 * <p>
 * Input: nums = [1,1,1,1]
 * Output: 0
 * <p>
 * 这个题应该可以参照https://leetcode.com/problems/longest-arithmetic-subsequence/的解法求解
 *
 * @author: WangQuanzhou
 * @date: 2021-10-28 1:12 PM
 */
public class LongestHarmoniousSubsequence {

    /**
     * 参照https://leetcode.com/problems/longest-arithmetic-subsequence/的解法求解
     * <p>
     * 实测会发现不对，比如对于
     * Input: nums = [1,2,3,4]
     * Output: 2
     * 实际代码会输出4，但是这并不是符合题意的
     *
     * @param nums
     * @return
     */
    public int findLHS2(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        int res = 0;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[j] - nums[i] != 1) {
                    continue;
                }
                dp[j] = dp[i] + 1;
                res = Math.max(res, dp[j]);
            }
        }
        return res + 1;
    }


    /**
     * 看了下LeetCode的solution，提供了几种方案：
     * 解法1、直接枚举出nums数组的全部子集「这个过程可以参考subsets那个题」，然后判断每个自己是否满足题意
     * 解法2、使用两层循环，在循环的过程中判断元素是否是相等或者差值为1，然后求一个最大的长度即可
     * 解法3、借助hashmap的思想，预先将元素存入map，然后判断「元素+1」的数据是否存在于map，然后求得最大的长度
     * <p>
     * 下面分别实现一下解法2、3
     * <p>
     * 解法2时间复杂度O(N^2)
     *
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        int len = nums.length;
        int res = 0;

        for (int i = 0; i < len; i++) {
            boolean flag = false;
            int count = 0;
            // 注意这里j从0开始  而不是1开始
            for (int j = 0; j < len; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                } else if (nums[i] == nums[j] + 1) {
                    count++;
                    flag = true;
                }
            }
            if (flag) {
                res = Math.max(res, count);
            }
        }
        return res;
    }

    /**
     * 解法3  借助hashmap
     * <p>
     * 时间复杂度O(N)
     *
     * @param nums
     * @return
     */
    public int findLHS3(int[] nums) {
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>(length);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (map.containsKey(entry.getKey() + 1)) {
                int temp = map.get(entry.getKey()) + map.get(entry.getKey() + 1);
                res = Math.max(res, temp);
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 3, 3, 2, 2, 5, 2, 3, 7};
        int[] nums = {1, 2, 1, 3, 0, 0, 2, 2, 1, 3, 3};
//        int[] nums = {1, 2, 3, 4};
//        int[] nums = {1, 1, 1, 1};
        LongestHarmoniousSubsequence subsequence = new LongestHarmoniousSubsequence();
        System.out.println(subsequence.findLHS3(nums));
        System.out.println(subsequence.findLHS(nums));
    }
}
