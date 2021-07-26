package com.audi.hash;


/**
 * https://leetcode.com/problems/richest-customer-wealth/
 *
 * @author: WangQuanzhou
 * @date: 2021-07-26 10:30 PM
 */
public class RichestCustomerWealth {
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int i = 0; i < accounts.length; i++) {
            int sum = 0;
            for (int j = 0; j < accounts[0].length; j++) {
                sum += accounts[i][j];
            }
            if (max < sum) {
                max = sum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        RichestCustomerWealth wealth = new RichestCustomerWealth();
        int[][] accounts = {{2, 8, 7}, {7, 1, 3}, {1, 9, 5}};
        System.out.println(wealth.maximumWealth(accounts));
    }
}
