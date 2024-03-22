package com.hyfly.asm.test01;

import org.objectweb.asm.*;

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
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitVarInsn(Opcodes.LSTORE, 1);
        }

        @Override
        public void visitInsn(int opcode) {
            // 在方法返回处插入代码
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {
                // 下面的代码是通过 ASM Bytecode Viewer 生成的 ASM 文件中复制出来的
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
                mv.visitVarInsn(Opcodes.LSTORE, 3);
                Label label7 = new Label();
                mv.visitLabel(label7);
                mv.visitLineNumber(20, label7);
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.LLOAD, 3);
                mv.visitVarInsn(Opcodes.LLOAD, 1);
                mv.visitInsn(Opcodes.LSUB);
                mv.visitInvokeDynamicInsn("makeConcatWithConstants", "(J)Ljava/lang/String;", new Handle(Opcodes.H_INVOKESTATIC, "java/lang/invoke/StringConcatFactory", "makeConcatWithConstants", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", false), new Object[]{"TargetClass#fun01 cost: \u0001"});
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                Label label8 = new Label();
                mv.visitLabel(label8);
                mv.visitLineNumber(21, label8);
            }

            super.visitInsn(opcode);
        }
    }
}
