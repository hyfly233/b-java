package com.hyfly.asm.test01;

/**
 * java -cp .:../lib/asm-9.1.jar:../lib/asm-util-9.1.jar org.objectweb.asm.util.ASMifier com.hyfly.asm.TargetClass
 * idea plugin: ASM Bytecode Outline / ASM Bytecode Viewer
 *
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
