package com.hyfly.classloader;

public class ClassLoader01 {

    public static void main(String[] args) throws Exception {
        // 获取启动类加载器
        String str = "hello class loader";
        System.out.println("str class loader " + str.getClass().getClassLoader());

        System.out.println("\n ----------- \n");

        // 获取平台类加载器
        Class<?> sqlDriver = Class.forName("java.sql.Driver");
        ClassLoader classLoader = sqlDriver.getClassLoader();
        System.out.println("sqlDriver class loader " + classLoader);
        System.out.println("sqlDriver parent class loader " + classLoader.getParent());

        System.out.println("\n ----------- \n");

        // 获取应用类加载器
        ClassLoader01 classLoader01 = new ClassLoader01();
        ClassLoader classLoader03 = classLoader01.getClass().getClassLoader();
        System.out.println("classLoader01 class loader " + classLoader03);
        System.out.println("classLoader01 parent class loader " + classLoader03.getParent());
        System.out.println("classLoader01 parent parent class loader " + classLoader03.getParent().getParent());

        System.out.println("\n ----------- \n");

        Class<?> jShell = Class.forName("jdk.jshell.JShell");
        System.out.println("jShell class loader " + jShell.getClassLoader());

        System.out.println("\n ----------- \n");

        MyClassLoader myClassLoader = new MyClassLoader("myClassLoader");
        Class<?> myClass = myClassLoader.loadClass("com.hyfly.classloader.MyClass");
        System.out.println("myClass class loader " + myClass.getClassLoader());
        System.out.println("myClass parent class loader " + myClass.getClassLoader().getParent());

        System.out.println("\n ----------- \n");

        Class<?> driverManager = Class.forName("java.sql.DriverManager");
        System.out.println("driverManager class loader " + driverManager.getClassLoader());

        Class<?> mysqlDriver = Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("mysqlDriver class loader " + mysqlDriver.getClassLoader());
    }
}
