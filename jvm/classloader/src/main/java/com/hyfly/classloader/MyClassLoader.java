package com.hyfly.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    public String name = "";

    public MyClassLoader(String name) {
        this.name = name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        byte[] data = null;

        InputStream in;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        name = name.replace(".", "/");

        try (out) {
            in = new FileInputStream("./" + name + ".class");

            int ch;
            while (-1 != (ch = in.read())) {
                out.write(ch);
            }

            data = out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
