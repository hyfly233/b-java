package com.hyfly.vt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Test05 {
    public static void main(String[] args) {

        ThreadFactory virtualThreadFactory = Thread.ofVirtual()
                .name("virtual-thread", 0)
                .factory();

        try (ExecutorService executorService = Executors.newThreadPerTaskExecutor(virtualThreadFactory)) {
            for (int i = 0; i < 10000; i++) {
                int finalI = i;
                executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "_" + finalI);
                });
            }
        }
    }
}
