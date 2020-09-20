package com.company;

public class Main3 {
    /**

     Задание №4

     **/
    public static void main(String[] args) {
        int x = 27;
        int y = 15;

        // y = x - y;
        // y = y - x;
        // y = y + x;
        // y = y + x;
        y = x / y;
        // y = y / x;
        // y = y * x;

        x = x - y;
        y = y - x;

        System.out.println(Math.abs(x));
        System.out.println(Math.abs(y));
    }
}
