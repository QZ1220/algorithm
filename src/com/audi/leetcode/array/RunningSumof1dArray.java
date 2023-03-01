package com.audi.leetcode.array;

public class RunningSumof1dArray {

    public int[] runningSum(int[] nums) {
        if (null==nums||nums.length<2){
            return nums;
        }
        int sum=0;
        for (int i = 0; i < nums.length; i++) {
            sum=sum+nums[i];
            nums[i]=sum;
        }
        return nums;
    }

    public static void main(String[] args) {

    }
}
