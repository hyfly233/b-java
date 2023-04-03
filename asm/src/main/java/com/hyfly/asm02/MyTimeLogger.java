package com.hyfly.asm02;

public class MyTimeLogger {
    private static long startTime = 0L;

    public static void start() {
        startTime = System.currentTimeMillis();
    }

    public static void end() {
        long endTime = System.currentTimeMillis();
        System.out.println("MyTimeLogger invoke method cost: " + (endTime - startTime));
    }
}
