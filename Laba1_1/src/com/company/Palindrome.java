// package com.company;

public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if(isPalindrome(s)){
                System.out.print(s + " палиндром");
            }
            else {
                System.out.print(s + " не палиндром");
            }
        }
    }
    public static String reverseString(String s) {
        String s1 = new String();
        for (int i = s.length() - 1; i >= 0; i--)
            s1 += s.charAt(i);
        return s1;
    }
    public static boolean isPalindrome(String s) {
        return s.equals(reverseString(s));
    }
}
