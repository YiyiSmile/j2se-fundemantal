package indi.tom.test;

public class Hello {
    public void sayHello() {
        System.out.println("Hello, AspectJ!");
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        hello.sayHello();
    }
}
