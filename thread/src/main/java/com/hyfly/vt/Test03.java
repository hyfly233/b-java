package com.hyfly.vt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test03 {
    public static void main(String[] args) {

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10000; i++) {
                int finalI = i;
                executorService.submit(() -> {
                    System.out.println(finalI);
                });
            }
        }
    }
}
