package com.audi.leetcode.array;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 * <p>
 * 本质的思路和接雨水的有点像，使用数组保存当前节点左边所有数据的乘积，另一个数组保存右边节点所有数据的乘积
 * <p>
 * 结果就是左边的乘积诚意右边乘积
 *
 * @author WangQuanzhou
 * @date 2020-04-16
 */
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        if (null == nums || nums.length < 2) {
            return nums;
        }

        int length = nums.length;
        int[] res = new int[length];
        if (length == 2) {
            res[0] = nums[1];
            res[1] = nums[0];
            return res;
        }

        int[] left = new int[length];
        left[0] = 1;
        int[] right = new int[length];
        right[length - 1] = 1;

        for (int i = 1; i < length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        for (int i = length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < length; i++) {
            if (i == 0) {
                res[i] = right[0];
            } else if (i == length - 1) {
                res[i] = left[i];
            } else {
                res[i] = left[i] * right[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 5, 4, 3};
        int[] nums = {2,3,1};
        ProductOfArrayExceptSelf productOfArrayExceptSelf = new ProductOfArrayExceptSelf();
        int[] res = productOfArrayExceptSelf.productExceptSelf(nums);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + "  ");
        }
    }

}
