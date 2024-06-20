package com.hyfly.vt;

public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            Thread.startVirtualThread(() -> {
                System.out.println(finalI);
            });
        }

        Thread.sleep(1000);
    }
}
