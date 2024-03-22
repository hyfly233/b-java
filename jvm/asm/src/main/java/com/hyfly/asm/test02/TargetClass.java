package com.hyfly.asm.test02;

/**
 * @author flyhy
 */
public class TargetClass {

    public void fun01() {
        try {
            Thread.sleep(100);
            System.out.println("TargetClass#fun01 run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
