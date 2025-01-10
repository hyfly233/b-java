package com.hyfly.asm.test01;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

public class Generator {
    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader("com/hyfly/asm/test01/TargetClass");

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        ClassVisitor cv = new MyClassVisitor(cw);

        cr.accept(cv, ClassReader.SKIP_DEBUG);

        byte[] data = cw.toByteArray();

        // 输出到文件
        File file = new File("/Users/flyhy/workspace/jvm-test/asm/target/classes/com/hyfly/asm/test01/TargetClass.class");

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
    }
}
