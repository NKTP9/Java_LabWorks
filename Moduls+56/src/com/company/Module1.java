package com.company;

import java.util.Scanner;

public class Module1
{
    public static void main(String[] args) {
        // Задание 1
        System.out.println(remainder(1, 3));
        System.out.println(remainder(3, 4));
        System.out.println(remainder(-9, 45));
        // Задание 2
        System.out.println(triArea(3, 2));
        // Задание 3
        System.out.println(animals(2, 3, 5));
        // Задание 4
        System.out.println(profitableGamble(0.2, 50, 9));
        // Задание 5
        System.out.println(operation(24, 15, 9));
        // Задание 6
        System.out.println(ctoa('A'));
        // Задание 7
        System.out.println(addUpTo(7));
        // Задание 8
        System.out.println(nextEdge(8, 10));
        // Задание 9
        Scanner input = new Scanner(System.in);
        System.out.println("Введите размер: ");
        int size = input.nextInt();
        int array[] = new int[size];
        System.out.println("Введите элементы: ");
        for (int i = 0; i < size; i++) {
            array[i] = input.nextInt();
        }
        System.out.println(sumOfCubes(array));
        // Задание 10
        System.out.println(abcmath(5, 2, 1));
    }

    public static double remainder(double a, double b) {
        return a%b;
    }

    public static double triArea(double a, double b) {
        return (a*b)/2;
    }

    public static double animals(double a, double b, double c) {
        return 2*a+4*b+4*c;
    }

    public static boolean profitableGamble(double prob, double prize, double pay) {
        return prob * prize > pay;
    }

    public static String operation (int c, int a,int b){
        String s= "none";
        if (a+b==c){
            s= "added";
        }
        else{
            if(a-b==c || b-a==c){
                s= "subtracted";
            }
            else{
                if(a*b==c){
                    s= "multiplicated";
                }
                else{
                    if (a/b==c || b/c==c){
                        s= "separated";
                    }
                }
            }
        }
        return s;
    }

    public static int ctoa(final int ascii) {
        return (char)ascii;
    }

    public static int addUpTo(int a) {
            int i= 0, summa = 0;
            while (i!=a) {
                i++;
                summa = summa + i;
            }
            return summa;
    }

    public static double nextEdge(double a, double b) {
        return a + b - 1;
    }

    public static int sumOfCubes(int [] a) {
        int sum=0;
        for (int i = 0; i < a.length; i++) {
            sum+=(a[i]*a[i]*a[i]);
        }
        return sum;
    }

    public static boolean abcmath(int a, int b, int c){
        int i=0;
        while (i!=b){
            a=a*2;
            i++;
        }
        return (a%c==0)? true:false;
    }
}
