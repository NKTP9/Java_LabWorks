package com.company;

public class Main10 {
    /**

     Задание №11

     **/
    private static class Pool {
        final public int a = 25;
        final protected int b = 25;
        final private int c = 2;

        public static long getVolume(int a, int b, int c) {
            return a * b * c;
        }

    }

    public static void main(String[] args) {
        Pool pool = new Pool();
        long poolVolume = pool.getVolume(25, 5, 2);
        System.out.println(poolVolume);
    }
}
