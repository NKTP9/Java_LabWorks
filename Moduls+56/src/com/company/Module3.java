package com.company;

public class Module3 {
    public static void main(String[] args) {
        // Задание №1
        System.out.print("1) ");
        solutions(1, 2, 1);

        // Задание №2
        System.out.println("2) " + "The second position of zip in \"all zip files are compressed\" is " + findZip("all zip files are compressed"));
        System.out.println("2) " + "The second position of zip in \"all zip files are zipped\" is " + findZip("all zip files are zipped"));

        // Задание №3
        System.out.println("3) 6 = " + checkPerfect(6));

        // Задание №4
        System.out.print("4) Ada = "); flipEndChars("Ada");

        // Задание №5
        System.out.println("5) #c5DB1f = " + isValidHexCode("#c5DB1f"));

        // Задание №6
        System.out.println("6) " + same(new int [] {1, 3, 4, 4, 4}, new int [] {2, 5, 7}) );

        // Задание №7
        System.out.println("7) " + isKaprekar(297));

        // Задание №8
        System.out.print("8) "); longestZero("01100001011000"); System.out.println();

        // Задание №9
        System.out.println("9) " + nextPrime(24));

        // Задание №10
        System.out.println("10) " + rightTriangle(3, 4, 5) );

    }

    // Квадратное уравнение
    public static void solutions(int a, int b, int c) {
        double x, x1, x2;
        double D = Math.pow(b, 2) - 4 * a * c;
        if (D > 0) {
            x1 = (-b + Math.sqrt(D)) / (2 * a);
            x2 = (-b - Math.sqrt(D)) / (2 * a);
            System.out.println(a + " * x^2" + " + " + b + " + " + c + "= 0 has two solutions (x= " + x1 + " and x= " + x2 + ").");
        } else if (D == 0) {
            x = -b / (2 * a);
            System.out.println(a + "x^2" + " = 0 has one solution (x= " + x + ").");
        } else
            System.out.println(a + " * x^2" + " + " + b + " + " + c + " = 0 has no solutions.");
    }

    // Позиция второго вхождения
    public static int findZip(String s) {
        int iz1 = s.indexOf("zip");
        int iz2 = s.indexOf("zip", iz1 + 1);
        return iz2;
    }

    // Совершенное число
    public static boolean checkPerfect(int a) {
        int sum = 0;
        for (int i = 1; i < a; i++) {
            if (a % i == 0) {
                sum += i;
            }

        }
        return sum == a;
    }

    // Замена символов
    public static void flipEndChars(String s) {
        String a1 = "";
        if (s.length() == 1) {
            System.out.println("Incompatible.");
        } else {
            char beginning = s.charAt(0);
            char end = s.charAt(s.length() - 1);
            if (beginning != end) {
                a1 = s.substring(1, s.length() - 1);
                System.out.println(end + a1 + beginning);

            } else if (beginning == end) {
                System.out.println("Two's a pair.");
            }
        }
    }

    // Шестнадцатеричный код
    public static boolean isValidHexCode(String s) {
        char c;
        boolean pr = false;
        c = s.charAt(0);
        if (s.length() <= 7) {
            if (c == '#') {
                for (int i = 1; i < s.length(); i++) {
                    c = s.charAt(i);
                    if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) {
                        pr = true;
                    } else
                        pr = false;
                }
            } else
                pr = false;
        } else
            pr = false;
        return pr;
    }


    // Уникальные элементы
    public static boolean same(int [] mas1, int [] mas2) {
        int c1 = 0, c2 = 0;
        for (int i = 0; i < mas1.length; i++) {
            for (int j = i+1; j < mas1.length; j++) {
                if(mas1[i] == mas1[j]){
                    c1++;
                    i = j;
                }
            }
        }
        for (int i = 0; i < mas2.length; i++) {
            for (int j = i + 1; j < mas2.length; j++) {
                if(mas2[i] == mas2[j]){
                    c2++;
                    i = j;
                }
            }
        }
        return (mas1.length-c1) == (mas2.length-c2);
    }

    //число Капрекара
    public static boolean isKaprekar(int a) {
        int n = (int)Math.pow(a,2);
        int length = Integer.toString(n).length(); //убираем в длинне точку и ноль
        if(length == 1) {
            int l = 0;
            double r = n;
            return (l + r) == a;
        }
        else {
            int c = (int) Math.ceil(length/2.0);//(int) убираю .0
            int l = (int) (n / Math.pow(10,c));//убираю .0
            int r = (int) (n % Math.pow(10,c));//убираю .0
            return (l + r) == a;
        }
    }

    //Самая большая последовательность
    public static void longestZero (String a) {
        int c11 = 0;
        int c12 = 0;
        char s1 = '0';
        char s2 = '0';
        for (int i = 0; i < a.length()-1; i++) {
            char c1 = a.charAt(i);
            char c2 = a.charAt(i+1);
            if((int)c1==(int)c2){
                c11++;
                s1 = c1;

            }
            else {
                if(c12 < c11){
                    c12 = c11;
                    s2 = s1;
                    c11 = 0;
                }
            }
        }
        for (int i = 0; i < c12 + 1; i++) {
            System.out.print(s2);
        }
    }

    //Следующее простое число;
    public static int nextPrime (int n) {
        if (n == 2 || n == 3 || n == 5 || n == 7) {
            return n;
        }
        else {
            if (n % 2 != 0 && n % 3 != 0 && n % 5 != 0 && n % 7 != 0) {
                return n;
            }
            else {
                while (true) {
                    n++;
                    if (n % 2 != 0 && n % 3 != 0 && n % 5 != 0 && n % 7 != 0) {
                        return n;
                    }
                }
            }

        }

    }

    //Прямоугольный треугольник
    public static boolean rightTriangle (int a, int b, int c) {
        int max = a;
        if(b<c) {
            if(a<c) {
                max=c;
                return max == Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
            }
        }
        else {
            if(a<b) {
                max = b;
                return max == Math.sqrt(Math.pow(c,2)+Math.pow(a,2));
            }
        }
        return max == Math.sqrt(Math.pow(c,2)+Math.pow(b,2));
    }
}
