package com.company;

public class Main8 {
    /**

     Задание №9

     **/
    public static void main(String[] args) {
        System.out.println(sumDigitsInNumber(546));
    }

    public static int sumDigitsInNumber(int number) {
        int sum = 0;
        while(number != 0) {
            sum += (number % 10);
            number/=10;
        }
        return sum;
    }
}
