package com.audi.leetcode.array;


/**
 * https://leetcode.com/problems/single-number-iii/
 * <p>
 * 一个数组中，有一个数字只出现了一次，其余数据都出响了三次，找出这个只出现一次的数字
 * <p>
 * 题目要求实现o（n）的时间复杂度，常数级的空间复杂度
 * <p>
 * 这个题和single-number很像，这里参考https://www.ixigua.com/6907241701643911688?logTag=eb5a61bce524c42a0146
 * <p>
 * 根据数字的二进制位，统计每一位上 数字1出现的次数。再 mod 3，即可得到结果
 *
 * @author: WangQuanzhou
 * @date: 2021-08-21 2:01 PM
 */
public class SingleNumberII {

    public int singleNumber(int[] nums) {

        int result = 0;
        // 题设给出了数字的范围   -231 <= nums[i] <= 231 - 1
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int num : nums) {
                num = num >> i;
                count += (num & 1);
            }
            // 事实上，count%3要么等于1，要么等于0，等于1表示是单独的那个数贡献的
            // 这里的3，还可以扩展到N，也就是数组中其余数字都重复出现N次，唯独有一个只出现一次
            if ((count % 3) == 1) {
                // 累计result，注意方式
                result = (result | 1 << i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SingleNumberII singleNumberII = new SingleNumberII();
        int[] nums = {30000, 500, 100, 30000, 100, 30000, 100};
        int number = singleNumberII.singleNumber(nums);
        System.out.println(number);
    }
}
