package com.audi.leetcode.hash;

import java.util.*;

/**
 * https://leetcode.com/problems/majority-element-ii/
 * <p>
 * 题目给了一个整型数组，求出数组所有符合条件的数字
 * 条件：该数字在数组中出现的次数大于3/n，n为数组的长度
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: [3]
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: [1]
 * Example 3:
 * <p>
 * Input: nums = [1,2]
 * Output: [1,2]
 * <p>
 * 借助hash表实现
 *
 * @author : wangquanzhou
 * @date : 2021/12/18 21:55
 */
public class MajorityElementII {

    /**
     * 借助hash表实现，虽然可以AC但是性能貌似不是特别好
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> dtsList = new LinkedList<>();
        if (nums.length < 2) {
            // 题设保证了数组不为空，这里就不做空判断了
            dtsList.add(nums[0]);
            return dtsList;
        }

        int length = nums.length;
        int baseCount = length / 3;
        // 使用set存放结果，并且对结果进行去重
        Set<Integer> dtsSet = new HashSet<>(4);

        Map<Integer, Integer> map = new HashMap<>(baseCount);
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
                if (map.get(num) > baseCount) {
                    dtsSet.add(num);
                }
            } else {
                map.put(num, 1);
                if (1 > baseCount) {
                    dtsSet.add(num);
                }
            }
        }
        dtsList.addAll(dtsSet);
        return dtsList;
    }


    /**
     * https://blog.csdn.net/weixin_41504611/article/details/103804027
     * <p>
     * 基于摩尔投票法
     *
     * 超过n/3的数最多只能有两个。先选出两个候选人A,B。 遍历数组，分三种情况：
     *
     * 1.如果投A（当前元素等于A），则A的票数++;
     *
     * 2.如果投B（当前元素等于B），B的票数++；
     *
     * 3.如果A,B都不投（即当前与A，B都不相等）,那么检查此时A或B的票数是否减为0：
     *
     * 3.1 如果为0,则当前元素成为新的候选人；
     *
     * 3.2 如果A,B两个人的票数都不为0，那么A,B两个候选人的票数均减一；
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> dstList = new LinkedList<>();
        int m = -1;
        int n = -1;
        int countM = 0;
        int countN = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if (temp == m) {
                countM++;
            } else if (temp == n) {
                countN++;
            } else if (countM == 0) {
                m = nums[i];
                countM = 1;
            } else if (countN == 0) {
                n = nums[i];
                countN = 1;
            } else {
                countM--;
                countN--;
            }
        }

        // 再便利一次，看看m，n是否是符合题意
        countM = 0;
        countN = 0;
        for (int num : nums) {
            if (m == num) {
                countM++;
                continue;
            }
            if (n == num) {
                countN++;
            }
        }
        int len = nums.length;
        len = len / 3;
        if (countM > len) {
            dstList.add(m);
        }
        if (countN > len) {
            dstList.add(n);
        }
        return dstList;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2};
        MajorityElementII majorityElementII = new MajorityElementII();
        System.out.println(majorityElementII.majorityElement(nums));
    }
}
