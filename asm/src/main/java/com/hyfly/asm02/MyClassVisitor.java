package com.hyfly.asm02;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor {

    public MyClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM9, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if (!"<init>".equals(name) && mv != null) {
            // 为这种方法增加记录执行时间的功能
            mv = new MyMethodVisitor(mv);
        }
        return mv;
    }

    static class MyMethodVisitor extends MethodVisitor {

        public MyMethodVisitor(MethodVisitor methodVisitor) {
            super(Opcodes.ASM9, methodVisitor);
        }

        @Override
        public void visitCode() {
            super.visitCode();
            // 在方法开始处插入代码
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/hyfly/asm02/MyTimeLogger", "start", "()V", false);
        }

        @Override
        public void visitInsn(int opcode) {
            // 在方法返回处插入代码
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/hyfly/asm02/MyTimeLogger", "end", "()V", false);
            }

            super.visitInsn(opcode);
        }
    }
}
