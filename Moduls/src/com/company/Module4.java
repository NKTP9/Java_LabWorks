package com.company;

import java.util.Arrays;

public class Module4 {
    public static void main(String[] args) {

        System.out.println(Essay("hello my name is Bessie and this is my essay", 10, 7));

        System.out.print("2) ");
        System.out.println(Arrays.toString(split("((()))")));
        System.out.print("2) ");
        System.out.println(Arrays.toString(split("()()()")));

        System.out.print("3) ");
        System.out.print(toCamelCase("hello_edabit"));
        System.out.println();
        System.out.print("3) ");
        System.out.print(toSnakeCase("helloEdabit"));
        System.out.println();

        System.out.println("4) " + overTime(16, 18, 30, 1.8));

        System.out.print("5) ");
        BMI(55, 1.65);

        System.out.println("6) " + bugger(999));

        System.out.print("7) ");
        System.out.println(toStarShorthand("77777geff"));

        doesRhyme("Sam I am!", "Green eggs and ham.");
        doesRhyme("You are off to the races", "a splendid day.");

        trouble(9988811, 21888288);

        System.out.print("10) ");
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
    }

    // Сочинение. Задание №1
    public static String Essay(String s, int n, int k) {
        String line = "", s1 = "";
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (line.replaceAll("[ ]", "").length() + word.length() > k) {
                s1 += line.trim() + '\n';
                line = word + " ";
            } else
                {
                    line += word + " ";
                }
        }
        s1 += line.trim();
        return s1;
    }

    // Кластеры строк. Задание №2
    public static String[] split(String str) {
        int count = 0;
        String s1 = "";
        for (int i = 0; i < str.length(); i++) {
            s1 += str.charAt(i);
            if (str.charAt(i) == '(')
                count += 1;
            else
                count += -1;
            if (count == 0) {
                s1 += " ";
            }
        }
        return s1.split(" ");
    }

    // Две функции. Задание №3
    public static String toCamelCase(String s) {
        String s1 = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '_') {
                String s2 = "" + s.charAt(i + 1);
                s1 += s2.toUpperCase();
                i++;
            } else {
                s1 += "" + s.charAt(i);
            }
        }
        return s1;
    }

    public static String toSnakeCase(String s) {
        String s1 = "";
        for (int i = 0; i < s.length(); i++) {
            String s2 = "" + s.charAt(i);
            if (s2 == s2.toUpperCase()) {
                s1 += "_" + s2.toLowerCase();
            } else {
                s1 += "" + s.charAt(i);
            }
        }
        return s1;
    }

    // Подсчёт зарплаты. Задание №4
    public static double overTime(double n, double k, double st, double mn) {
        double salary = 0;
        for (double i = n; i < 17; i++) {
            salary += st;
        }
        if (k > 17) {
            for (double i = 17; i < k; i++) {
                salary = salary + (st * mn);
            }
        }
        return salary;
    }

    // Вес. Задание №5
    public static void BMI(double w, double h) {
        double bmi = w / Math.pow(h, 2);
        if (bmi < 18.5) {
            String result = String.format("%.2f", bmi);
            System.out.println(result + " Underweight");
        } else if (bmi >= 18.5 && bmi < 25) {
            String result = String.format("%.2f", bmi);
            System.out.println(result + " Normal weight");
        } else {
            String result = String.format("%.2f", bmi);
            System.out.println(result + " Overweight");
        }
    }

    // Мультипликативное постоянство. Задание №6
    public static int bugger(int num) {
        int numberOfTimes = 0;
        int temp;
        while (num > 9) {
            temp = 1;
            while (num > 0) {
                temp *= num % 10;
                num /= 10;
            }
            num = temp;
            numberOfTimes++;
        }
        return numberOfTimes;
    }

    // Звёздная стенография. Задание №7
    public static String toStarShorthand(String str) {
        String m = "";
        for (int i = 0; i < str.length(); i++) {
            int c = 1;
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    i++;
                    c++;
                }
            }
            if (c > 1)
                m += str.charAt(i) + "*" + c;
            else
                m += str.charAt(i);
        }
        return m;
    }

    // Рифма строк. Задание №8
    public static void doesRhyme(String s1, String s2) {
        String s11 = "";
        String s22 = "";
        String s3 = s1.toLowerCase();
        String s4 = s2.toLowerCase();
        s1 = s3.replaceAll("[.,!;:]", "");
        s2 = s4.replaceAll("[.,!;:]", "");
        for (int i = s1.length() - 1; i > 0; i--) {
            if (s1.charAt(i) != ' ') {
                if (s1.charAt(i) == 'a' || s1.charAt(i) == 'o' || s1.charAt(i) == 'i' || s1.charAt(i) == 'e' || s1.charAt(i) == 'u')
                    s11 += s1.charAt(i);
            } else
                break;
        }
        for (int i = s2.length() - 1; i > 0; i--) {
            if (s2.charAt(i) != ' ') {
                if (s2.charAt(i) == 'a' || s2.charAt(i) == 'o' || s2.charAt(i) == 'i' || s2.charAt(i) == 'e' || s2.charAt(i) == 'u')
                    s22 += s2.charAt(i);
            } else
                break;
        }
        if (s11.equals(s22))
            System.out.println("8) True");
        else
            System.out.println("8) False");
    }

    // Повторение. Задание №9
    public static void trouble(int num1, int num2) {
        String s = String.valueOf(num1);
        String s1 = "";
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i + 1) == s.charAt(i + 2)) {
                s1 += s.charAt(i);
                s1 += s.charAt(i + 1);
                s1 += s.charAt(i + 2);
                break;
            }
        }
        s = String.valueOf(num2);
        char s2 = s1.charAt(0);
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s2) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    System.out.println("9) True");
                    break;
                }
            }
        }
    }

    // Уникальные символы. Задание №10
    public static int countUniqueBooks(String s, char be) {
        String ss = "";
        int i = 0;
        while (i < s.length()) {
            while (i < s.length() && s.charAt(i) != be)
                i++;
            i++;
            String s1 = "";
            while (i < s.length() && s.charAt(i) != be) {
                s1 += s.charAt(i);
                i++;
            }
            if (i < s.length() && s1.length() != 0)
                ss += s1;
            i++;
        }
        if (ss.length() == 0)
            return 0;
        int count = 0;
        for (int j = 0; j < ss.length(); j++)
            if (!ss.substring(0,j).contains(""+ss.charAt(j))) {
                count++;
            }
        return count;
    }
}


