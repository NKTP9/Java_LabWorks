// package com.company;


public class Primes {
    public static void main(String[] args) {
        for(int i = 2; i <= 100; i++)
        {
            boolean prime = IsPrime(i);
            if (prime) {
                System.out.println(i + " " + prime);
            }
        }
    }
    public static boolean IsPrime(int n) {
        boolean prime = false;
        if(n == 2 || n == 3 || n == 5 || n == 7) {
            prime = true;
        }
        else if(n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0) {
            prime = false;
        }
        else {
            prime = true;
        }
        return prime;
    }
}