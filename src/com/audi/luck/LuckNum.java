package com.audi.luck;

import java.util.Scanner;

public class LuckNum {

    private Integer luck(Integer num) {
        for (Integer i = num + 1; i < 999998; i++) {
            String numStr = i.toString();
            int first = Integer.valueOf(numStr.charAt(0) - 48);
            int second = Integer.valueOf(numStr.charAt(1) - 48);
            int third = Integer.valueOf(numStr.charAt(2) - 48);
            int fourth = Integer.valueOf(numStr.charAt(3) - 48);
            int fifth = Integer.valueOf(numStr.charAt(4) - 48);
            int sixth = Integer.valueOf(numStr.charAt(5) - 48);
            if (first + second + third == fourth + fifth + sixth) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        LuckNum luckNum = new LuckNum();

        Scanner input = new Scanner(System.in);
        String next = input.next();
        System.out.println(luckNum.luck(Integer.valueOf(next)));
    }
}
