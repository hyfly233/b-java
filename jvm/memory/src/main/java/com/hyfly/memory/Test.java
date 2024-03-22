package com.hyfly.memory;

public class Test {
    public static void main(String[] args) {
        System.out.println("total memory: " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
        System.out.println("free memory: " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println("max memory: " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
    }
}
