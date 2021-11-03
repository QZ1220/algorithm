package com.audi.shame;


import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * byteDance
 * 给定一个正整数，如果正整数按照如下规则计算最终等于1，那么就说这个数是happyNum
 * <p>
 * https://leetcode.com/problems/happy-number/
 * <p>
 * 19
 * 1^2+9^2=82
 * <p>
 * 82
 * 8^2+2^2=68
 * <p>
 * 68
 * 6^2+8^2=100
 * <p>
 * 100
 * 1^2+0^2+0^2=1
 * <p>
 * 注意，在上面的计算过程中可能会出现环状，需要避免死循环的出现，可以使用一个set记录计算过程中的值，如果出现了重复的值，
 * 那么就直接返回false
 * 比如输入11
 *
 * @author: WangQuanzhou
 * @date: 2021-11-02 5:23 PM
 */
public class HappyNum {

    public boolean isHappy(int num) {
        // 题设限定了输入是一个正整数
        if (num == 1) {
            return Boolean.TRUE;
        }
        // 使用set记录计算过程中出现的元素，避免出现环状，引发死循环
        Set<Integer> set = new HashSet<>();
        set.add(num);

        // 循环计算  看看最终是否能得到1
        while (num != 1) {
            String s = ((Integer) num).toString();
            int len = s.length();
            int temp = 0;
            for (int i = 0; i < len; i++) {
                int item = s.charAt(i) - '0';
                temp = temp + item * item;
            }
            if (temp == 1) {
                return Boolean.TRUE;
            }
            if (set.contains(temp)) {
                return Boolean.FALSE;
            }
            set.add(temp);
            num = temp;
        }
        return Boolean.FALSE;
    }

    public static void main(String[] args) {

        HappyNum happyNum = new HappyNum();
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.SIZE);
        System.out.println(Integer.BYTES);
//        BitSet bitSet = new BitSet();
//        for (int i = 1; i < Integer.MAX_VALUE; i++) {
//            if (happyNum.isHappy(i)) {
//                bitSet.set(i);
//            }
//        }
//        System.out.println(bitSet.size());

        // 假如将isHappy(int num)做成一个服务，需要承载很高的并发量，比如每秒上万次的请求，
        // 显然每次都去调用isHappy进行实时计算是不太经济，在极高的并发情况下也是不能满足性能要求的
        // 1、此时可以考虑将int正整数是否是happyNum预先计算好，存储在bit数组中(java语言对应的是BitSet)，后续的计算全部从bit数组中去获取即可
        // 因为输入是正整数，去掉负数，那么还剩下2^31次方个数，`2147483647/1024/1024/8≈256MB`，需要256MB的空间


        // 2、还有一种更为巧妙的办法：
        // 可以考虑不直接存储原始的输入num，而是存储原始num经过规则计算一次以后的数，假设为temp
        // 可以试想一下temp在什么情况下最大
        // 因为num的最大值为
        // 2147483647
        // 8589934592
        // 1999999999
        // 最大的temp就是1^2+9*9^2=730
        // 因为temp最大也才730，因此可以采用一种折中的办法：
        // 预先计算所有num是否是happyNum，如果是就将第一次计算的结果存入一个set，730*4B=2920B，将近3KB的空间
        // 如果使用BitSet的话就更小，1B可以表示8个数的存在性，理论上需要1B*100=800，不到1KB的空间
        // 后续使用的时候，对于输入num按照规则进行一次计算，然后查看计算的结果是否存在于set中，存在就是happy的

    }
}
