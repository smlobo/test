package org.sheldon.java.module;

public class JavaModule {
    public static void foo() {
        System.out.println("JavaModule.foo()");
    }

    public static void main(String[] args) {
        System.out.println("JavaModule.main()");
        foo();
        System.out.println(JavaModule.class.getModule());
        System.out.println(JavaModule.class.getName());
        System.out.println(JavaModule.class.getName().replace('.', '/'));
        System.out.println(JavaModule.class.getPackage().getName());
    }
}