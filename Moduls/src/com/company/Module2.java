package com.company;

import java.util.Arrays;

public class Module2 {

    public static void main(String[] args) {
        // Задание №1
        System.out.println("1) " + repeat("mice", 5));
        // Задание №2
        int[] array = {10, 4, 1, 4, -10, -50, 32, 21};
        System.out.println("2) " + differenceMaxMin(array));
        // Task #3
        int[] array1 = {1, 2, 3, 4};
        System.out.println("3) " + isAvgWhole(array1));
        // Task #4
        int[] array2 = {3, 3, -2, 408, 3, 3};
        System.out.println("4) " + Arrays.toString(cumulativeSum(array2)));
        // Task #5
        System.out.println("5) " + getDecimalPlaces("43.20"));
        // Task #6
        System.out.println("6) " + Fibonacci(12));
        // Task #7
        System.out.println("7) " + "59001 is " + isValid("59001"));
        System.out.println("853a7 is " + isValid("853a7"));
        System.out.println("853 77 is " + isValid("853 77"));
        // Task #8
        System.out.println("8) " + isStrangePair("ab", "ba"));
        // Task #9
        System.out.println("9) " + isPrefix("automation", "auto-"));
        System.out.println(isSuffix("vacation", "-tion"));
        // Task #10
        System.out.println("10) " + boxSeq(2));
    }

    public static String repeat(String s, int n) {
        String s1 = "";
            for(int i = 0; i < s.length(); i++) {
                for(int j = 0; j < n; j++) {
                    s1 += s.charAt(i);
                }
            }
        return s1;
    }

    public static int differenceMaxMin(int[] array) {
        int max = 30000;
        int min = -30000;
        for(int i = 0; i < array.length; i++ ){
            if(array[i] > min)
                min = array[i];
            if(array[i] < max)
                max = array[i];
        }
        System.out.println("2) Smallest number is " + max + "," + " biggest is " + min);
        return min - max;
    }

    public static boolean isAvgWhole(int[] array) {
        double num = 0;
        for(int i = 0; i < array.length; i++)
            num +=array[i];
        num /= array.length;
        return num == Math.floor(num);
    }

    public static int[] cumulativeSum(int[] array) {
        int[] array1 = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j <= i; j++)
                array1[i] += array[j];
        }
        return array1;
    }

    public static int getDecimalPlaces(String s) {
        int amount = 0;
        boolean hs = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                hs = true;
            }
            else if (hs) {
                amount++;
            }
        }
        return amount;
    }

    public static int Fibonacci (int a) {
        int num1 = 1;
        int num2 = 1;
        int num3 = 0;
        if(a == 1)
            return 1;
        else
            for (int i = 2; i <= a; i++) {
                num3 = num1 + num2;
                num1 = num2;
                num2 = num3;
            }
            return num3;
    }

    public static boolean isValid (String s) {
       /* try {
            double n = Double.parseDouble(s);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return s.length() < 6; */
        boolean b = true;
        if (s.length() > 5)
            b = false;
        if (s.indexOf(" ") != -1) {
            b = false;
        }
        else {
            int c = 0;
            for (int i = 0; i < s.length(); i++) {
                char a = s.charAt(i);
                if (a >= '0' && a <= '9') {
                    c++;
                }
            }
            if (c == s.length()) {
                b = true;
            } else {
                 b = false;
            }
        }
        return b;
    }


    public static boolean isStrangePair(String s, String s2){
        if(s.isEmpty()) {
            return false;
        }
        else if(s.indexOf(0) == s2.indexOf(s2.length()-1)) {
            if(s2.indexOf(0) == s.indexOf(s2.length()-1)) {
                return true;
            }
            else return false;
        }
        else return false;
    }

    public static boolean isPrefix(String s1, String s2) {
        for (int i = 0; i < s2.length() - 1; i++) {
            if(s1.charAt(i) != s2.charAt(i))
                return false;
        }
        return true;
    }

    public static boolean isSuffix (String s1, String s2) {
        int n = 1;
        for (int i = s1.length() - s2.length() + 1; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(n))
                return false;
            n++;
        }
        return true;
    }

    public static int boxSeq(int s) {
        int n = 0;
        for (int i = 1; i <= s; i++) {
            if(i % 2 == 1) {
                n += 3;
            }
            else
                n -=1;
        }
        return n;
    }
}
