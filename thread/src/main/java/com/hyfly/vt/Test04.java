package com.hyfly.vt;

import java.util.concurrent.ThreadFactory;

public class Test04 {
    public static void main(String[] args) throws InterruptedException {

        ThreadFactory virtualThreadFactory = Thread.ofVirtual()
                .name("virtual-thread", 0)
                .factory();

        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            Thread factoryThread = virtualThreadFactory.newThread(() -> {
                System.out.println(finalI);
            });
            factoryThread.start();
        }

        Thread.sleep(1000);
    }
}
